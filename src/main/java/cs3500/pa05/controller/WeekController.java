package cs3500.pa05.controller;

import static cs3500.pa05.Driver.hasBeenSplashed;
import static java.util.Arrays.asList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.commands.ColorChange;
import cs3500.pa05.model.BujoFileReader;
import cs3500.pa05.model.BujoFileWriter;
import cs3500.pa05.model.BulletJournalItem;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Item;
import cs3500.pa05.model.JsonUtils;
import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.model.PlannerWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.json.PlannerModelJson;
import cs3500.pa05.model.json.PlannerWeekJson;
import cs3500.pa05.view.MainWeekView;
import cs3500.pa05.view.WeekViewInterface;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * test class (can be deleted, just something to fill package)
 */
public class WeekController implements Controller {

  private final Stage stage;
  @FXML
  private MenuItem eventButton;
  @FXML
  private MenuItem taskButton;
  @FXML
  private RadioButton blueBackground;

  @FXML
  private RadioButton emmaBackground;

  @FXML
  private RadioButton katieBackground;

  @FXML
  private RadioButton defaultBackground;

  @FXML
  private TitledPane categoryMenu;
  private PlannerModel week;
  private Day startDay;
  @FXML
  private ColorPicker sidePaneTheme;
  @FXML
  private Pane sidePanel;
  @FXML
  private Pane weekNamePane;
  @FXML
  private AnchorPane sceneAnchor;
  @FXML
  private ColorPicker headerTheme;
  @FXML
  private ColorPicker backTheme;
  @FXML
  private MenuButton weekStart;
  @FXML
  private HBox weekDisplay;
  @FXML
  private Label day1;
  @FXML
  private Label day2;
  @FXML
  private Label day3;
  @FXML
  private Label day4;
  @FXML
  private Label day5;
  @FXML
  private Label day6;
  @FXML
  private Label day7;
  private List<Day> dayList;
  @FXML
  private Spinner<Integer> maxEvents;
  @FXML
  private VBox mondayPane;

  @FXML
  private VBox tuesdayPane;
  @FXML
  private VBox wednesdayPane;
  @FXML
  private VBox thursdayPane;
  @FXML
  private VBox fridayPane;
  @FXML
  private VBox saturdayPane;
  @FXML
  private VBox sundayPane;

  @FXML
  private Button saveWeek;
  @FXML
  private Button saveAs;
  @FXML
  private Button openPrev;
  private final String currentTheme;

  @FXML
  private Button openTemplate;

  @FXML
  private Label currFile;

  @FXML
  private TextField setPass;
  @FXML
  private Button savePass;
  @FXML
  private Button editPass;

  /**
   * represents the controller for the week view
   *
   * @param stage the stage to display the scene on
   * @param m     the planner week.
   */
  public WeekController(Stage stage, PlannerModel m) {
    this.currentTheme = "";
    this.stage = stage;
    week = m;
    startDay = Day.MONDAY;
    this.createMainWeek();
    SpinnerValueFactory<Integer> hourValues
        = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
    maxEvents.setValueFactory(hourValues);
    saveWeek.setOnAction(e -> saveFile());
  }

  /**
   * Initializes the BulletJournal
   *
   * @throws IllegalStateException if the game board is not defined
   */
  @Override
  public void run() throws IllegalStateException, IOException {
    this.handleEventButton();
    this.displayWeekTasks();
    this.createNewCategories();
    for (MenuItem item : weekStart.getItems()) {
      item.setOnAction(e -> setDay(Day.valueOf(item.getText().toUpperCase())));
    }
    //saveWeek.setOnAction(e -> saveFile());
    openPrev.setOnAction(e -> openOld());
    this.openTemplate.setOnAction(e -> handleSavePref());
    handleFileButtons();
    this.displayWeekTasks();
    this.currFile.setText("Current File: " + this.week.getPath());
    if (!this.week.getPassword().equals("")) {
      this.method();
    }
    savePass.setOnAction(e -> setPassword());
    editPass.setOnAction(e -> this.setPass.setEditable(true));

  }

