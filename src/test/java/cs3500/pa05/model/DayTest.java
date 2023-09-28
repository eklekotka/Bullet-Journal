package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * tests for the methods in the Day enum
 */
public class DayTest {

  /**
   * tests the toString method in the day enum
   */
  @Test
  public void testToString() {
    assertEquals(Day.MONDAY.toString(), "Monday");
    assertEquals(Day.TUESDAY.toString(), "Tuesday");
    assertEquals(Day.WEDNESDAY.toString(), "Wednesday");
    assertEquals(Day.THURSDAY.toString(), "Thursday");
    assertEquals(Day.FRIDAY.toString(), "Friday");
    assertEquals(Day.SATURDAY.toString(), "Saturday");
    assertEquals(Day.SUNDAY.toString(), "Sunday");

  }


  /**
   * tests the toString method throws the correct exception
   */
  @Test
  public void testInvalidEnum() {
    Day empty = null;
    assertThrows(NullPointerException.class, () -> empty.toString());

  }
}
