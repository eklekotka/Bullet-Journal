package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * examples and tests for methods in the PlannerDay class
 */
public class PlannerDayTest {

  private BulletJournalItem emptyEvent;
  private BulletJournalItem oodLab;
  private BulletJournalItem lecture;
  private BulletJournalItem laundry;

  private List<BulletJournalItem> mondayItems;
  private List<BulletJournalItem> mondayTasks;
  private List<BulletJournalItem> mondayEvents;
  private List<BulletJournalItem> mondaySchoolItems;
  private List<BulletJournalItem> mondayChoresItems;


  private PlannerDay sunday;
  private PlannerDay monday;

  private BulletJournalItem walk;
  private BulletJournalItem run;
  private BulletJournalItem cool;

  /**
   * initialize examples before each test
   */
  @BeforeEach
  public void setUp() {
    LocalTime nineFiftyAm = LocalTime.of(9, 50);
    LocalTime eightAm = LocalTime.of(8, 0);
    LocalTime sixFifteenPm = LocalTime.of(18, 15);

    emptyEvent = new Event("Empty", Day.SUNDAY, "Test", LocalTime.MIDNIGHT, 50);
    BulletJournalItem examTwo =
        new Event("Exam Two", Day.THURSDAY, "School", nineFiftyAm, "Second OOD Exam", 50);
    oodLab =
        new Event("Lab", Day.MONDAY, "School", eightAm, "Lab for OOD", 40);
    lecture = new Event("Lecture", Day.MONDAY, "School", nineFiftyAm, "Lecture for OOD", 50);
    BulletJournalItem emptyTask = new Task("Empty", Day.SUNDAY, "Test");
    BulletJournalItem study = new Task("Study", Day.WEDNESDAY, "School", "Study for Exam");
    laundry = new Task("Laundry", Day.MONDAY, "Chores", "Do Laundry");

    mondayItems = new ArrayList(Arrays.asList(laundry, lecture, oodLab));
    mondayTasks = new ArrayList(Arrays.asList(laundry));
    mondayEvents = new ArrayList(Arrays.asList(lecture, oodLab));
    mondaySchoolItems = new ArrayList(Arrays.asList(lecture, oodLab));
    mondayChoresItems = new ArrayList(Arrays.asList(laundry));

    sunday = new PlannerDay(Day.SUNDAY);
    monday = new PlannerDay(Day.MONDAY, mondayTasks, mondayEvents, 3);

    walk = new Task("walk", Day.SUNDAY, "exercise");
    run = new Task("run", Day.TUESDAY, "exercise");
    cool = new Task("cool", Day.SUNDAY, "fun");

  }

  /**
   * tests the method filterByCategory
   */
  @Test
  public void testFilterByCategory() {
    System.out.println(mondaySchoolItems);
    System.out.println(monday.filterByCategory("School"));
    assertEquals(mondaySchoolItems, monday.filterByCategory("School"));
    assertEquals(mondayChoresItems, monday.filterByCategory("Chores"));

  }

  /**
   * test for add method
   */
  @Test
  public void testAdd() {
    sunday.setMaxToDoint(1);
    ArrayList walkList = new ArrayList<>(Arrays.asList(walk));
    sunday.addTask(walk);
    assertEquals(walkList, sunday.getItems());

    ArrayList runList = new ArrayList<>(Arrays.asList(run));
    sunday.addTask(run);
    assertEquals(walkList, sunday.getItems());

  }

  /**
   * tests the remove method in PlannerDay
   */
  @Test
  public void testRemove() {
    monday.remove(cool);
    assertEquals(mondayItems, monday.getItems());
    monday.remove(laundry);
    assertEquals(mondaySchoolItems, monday.getItems());
    monday.remove(oodLab);
    assertEquals(1, monday.getItems().size());
  }

  /**
   * tests the toDoToString method in PlannerDay
   */
  @Test
  public void testTodoToString() {
    sunday.setMaxToDoint(2);
    sunday.addEvent(emptyEvent);
    String s = "Empty" + System.lineSeparator()
       + "Day : Sunday" + System.lineSeparator()
       + "Category : Test" + System.lineSeparator()
       + "Time : 00:00" + System.lineSeparator()
       + "Duration : 50minutes" + System.lineSeparator()
       + "Description : " ;
    ArrayList<String> strings = new ArrayList<>(Arrays.asList(s));
    sunday.addEvent(oodLab);
    assertEquals(strings, sunday.todosToString());

  }

  /**
   * tests the throwing of an error in the add method in PlannerDay
   */
  @Test
  public void testAddError() {
    assertThrows(RuntimeException.class, () -> monday
        .addTask(new Task("cry", Day.MONDAY, "ood")));

  }

  /**
   * tests the wipe method in PlannerDay
   */
  @Test
  public void testWipe() {
    assertEquals(monday.getItems().size(), 3);
    monday.wipeLists();
    assertEquals(monday.getItems().size(), 0);
  }
}