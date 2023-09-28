package cs3500.pa05.model;

/**
 * to represent the seven different possible days of the week
 */
public enum Day {

  /**
   * represents Monday
   * */
  MONDAY,

  /**
   * represents Tuesday
   * */
  TUESDAY,

  /**
   * represents Wednesday
   * */
  WEDNESDAY,

  /**
   * represents Thursday
   * */
  THURSDAY,

  /**
   * represents Friday
   * */
  FRIDAY,

  /**
   * represents Saturday
   * */
  SATURDAY,

  /**
   * represents Sunday
   * */
  SUNDAY;

  /**
   * @return string form of given day
   */
  @Override
  public String toString() {
    String result = switch (this) {
      case MONDAY -> "Monday";
      case TUESDAY -> "Tuesday";
      case WEDNESDAY -> "Wednesday";
      case THURSDAY -> "Thursday";
      case FRIDAY -> "Friday";
      case SATURDAY -> "Saturday";
      case SUNDAY -> "Sunday";
    };
    return result;
  }
}