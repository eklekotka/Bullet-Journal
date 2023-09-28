package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.BulletJournalItem;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import java.time.LocalTime;

/**
 *
 * @param name name of event
 * @param day day the event takes place
 * @param category category of the event
 * @param time time the event takes place
 * @param description description of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("day") Day day,
    @JsonProperty("category") String category,
    @JsonProperty("time") String time,
    @JsonProperty("description") String description,
    @JsonProperty("duration") int duration) {

  @Override
  public boolean equals(Object other) {

    if (!(other instanceof EventJson)) {
      return false;
    } else {
      EventJson otherJson = (EventJson) other;

      return this.name.equals(otherJson.name)
          && this.day.equals(otherJson.day)
          && this.category.equals(otherJson.category)
          && this.time.equals(otherJson.time)
          && this.description.equals(otherJson.description)
          && this.duration == otherJson.duration;

    }
  }

  /**
   * converts an EventJson to an Event
   *
   * @return an Event
   */
  public BulletJournalItem toEvent() {
    LocalTime t = LocalTime.of(Integer.parseInt(time().substring(0, time().indexOf(":"))),
        Integer.parseInt(time().substring(time().indexOf(":") + 1)));
    return new Event(name(), day(), category(), t, description(), duration());

  }
}
