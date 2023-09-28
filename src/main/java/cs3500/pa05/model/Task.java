package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.TaskJson;
import java.util.Objects;

/**
 * to represent a task to appear on a bullet journal day
 */
public class Task extends BulletJournalItem {
  private boolean completed;

  /**
   * @param n    name of task
   * @param d    day of task
   * @param c    category of task
   * @param desc description of task
   */
  public Task(String n, Day d, String c, String desc) {
    super(n, d, c, desc);
    this.completed = false;

  }

  /**
   * constructor for item with no description
   *
   * @param n    name of task
   * @param d    day of task
   * @param c    category of tasks
   */
  public Task(String n, Day d, String c) {
    super(n, d, c);
    this.completed = false;
  }

  /**
   * constructor that takes in all items (for deserialization)
   *
   * @param n    name of task
   * @param d    day of task
   * @param c    category of tasks
   * @param desc    description of tasks
   * @param comp    completion status of tasks
   */
  public Task(String n, Day d, String c, String desc, boolean comp) {
    super(n, d, c, desc);
    this.completed = comp;
  }

  /**
   *
   * @return task as a formatted string
   */
  @Override
  public String getString() {
    return this.name + System.lineSeparator()
        + "Day : " + this.day.toString() + System.lineSeparator()
        + "Category : " + this.category + System.lineSeparator()
        + "Completed? : " + this.completed + System.lineSeparator()
        + "Description : " + this.description;

  }

  /**
   * changes a task's completion status to true
   */
  @Override
  public void markAsComplete() {
    this.completed = true;

  }

  /**
   *
   * @return this task serialized as a jsons
   */
  @Override
  public JsonNode itemToJson() {
    TaskJson taskRecord =
        new TaskJson(this.name, this.day, this.category, this.description, this.completed);
    JsonNode taskNode = JsonUtils.serializeRecord(taskRecord);
    return taskNode;

  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Task)) {
      return false;
    }

    Task t = (Task) o;
    return Objects.equals(t.name, this.name)
        && t.description.equals(description)
        && this.category.equals(t.category)
        && t.day == this.day
        && this.completed == t.completed;

  }

  /**
   * overrides hashCode
   *
   * @return an integer, representing the hashcode
   */
  @Override
  public int hashCode() {
    return this.name.hashCode() * this.category.hashCode() * this.description.hashCode();
  }
}
