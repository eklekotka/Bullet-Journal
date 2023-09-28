package cs3500.pa05.controller;

import cs3500.pa05.model.PlannerModel;
import cs3500.pa05.view.PasswordView;
import cs3500.pa05.view.WeekViewInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

/**
 * controller for the popup password window
 */
public class PasswordController implements PopupController {

  private final Controller main;
  private final Stage stage;
  private final Popup popup;
  private final PlannerModel model;

  WeekViewInterface view = new PasswordView(this);

  @FXML
  private TextField passwordField;
  @FXML
  private Button loginButton;
  @FXML
  private Label incorrectPass;

  /**
   * represents the controller
   *
   * @param main represents the main controller
   * @param stage represents the stage on which this window is displayed
   * @param m represents the model which holds the week
   */
  PasswordController(Controller main, Stage stage, PlannerModel m) {
    this.main = main;
    this.stage = stage;
    this.model = m;
    this.popup = new Popup();
    popup.getContent().add(view.load().getRoot());
    loginButton.setOnAction(e -> checkPassword());
  }

  /**
   * shows the popup scene
   */
  public void handle() {
    this.popup.show(stage);

    this.checkPassword();
    incorrectPass.setVisible(false);

  }

  @Override
  public void run() {

  }

  /**
   * checks if the week's password is equal to the user's input
   */
  private void checkPassword() {
    if (passwordField.getText().equals(model.getPassword())) {
      popup.hide();
    } else {
      incorrectPass.setVisible(true);
    }
  }
}
