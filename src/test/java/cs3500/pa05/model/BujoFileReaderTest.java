//package cs3500.pa05.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.BufferedReader;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
///**
// * writes examples for and tests the methods in the class BujoFileReader
// */
//public class BujoFileReaderTest {
//  private ObjectMapper mapper;
//  private BufferedReader reader;
//  private PlannerWeek testWeek;
//
//  private List<String> categories;
//  private PlannerDay[] week;
//
//  private LocalTime nineFiftyAm;
//  private LocalTime eightAm;
//  private LocalTime sixFifteenPm;
//
//  private BulletJournalItem emptyEvent;
//  private BulletJournalItem examTwo;
//  private BulletJournalItem oodLab;
//  private BulletJournalItem lecture;
//  private BulletJournalItem emptyTask;
//  private BulletJournalItem study;
//  private BulletJournalItem laundry;
//
//  private List<BulletJournalItem> mondayItems;
//  private List<BulletJournalItem> mondaySchoolItems;
//  private List<BulletJournalItem> mondayChoresItems;
//
//
//  private PlannerDay sunday;
//  private PlannerDay monday;
//  private PlannerDay tuesday;
//  private PlannerDay wednesday;
//  private PlannerDay thursday;
//  private PlannerDay friday;
//  private PlannerDay saturday;
//
//  /**
//   * initializes examples before each test
//   * initialize examples before each test
//   */
//  @BeforeEach
//  public void setUp() {
//    testWeek = new PlannerWeek(Day.SUNDAY);
//    nineFiftyAm = LocalTime.of(9, 50);
//    eightAm = LocalTime.of(8, 0);
//    sixFifteenPm = LocalTime.of(18, 15);
//
//    emptyEvent = new Event("Empty", Day.SUNDAY, "Test", LocalTime.MIDNIGHT);
//    examTwo =
//        new Event("Exam Two", Day.THURSDAY, "School", nineFiftyAm, "Second OOD Exam");
//    oodLab =
//        new Event("Lab", Day.MONDAY, "School", eightAm, "Lab for OOD");
//    lecture = new Event("Lecture", Day.MONDAY, "School", nineFiftyAm, "Lecture for OOD");
//    emptyTask = new Task("Empty", Day.SUNDAY, "Test");
//    study = new Task("Study", Day.WEDNESDAY, "School", "Study for Exam");
//    laundry = new Task("Laundry", Day.MONDAY, "Chores", "Do Laundry");
//
//    mondayItems = new ArrayList(Arrays.asList(lecture, oodLab, laundry));
//    mondaySchoolItems = new ArrayList(Arrays.asList(lecture, oodLab));
//    mondayChoresItems = new ArrayList(Arrays.asList(laundry));
//
//    sunday = new PlannerDay(Day.SUNDAY);
////    monday = new PlannerDay(Day.MONDAY, mondayItems, 3);
//    monday = new PlannerDay(Day.MONDAY);
//    tuesday = new PlannerDay(Day.TUESDAY);
//    wednesday = new PlannerDay(Day.WEDNESDAY);
//    thursday = new PlannerDay(Day.THURSDAY);
//    friday = new PlannerDay(Day.FRIDAY);
//    saturday = new PlannerDay(Day.SATURDAY);
//
//    categories = new ArrayList<>(Arrays.asList("School", "Chores"));
//    week = new PlannerDay[] {sunday, monday, tuesday, wednesday, thursday, friday, saturday};
//
//    testWeek = new PlannerWeek(week, categories, 4);
//
//  }
//
//  /**
//   * tests the bujoToJson method
//   *
//   * @throws JsonProcessingException
//   */
//  @Test
//  public void testBujoToJson() throws JsonProcessingException {
//    JsonNode weekNode = testWeek.plannerWeekToJson();
//    ObjectMapper mapper = new ObjectMapper();
//    BujoFileWriter writer = new BujoFileWriter(mapper);
//    writer.jsonToBujo(weekNode, "src\\main\\resources\\testBujoLocation\\test2.bujo");
//
//    BujoFileReader reader = new BujoFileReader(mapper);
//    assertEquals(reader.bujoToJson(
//        "src\\main\\resources\\testBujoLocation\\test2.bujo"), weekNode);
//
//  }
//}
