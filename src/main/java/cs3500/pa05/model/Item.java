package cs3500.pa05.model;

/**
 * to represent an item in pa05
 */
public interface Item {

  /**
   *
   * @return name of the item
   */
  String getName();

  /**
   *
   * @return formatted string of this item
   */
  String getString();

  /**
   *
   * @return if this item has the sameCategory as a
   */
  boolean  sameCategory(String otherCategory);


  /**
   * mark an item as completed
   */
  void markAsComplete();


}
