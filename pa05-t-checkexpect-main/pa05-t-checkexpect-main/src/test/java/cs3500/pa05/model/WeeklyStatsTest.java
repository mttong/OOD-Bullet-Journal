package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the WeeklyStats class
 */
public class WeeklyStatsTest {

  WeeklyStats stats;

  Category none;
  Category school;
  Category work;
  Category fun;

  List<Category> categoryList;

  /**
   * Set up
   */
  @BeforeEach
  public void setUp() {
    categoryList = Settings.getInstance().getCategories();
    none = categoryList.get(0);
    school = categoryList.get(1);
    work = categoryList.get(2);
    fun = categoryList.get(3);

    Model model = new WeekdaysModel();
    Activity oodhw = new Task("ood hw", "very hard", DayOfWeek.MONDAY, school,
        CompletionStatus.IN_PROGRESS);
    Activity cyberhw = new Task("cyber hw", "not hard", DayOfWeek.FRIDAY, school,
        CompletionStatus.COMPLETED);
    Activity taGrading = new Task("TA grading", "grading homework 7", DayOfWeek.THURSDAY, work,
        CompletionStatus.NOT_STARTED);
    Activity applyToCoOp = new Task("Apply to Co-op", "apply to co-ops on NUWorks",
        DayOfWeek.MONDAY, fun, CompletionStatus.COMPLETED);
    Activity dinner = new Event("dinner", "dinner with susan", DayOfWeek.SUNDAY, fun,
        LocalTime.of(18, 00), LocalTime.of(19, 00));
    Activity taMeeting = new Event("TA meeting", "TA meeting at Kariotis 110", DayOfWeek.THURSDAY,
        work, LocalTime.of(17, 15), LocalTime.of(18, 00));

    model.addActivity(oodhw);
    model.addActivity(cyberhw);
    model.addActivity(taGrading);
    model.addActivity(dinner);
    model.addActivity(taMeeting);
    model.addActivity(applyToCoOp);

    this.stats = new WeeklyStats(model);
  }

  /**
   * test the event and task hashmap of category values
   */
  @Test
  void testCategoryValues() {
    Map<Category, Integer> expectedEvents = new HashMap<>();
    expectedEvents.put(fun, 1);
    expectedEvents.put(work, 1);
    expectedEvents.put(none, 0);
    expectedEvents.put(school, 0);

    Map<Category, Integer> actualEvents = this.stats.getEventCategoryValues();

    assertEquals(expectedEvents.size(), actualEvents.size());

    Map<Category, Integer> expectedTasks = new HashMap<>();
    expectedTasks.put(fun, 1);
    expectedTasks.put(school, 2);
    expectedTasks.put(work, 1);
    expectedTasks.put(none, 0);

    Map<Category, Integer> actualTasks = this.stats.getTaskCategoryValues();

    assertEquals(expectedTasks.size(), actualTasks.size());

  }

  /**
   * testing the total amount of events and tasks
   */
  @Test
  void testTotals() {
    assertEquals(2, this.stats.getTotalEvents());
    assertEquals(4, this.stats.getTotalTasks());
  }

  /**
   * testing the percentage tasks done
   */
  @Test
  void testGetPercent() {
    assertEquals(0.5, this.stats.getPercentDone());

    Model emptyModel = new WeekdaysModel();
    WeeklyStats emptyStats = new WeeklyStats(emptyModel);

    assertEquals(0.0, emptyStats.getPercentDone());
  }
}
