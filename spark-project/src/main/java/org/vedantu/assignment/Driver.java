package org.vedantu.assignment;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Following are the questions for the assignment:
 * 1. Find the average session duration (sessionid is the defining field for every session) when "appnameenc"=1 and
 * "appnameenc"=2.
 * 2. Count of "calc_userid" for each "region". ignore "-" and nulls
 * 3. Consider "eventlaenc" =126 or 107 as defining actions. Calculate first and second defining action, ordered
 * based on time, for each "calc_userid" and also find the count of those actions.
 */

public class Driver {
  private static final Logger LOG = LoggerFactory.getLogger(Driver.class);

  // Running spark in local mode.
  public static final String MASTER = "local[4]";

  public static void main(String[] args) {
    // Spark job name
    String jobName = "assignment";
    // Input data directory path
    String path  = "spark-project/data/";
    // Output file path
    String outputPath  = "spark-project/output/";

    // Create spark conf
    SparkConf conf = new SparkConf()
        .set("spark.executor.memory", "4g")
        .set("spark.driver.maxResultSize", "2g")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .set("spark.driver.extraJavaOptions",
            "/Users/dibyendu.karmakar/projects/hudi/packaging/hoodie-spark-bundle/target/lib/*")
        .set("spark.sql.crossJoin.enabled", "true");

    // Create spark session
    SparkSession spark = SparkSession.builder()
        .appName(jobName)
        .config(conf)
        .master(MASTER)
        .getOrCreate();

    // Add shutdown hook to close spark session
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      LOG.info("Calling shutdown hook...");
      spark.close();
    }));


    CalculationModule calculationModule = new CalculationModule(spark.sqlContext(), path, outputPath);
    calculationModule.question1();
    calculationModule.question2();
    calculationModule.question3();
  }
}
