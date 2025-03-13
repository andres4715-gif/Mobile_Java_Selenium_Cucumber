package com.example.config;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ConfigReader {
  private final Properties properties;

  public ConfigReader() {
    properties = loadProperties("config.properties");
  }

  /**
   * Get properties
   *
   * @return properties object
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * Get properties from specific file
   * @param fileName name of file
   * @return properties from file
   */
  public Properties getPropertiesFromFile(String fileName) {
    return loadProperties(fileName);
  }

  /**
   * Load properties from file
   *
   * @param fileName name of file
   * @return properties from file
   */
  private Properties loadProperties(String fileName) {
    Properties props = new Properties();
    try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
      if (input == null) {
        log.error("Unable to find {} file", fileName);
        throw new IOException("Unable to find " + fileName + " file");
      }
      props.load(input);
      log.info("Loaded {} properties from {}", props.size(), fileName);
    } catch (IOException e) {
      log.error("Failed to load properties from {}", fileName, e);
      throw new RuntimeException("Failed to load properties from " + fileName, e);
    }
    return props;
  }

  /**
   * Get property value
   *
   * @param key property key
   * @return property value
   */
  public String getProperty(String key) {
    String value = properties.getProperty(key);
    if (value == null) {
      log.warn("Property {} not found", key);
    }
    return value;
  }

  /**
   * Get property value with default
   *
   * @param key          property key
   * @param defaultValue default value
   * @return property value or default
   */
  public String getProperty(String key, String defaultValue) {
    String value = properties.getProperty(key, defaultValue);
    if (value.equals(defaultValue)) {
      log.warn("Property {} not found, using default value: {}", key, defaultValue);
    }
    return value;
  }

  /**
   * Get boolean property
   *
   * @param key          property key
   * @param defaultValue default value
   * @return boolean property
   */
  public boolean getBooleanProperty(String key, boolean defaultValue) {
    String value = getProperty(key);
    if (value == null) {
      return defaultValue;
    }
    return Boolean.parseBoolean(value);
  }

  /**
   * Get integer property
   *
   * @param key          property key
   * @param defaultValue default value
   * @return integer property
   */
  public int getIntProperty(String key, int defaultValue) {
    String value = getProperty(key);
    if (value == null) {
      return defaultValue;
    }
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      log.error("Failed to parse {} as integer", value, e);
      return defaultValue;
    }
  }
}
