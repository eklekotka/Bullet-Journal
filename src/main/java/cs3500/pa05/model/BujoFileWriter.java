package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class responsible for writing a bullet journal file based on given json
 */
public class BujoFileWriter {
  private final ObjectMapper mapper;

  /**
   *
   * @param m given object mapper to serialize java objects to strings
   */
  public BujoFileWriter(ObjectMapper m) {
    this.mapper = m;
  }

  /**
   *
   * @param node desired json node to be written as bujo file
   * @param filePath the file path for the model
   */
  public void jsonToBujo(JsonNode node, String filePath) {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    try {
      BufferedWriter writer = new BufferedWriter(
          new FileWriter(filePath));
      writer.write(mapper.writeValueAsString(node));
      writer.close();

    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid json node, could not convert to .bujo");

    }
  }
}

