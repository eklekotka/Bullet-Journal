package cs3500.pa05.view;

import cs3500.pa05.controller.PopupController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * represents the view for the newEvent window
 */
public class NewEventView implements WeekViewInterface {

  private FXMLLoader weekLoader;
  private PopupController controller;

  /**
   * represents the controller for the newEvent window
   *
   * @param controller represents the controller for this class
   */
  public NewEventView(PopupController controller) {
    this.weekLoader = new FXMLLoader();
    this.controller = controller;
    this.weekLoader.setLocation(getClass().getClassLoader().getResource("EventCreator.fxml"));
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
