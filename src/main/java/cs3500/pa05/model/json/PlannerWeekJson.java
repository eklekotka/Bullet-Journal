package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.PlannerDay;
import cs3500.pa05.model.PlannerWeek;
import java.util.Arrays;
import java.util.List;

/**
 * @param week       list of days of the week
 * @param categories all categories for the week
 * @param maxToDos   max items that can be done for the week
 */
public record PlannerWeekJson(
    @JsonProperty("planner-week") JsonNode[] week,
    @JsonProperty("categories") List<String> categories,
    @JsonProperty("max-to-dos") int maxToDos,
    @JsonProperty("week-start-day") Day day,
    @JsonProperty("current-theme") String theme) {

  @Override
  public boolean equals(Object other) {

    if (!(other instanceof PlannerWeekJson)) {
      return false;
    } else {
      PlannerWeekJson otherJson = (PlannerWeekJson) other;

      return Arrays.equals(this.week, otherJson.week)
          && this.categories.equals(otherJson.categories)
          && this.maxToDos == otherJson.maxToDos
          && this.day.equals(otherJson.day)
          && this.theme.equals(otherJson.theme);

    }
  }

  /**
   *
   * @return this json as a PlannerWeek java object
   */
  public PlannerWeek toPlannerWeek() {
    ObjectMapper mapper = new ObjectMapper();
    PlannerDay[] days = new PlannerDay[7];

    for (int index = 0; index < 7; index++) {
      PlannerDayJson plannerDayJson = mapper.convertValue(week[index], PlannerDayJson.class);
      days[index] = plannerDayJson.toPlannerDay();

    }

    return new PlannerWeek(days, this.categories, this.maxToDos);

  }
}
