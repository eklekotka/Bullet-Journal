package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.PlannerWeekJson;
import java.util.ArrayList;
import java.util.List;

/**
 * to represent a week in a planner
 */
public class PlannerWeek implements TaskChangers {
  private final PlannerDay[] week;
  private final List<String> categories;
  private final int maxToDos;
  private Day weekStartDay;
  private String theme;

  /**
   *
   * @param w array of seven day elements
   * @param c list of categories available in bullet journal
   * @param max maximum number of tasks to be done in a week
   */
  public PlannerWeek(PlannerDay[] w, List<String> c, int max) {
    this.week = w;
    this.categories = c;
    this.maxToDos = max;
    this.theme = "";
  }


  /**
   * constructor for a completely new week
   *
   * @param startDay day the new week starts on
   */
  public PlannerWeek(Day startDay) {
    this.week = initWeek(startDay);
    this.categories = new ArrayList<>();
    this.maxToDos = 0;
    this.theme = "";
    weekStartDay = startDay;

  }


  /**
   *
   * @return an array of PlannerDay to represent a week
   */
  private PlannerDay[] initWeek(Day day) {
    Day[] vals = Day.values();
    int ord = day.ordinal();
    PlannerDay[] week = new PlannerDay[7];
    for (int i = 0; i < 7; i++) {
      week[i] = new PlannerDay(vals[(ord + i) % 7]);
    }
    return week;
  }

  /**
   * sets the changed theme
   *
   * @param changedTheme the theme to change to
   * */
  public void setTheme(String changedTheme) {
    this.theme = changedTheme;
  }

  /**
   * sets the changed theme
   *
   * @return the current theme
   * */
  public String getTheme() {
    return this.theme;
  }

  /**
   *
   * @param newCategory desired new category to be added to list of current categories
   */
  public void addToCategories(String newCategory) {
    this.categories.add(newCategory);
  }

  public List<String> getCategory() {
    return this.categories;
  }



  /**
   * adds an item to the bullet journal
   *
   * @param thing represents the item being added to the bullet journal
   */
  public void addToDoTask(BulletJournalItem thing) {
    for (PlannerDay day : week) {
      try {
        day.addTask(thing);
      } catch (RuntimeException e) {
        throw new RuntimeException(e.getMessage());
      }
    }
  }

  /**
   *
   * @param thing adds given item the bullet journal
   */
  public void addToDoEvent(BulletJournalItem thing) {
    for (PlannerDay day : week) {
      try {
        day.addEvent(thing);
      } catch (RuntimeException e) {
        throw new RuntimeException(e.getMessage());
      }
    }
  }

  /**
   * removes given item from the task list on which the task takes place
   *
   * @param thing represents the thing being removed
   */
  public void removeToDo(BulletJournalItem thing) {
    for (PlannerDay day : week) {
      day.remove(thing);

    }
  }

  /**
   * set the maximum things that can be done on any given day in the week
   *
   * @param max the max amount that can be done
   */
  public void setMaxToDo(int max) {
    for (PlannerDay day : week) {
      day.setMaxToDoint(max);
    }
  }

  /**
   *
   *
   * @param i index of the desired day of the week
   * @return desired day of the week depending on index of input
   */
  public PlannerDay getDay(int i) {
    return week[i];

  }

  /**
   *
   * @return this week as a serialized json node
   */
  public JsonNode plannerWeekToJson() {
    JsonNode[] week = new JsonNode[7];

    for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
      week[dayIndex] = this.getDay(dayIndex).dayToJson();

    }
    PlannerWeekJson weekRecord =
        new PlannerWeekJson(week, this.categories, this.maxToDos, this.weekStartDay, this.theme);
    JsonNode weekJson = JsonUtils.serializeRecord(weekRecord);
    return weekJson;
  }



  /**
   * removes all items in the days of this week
   */
  public void wipeDays() {
    for (PlannerDay day : week) {
      day.wipeLists();
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof PlannerWeek)) {
      return false;
    } else {
      PlannerWeek otherWeek = (PlannerWeek) other;
      return this.week.equals(otherWeek.week)
          && this.categories.equals(otherWeek.categories)
          && this.maxToDos == otherWeek.maxToDos
          && this.weekStartDay.equals(otherWeek.weekStartDay)
          && this.theme.equals(otherWeek.theme);

    }
  }

}

