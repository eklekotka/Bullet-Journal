package cs3500.pa05.controller;

/**
 * controls the different popup windows
 */
public interface PopupController extends Controller {


  /**
   * displays the mini-viewer for an event
   * */
  void handle();

  /**
   * Initializes the popup methods
   *
   * @throws IllegalStateException if the game board is not defined
   */
  void run();


}
