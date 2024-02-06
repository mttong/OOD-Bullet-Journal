package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Task class test
 */
public class TaskTest {

  Task task;
  Task emptyTask;

  /**
   * setting up a task
   */
  @BeforeEach
  void setUp() {
    task = new Task("testing", "testing pa05", DayOfWeek.THURSDAY, new Category("CS", Color.BLUE),
      CompletionStatus.IN_PROGRESS);

    emptyTask = new Task();
  }

  /**
   * testings task methods, and others
   */
  @Test
  void testTask() {
    assertEquals(task.getPriority(), 13);

    assertEquals(task.getStatus(), CompletionStatus.IN_PROGRESS);
    task.setStatus(CompletionStatus.COMPLETED);
    assertEquals(task.getStatus(), CompletionStatus.COMPLETED);

    assertEquals(task.getType(), ActivityType.TASK);
    assertEquals(emptyTask.getType(), ActivityType.TASK);

    assertEquals(task.getWeekday(), DayOfWeek.THURSDAY);
    task.setWeekday(DayOfWeek.MONDAY);
    assertEquals(task.getWeekday(), DayOfWeek.MONDAY);

    assertEquals(task.getName(), "testing");
    task.setName("hello");
    assertEquals(task.getName(), "hello");

    task.setDescription("hi");
    assertEquals(task.getDescription(), "hi");

    Category random = new Category("random", Color.ALICEBLUE);
    task.setCategory(random);
    assertEquals(task.getCategory(), random);

    assertEquals(random.getColor(), Color.ALICEBLUE);

    Category hi = new Category("hi", Color.BLUE);
    assertFalse(random.equals(task));
    assertFalse(random.equals(hi));

    Settings.getInstance().setLocalDate(LocalDate.now());
    assertEquals(Settings.getInstance().getLocalDate(), LocalDate.now());

    assertEquals(CompletionStatus.COMPLETED.getName(), "Completed");
  }
}
