package cs3500.pa05.view;


import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.PlannerDay;
import cs3500.pa05.model.PlannerWeek;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * represents the scene that displays the Week
 */
public class MainWeekView implements WeekViewInterface {
  FXMLLoader weekLoader;
  Controller controller;
  @FXML
  ScrollPane monday;
  @FXML
  ScrollPane tuesday;
  @FXML
  ScrollPane wednesday;
  @FXML
  ScrollPane thursday;
  @FXML
  ScrollPane friday;
  @FXML
  ScrollPane saturday;
  @FXML
  ScrollPane sunday;

  /**
   * represents the constructor for the weekView
   */
  public MainWeekView(Controller controller) {
    this.weekLoader = new FXMLLoader();
    this.controller = controller;
    this.weekLoader.setLocation(getClass().getClassLoader().getResource("journal.fxml"));
    this.weekLoader.setController(controller);

  }

  /**
   * Loads a scene from a Week GUI layout.
   *
   * @return the layout
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.weekLoader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }

  /**
   *
   * @param week desired week to be displayed
   */
  public void showWeek(PlannerWeek week) {
    monday.getChildrenUnmodifiable().add(showItems(week.getDay(0)));
    tuesday.getChildrenUnmodifiable().add(showItems(week.getDay(1)));
    wednesday.getChildrenUnmodifiable().add(showItems(week.getDay(2)));
    thursday.getChildrenUnmodifiable().add(showItems(week.getDay(3)));
    friday.getChildrenUnmodifiable().add(showItems(week.getDay(4)));
    saturday.getChildrenUnmodifiable().add(showItems(week.getDay(5)));
    sunday.getChildrenUnmodifiable().add(showItems(week.getDay(6)));
  }

  /**
   *
   * @param day whose items will be shown
   * @return vbox with given day's items
   */
  private VBox showItems(PlannerDay day) {
    VBox pane = new VBox();
    List<String> strings = day.todosToString();
    for (String item : strings) {
      Label  itemShow = new Label(item);
      pane.getChildren().add(itemShow);
    }
    return pane;
  }
}
