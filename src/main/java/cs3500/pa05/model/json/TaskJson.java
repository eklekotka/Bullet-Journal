package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.BulletJournalItem;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;

/**
 *
 * @param name of task
 * @param day task occurs
 * @param category category of the task
 * @param description of this task
 * @param completed whether the task is completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("day") Day day,
    @JsonProperty("category") String category,
    @JsonProperty("description") String description,
    @JsonProperty("completed") boolean completed) {

  @Override
  public boolean equals(Object other) {

    if (!(other instanceof TaskJson)) {
      return false;
    } else {
      TaskJson otherJson = (TaskJson) other;

      return this.name.equals(otherJson.name)
          && this.day.equals(otherJson.day)
          && this.category.equals(otherJson.category)
          && this.description.equals(otherJson.description)
          && this.completed == otherJson.completed;

    }
  }

  /**
   *
   * @return this taskJson as a Task
   */
  public BulletJournalItem toTask() {
    return new Task(name(), day(), category(), description(), completed());
  }
}

