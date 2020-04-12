package org.vedantu.assignment;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

  public static void main(String[] args) throws IOException {
    Configuration configuration = getConfiguration();
    // Spark job name
    String jobName = "assignment";
    // Input data directory path
    String path = configuration.getInputPath();
    // Output file path
    String outputPath = configuration.getOutputPath();
    // Spark master
    String MASTER = configuration.getMaster();

    // Create spark conf
    SparkConf conf = new SparkConf()
        .set("spark.executor.memory", "4g")
        .set("spark.driver.maxResultSize", "2g")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
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

  /**
   * Get application properties from the property file present in the class path.
   *
   * @return MCSquareConfiguration
   * @throws IOException
   */
  private static Configuration getConfiguration() throws IOException {
    LOG.info("Loading application configurations.");
    InputStream inputStream = getResource(ConfigurationKeys.APPLICATION_PROPERTIES);
    Properties properties = new Properties();
    properties.load(inputStream);
    return Configuration.builder().withProperties(properties).build();
  }

  /**
   * Convenience method that returns a resource as inputstream from the
   * classpath.
   * <p>
   * It first attempts to use the Thread's context classloader and if not
   * set it uses the <code>ClassUtils</code> classloader.
   *
   * @param name resource to retrieve.
   * @return inputstream with the resource, NULL if the resource does not
   * exist.
   */
  public static InputStream getResource(String name) {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    if (cl == null) {
      cl = Driver.class.getClassLoader();
    }
    return cl.getResourceAsStream(name);
  }
}
