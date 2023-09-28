package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournalItem;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.view.NewEventView;
import cs3500.pa05.view.WeekViewInterface;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * represents the controller for the event creator window
 */
public class EventController implements PopupController {

  private final Controller main;
  private final Stage stage;
  private final Popup popup;

  @FXML
  private MenuItem am;
  @FXML
  private MenuItem pm;
  @FXML
  private Button exitButton;
  @FXML
  private Spinner<Integer> hourSpinner;
  @FXML
  private Spinner<Integer> minuteSpinner;
  @FXML
  private Spinner<Integer> startHourSpinner;
  @FXML
  private Spinner<Integer> startMinuteSpinner;
  @FXML
  private TextField eventName;
  @FXML
  private TextArea eventDesc;
  @FXML
  private MenuButton dayLabel;
  @FXML
  private ComboBox<String> categoryOpt;
  @FXML
  private Button saveCategory;
  @FXML
  private Button saveButton;
  @FXML
  private MenuItem sunday;
  @FXML
  private MenuItem monday;
  @FXML
  private MenuItem tuesday;
  @FXML
  private MenuItem wednesday;
  @FXML
  private MenuItem thursday;
  @FXML
  private MenuItem friday;
  @FXML
  private MenuItem saturday;


  private Day dayOfWeek;

  private final List<String> listOfCategories = new ArrayList<>(List.of("new category"));

  private final PlannerModel week;
  private String category;
  private LocalTime time;


  /**
   * represents the constructor
   *
   * @param stage the main stage to display the scene on
   * @param main  the main controller
   * @param model the planner model
   */
  public EventController(Controller main, Stage stage, PlannerModel model) {
    this.week = model;
    this.main = main;
    this.stage = stage;
    this.popup = new Popup();
    this.category = "";
    this.time = LocalTime.MIDNIGHT;
    WeekViewInterface view = new NewEventView(this);
    popup.getContent().add(view.load().getRoot());
    exitButton.setOnAction(e -> popup.hide());
    //this.daySelector();
    this.timeSelector();
    for (MenuItem item : dayLabel.getItems()) {
      item.setOnAction(e -> setName(item));
    }
    categoryOpt.setOnAction(e -> createNewCategory());
    saveButton.setOnAction(e -> this.onSaveClick());
    this.run();
  }

  /**
   * Initializes an event scene.
   *
   * @throws IllegalStateException if the game board is not defined
   */
  public void run() throws IllegalStateException {
    SpinnerValueFactory<Integer> hourValues
        = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
    SpinnerValueFactory<Integer> startHourValues
        = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
    hourSpinner.setValueFactory(hourValues);
    startHourSpinner.setValueFactory(startHourValues);

    SpinnerValueFactory<Integer> startMinuteValues
        = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60);
    SpinnerValueFactory<Integer> minuteValues
        = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60);
    startMinuteSpinner.setValueFactory(startMinuteValues);
    minuteSpinner.setValueFactory(minuteValues);

    categoryOpt.setPromptText("Choose category");
    categoryOpt.getItems().addAll(week.getCategory());
    categoryOpt.getItems().addAll(listOfCategories);
    timeSelector();
  }

  /**
   * creates the popup scene containing the new Event
   */
  public void handle() {
    this.popup.show(stage);
  }


  /**
   * saves a task when the save button is clicked and if the max number of events for the day
   * hasn't been reached yet
   */
  public void onSaveClick() {
    if (eventName.getText().equals("") || dayOfWeek == null || category.equals("")
    || time == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please fill out all the fields, description is optional");
      alert.show();
    } else {
      BulletJournalItem event = new Event(eventName.getText(), dayOfWeek, category,
          time, eventDesc.getText(), getTime());
      try {
        week.addWeekEvent(event);
      } catch (RuntimeException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "You have too many items on this day or you have not set max to-dos!");
        alert.show();
      }
      try {
        main.run();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      popup.hide();
    }
  }

  private int getTime() {
    return hourSpinner.getValue() * 60 + minuteSpinner.getValue();
  }


  /**
   * sets the label of the drop-down to the chosen day and also
   * sets the chosen day as the current event's day
   * */
  private void setName(MenuItem item) {
    dayOfWeek = Day.valueOf(item.getText().toUpperCase());
    dayLabel.setText(item.getText());

  }

  /**
   * handles creating a new category when the respective drop-down item is chosen
   * by setting the comboBox to be editable
   */
  private void createNewCategory() {
    if (categoryOpt.getValue().equals("new category")) {
      categoryOpt.setEditable(true);
      categoryOpt.setStyle("-fx-font: 12 arial;");
      saveCategory.setOnAction(e -> updateCatList());
      categoryOpt.setOnAction(e -> category = categoryOpt.getValue());
    } else {
      categoryOpt.setEditable(false);
      category = categoryOpt.getValue();
    }
  }

  /**
   * adds the newly created category to the dropdown menu and also
   * adds it to the week's list of categories
   */
  private void updateCatList() {
    categoryOpt.getItems().addAll(categoryOpt.getValue());
    listOfCategories.add(categoryOpt.getValue());
    this.week.addCategory(categoryOpt.getValue());
    try {
      main.run();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void handlePm() {
    time = LocalTime.of(startHourSpinner.getValue() + 12, startMinuteSpinner.getValue());
  }

  private void handleAm() {
    time = LocalTime.of(startHourSpinner.getValue(), startMinuteSpinner.getValue());
  }

  /**
   * deploys a setOnAction on the am, and pm menu choices
   * */
  private void timeSelector() {
    pm.setOnAction(e -> handlePm());
    am.setOnAction(e -> handleAm());
  }
}

