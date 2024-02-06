package cs3500.pa05.model.activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Represents an event
 */
public class Event extends Activity {

  //fields
  private LocalTime startTime;
  private LocalTime endTime;


  /**
   * Constructor
   *
   * @param name        name of the event
   * @param description description of the event
   * @param weekday     weekday of the event
   * @param category    category of the event
   * @param startTime   event start time
   * @param endTime     event end time
   */
  public Event(String name, String description, DayOfWeek weekday, Category category,
      LocalTime startTime, LocalTime endTime) {
    super(name, description, weekday, category);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Default constructor
   */
  public Event() {
    super();
    this.startTime = null;
    this.endTime = null;
  }

  /**
   * get the priority of the activity
   *
   * @return priority in int
   */
  @Override
  public int getPriority() {
    return Integer.MAX_VALUE; // event has no priority
  }

  /**
   * Gets the start time of this event
   *
   * @return LocalTime startime
   */
  public LocalTime getStartTime() {
    return this.startTime;
  }

  /**
   * Sets the start time of this event to the given start time
   *
   * @param startTime given start time
   */
  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  /**
   * Gets the end time of this event
   *
   * @return LocalTime end time
   */
  public LocalTime getEndTime() {
    return this.endTime;
  }

  /**
   * Sets the end time of this event to the given end time
   *
   * @param endTime given end time
   */
  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  /**
   * getter for type
   *
   * @return type
   */
  @Override
  public ActivityType getType() {
    return ActivityType.EVENT;
  }

  /**
   * to string to testing purposes
   *
   * @return string representing an event
   */
  @Override
  public String toString() {
    return "Name: " + this.name + "\n" + "Category" + this.category.getName() + "\n"
      //+ "Weekday:" + this.weekday.getRepresentation() + "\n"
      + "Description:" + this.description + "\n" + "Start Time" + this.startTime.toString() + "\n"
      + "End Time" + this.endTime.toString();
  }
}
