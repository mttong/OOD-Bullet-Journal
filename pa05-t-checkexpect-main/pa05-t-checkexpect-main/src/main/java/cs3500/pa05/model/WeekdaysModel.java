package cs3500.pa05.model;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * represents a weekday model class for bullet journal - Holds all important information
 */
public class WeekdaysModel implements Model {

  //fields
  private final Map<DayOfWeek, List<Activity>> activities;

  /**
   * Constructor that initialize an empty map of activities
   */
  public WeekdaysModel() {
    this.activities = new HashMap<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      this.activities.put(day, new ArrayList<>());
    }
  }

  /**
   * add an activity to a specific week
   *
   * @param activity activity to add
   */
  public void addActivity(Activity activity) {
    this.removeIfExist(activity);
    this.activities.get(activity.getWeekday()).add(activity);
  }

  /**
   * Removes the given acitivity from the hashmap
   *
   * @param activity Task or Event Acitivity
   */
  public void removeActivity(Activity activity) {
    //get the week day of the activity
    DayOfWeek day = activity.getWeekday();
    Activity toRemove = null;
    List<Activity> dayActivities = activities.get(day);
    //find the activity to remove
    for (Activity dayActivity : dayActivities) {
      if (dayActivity.equals(activity)) {
        toRemove = dayActivity;
      }
    }
    dayActivities.remove(toRemove);
  }

  /**
   * private method to remove an activity if it exists
   *
   * @param activity activity instance
   */
  private void removeIfExist(Activity activity) {
    //loops through each day and each list of activities for each day
    for (DayOfWeek weekday : DayOfWeek.values()) {
      List<Activity> items = this.activities.get(weekday);
      for (int i = 0; i < items.size(); i++) {
        if (activity == items.get(i)) {
          items.remove(activity);
          return;
        }
      }
    }
  }

  /**
   * get all activities of a specific category
   *
   * @param category category to filter, or null
   * @return activities
   */
  public Map<DayOfWeek, List<Activity>> getActivities(Category category) {
    if (category == null) {
      return this.activities;
    }
    Map<DayOfWeek, List<Activity>> ret = new HashMap<>();
    for (DayOfWeek weekday : DayOfWeek.values()) {
      ret.put(weekday, this.activities.get(weekday).stream()
          .filter(element -> element.getCategory().equals(category)).toList());
    }
    return ret;
  }

  /**
   * get a queue of all tasks of a specific category, ranked by priority
   *
   * @param category category to filter, or null
   * @return a queue of tasks
   */
  public List<Task> getTaskQueue(Category category) {
    List<Task> ret = new ArrayList<>();
    for (List<Activity> dayActivities : this.activities.values()) {
      for (Activity activity : dayActivities) {
        if (activity.getType() == ActivityType.TASK) {
          ret.add(
              (Task) activity); //casting because we are checking to ensure it is a task beforehand
        }
      }
    }
    Collections.sort(ret);
    if (category == null) {
      return ret;
    }
    return ret.stream().filter(element -> element.getCategory().equals(category)).toList();
  }

  /**
   * iterate through the current activities and see if user exceed any limit
   *
   * @return weekdays that exceeds the commitment working.
   */
  public List<DayOfWeek> shouldDisplayCommitmentWarning() {
    List<DayOfWeek> ret = new ArrayList<>();
    int maxTask = Settings.getInstance().getTaskMax();
    int maxEvent = Settings.getInstance().getEventMax();
    for (DayOfWeek weekday : DayOfWeek.values()) {
      int curTask = 0;
      int curEvent = 0;
      for (Activity activity : this.activities.get(weekday)) {
        if (activity.getType() == ActivityType.EVENT) {
          curEvent += 1;
        } else {
          curTask += 1;
        }
      }
      if (curTask > maxTask || curEvent > maxEvent) {
        ret.add(weekday);
      }
    }
    return ret;
  }
}
