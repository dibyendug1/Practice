package org.vedantu.assignment;

import lombok.Builder;
import lombok.Getter;

import java.util.Properties;

@Builder
@Getter
public class Configuration {
  private String outputPath;
  private String inputPath;
  private String master;

  public static class ConfigurationBuilder {
    public ConfigurationBuilder withProperties(Properties props) {
      this.outputPath = String.valueOf(props.get(ConfigurationKeys.OUTPUT_PATH));
      this.inputPath = String.valueOf(props.get(ConfigurationKeys.INPUT_PATH));
      this.master = String.valueOf(props.get(ConfigurationKeys.SPARK_MASTER));
      return this;
    }
  }
}
