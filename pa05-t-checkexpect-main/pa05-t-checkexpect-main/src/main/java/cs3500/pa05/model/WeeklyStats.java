package cs3500.pa05.model;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents a statistic class for Journal
 */
public class WeeklyStats {
  //fields

  private final List<Category> categories;

  private final List<Task> tasks;

  private final Model weeklyModel;
  int totalTasks;
  int tasksCompleted;
  int tasksInProgress;
  int totalEvents;
  Map<Category, Integer> eventCategoryValues;
  Map<Category, Integer> taskCategoryValues;

  /**
   * Constructor
   *
   * @param weeklyModel the program's model
   */
  public WeeklyStats(Model weeklyModel) {
    //init variables
    this.weeklyModel = weeklyModel;
    this.categories = Settings.getInstance().getCategories();

    this.eventCategoryValues = categoryValues(ActivityType.EVENT);
    this.taskCategoryValues = categoryValues(ActivityType.TASK);

    this.totalTasks = setTotal(taskCategoryValues);
    this.totalEvents = setTotal(eventCategoryValues);

    this.tasks = weeklyModel.getTaskQueue(null);
    this.tasksCompleted = taskNumbers(CompletionStatus.COMPLETED);
    this.tasksInProgress = taskNumbers(CompletionStatus.IN_PROGRESS);
  }

  /**
   * gets the totol number of keys in the given hashmap
   *
   * @param map given hashmap
   * @return int number of keys in the given map
   */
  private int setTotal(Map<Category, Integer> map) {
    int total = 0;
    for (Integer value : map.values()) {
      total += value;
    }
    return total;
  }

  /**
   * Creates a hashmap of category values to number of instances that they occur
   *
   * @param type an activity - event or task
   * @return hashmap of all categories in the given activity
   */
  private Map<Category, Integer> categoryValues(ActivityType type) {
    Map<Category, Integer> ret = new HashMap<>();
    for (Category category : categories) {
      int categoryTotal = 0;
      for (List<Activity> categoryActivities : weeklyModel.getActivities(category).values()) {
        for (Activity activity : categoryActivities) {
          if (activity.getType() == type) {
            categoryTotal++;
          }
        }
      }
      ret.put(category, categoryTotal);
    }
    return ret;
  }

  /**
   * Checks how many tasks in the list have the given status
   *
   * @param status completionStatus
   * @return int - number of tasks that have the given completion status
   */
  private int taskNumbers(CompletionStatus status) {
    int value = 0;
    for (Task task : tasks) {
      if (task.getStatus() == status) {
        value++;
      }
    }
    return value;
  }

  /**
   * Gets the precentage of tasks completed
   *
   * @return percentage of tasks completed
   */
  public double getPercentDone() {
    if (totalTasks == 0) {
      return 0.0;
    } else {
      return ((double) tasksCompleted / (double) totalTasks);
    }

  }

  /**
   * Gets the event categories mapped to how many of each are present in the journal
   *
   * @return Hashmap of all the event categories with # of instances
   */
  public Map<Category, Integer> getEventCategoryValues() {
    return eventCategoryValues;
  }

  /**
   * Gets the task categories mapped to how many of each are present in the journal
   *
   * @return Hashmap of all the task categories with # of instances
   */
  public Map<Category, Integer> getTaskCategoryValues() {
    return taskCategoryValues;
  }

  /**
   * Gets the total events currently in the journal
   *
   * @return int event tasks
   */
  public int getTotalEvents() {
    return totalEvents;
  }

  /**
   * Gets the total tasks currently in the journal
   *
   * @return int total tasks
   */
  public int getTotalTasks() {
    return totalTasks;
  }
}
