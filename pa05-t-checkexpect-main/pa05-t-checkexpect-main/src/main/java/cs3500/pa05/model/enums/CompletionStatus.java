package cs3500.pa05.model.enums;

/**
 * represents an enumeration of task completion status
 */
public enum CompletionStatus {
  NOT_STARTED(0, "Not Started"), IN_PROGRESS(10, "In Progress"), COMPLETED(20, "Completed");

  private final int priority;
  private final String name;

  /**
   * default constructor for completion status
   *
   * @param priority the priority in integer
   */
  CompletionStatus(int priority, String name) {

    this.priority = priority;
    this.name = name;
  }

  /**
   * getter for priority
   *
   * @return priority
   */
  public int getPriority() {
    return this.priority;
  }

  /**
   * Gets the name of this CompletionStatus enum
   *
   * @return String - name
   */
  public String getName() {
    return this.name;
  }
}
