package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournalItem;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.NewTaskView;
import cs3500.pa05.view.WeekViewInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * represents the controller for task
 */
public class TaskController implements PopupController {
  private final Stage stage;
  private final Popup popup;
  private final PlannerModel week;
  private String category;

  @FXML
  private TextField newCategory;
  @FXML
  private Button exitButton;

  @FXML
  private Button saveButton;
  @FXML
  private TextField name;
  @FXML
  private TextArea description;
  @FXML
  private SplitMenuButton days;
  @FXML
  private SplitMenuButton dayLabel;
  private Day dayOfWeek;

  @FXML
  private ComboBox<String> categoryOpt;
  @FXML
  private Button saveCategory;

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

  private final Controller main;

  private final List<String> listOfCategories = new ArrayList<>(List.of("new category"));


  /**
   * represents the constructor
   *
   * @param main represents the main controller
   * @param stage the main stage to display the scene on
   * @param model represents the model which holds the week
   */
  public TaskController(Controller main, Stage stage, PlannerModel model) {
    this.main = main;
    this.stage = stage;
    this.popup = new Popup();
    dayOfWeek = null;
    WeekViewInterface view = new NewTaskView(this);
    category = "";
    this.week = model;
    popup.getContent().add(view.load().getRoot());
    exitButton.setOnAction(e -> popup.hide());
    //this.daySelector();
    for (MenuItem item : dayLabel.getItems()) {
      item.setOnAction(e -> setName(item));
    }
    categoryOpt.setOnAction(e -> createNewCategory());
    saveButton.setOnAction(e -> this.onSaveClick());
    this.run();

  }

  /**
   * Initializes a task scene.
   *
   * @throws IllegalStateException if the game board is not defined
   */
  public void run() throws IllegalStateException {
    categoryOpt.setPromptText("Choose category");
    categoryOpt.getItems().addAll(week.getCategory());
    categoryOpt.getItems().addAll(listOfCategories);
  }

  /**
   * saves a task when the save button is clicked and if the max number of events for the day
   * hasn't been reached yet
   */
  public void onSaveClick() {
    if (name.getText().equals("") || dayOfWeek == null || category.equals("")) {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please fill out all the fields, description is optional");
      alert.show();
    } else {
      BulletJournalItem task = new Task(name.getText(), dayOfWeek, category, description.getText());
      try {
        week.addWeekTask(task);
      } catch (RuntimeException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "you have too many items on this day or need to set max size!");
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

  /**
   * creates the popup scene containing the new Task
   */
  public void handle() {
    this.popup.show(stage);

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
   * */
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

}