  /**
   * reloads the screen with the journal when the splash screen is completed
   */
  private void reloadScreen() {
    this.createMainWeek();
    SpinnerValueFactory<Integer> hourValues
        = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12);
    maxEvents.setValueFactory(hourValues);
    this.handleEventButton();
    this.createNewCategories();
    for (MenuItem item : weekStart.getItems()) {
      item.setOnAction(e -> setDay(Day.valueOf(item.getText().toUpperCase())));
    }
    openPrev.setOnAction(e -> openOld());
    this.openTemplate.setOnAction(e -> handleSavePref());
    handleFileButtons();
    this.currFile.setText(this.currFile.getText() + this.week.getPath());
    this.displayWeekTasks();

    if (!this.week.getPassword().equals("")) {
      this.method();
    }
    savePass.setOnAction(e -> setPassword());
    editPass.setOnAction(e -> this.setPass.setEditable(true));

  }

  /**
   * creates the main scene containing a week
   */
  @FXML
  private void createMainWeek() {
    WeekViewInterface weekView = new MainWeekView(this);
    Scene scene = weekView.load();
    scene.getStylesheets().add("/default.css"); //default styleSheet
    stage.setScene(scene);
    if (!hasBeenSplashed) {
      splashScreen();
      hasBeenSplashed = true;
    }
    stage.setMaximized(true);

    stage.show();
    //handles the changing of the scene
    this.changeColorButton(scene);
  }

  /**
   * popup a window give a view containing a scene
   */
  private void handleEventButton() {
    PopupController eventController = new EventController(this, stage, week);
    PopupController taskController = new TaskController(this, stage, week);

    eventButton.setOnAction(e -> eventController.handle());
    taskButton.setOnAction(e -> taskController.handle());
    week.setMaxToDo(maxEvents.getValue());
  }

  /**
   * popup a window give a view containing a scene
   *
   * @param scene the current scene to change the color of
   */
  private void changeColorButton(Scene scene) {
    ColorChange colorChange = new ColorChange();

    String cssDefault = ("default.css");
    String cssBlue = ("backgroundBlue.css");
    String emma = ("EmmaJournal.css");
    blueBackground.setOnAction(e -> colorChange.changeTheme(scene, cssBlue));
    defaultBackground.setOnAction(e -> colorChange.changeTheme(scene, cssDefault));
    emmaBackground.setOnAction(e -> colorChange.changeTheme(scene, emma));
    String katie = ("katie.css");
    katieBackground.setOnAction(e -> colorChange.changeTheme(scene, katie));

    sidePaneTheme.setOnAction(e -> customSidePaneTheme());
    headerTheme.setOnAction(e -> customHeaderTheme());
    backTheme.setOnAction(e -> customBackTheme());

    sidePaneTheme.setOnAction(e -> customSidePaneTheme());
    headerTheme.setOnAction(e -> customHeaderTheme());
    backTheme.setOnAction(e -> customBackTheme());

    if (!this.week.getWeekTheme().equals("")) {
      colorChange.changeTheme(scene, this.week.getWeekTheme());
    }
  }

  /**
   * customizes the side panel
   */
  private void customSidePaneTheme() {
    Color color = sidePaneTheme.getValue();
    sidePanel.setBackground(new Background(
        new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  /**
   * customizes the header theme
   */
  private void customHeaderTheme() {
    Color color = headerTheme.getValue();
    weekNamePane.setBackground(new Background(
        new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  /**
   * customizes the background theme
   */
  private void customBackTheme() {
    Color color = backTheme.getValue();
    sceneAnchor.setBackground(new Background(
        new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  /**
   * creates a new category on the side for the user to choose from
   */
  private void createNewCategories() {
    ToggleGroup categoryToggle = new ToggleGroup();
    Button create = new Button("new category");
    Button allButton = new Button("view all");

    VBox vtBox = new VBox();
    HBox hzBox = new HBox();
    Button save = new Button("save");
    hzBox.getChildren().addAll(create, save);
    vtBox.getChildren().addAll(hzBox, allButton);
    categoryMenu.setContent(vtBox);
    //adds the categories made in event/task to the sidePanel
    for (String s : this.week.getCategory()) {
      RadioButton text = new RadioButton(s);
      text.setStyle("-fx-font: 12 arial;");
      text.setToggleGroup(categoryToggle);
      vtBox.getChildren().add(text);
      text.setOnAction(e -> displayFilteredTasks(s));
    }
    create.setOnAction(e -> saveCategories(vtBox, save, categoryToggle));
    allButton.setOnAction(e -> this.displayWeekTasks());

  }

  /**
   * saves the new category to the list on the side
   */
  private void saveCategories(VBox vtBox, Button save, ToggleGroup categoryToggle) {
    TextField text = new TextField();
    text.setEditable(true);
    text.setMaxSize(100, 100);
    vtBox.getChildren().add(text);
    save.setOnAction(e -> categoryListProperties(text, vtBox, categoryToggle));
    text.setStyle("-fx-font: 12 arial;");
  }

  /**
   * deploys the splash screen
   */
  private void splashScreen() {

    try {
      StackPane pane = FXMLLoader.load(getClass().getResource("/Welcome.fxml"));
      sceneAnchor.getChildren().setAll(pane);

      FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
      fadeIn.setFromValue(0);
      fadeIn.setToValue(1);
      fadeIn.setCycleCount(1);

      FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
      fadeOut.setFromValue(1);
      fadeOut.setToValue(0);
      fadeOut.setCycleCount(1);

      fadeIn.play();
      fadeIn.setOnFinished(e -> fadeOut.play());

      fadeOut.setOnFinished(e ->
          this.reloadScreen());

    } catch (IOException e) {
      System.err.println("Problem with splash screen" + e);
    }
  }

  /**
   * displays only the tasks and events with the chosen category
   */
  private void displayFilteredTasks(String cat) {
    ArrayList<VBox> paneList = new ArrayList<>(asList(mondayPane, tuesdayPane, wednesdayPane,
        thursdayPane, fridayPane, saturdayPane, sundayPane));
    for (VBox box : paneList) {
      box.getChildren().clear();
    }

    //the day the user chooses
    int ind = dayList.indexOf(startDay);
    int labelInd = 0;
    for (int index = ind; index < dayList.size(); index++) {
      paneList.get(labelInd).getChildren()
          .add(displayTasks(week.getDay(index).filterByCategory(cat)));
      labelInd++;
    }

    for (int first = 0; first < ind; first++) {
      paneList.get(labelInd).getChildren()
          .add(displayTasks(week.getDay(first).filterByCategory(cat)));
      labelInd++;
      weekStart.setDisable(true);
    }

  }


  /**
   * updates the list of categories for the side panel
   */
  private void categoryListProperties(TextField text, VBox vbox, ToggleGroup categoryToggle) {
    RadioButton box = new RadioButton(text.getText());
    box.setToggleGroup(categoryToggle);
    vbox.getChildren().remove(text);
    vbox.getChildren().add(box);
    text.setEditable(false);
    //categoryList.add(text.getText());
    week.addCategory(text.getText());
    this.handleEventButton();


  }

  private void displayWeekTasks() {
    setDay(startDay);
  }


  /**
   * sets the day according to user input
   *
   * @param item the day that is clicked
   */
  private void setDay(Day item) {
    startDay = item;
    dayList = new ArrayList<>(asList(Day.MONDAY, Day.TUESDAY,
        Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY));
    ArrayList<Label> labelList = new ArrayList<>(asList(day1, day2, day3, day4, day5, day6, day7));
    ArrayList<VBox> paneList = new ArrayList<>(asList(mondayPane, tuesdayPane, wednesdayPane,
        thursdayPane, fridayPane, saturdayPane, sundayPane));
    for (VBox box : paneList) {
      box.getChildren().clear();
    }

    //the day the user chooses
    int ind = dayList.indexOf(item);
    int labelInd = 0;
    for (int index = ind; index < dayList.size(); index++) {
      paneList.get(labelInd).getChildren().add(displayTasks(week.getDay(index).getItems()));
      labelList.get(labelInd).setText(String.valueOf(dayList.get(index)));
      labelInd++;
    }

    for (int first = 0; first < ind; first++) {
      labelList.get(labelInd).setText(String.valueOf(dayList.get(first)));
      paneList.get(labelInd).getChildren().add(displayTasks(week.getDay(first).getItems()));
      labelInd++;
      weekStart.setDisable(true);
    }
  }

  /**
   * displays to the monday mane for test,
   * figure out how to link the name to
   */
  private VBox displayTasks(List<BulletJournalItem> items) {
    VBox vtBox = new VBox();
    for (BulletJournalItem item : items) {
      Button button = new Button(item.getName());
      button.setOnAction(e -> handleItemPopUp(item));
      vtBox.getChildren().add(button);
    }
    return vtBox;
  }

  private void handleItemPopUp(Item item) {
    if (item instanceof Task task) {
      TaskVisController vis = new TaskVisController(this, stage, week, task);
      vis.handle();
      vis.run();
    } else if (item instanceof Event) {
      Event event = (Event) item;
      EventVisController eventVis = new EventVisController(this, stage, week, event);
      eventVis.handle();
    }
  }

  private void handleOpenButtonClick() {
    FileChooser filePick = new FileChooser();
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("bujos only",
        new ArrayList<>(Arrays.asList("bujo")));
    filePick.setSelectedExtensionFilter(filter);
    File chosenFile = filePick.showOpenDialog(stage);
    this.week.updatePath(String.valueOf(chosenFile));
    try {
      this.run();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private void method() {
    System.out.println("METHOD");
    PasswordController controller = new PasswordController(this, stage, week);
    controller.handle();
  }

  private void handleFileButtons() {
    saveWeek.setOnAction(e -> saveFile());
    saveAs.setOnAction(e -> saveAsFile());
  }

  /**
   * saves a new file
   */
  private void saveFile() {
    if (week.getPath().equals("")) {
      Alert warning = new Alert(Alert.AlertType.WARNING, "must save-as first!");
      warning.show();
    } else {
      this.week.setWeekTheme(currentTheme);
      ObjectMapper mapper = new ObjectMapper();
      BujoFileWriter fileWriter = new BujoFileWriter(mapper);
      JsonNode node = week.changeToJson();
      fileWriter.jsonToBujo(node, week.getPath());
    }
  }

  private void setPassword() {
    this.week.setPassword(this.setPass.getText());
    this.setPass.setEditable(false);
  }

  private void saveAsFile() {
    if (!this.week.getPath().equals("")) {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "You've have a version on your computer, " +
              "please Save!");
      alert.show();
    } else {
      if (!this.week.getPassword().equals("")) {
        this.week.setWeekTheme(currentTheme);
        FileChooser chooser = new FileChooser();
        File saveFile = chooser.showSaveDialog(this.stage);
        BujoFileWriter writer = new BujoFileWriter(new ObjectMapper());
        JsonNode node = week.changeToJson();
        week.updatePath(saveFile.getAbsolutePath());
        String bujoExt = saveFile.getAbsolutePath() + ".bujo";
        week.updatePath(bujoExt);
        writer.jsonToBujo(node, bujoExt);
      } else {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "You haven't set the password yet!");
        alert.show();
      }
    }

  }

  private void openOld() {
    FileChooser choose = new FileChooser();
    File fileGetter = choose.showOpenDialog(stage);
    System.out.println(fileGetter);
    BujoFileReader read = new BujoFileReader(new ObjectMapper());
    JsonNode node = read.bujoToJson(fileGetter.getAbsolutePath());
    String filePath = fileGetter.getAbsolutePath();

    PlannerModelJson modelJson = JsonUtils.toPlannerModelJson(node);
    PlannerWeekJson weekJson = JsonUtils.toPlannerWeekJson(modelJson.week());
    PlannerWeek plannerWeek = weekJson.toPlannerWeek();
        PlannerModel model1 = new PlannerModel(plannerWeek, filePath, modelJson.password());

    week = model1;

    try {
      this.run();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleSavePref() {
    if (week.getPath().equals("")) {
      Alert warning = new Alert(Alert.AlertType.WARNING, "File hasn't been saved! Save as!");
      warning.show();
    } else {
      this.week.setWeekTheme(currentTheme);
      ObjectMapper mapper = new ObjectMapper();
      FileChooser chooser = new FileChooser();
      File saveFile = chooser.showSaveDialog(this.stage);
      week.wipeToDo();
      BujoFileWriter fileWriter = new BujoFileWriter(mapper);
      JsonNode node = week.changeToJson();
      fileWriter.jsonToBujo(node, saveFile.getPath() + ".bujo");
    }
  }
}
