package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents a testing class for WeekdaysModel
 */
public class WeekdayModelTest {

  private WeekdaysModel model;

  private Task task1;
  private Task task2;
  private Task task3;
  private Task task4;
  private Event event1;

  /**
   * helper methods to set up fields before each test
   */
  @BeforeEach
  public void setUp() {
    this.model = new WeekdaysModel();
    this.task1 = new Task("shower", "very important", DayOfWeek.FRIDAY, new Category("lol", null),
      CompletionStatus.NOT_STARTED);
    this.task2 = new Task("sleep", "sleeping now", DayOfWeek.FRIDAY, new Category("haha", null),
      CompletionStatus.IN_PROGRESS);
    this.task3 = new Task("essay", "don't wanna write", DayOfWeek.MONDAY,
        new Category("haha", null), CompletionStatus.COMPLETED);
    this.task4 = new Task("prep exam", "no", DayOfWeek.MONDAY, new Category("haha", null),
      CompletionStatus.IN_PROGRESS);
    this.event1 = new Event("date night", "yeah", DayOfWeek.TUESDAY, new Category("haha", null),
      LocalTime.of(20, 0), LocalTime.of(22, 0));
    Event event2 = new Event("IRS audit", "crap", DayOfWeek.SATURDAY, new Category("haha", null),
        LocalTime.of(8, 0), LocalTime.of(8, 30));
    this.model.addActivity(this.task1);
    this.model.addActivity(this.task2);
    this.model.addActivity(this.task3);
    this.model.addActivity(this.task4);
    this.model.addActivity(this.event1);
    this.model.addActivity(event2);
    Settings.reset();
  }

  /**
   * testing getTaskQueue
   */
  @Test
  public void testGetTaskQueue() {
    List<Task> items = this.model.getTaskQueue(null);
    assertEquals(items.size(), 4);
    List<Task> ranked = List.of(this.task1, this.task4, this.task2, this.task3);
    for (int i = 0; i < items.size(); i++) {
      assertEquals(items.get(i), ranked.get(i));
    }

    items = this.model.getTaskQueue(new Category("non-existent", Color.WHITE));
    assertEquals(items.size(), 0);
    items = this.model.getTaskQueue(new Category("lol", Color.WHITE));
    assertEquals(items.size(), 1);
    assertEquals(items.get(0), this.task1);
  }

  /**
   * testing add activities
   */
  @Test
  public void testAddActivities() {
    int size = this.model.getActivities(null).get(DayOfWeek.FRIDAY).size();
    this.model.addActivity(this.task1);
    assertEquals(this.model.getActivities(null).get(DayOfWeek.FRIDAY).size(), size);
    size = this.model.getActivities(null).get(DayOfWeek.TUESDAY).size();
    this.model.addActivity(this.event1);
    assertEquals(this.model.getActivities(null).get(DayOfWeek.TUESDAY).size(), size);
    size = this.model.getActivities(null).get(DayOfWeek.MONDAY).size();

    Task task = new Task("lol", "", DayOfWeek.MONDAY, null, null);
    this.model.addActivity(task);
    assertEquals(this.model.getActivities(null).get(DayOfWeek.MONDAY).size(), size + 1);

    this.model.removeActivity(task);
    assertEquals(this.model.getActivities(null).get(DayOfWeek.MONDAY).size(), size);
  }

  /**
   * testing getActivities method
   */
  @Test
  public void testGetActivities() {
    Map<DayOfWeek, List<Activity>> items = this.model.getActivities(null);
    assertEquals(items.get(DayOfWeek.MONDAY).size(), 2);
    assertEquals(items.get(DayOfWeek.TUESDAY).size(), 1);
    assertEquals(items.get(DayOfWeek.WEDNESDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.THURSDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.FRIDAY).size(), 2);
    assertEquals(items.get(DayOfWeek.SATURDAY).size(), 1);
    assertEquals(items.get(DayOfWeek.SUNDAY).size(), 0);
    items = this.model.getActivities(new Category("lol", Color.WHITE));
    assertEquals(items.get(DayOfWeek.MONDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.TUESDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.WEDNESDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.THURSDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.FRIDAY).size(), 1);
    assertEquals(items.get(DayOfWeek.SATURDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.SUNDAY).size(), 0);
    items = this.model.getActivities(new Category("non-existent", Color.WHITE));
    assertEquals(items.get(DayOfWeek.MONDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.TUESDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.WEDNESDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.THURSDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.FRIDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.SATURDAY).size(), 0);
    assertEquals(items.get(DayOfWeek.SUNDAY).size(), 0);
  }

  /**
   * testing shouldDisplayCommitmentWarning method
   */
  @Test
  public void testShouldDisplayCommitmentWarning() {
    assertEquals(this.model.shouldDisplayCommitmentWarning().size(), 4);
    Settings.getInstance().setEventMax(10);
    assertEquals(this.model.shouldDisplayCommitmentWarning().size(), 2);
    Settings.getInstance().setTaskMax(10);
    Settings.getInstance().setEventMax(0);
    assertEquals(this.model.shouldDisplayCommitmentWarning().size(), 2);
    Settings.getInstance().setEventMax(10);
    assertEquals(this.model.shouldDisplayCommitmentWarning().size(), 0);
  }
}