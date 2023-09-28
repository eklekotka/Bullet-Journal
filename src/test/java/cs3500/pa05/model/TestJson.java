//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cs3500.pa05.model.json.PlannerDayJson;
//import cs3500.pa05.model.json.TaskJson;
//import java.time.MonthDay;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//
///**
// * tests the methods in the JsonUtils class
// */
//public class JsonUtilsTest {
//  private JsonUtils jsonUtils;
//  private ObjectMapper mapper;
//
//  private BulletJournalItem study;
//  private List<BulletJournalItem> mondayItems;
//  private PlannerDay monday;
//
//  private TaskJson taskJson;
//  private TaskJson mock;
//  private PlannerDayJson plannerDayJson;
//  private List<JsonNode> mondayJsons;
//  private JsonNode taskNode;
//  private JsonNode plannerDayNode;
//
//  /**
//   * reinitialize data before each test
//   */
//  public void setUp() {
//    jsonUtils = new JsonUtils();
//    mapper = new ObjectMapper();
//
//    study = new Task("Study", Day.MONDAY, "School", "Study for exam");
//    mondayItems = new ArrayList<BulletJournalItem>(Arrays.asList(study));
//    monday = new PlannerDay(Day.SUNDAY, mondayItems, 10);
//
//    taskJson = new TaskJson("Study", Day.MONDAY, "School", "");
//    taskNode = mapper.convertValue(taskJson, JsonNode.class);
//
//  }
//
//  /**
//   * tests the serializeRecord method in the JsonUtils class
//   */
//  @Test
//  public void testSerializeRecord() {
//    setUp();
//    assertEquals(taskNode, jsonUtils.serializeRecord(taskJson));
////    assertThrows(IllegalArgumentException.class, () -> jsonUtils.serializeRecord(mock));
//
//  }
//}
