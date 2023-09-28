package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.WeekController;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.model.PlannerWeek;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents a Whack-A-Mole game application.
 */
public class Driver extends Application {

  public static boolean hasBeenSplashed = false;

  /**
   * Starts the GUI for a game of Whack-A-Mole.
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) throws IOException {


    try {
      Controller controller = new WeekController(stage,
          new PlannerModel(new PlannerWeek(Day.MONDAY)));
      controller.run();


    } catch (IllegalStateException exc) {
      System.err.println("Cannot load GUI");
    }
  }

  /**
   * Entry point for a game of Whack-a-Mole.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }

}