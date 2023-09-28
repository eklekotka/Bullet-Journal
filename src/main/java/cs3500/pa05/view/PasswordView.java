package cs3500.pa05.view;

import cs3500.pa05.controller.PopupController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * view responsible for loading password scene
 */
public class PasswordView implements WeekViewInterface {
  private FXMLLoader weekLoader;
  private PopupController controller;

  /**
   * represents the controller for the newEvent window
   *
   * @param controller represents the controller for this class
   */
  public PasswordView(PopupController controller) {
    this.weekLoader = new FXMLLoader();
    this.controller = controller;
    this.weekLoader.setLocation(getClass().getClassLoader().getResource("password.fxml"));
    this.weekLoader.setController(controller);
  }

  /**
   * Loads a scene from a Week GUI layout.
   *
   * @return the layout
   */
  public Scene load() throws IllegalStateException {
    try {
      return this.weekLoader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
