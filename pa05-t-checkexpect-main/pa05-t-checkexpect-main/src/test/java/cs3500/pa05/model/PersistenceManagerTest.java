package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * a testing class for Persistence Manager
 */
public class PersistenceManagerTest {

  private static final String RESOURCES_DIR = "src/test/resources/";
  private static final String EMPTY = RESOURCES_DIR + "empty-";
  private static final String COMPREHENSIVE = RESOURCES_DIR + "comprehensive-";
  private static final String SETTING_ONLY = RESOURCES_DIR + "setting-only-";

  private WeekdaysModel model;

  /**
   * read from file helper
   *
   * @param f File
   * @return String
   */
  private String readFrom(File f) {
    StringBuilder builder = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(f));
      while (true) {
        String str = reader.readLine();
        if (str == null) {
          break;
        }
        builder.append(str).append("\n");
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    return builder.toString();
  }

  /**
   * set up settings and WeekdaysModel
   */
  @BeforeEach
  public void setUp() {
    Settings.reset();
    this.model = new WeekdaysModel();
  }

  /**
   * test read from
   */
  @Test
  public void testReadFrom() {
    PersistenceManager.loadDataFrom(new File(SETTING_ONLY + "expected.bujo"), this.model);
    assertEquals(Settings.getInstance().getName(), "Ben Lerner");
    assertEquals(Settings.getInstance().getEmail(), "blerner@northeastern.edu");
    assertEquals(Settings.getInstance().getEventMax(), 2);
    assertEquals(Settings.getInstance().getTaskMax(), 2);
    Map<DayOfWeek, List<Activity>> activities = this.model.getActivities(null);
    for (DayOfWeek week : Settings.getInstance().getDaysOfWeek()) {
      assertEquals(activities.get(week).size(), 0);
    }
    Settings.reset();
    this.model = new WeekdaysModel();
    PersistenceManager.loadDataFrom(new File(COMPREHENSIVE + "expected.bujo"), this.model);
    assertEquals(Settings.getInstance().getName(), "Ben Lerner");
    assertEquals(Settings.getInstance().getEmail(), "blerner@northeastern.edu");
    assertEquals(Settings.getInstance().getEventMax(), 2);
    assertEquals(Settings.getInstance().getTaskMax(), 2);
    activities = this.model.getActivities(null);
    List<Integer> sizes = List.of(0, 0, 0, 1, 0, 0, 1);
    int index = 0;
    for (DayOfWeek week : Settings.getInstance().getDaysOfWeek()) {
      assertEquals(activities.get(week).size(), sizes.get(index++));
    }
    Settings.reset();
    this.model = new WeekdaysModel();
    PersistenceManager.loadDataFrom(new File(EMPTY + "expected.bujo"), this.model);
    assertNull(Settings.getInstance().getName());
    assertNull(Settings.getInstance().getEmail());
    assertEquals(Settings.getInstance().getEventMax(), 0);
    assertEquals(Settings.getInstance().getTaskMax(), 0);
  }

  /**
   * test write to
   */
  @Test
  public void testWriteTo() {
    Settings.getInstance().setName("Ben Lerner");
    Settings.getInstance().setEmail("blerner@northeastern.edu");
    Settings.getInstance().setEventMax(2);
    Settings.getInstance().setTaskMax(2);
    Settings.getInstance().setLocalDate(LocalDate.of(2001, 9, 11));
    PersistenceManager.saveDataTo(new File(SETTING_ONLY + "actual.bujo"), this.model);
    assertEquals(this.readFrom(new File(SETTING_ONLY + "actual.bujo")),
        this.readFrom(new File(SETTING_ONLY + "expected.bujo")));

    this.model.addActivity(new Task("laundry", "do laundry is important", DayOfWeek.MONDAY,
        new Category("chores", Color.WHITE), CompletionStatus.NOT_STARTED));
    this.model.addActivity(
      new Event("date night", "fun", DayOfWeek.FRIDAY, new Category("personal", Color.WHITE),
        LocalTime.of(20, 30), LocalTime.of(23, 30)));
    PersistenceManager.saveDataTo(new File(COMPREHENSIVE + "actual.bujo"), this.model);
    assertEquals(this.readFrom(new File(COMPREHENSIVE + "actual.bujo")),
        this.readFrom(new File(COMPREHENSIVE + "expected.bujo")));

    assertDoesNotThrow(() -> {
      // try...catch should take care of the exception, so nothing throws
      PersistenceManager.saveDataTo(new File("/root/hello.bujo"), this.model);
    });
  }
}
