package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.view.EventVisView;
import cs3500.pa05.view.WeekViewInterface;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * represents the class that controls the miniViewer for an event
 * */
public class EventVisController implements PopupController {
  private final Stage stage;
  private final Popup popup;
  private final PlannerModel week;
  private final Controller main;
  private final Event event;
  @FXML
  private Button eventExit;
  @FXML
  private Label eventShowLabel;
  @FXML
  private Label eventDescriptionLabel;
  @FXML
  private Button eventDelete;

  /**
   * represents the constructor for the event-mini viewer
   *
   * @param main represents the main controller that displays the week view
   * @param event represents the event that is going to be displayed
   * @param model represents the model which holds the current week
   * @param stage represents the stage to display the popup scene onto
   * */
  public EventVisController(Controller main, Stage stage, PlannerModel model, Event event) {
    this.main = main;
    this.stage = stage;
    this.popup = new Popup();
    WeekViewInterface view = new EventVisView(this);
    this.week = model;
    this.event = event;
    popup.getContent().add(view.load().getRoot());
  }

  /**
   * displays the mini-viewer for an event
   * */
  @Override
  public void handle() {
    this.popup.show(stage);
    this.run();
  }

  /**
   * Initializes the event mini-viewer scene
   *
   * @throws IllegalStateException if the game board is not defined
   */
  @Override
  public void run() {
    eventExit.setOnAction(e -> popup.hide());
    eventShowLabel.setText(event.getName());
    eventDescriptionLabel.setText(event.getString());
    eventDelete.setOnAction(e -> deleteHandler());
  }

  /**
   * removes the event from the board when the delete button has been clicked
   * */
  private void deleteHandler() {
    week.remove(event);
    this.run();
    try {
      main.run();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    popup.hide();
  }
}
