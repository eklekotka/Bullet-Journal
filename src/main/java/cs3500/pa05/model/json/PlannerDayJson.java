package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BulletJournalItem;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.PlannerDay;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @param day of the week this represents
 * @param tasks to be done for this day
 * @param events occurring on this day
 * @param maxIssues maximum issues that can be done today
 * @param hasHitItemMax whether the day has hit the maximum issue amount
 */
public record PlannerDayJson(
    @JsonProperty("day") Day day,
    @JsonProperty("tasks") List<JsonNode> tasks,
    @JsonProperty("events") List<JsonNode> events,
    @JsonProperty("max-issues") int maxIssues,
    @JsonProperty("has-hit-items-max") boolean hasHitItemMax) {

  @Override
  public boolean equals(Object other) {

    if (!(other instanceof PlannerDayJson)) {
      return false;
    } else {
      PlannerDayJson otherJson = (PlannerDayJson) other;

      return this.day.equals(otherJson.day)
          && this.tasks.equals(otherJson.tasks)
          && this.events.equals(otherJson.events)
          && this.maxIssues == otherJson.maxIssues
          && this.hasHitItemMax == otherJson.hasHitItemMax;

    }
  }

  /**
   *
   * @return this converted to an object of the PlannerDay class
   */
  public PlannerDay toPlannerDay() {
    ObjectMapper mapper = new ObjectMapper();
    List<BulletJournalItem> taskList = new ArrayList<>();
    List<BulletJournalItem> eventList = new ArrayList<>();

    for (JsonNode task : tasks) {
      TaskJson taskJson = mapper.convertValue(task, TaskJson.class);
      taskList.add(taskJson.toTask());

    }

    for (JsonNode event : events) {
      EventJson eventJson = mapper.convertValue(event, EventJson.class);
      eventList.add(eventJson.toEvent());

    }

    return new PlannerDay(this.day, taskList, eventList, this.maxIssues);
  }
}
