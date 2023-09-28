package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.PlannerWeekJson;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * examples and tests for the methods in the PlannerWeek class
 */
public class PlannerWeekTest {

  private PlannerWeek testWeek;
  private PlannerWeek testWeek2;
  private PlannerDay mon;
  private PlannerDay tues;
  private PlannerDay wed;
  private PlannerDay thur;
  private PlannerDay fri;
  private PlannerDay sat;
  private PlannerDay sun;


  /**
   * initializes examples before each test
   */
  @BeforeEach
  public void setUp() {
    testWeek = new PlannerWeek(Day.MONDAY);
    mon = new PlannerDay(Day.MONDAY);
    tues = new PlannerDay(Day.TUESDAY);
    wed = new PlannerDay(Day.WEDNESDAY);
    thur = new PlannerDay(Day.THURSDAY);
    fri = new PlannerDay(Day.FRIDAY);
    sat = new PlannerDay(Day.SATURDAY);
    sun = new PlannerDay(Day.SUNDAY);
    PlannerDay[] week = new PlannerDay[7];
    week[0] = mon;
    week[1] = tues;
    week[2] = wed;
    week[3] = thur;
    week[4] = fri;
    week[5] = sat;
    week[6] = sun;


    testWeek2 = new PlannerWeek(week, new ArrayList<>(), 3);
  }

  /**
   * test for setTheme method
   */
  @Test
  public void testSetTheme() {
    testWeek.setTheme("slay");
    assertEquals("slay", testWeek.getTheme());
    testWeek.setTheme("finish line is near!!!");
    assertEquals("finish line is near!!!", testWeek.getTheme());
  }

  /**
   * test for addCategory method
   */
  @Test
  public void testAddCategory(){
    assertEquals(new ArrayList<>(), testWeek.getCategory());
    testWeek.addToCategories("finished!!");
    testWeek.addToCategories("I can rest now!");
    assertEquals(new ArrayList<>(Arrays.asList("finished!!", "I can rest now!")),
        testWeek.getCategory());
  }

  /**
   * test for setMaxToDo method in PlannerWeek
   */
  @Test
  public void testSetMaxToDo() {
    assertThrows(RuntimeException.class, () ->
        testWeek.addToDoTask(new Task("yay", Day.SUNDAY, "help")));
    testWeek.setMaxToDo(3);
    testWeek.addToDoTask(new Task("yay", Day.SUNDAY, "help"));
    assertEquals(new ArrayList<>(Arrays.asList(new Task("yay", Day.SUNDAY, "help"))),
        testWeek.getDay(6).getItems());
  }

  /**
   * test for addToDoTask method in PlannerWeek
   */
  @Test
  public void testAddToDoTask() {
    testWeek.setMaxToDo(3);
    testWeek.addToDoTask(new Task("yay", Day.SUNDAY, "help"));
    assertEquals(new ArrayList<>(Arrays.asList(new Task("yay", Day.SUNDAY, "help"))),
        testWeek.getDay(6).getItems());
    Task t = new Task("String", Day.SUNDAY, "slay");
    testWeek.addToDoTask(t);
    assertEquals(new ArrayList<>(Arrays.asList(new Task("yay", Day.SUNDAY, "help"), t)),
        testWeek.getDay(6).getItems());
  }

  /**
   * test for addToDoEvent in PlannerWeek
   */
  @Test
  public void testAddToDoEvent() {
    testWeek.setMaxToDo(2);
    Event ski = new Event("ski", Day.SUNDAY, "cool",
        LocalTime.of(8, 00), 40);
    testWeek.addToDoEvent(ski);
    assertEquals(new ArrayList<>(Arrays.asList(ski)), testWeek.getDay(6).getItems());
  }

  /**
   * test for remove method in PlannerWeek
   */
  @Test
  public void testRemove() {
    testWeek.setMaxToDo(2);
    Task happy = new Task("happy", Day.FRIDAY, "cool");
    Task cool = new Task("cool", Day.FRIDAY, "cool");
    testWeek.addToDoTask(cool);
    testWeek.addToDoTask(happy);

    assertEquals(new ArrayList<>(Arrays.asList(cool, happy)), testWeek.getDay(4).getItems());
    testWeek.removeToDo(cool);
    assertEquals(new ArrayList<>(Arrays.asList(happy)), testWeek.getDay(4).getItems());
  }

  /**
   * test for wipeDays method in PlannerWeek
   */
  @Test
  public void testWipe() {
    testWeek.setMaxToDo(4);
    Task happy = new Task("happy", Day.FRIDAY, "cool");
    Task cool = new Task("cool", Day.FRIDAY, "cool");
    Event slay = new Event("slay", Day.THURSDAY, "cool",
        LocalTime.of(2, 2), 40);
    Event presentGui = new Event("present", Day.THURSDAY, "school",
        LocalTime.of(9, 50), 40);
    testWeek.addToDoTask(happy);
    testWeek.addToDoTask(cool);
    testWeek.addToDoEvent(slay);
    testWeek.addToDoTask(presentGui);
    assertEquals(2, testWeek.getDay(4).getItems().size());
    assertEquals(2, testWeek.getDay(3).getItems().size());
    testWeek.wipeDays();
    assertEquals(0, testWeek.getDay(4).getItems().size());
    assertEquals(0, testWeek.getDay(3).getItems().size());
  }

  /**
   * test for equals method in PlannerWeek
   */
  @Test
  public void testEquals() {
    PlannerWeek week = new PlannerWeek(Day.MONDAY);
    PlannerWeek week2 = new PlannerWeek(Day.MONDAY);
    assertTrue(testWeek.equals(testWeek));
    assertFalse(testWeek2.equals(testWeek));
    assertFalse(testWeek2.equals(mon));
  }

  /**
   * test for plannerWeekToJson in PlannerWeek
   */
  @Test
  public void testPlannerWeekToJson() throws JsonProcessingException {
    JsonNode[] weekJson = new JsonNode[7];
    weekJson[0] = mon.dayToJson();
    weekJson[1] = tues.dayToJson();
    weekJson[2] = wed.dayToJson();
    weekJson[3] = thur.dayToJson();
    weekJson[4] = fri.dayToJson();
    weekJson[5] = sat.dayToJson();
    weekJson[6] = sun.dayToJson();
    PlannerWeekJson json = new PlannerWeekJson(weekJson, testWeek2.getCategory(), 3,
        null, "");
    JsonNode node = JsonUtils.serializeRecord(json);
    ObjectMapper map = new ObjectMapper();
    assertEquals(map.writeValueAsString(node),
        map.writeValueAsString(testWeek2.plannerWeekToJson()));
  }
}
