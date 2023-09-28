package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * class responsible for reading a bujo file
 */
public class BujoFileReader {
  private final ObjectMapper mapper;

  /**
   * represents the constructor for the BujoFileReader
   *
   * @param m the object mapper
   * */
  public BujoFileReader(ObjectMapper m) {
    this.mapper = m;

  }

  /**
   *
   * @param file given bujo file to be converted to a Json
   * @return a serialized node of the file located at given file path
   */
  public JsonNode bujoToJson(String file) {
    JsonNode node;
    StringBuilder jsonString = new StringBuilder();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = "";

      while ((line = reader.readLine()) != null) {
        jsonString.append(line);

      }
      reader.close();
      node = mapper.readValue(jsonString.toString(), JsonNode.class);
      return node;

    } catch (IOException e) {
      throw new IllegalArgumentException("Could not convert .bujo file to json");

    }
  }
}
