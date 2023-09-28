package cs3500.pa05.controller;

import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.TaskVisView;
import cs3500.pa05.view.WeekViewInterface;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * represents the class that controls the miniViewer for a task
 * */
public class TaskVisController implements PopupController {

  private final Stage stage;
  private final Popup popup;
  private final PlannerModel week;
  private final Controller main;
  private final Task task;
  @FXML
  private Button taskDelete;
  @FXML
  private Label taskNameShow;
  @FXML
  private Label taskDescriptionShow;
  @FXML
  private CheckBox complete;
  @FXML
  private Button taskExit;


  /**
   * represents the constructor for the event-mini viewer
   *
   * @param main represents the main controller that displays the week view
   * @param task represents the task that is going to be displayed
   * @param model represents the model which holds the current week
   * @param stage represents the stage to display the popup scene onto
   * */
  public TaskVisController(Controller main, Stage stage, PlannerModel model, Task task) {
    this.main = main;
    this.stage = stage;
    this.popup = new Popup();
    WeekViewInterface view = new TaskVisView(this);
    this.week = model;
    this.task = task;
    popup.getContent().add(view.load().getRoot());
  }

  /**
   * displays the mini-viewer for a task
   * */
  @Override
  public void handle() {
    this.popup.show(stage);
    this.run();
  }


  /**
   * Initializes the task mini-viewer scene
   *
   * @throws IllegalStateException if the game board is not defined
   */
  @Override
  public void run() {
    taskExit.setOnAction(e -> popup.hide());
    taskNameShow.setText(task.getName());
    taskDescriptionShow.setText(task.getString());
    complete.setOnAction(e -> task.markAsComplete());
    taskDelete.setOnAction(e -> deleteHandler());
  }

  /**
   * removes the task from the board when the delete button has been clicked
   * */
  private void deleteHandler() {
    week.remove(task);
    this.run();
    try {
      main.run();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    popup.hide();
  }
}
