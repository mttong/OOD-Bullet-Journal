package cs3500.pa05.model.activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import java.time.DayOfWeek;

/**
 * Represents a Task
 */
public class Task extends Activity {

  //fields
  private CompletionStatus status;

  /**
   * default constructor for a task
   *
   * @param name        name of the task
   * @param description description of the task
   * @param weekday     weekday of the task
   * @param category    category of the task
   * @param status      task status
   */
  public Task(String name, String description, DayOfWeek weekday, Category category,
      CompletionStatus status) {
    super(name, description, weekday, category);
    this.status = status;
  }

  /**
   * Default constructor
   */
  public Task() {
    super();
    this.status = null;
  }

  /**
   * get the priority of the activity
   *
   * @return priority in int
   */
  @Override
  public int getPriority() {
    return this.status.getPriority() + this.weekday.ordinal();
  }

  /**
   * Gets the completion status of this task
   *
   * @return completion status
   */
  public CompletionStatus getStatus() {
    return status;
  }

  /**
   * Sets the completion status of this task
   *
   * @param status given completion status to set to
   */
  public void setStatus(CompletionStatus status) {
    this.status = status;
  }

  /**
   * getter for type
   *
   * @return type
   */
  @Override
  public ActivityType getType() {
    return ActivityType.TASK;
  }
}
