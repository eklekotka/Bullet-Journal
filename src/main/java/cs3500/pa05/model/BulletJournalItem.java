package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * to represent an item to appear on a bullet journal day
 */
public abstract class BulletJournalItem implements Item {
  protected String name;
  protected Day day;
  protected String category;
  protected String description;

  /**
   *
   * @param n name of the item
   * @param d day that the item will take place
   * @param c category of the item
   * @param desc description of the item
   */
  public BulletJournalItem(String n, Day d, String c, String desc) {
    this.name = n;
    this.day = d;
    this.category = c;
    this.description = desc;

  }

  /**
   *
   * @param n name of the item
   * @param d day that the item will take place
   * @param c category of the item
   */
  public BulletJournalItem(String n, Day d, String c) {
    this.name = n;
    this.day = d;
    this.category = c;
    this.description = "";

  }

  /**
   *
   * @return return name of the item
   */
  public String getName() {
    return this.name;
  }

  /**
   *
   * @return return the item and its information as an organized string
   */
  public abstract String getString();

  /**
   * determines if the category is the same as the given category
   *
   * @param otherCategory a String, representing the category
   * @return true if the string/categories match
   */
  public boolean sameCategory(String otherCategory) {
    return this.category.equals(otherCategory);
  }

  /**
   * changes item's completion status to true
   */
  public void markAsComplete() {
    throw new UnsupportedOperationException("Item is unable to perform this function");
  }

  /**
   *
   * @return serializes item to a json node
   */
  public abstract JsonNode itemToJson();

}

