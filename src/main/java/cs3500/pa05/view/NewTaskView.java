package cs3500.pa05.view;

import cs3500.pa05.controller.PopupController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * displays a new task menu to create a new task
 */
public class NewTaskView implements WeekViewInterface {
  private FXMLLoader taskLoader;
  private PopupController controller;

  /**
   *
   * @param controller controller for popup windows
   */
  public NewTaskView(PopupController controller) {
    this.taskLoader = new FXMLLoader();
    this.controller = controller;
    this.taskLoader.setLocation(getClass().getClassLoader().getResource("TaskCreator.fxml"));
    this.taskLoader.setController(controller);
  }

  /**
   * Loads a scene from a Week GUI layout.
   *
   * @return the layout
   */

  public Scene load() throws IllegalStateException {
    try {
      return this.taskLoader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
