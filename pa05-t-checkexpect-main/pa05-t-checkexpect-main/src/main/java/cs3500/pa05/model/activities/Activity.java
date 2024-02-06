package cs3500.pa05.model.activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import java.time.DayOfWeek;

/**
 * Represents an activity: Event or Task
 */
public abstract class Activity implements Comparable<Activity> {

  //fields
  protected String name;
  protected String description;
  protected DayOfWeek weekday;
  protected Category category;

  /**
   * Constructor
   *
   * @param name        name of the activity
   * @param description a short description of the activity
   * @param weekday     weekday the activity belongs
   * @param category    category the activity belongs
   */
  public Activity(String name, String description, DayOfWeek weekday, Category category) {
    this.name = name;
    this.description = description;
    this.weekday = weekday;
    this.category = category;
  }

  /**
   * Default constructor
   */
  public Activity() {
    this.name = null;
    this.description = null;
    this.weekday = null;
    this.category = null;
  }

  /**
   * get the priority of the activity
   *
   * @return priority in int
   */
  public abstract int getPriority();

  /**
   * getter for weekday
   *
   * @return weekday
   */
  public DayOfWeek getWeekday() {
    return this.weekday;
  }

  /**
   * Sets the weekday field
   *
   * @param weekday given description to set to
   */
  public void setWeekday(DayOfWeek weekday) {
    this.weekday = weekday;
  }

  /**
   * getter for name
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name field
   *
   * @param name given name to set to
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * getter for description
   *
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description field
   *
   * @param description given description to set to
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * getter for category
   *
   * @return category
   */
  public Category getCategory() {
    return category;
  }

  /**
   * Sets the category field
   *
   * @param category given category to set to
   */
  public void setCategory(Category category) {
    this.category = category;
  }

  /**
   * getter for type
   *
   * @return type
   */
  public abstract ActivityType getType();

  /**
   * compare the current activity to given activity based on priority
   *
   * @param that the object to be compared.
   * @return difference
   */
  @Override
  public int compareTo(Activity that) {
    return this.getPriority() - that.getPriority();
  }

}
