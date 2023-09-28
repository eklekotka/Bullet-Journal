package cs3500.pa05.controller;

import java.io.IOException;
import java.util.ArrayList;

/**
 * controller interface for mvc format
 */
public interface Controller {

  /**
   * Initializes the BulletJournal
   *
   * @throws IllegalStateException if the game board is not defined
   */
  void run() throws IllegalStateException, IOException;


}
