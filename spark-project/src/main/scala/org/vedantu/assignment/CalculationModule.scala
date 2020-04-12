package org.vedantu.assignment

import java.io.{File, PrintWriter}

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{SQLContext, functions}
import org.apache.spark.sql.functions.{col, _}

/**
 * Contains the fucntion wich will print the output for below questions
 * 1. Find the average session duration (sessionid is the defining field for every session) when "appnameenc"=1 and "appnameenc"=2.
 * 2. Count of "calc_userid" for each "region". ignore "-" and nulls
 * 3. Consider "eventlaenc" =126 or 107 as defining actions. Calculate first and second defining action, ordered based on time, for each "calc_userid" and also find the count of those actions.
 *
 * @param sqlContext
 * @param path
 */
class CalculationModule(sqlContext: SQLContext, path: String, outputPath: String) extends Serializable {
  val df = sqlContext.jsonFile(path);

  def question1(): Unit = {
    // print writer object to write the output in a file
    val pw = new PrintWriter(new File(outputPath + "/question1.txt"))

    val output = df.filter("appnameenc in (1,2)")
      .groupBy("sessionid")
      .agg((avg("timestamp") - min("timestamp")).as("average"))
      .select(col("sessionid"), col("average"))

    pw.write(String.format("| %20s | %20s |\n", "sessionid", "average in ms"))
    output.collect().foreach(r =>
      pw.write(String.format("| %20s | %20s |\n", r.get(0).toString, r.get(1).toString)))
    pw.close()
  }

  def question2(): Unit = {
    // print writer object to write the output in a file
    val pw = new PrintWriter(new File(outputPath + "/question2.txt"))

    val output = df.filter("region is not null and region <> '-'")
      .groupBy("region", "calc_userid")
      .agg(functions.count("calc_userid").as("calc_userid count"))
      .orderBy("region", "calc_userid")

    pw.write(String.format("| %20s | %20s | %20s |\n", "region", "calc_userid", "userid count"))
    output.collect().foreach(r =>
      pw.write(String.format("| %20s | %20s | %20s |\n", r.get(0).toString, r.get(1).toString, r.get(2).toString)))
    pw.close()
  }

  def question3(): Unit = {
    // print writer object to write the output in a file
    val pw = new PrintWriter(new File(outputPath + "/question3.txt"))

    // Windowspec to partition based on userid and then sort based on timestamp
    val filterSpec = Window.partitionBy("calc_userid").orderBy(col("timestamp"))

    // Get first event for the userids
    val first_event_df = df.filter("eventlaenc in (126,107)").withColumn("row", row_number.over(filterSpec)).where(col("row")
      === 1).select(col("calc_userid"), col("eventlaenc"))

    // Count number of occurrence of an event
    val count_df = df.filter("eventlaenc in (126,107)").withColumn("row", row_number.over(filterSpec)).groupBy("calc_userid",
      "eventlaenc")
      .agg(count("eventlaenc").as("count"))

    val joined_df = first_event_df.as('a)
      .join(count_df.as('b), col("a.calc_userid") === col("b.calc_userid"), "left_outer")

    // Get count of first occurred event
    val first_match = joined_df.where("a.eventlaenc = b.eventlaenc")
      .select(col("a.calc_userid").as("calc_userid"),
        col("a.eventlaenc").as("first_action"),
        col("b.count").as("first_action_count"))

    // Get count of second occurred event
    val second_match = joined_df.where("a.eventlaenc <> b.eventlaenc")
      .select(col("a.calc_userid").as("calc_userid"),
        col("b.eventlaenc").as("second_action"),
        col("b.count").as("second_action_count"))

    val output = first_match.as('f)
      .join(second_match.as('s), col("f.calc_userid") === col("s.calc_userid"), "left_outer")
      .orderBy("f.calc_userid")
      .select(col("f.calc_userid").as("calc_userid"),
        col("f.first_action").as("first_action"),
        col("f.first_action_count").as("first_action_count"),
        col("s.second_action").as("second_action"),
        col("s.second_action_count").as("second_action_count"))

    pw.write(String.format("%20s | %20s | %20s | %20s | %20s |\n", "calc_userid", "first_action", "first_action_count",
      "second_action", "second_action_count"))
    output.collect().foreach(row => pw.write(String.format("%20s | %20s | %20s | %20s | %20s |\n",
      replaceNull(row.get(0)).toString,
      replaceNull(row.get(1)).toString,
      replaceNull(row.get(2)).toString,
      replaceNull(row.get(3)).toString,
      replaceNull(row.get(4)).toString)))
  }

  def replaceNull(value: Any): Any = {
    if (value == null)
      return ""
    return value
  }
}
