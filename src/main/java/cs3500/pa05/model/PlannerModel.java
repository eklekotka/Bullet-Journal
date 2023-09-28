package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.PlannerModelJson;
import java.util.List;

/**
 * model for the bullet journal app
 */
public class PlannerModel {
  private final PlannerWeek week;
  private String filePath;
  private String password = "";

  /**
   * represents the constructor for a planner model representing
   * a new week/file
   *
   * @param w given week to be displayed
   */
  public PlannerModel(PlannerWeek w) {
    this.week = w;
    this.filePath = "";
    this.password = "";
  }

  /**
   * represents the constructor for a planner model representing
   * an already existing week and its path
   *
   * @param w given week to be displayed
   * @param path represents the path where the week is stored
   */
  public PlannerModel(PlannerWeek w, String path) {
    this.week = w;
    this.filePath = path;
  }

  /**
   * represents the constructor for a planner model representing
   * an already existing week and its path
   *
   * @param w given week to be displayed
   * @param path represents the path where the week is stored
   * @param password represents the password for a Bulletjournal week
   */
  public PlannerModel(PlannerWeek w, String path, String password) {
    this.week = w;
    this.filePath = path;
    this.password = password;
  }

  /**
   * adds an event to the week
   *
   * @param i item to be added to the bullet journal
   */
  public void addWeekEvent(BulletJournalItem i) {
    try {
      week.addToDoEvent(i);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * adds a task to the week
   *
   * @param i item to be added to the bullet journal
   */
  public void addWeekTask(BulletJournalItem i) {
    try {
      week.addToDoTask(i);
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * add a category to the given week
   *
   * @param category represents the category being added to the category options
   */
  public void addCategory(String category) {
    week.addToCategories(category);
  }


  /**
   * sets the theme for the current week
   *
   * @param theme the theme to set for the week
   */
  public void setWeekTheme(String theme) {
    this.week.setTheme(theme);
  }

  /**
   * gets the current theme for the current week
   *
   * @return the current theme for the week
   */
  public String getWeekTheme() {
    return this.week.getTheme();
  }


  /**
   * returns the category
   *
   * @return the category list for the week
   */
  public List<String> getCategory() {
    return week.getCategory();
  }

  /**
   * removes an event or task from the bulletJournal
   *
   * @param i item to be removed in the bullet journal
   */
  public void remove(BulletJournalItem i) {
    week.removeToDo(i);
  }

  /**
   * set the maximum amount of items that can be done on any given day
   *
   * @param max the maximum number of things to do for a day
   */
  public void setMaxToDo(int max) {
    week.setMaxToDo(max);
  }


  /**
   *
   * @param day index of the desired day of the week
   * @return desired day of the week depending on index of input
   */
  public PlannerDay getDay(int day) {
    return week.getDay(day);
  }

  /**
   * changes a model to a JsonNode
   *
   * @return the JsonNode of a PlannerModel
   * */
  public JsonNode changeToJson() {
    PlannerModelJson model = new PlannerModelJson(week.plannerWeekToJson(),
        this.filePath, this.password);
    return JsonUtils.serializeRecord(model);
  }

  /**
   * updates the path of the file
   *
   * @param path the path to update for the week
   * */
  public void updatePath(String path) {
    filePath = path;
  }


  /**
   * returns the path of the current week
   *
   * @return the path of the current week
   * */
  public String getPath() {
    return filePath;
  }

  /**
   * wipes all tasks/events from a given week
   */
  public void wipeToDo() {
    week.wipeDays();
  }

  /**
   *
   * @return this mo
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * sets the password for the current week
   *
   * @param pass the pass for the week file
   */
  public void setPassword(String pass) {
    this.password = pass;
  }
}
