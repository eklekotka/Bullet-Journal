package cs3500.pa05.view;

import cs3500.pa05.controller.PopupController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * displays the saved events
 */
public class EventVisView implements WeekViewInterface {

  private FXMLLoader weekLoader;
  private PopupController controller;

  /**
   * represents the controller for the newEvent window
   *
   * @param controller represents the controller for this class
   */
  public EventVisView(PopupController controller) {
    this.weekLoader = new FXMLLoader();
    this.controller = controller;
    this.weekLoader.setLocation(getClass().getClassLoader().getResource("EventShow.fxml"));
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

}
