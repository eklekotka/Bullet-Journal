package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.model.PlannerWeek;

/**
 *
 * @param week node of current week to be represented in the model
 * @param path location of the bujo file the model is currently representing
 */
public record PlannerModelJson(
    @JsonProperty("planner-week") JsonNode week,
    @JsonProperty("file-path") String path,
    @JsonProperty("password") String password) {

  /**
   *
   * @return this json as a PlannerModel object
   */
  public PlannerModel toPlannerModel() {
    ObjectMapper mapper = new ObjectMapper();
    PlannerWeekJson weekJson = mapper.convertValue(week, PlannerWeekJson.class);
    PlannerWeek plannerWeek = weekJson.toPlannerWeek();
    return new PlannerModel(plannerWeek);

  }
}
