package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.EventJson;
import java.time.LocalTime;

/**
 * to represent an event to appear on a bullet journal day
 */
public class Event extends BulletJournalItem {
  private final LocalTime time;
  private int duration;

  /**
   *
   * @param n name of event
   * @param d day of the week event takes place
   * @param c category of event
   * @param t time of the event
   * @param desc description of the event
   */
  public Event(String n, Day d, String c, LocalTime t, String desc, int duration) {
    super(n, d, c, desc);
    this.time = t;
    this.duration = duration;

  }

  /**
   *
   * @param n name of event
   * @param d day of the week event takes place
   * @param c category of event
   * @param t time of the event
   */
  public Event(String n, Day d, String c, LocalTime t, int duration) {
    super(n, d, c);
    this.time = t;
    this.duration = duration;
  }

  /**
   *
   * @return event as a formatted string
   */
  @Override
  public String getString() {
    return this.name + System.lineSeparator()
        + "Day : " + this.day + System.lineSeparator()
        + "Category : " + this.category + System.lineSeparator()
        + "Time : " + this.timeToString() + System.lineSeparator()
        + "Duration : " + this.duration + "minutes" + System.lineSeparator()
        + "Description : " + this.description;
  }

  /**
   *
   * @return event's time as a string
   */
  private String timeToString() {
    return this.time.toString();

  }

  /**
   *
   * @return this event serialized as a jsons
   */
  @Override
  public JsonNode itemToJson() {
    EventJson eventRecord =
        new EventJson(this.name, this.day, this.category, this.timeToString(), this.description,
            this.duration);
    JsonNode eventNode = JsonUtils.serializeRecord(eventRecord);
    return eventNode;

  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Event)) {
      return false;
    } else {
      Event otherEvent = (Event) other;
      return this.name.equals(otherEvent.name)
          && this.day.equals(otherEvent.day)
          && this.category.equals(otherEvent.category)
          && this.time.equals(otherEvent.time)
          && this.description.equals(otherEvent.description);
    }
  }
}
