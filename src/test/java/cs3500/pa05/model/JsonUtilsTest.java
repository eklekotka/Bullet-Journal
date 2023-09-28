package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.PlannerWeekJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the methods in the JsonUtils class
 */
public class JsonUtilsTest {
  private PlannerWeek week;
  private JsonNode weekNode;

  private TaskJson taskJson;

  private JsonNode taskNode;

  /**
   * reinitialize data before each test
   */
  @BeforeEach
  public void setUp() {
    ObjectMapper mapper = new ObjectMapper();

    PlannerDay monday = new PlannerDay(Day.MONDAY);
    PlannerDay tuesday = new PlannerDay(Day.TUESDAY);
    PlannerDay wednesday = new PlannerDay(Day.WEDNESDAY);
    PlannerDay thursday = new PlannerDay(Day.THURSDAY);
    PlannerDay friday = new PlannerDay(Day.FRIDAY);
    PlannerDay saturday = new PlannerDay(Day.SATURDAY);
    PlannerDay sunday = new PlannerDay(Day.SUNDAY);

    PlannerDay[] weekArray;
    weekArray =
        new PlannerDay[] {monday, tuesday, wednesday, thursday, friday, saturday, sunday};
    week = new PlannerWeek(weekArray, new ArrayList<String>(), 10);
    JsonNode[] weekNodeArray;

//    PlannerWeekJson weekJson = new PlannerWeekJson(..., new ArrayList<String>(), 10, Day.MONDAY, "default.css");
    weekNode = week.plannerWeekToJson();


    taskJson =
        new TaskJson("Study", Day.THURSDAY, "School", "", false);
    taskNode =
        mapper.convertValue(taskJson, JsonNode.class);

  }

  /**
   * tests the serializeRecord method
   */
  @Test
  public void testSerializeRecord() {
    assertEquals(taskNode, JsonUtils.serializeRecord(taskJson));

  }

  /**
   * tests the toPlannerModelJson method
   */
  @Test
  public void testToPlannerModelJson() {

  }

  /**
   * tests the toPlannerWeekJson method
   */
  @Test
  public void testToPlannerWeekJson() {
//    assertEquals(weekJson, JsonUtils.toPlannerWeekJson(weekNode));

  }
}
