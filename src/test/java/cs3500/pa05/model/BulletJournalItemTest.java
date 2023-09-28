package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * examples and tests for the methods in the Item class
 */
public class BulletJournalItemTest {
  private LocalTime nineFiftyAm;
  private LocalTime eightAm;
  private LocalTime sixFifteenPm;

  private BulletJournalItem emptyEvent;
  private BulletJournalItem examTwo;
  private BulletJournalItem oodLab;
  private BulletJournalItem emptyTask;
  private BulletJournalItem study;
  private BulletJournalItem laundry;

  /**
   * initialize examples before each test
   */
  @BeforeEach
  public void setUp() {
    nineFiftyAm = LocalTime.of(9, 50);
    eightAm = LocalTime.of(8, 0);
    sixFifteenPm = LocalTime.of(18, 15);

    emptyEvent = new Event("Empty", Day.SUNDAY, "Test", LocalTime.MIDNIGHT, 60);
    examTwo =
        new Event("Exam Two", Day.THURSDAY, "School", nineFiftyAm,
            "Second OOD Exam", 60);
    oodLab =
        new Event("Lab", Day.MONDAY, "School", eightAm, "Lab for OOD",
            30);
    emptyTask = new Task("Empty", Day.SUNDAY, "Test");
    study = new Task("Study", Day.WEDNESDAY, "School", "Study for Exam");
    laundry = new Task("Laundry", Day.TUESDAY, "Chores", "Do Laundry");

  }

  /**
   * tests the getName method in the Item class
   */
  @Test
  public void testGetName() {
    assertEquals(emptyEvent.getName(), "Empty");
    assertEquals(emptyTask.getName(), "Empty");
    assertEquals(examTwo.getName(), "Exam Two");
    assertEquals(study.getName(), "Study");

  }

  /**
   * tests the getString method in the Item class
   */
  @Test
  public void testGetString() {
    assertEquals("Empty" + System.lineSeparator()
        + "Day : Sunday" + System.lineSeparator()
        + "Category : Test" + System.lineSeparator()
        + "Time : 00:00" + System.lineSeparator()
        + "Duration : 60minutes" + System.lineSeparator()
        + "Description : " , emptyEvent.getString());
    assertEquals("Lab" + System.lineSeparator()
        + "Day : Monday" + System.lineSeparator()
        + "Category : School" + System.lineSeparator()
        + "Time : 08:00" + System.lineSeparator()
        + "Duration : 30minutes" + System.lineSeparator()
        + "Description : Lab for OOD", oodLab.getString());
    assertEquals("Empty" + System.lineSeparator()
        + "Day : Sunday" + System.lineSeparator()
        + "Category : Test" + System.lineSeparator()
        + "Completed? : false" + System.lineSeparator()
        + "Description : ", emptyTask.getString());
    assertEquals("Laundry" + System.lineSeparator()
        + "Day : Tuesday" + System.lineSeparator()
        + "Category : Chores" + System.lineSeparator()
        + "Completed? : false" + System.lineSeparator()
        + "Description : Do Laundry", laundry.getString());
  }

  /**
   * tests the markAsCompleted method in the Task class
   */
  @Test
  public void testMarkAsCompleted() {
    Task laundry2 = new Task("Laundry", Day.TUESDAY, "Chores",
            "Do Laundry", true);
    laundry.markAsComplete();
    assertEquals(laundry2, laundry);

  }

  /**
   * tests the sameCategory method
   */
  @Test
  public void testSameCategory() {
    assertTrue(emptyEvent.sameCategory("Test"));
    assertTrue(laundry.sameCategory("Chores"));
    assertFalse(laundry.sameCategory("School"));

  }

  /**
   * tests the itemToJson method
   */
  @Test
  public void testItemToJson() {
/*
    EventJson json = new EventJson("Lab", Day.MONDAY, "School",
        "8:00", "Lab for OOD");
    JsonNode eventNode = JsonUtils.serializeRecord(json);
    assertEquals(eventNode, oodLab.itemToJson());

 */

  }

  @Test
  public void testEquals() {
    Task laundry2 = new Task("Laundry", Day.TUESDAY, "Chores", "Do Land");
    Event oodExam =  new Event("Exam Two", Day.THURSDAY, "School",
        nineFiftyAm, "Second OOD Exam", 45);
    assertNotEquals(examTwo, laundry);
    assertNotEquals(oodExam, laundry2);
    assertEquals(laundry, laundry);
    assertNotEquals(laundry2, laundry);

  }

  @Test
  public void testHashCode() {
    assertEquals(-1712181008, laundry.hashCode());
    assertEquals(712116260, study.hashCode());
  }
  @Test
  public void testErrors() {
    assertThrows(UnsupportedOperationException.class, () -> oodLab.markAsComplete());
  }

  @Test
  public void testJson() {
    BulletJournalItem study = new Task("Study", Day.MONDAY, "School", "");
    JsonNode studyNode = study.itemToJson();
    ObjectMapper mapper = new ObjectMapper();
    BujoFileWriter writer = new BujoFileWriter(mapper);
    writer.jsonToBujo(studyNode, "test2.bujo");

  }
}
