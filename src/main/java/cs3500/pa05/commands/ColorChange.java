package cs3500.pa05.commands;

import javafx.scene.Scene;

/**
 * represents the method that changes the theme for the main scene
 */
public class ColorChange {

  /**
   * represents the constructor
   */
  public ColorChange() {
  }


  /**
   * changes the given scene to the styleSheet passed in
   *
   * @param scene the scene to transform
   * @param css   the stylesheet to apply to the scene
   */
  public void changeTheme(Scene scene, String css) {
    switch (css) {
      case "default.css" -> {
        defaultCase(scene);
      }
      case "backgroundBlue.css" -> {
        blueCase(scene);
      }
      case "EmmaJournal.css" -> {
        emmaCase(scene);
      }
      case "katie.css" -> {
        katieCase(scene);
      }
      default -> scene.getStylesheets().add("default.css");
    }
  }


  /**
   * handles the default theme setting by loading in all default styleSheets
   *
   * @param scene the scene to transform
   */
  private void defaultCase(Scene scene) {
    scene.getStylesheets().clear();
    scene.getStylesheets().add("default.css");
    scene.getStylesheets().add("defaultEventCreator.css");
    scene.getStylesheets().add("defaultEventViewer.css");
    scene.getStylesheets().add("defaultTaskCreator.css");
    scene.getStylesheets().add("defaultTaskViewer.css");
  }

  /**
   * transforms the style of the planner to the blue theme
   * by loading the blue css styleSheets
   *
   * @param scene the scene to be altered
   */
  private void blueCase(Scene scene) {
    scene.getStylesheets().clear();
    scene.getStylesheets().add("backgroundBlue.css");
    scene.getStylesheets().add("blueEventCreator.css");
    scene.getStylesheets().add("blueEventViewer.css");
    scene.getStylesheets().add("blueTaskCreator.css");
    scene.getStylesheets().add("blueTaskViewer.css");
  }

  /**
   * transforms the planner to the Emma theme by loading the Emma styleSheets in
   *
   * @param scene the scene being transformed
   */
  private void emmaCase(Scene scene) {
    scene.getStylesheets().clear();
    scene.getStylesheets().add("EmmaJournal.css");
    scene.getStylesheets().add("EmmaEventCreator.css");
    scene.getStylesheets().add("EmmaTaskCreator.css");
    scene.getStylesheets().add("EmmaTaskViewer.css");
    scene.getStylesheets().add("EmmaEventViewer.css");
  }

  /**
   * transforms the theme to the Katie theme
   *
   * @param scene the scene to be transformed
   */
  private void katieCase(Scene scene) {
    scene.getStylesheets().clear();
    scene.getStylesheets().add("katie.css");
    scene.getStylesheets().add("katieEventCreator.css");
    scene.getStylesheets().add("katieEventViewer.css");
    scene.getStylesheets().add("katieTaskCreator.css");
  }
}
