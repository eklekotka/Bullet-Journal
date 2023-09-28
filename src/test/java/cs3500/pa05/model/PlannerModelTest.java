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
 * writes examples and tests methods in the PlannerModel class
 */
public class PlannerModelTest {
  PlannerModel blankWeek;
  PlannerWeek blank;
  PlannerModel sportWeek;
  PlannerWeek sport;
  PlannerDay[] days;
  PlannerDay sunday;
  PlannerDay monday;
  PlannerDay tuesday;
  PlannerDay wednesday;
  PlannerDay thursday;
  PlannerDay friday;
  PlannerDay saturday;
  PlannerModel mixedWeek;
  PlannerWeek mixed;

  /**
   * sets up the tasks and events for the plannerModel test
   */
  @BeforeEach
  public void setup() {
    blank = new PlannerWeek(Day.MONDAY);
    blankWeek = new PlannerModel(blank);
    ArrayList<BulletJournalItem> events = new ArrayList<>();
    ArrayList<BulletJournalItem> tasks = new ArrayList<>();

    ArrayList<String> categories = new ArrayList<>(Arrays.asList("sport", "school", "task"));
    Event soccer = new Event("Soccer", Day.MONDAY, "sport",
        LocalTime.of(2, 0), 3);
    Event game = new Event("Soccer Game", Day.MONDAY, "sport",
        LocalTime.of(3, 0), 15);

    Task buyPencils = new Task("buy pencils", Day.TUESDAY, "school");
    Task study = new Task("study for ood", Day.THURSDAY, "school");

    events.add(soccer);
    events.add(game);
    tasks.add(buyPencils);
    tasks.add(study);

    days = new PlannerDay[7];
    sunday = new PlannerDay(Day.SUNDAY);
    days[0] = sunday;

    monday = new PlannerDay(Day.MONDAY, tasks, events, 2);
    days[1] = monday;

    tuesday = new PlannerDay(Day.TUESDAY, new ArrayList<>(List.of(buyPencils)), events, 4);
    days[2] = tuesday;

    wednesday = new PlannerDay(Day.WEDNESDAY);
    days[3] = wednesday;

    thursday = new PlannerDay(Day.THURSDAY, new ArrayList<>(List.of(study)), events, 2);
    days[4] = thursday;

    friday = new PlannerDay(Day.FRIDAY);
    days[5] = friday;

    saturday = new PlannerDay(Day.SATURDAY);
    days[6] = saturday;

    sport = new PlannerWeek(days, categories, 4);
    sportWeek = new PlannerModel(sport);
  }

  /**
   * test for setMaxToDo
   * shows that setting the max for the week sets the max for every day of that week
   */
  @Test
  public void testSetMaxToDo() {
    assertThrows(RuntimeException.class, () ->
        sportWeek.addWeekTask(new Task("study for ood", Day.SATURDAY, "school")));
    sportWeek.setMaxToDo(3);
    sportWeek.addWeekTask(new Task("study for ood", Day.SATURDAY, "school"));
  }

  /**
   * test that adding categories has the correct output
   */
  @Test
  public void testAddCategories() {
    ArrayList<String> cats = new ArrayList<>(Arrays.asList("sport", "school", "task"));
    assertEquals(cats, sportWeek.getCategory());
    sportWeek.addCategory("relaxation");
    cats.add("relaxation");
    assertEquals(cats, sportWeek.getCategory());

    assertEquals(new ArrayList<>(), blankWeek.getCategory());
    blankWeek.addCategory("play");
    assertEquals(new ArrayList<>(List.of("play")), blankWeek.getCategory());
  }

  /**
   * tests that the get day method works correctly
   */
  @Test
  public void testGetDay() {
    assertEquals(monday, sportWeek.getDay(1));
    assertEquals(sunday, sportWeek.getDay(0));
  }
}
