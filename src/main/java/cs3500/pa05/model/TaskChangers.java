package cs3500.pa05.model;

/**
 * represents an interface for changing an aspect of a week
 **/
public interface TaskChangers {

  /**
   * adds a task to the list of tasks to do
   * */
  void addToDoTask(BulletJournalItem item);

  /**
   * adds an event to the list of events to do
   * */
  void addToDoEvent(BulletJournalItem item);


  /**
   * removes a task or event from the list of tasks or events
   * when the user wants to delete a task
   * */
  void removeToDo(BulletJournalItem item);
}
