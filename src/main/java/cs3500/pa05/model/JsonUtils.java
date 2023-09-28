package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.PlannerModelJson;
import cs3500.pa05.model.json.PlannerWeekJson;

/**
 * Simple utils class used to hold static methods that help with serializing and deserializing JSON.
 */
public class JsonUtils {

  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }

  /**
   *
   * @param node given node to be converted to a PlannerModelJson record
   * @return given node serialized as a PlannerModelJson
   */
  public static PlannerModelJson toPlannerModelJson(JsonNode node) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.convertValue(node, PlannerModelJson.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given node cannot be deserialized");
    }
  }

  /**
   *
   * @param node given node to be converted into a PlannerWeekJson
   * @return given node as a new PlannerWeekJson
   */
  public static PlannerWeekJson toPlannerWeekJson(JsonNode node) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.convertValue(node, PlannerWeekJson.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given node cannot be deserialized");

    }
  }
}