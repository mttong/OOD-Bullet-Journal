package cs3500.pa05.model;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

/**
 * represents a model interface
 */
public interface Model {

  /**
   * add an activity
   *
   * @param activity Activity
   */
  void addActivity(Activity activity);

  /**
   * remove an activity
   *
   * @param activity Activity
   */
  void removeActivity(Activity activity);

  /**
   * get a hashmap of activities
   *
   * @param category Category
   * @return Hashmap of each day of the week and the activities associated
   */
  Map<DayOfWeek, List<Activity>> getActivities(Category category);

  /**
   * get the task queue
   *
   * @param category Category
   * @return list of tasks
   */
  List<Task> getTaskQueue(Category category);


  /**
   * iterate through the current activities and see if user exceed any limit
   *
   * @return weekdays that exceeds the commitment working.
   */
  List<DayOfWeek> shouldDisplayCommitmentWarning();

}
