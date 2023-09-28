package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.PlannerDayJson;
import java.util.ArrayList;
import java.util.List;

/**
 * to represent a day in a planner
 */
public class PlannerDay {
  private final Day day;
  private final List<BulletJournalItem> daysTasks;
  private final List<BulletJournalItem> daysEvents;
  private final List<BulletJournalItem> daysItems;
  private int maxIssues;
  private final boolean hasHitItemMax;

  /**
   * constructor for initializing a totally new day when
   * the user creates a new week
   *
   * @param d represents the day of the week that is being made
   */
  public PlannerDay(Day d) {
    day = d;
    daysTasks = new ArrayList<>();
    daysEvents = new ArrayList<>();
    daysItems = new ArrayList<>();
    maxIssues = 0;
    hasHitItemMax = false;

  }


  /**
   *
   * @param d day
   * @param tasks to be done that day
   * @param events occurring on that day
   * @param max number of items that can be on that day
   */
  public PlannerDay(Day d, List<BulletJournalItem> tasks, List<BulletJournalItem> events, int max) {
    day = d;
    daysTasks = tasks;
    daysEvents = events;

    daysItems = new ArrayList<>();
    daysItems.addAll(daysTasks);
    daysItems.addAll(daysEvents);

    maxIssues = max;
    hasHitItemMax = determineIfHitMax();
  }

  /**
   * determines if the maximum about of tasks/events for the day have been reached
   *
   * @return if the maxEvents limit has been hit
   */
  public boolean determineIfHitMax() {
    boolean done = false;
    if (this.daysItems.size() == 0 && this.maxIssues == 0) {
      done = false;

    } else if (this.daysItems.size() >= this.maxIssues) {
      System.out.println(this.daysItems.size() >= this.maxIssues);
      done = true;

    }
    return done;
  }

  /**
   * add an item to a to-do list
   *
   * @param event represents the item being added
   */
  public void addEvent(BulletJournalItem event) {
    if (sameDay(event)) {
      if ((daysItems.size() == 0 && maxIssues == 0) || daysItems.size() < maxIssues) {
        daysItems.add(event);
        daysEvents.add(event);
        System.out.println("it" + daysItems);

      } else {
        throw new RuntimeException("too many items!");
      }
    }
  }

  /**
   * add an item to a to-do list
   *
   * @param task represents the item being added
   */
  public void addTask(BulletJournalItem task) {
    if (sameDay(task)) {
      if (daysItems.size() < maxIssues) {
        daysItems.add(task);
        daysTasks.add(task);
      } else {
        throw new RuntimeException("too many items!");
      }
    }
  }

  /**
   * remove an item from the to-do list of an event
   *
   * @param thing the item that is being removed
   */
  public void remove(BulletJournalItem thing) {
    if (daysEvents.contains(thing)) {
      daysEvents.remove(thing);
      daysItems.remove(thing);

    } else if (daysTasks.contains(thing)) {
      daysTasks.remove(thing);
      daysItems.remove(thing);

    }
  }

  /**
   * set the maximum amount of tasks and events a user completes in a day,
   * (does so with the text box that is meant to set types)
   *
   * @param amount the amount of issues a user desires to make
   */
  public void setMaxToDoint(int amount) {
    maxIssues = amount;
  }

  /**
   * filters a list by the given String, which represents a category
   *
   * @param category the category that the filtered list will contain
   * @return a list, containing only the items of the given category
   */
  public List<BulletJournalItem> filterByCategory(String category) {
    List<BulletJournalItem> filteredList = new ArrayList<>();

    for (BulletJournalItem thing : daysItems) {
      if (thing.sameCategory(category)) {
        filteredList.add(thing);
      }
    }
    return filteredList;
  }

  /**
   * is the given item on the sameDay as this day?
   *
   * @param item the item being compared
   * @return true if the item is on the same day of the week as this day
   */
  private boolean sameDay(BulletJournalItem item) {
    return item.day == day;

  }

  /**
   *
   * @return returns this planner's todos as a list of formatted strings
   */
  public List<String> todosToString() {
    List<String> stringVis = new ArrayList<>();
    for (BulletJournalItem item : daysItems) {
      String i = item.getString();
      stringVis.add(i);
      System.out.println(i);
    }
    return stringVis;
  }

  /**
   * returns the all the events and tasks
   *
   * @return the list of events and tasks
   * */
  public List<BulletJournalItem> getItems() {
    return daysItems;
  }

  /**
   *
   * @return this planner day as a json node
   */
  public JsonNode dayToJson() {
    List<JsonNode> jsonTasks = new ArrayList<>();
    List<JsonNode> jsonEvents = new ArrayList<>();

    for (BulletJournalItem task : this.daysTasks) {
      jsonTasks.add(task.itemToJson());

    }

    for (BulletJournalItem event : this.daysEvents) {
      jsonEvents.add(event.itemToJson());

    }

    PlannerDayJson dayRecord =
        new PlannerDayJson(this.day, jsonTasks, jsonEvents, this.maxIssues, this.hasHitItemMax);
    return JsonUtils.serializeRecord(dayRecord);

  }

  /**
   * removes all items from the list
   */
  public void wipeLists() {
    daysItems.clear();
    daysTasks.clear();
    daysEvents.clear();
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof PlannerDay otherWeek)) {
      return false;
    } else {
      return this.day.equals(otherWeek.day)
          && this.daysTasks.equals(otherWeek.daysTasks)
          && this.daysEvents.equals(otherWeek.daysEvents)
          && this.daysItems.equals(otherWeek.daysItems)
          && this.maxIssues == otherWeek.maxIssues
          && this.hasHitItemMax == otherWeek.hasHitItemMax;

    }
  }
}