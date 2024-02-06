package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.enums.ActivityType;
import java.time.DayOfWeek;
import java.time.LocalTime;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Event class tests
 */
public class EventTest {

  Event event;
  Event empty;

  /**
   * Before each, event set up
   */
  @BeforeEach
  void setUp() {
    event = new Event("dinner", "dinner with susan", DayOfWeek.THURSDAY,
        new Category("Networking", Color.BLUE), LocalTime.of(18, 00), LocalTime.of(19, 00));

    empty = new Event();
  }

  /**
   * test priority of event
   */
  @Test
  void testGetPriority() {
    assertEquals(event.getPriority(), Integer.MAX_VALUE);
    assertEquals(empty.getPriority(), Integer.MAX_VALUE);
  }

  /**
   * test event time
   */
  @Test
  void testGetTime() {
    assertEquals(event.getStartTime(), LocalTime.of(18, 00));
    assertNull(empty.getStartTime());

    assertEquals(event.getEndTime(), LocalTime.of(19, 00));
    assertNull(empty.getEndTime());
  }

  /**
   * set event time
   */
  @Test
  void testSetTime() {
    empty.setStartTime(LocalTime.of(17, 00));
    assertEquals(empty.getStartTime(), LocalTime.of(17, 00));

    empty.setEndTime(LocalTime.of(19, 00));
    assertEquals(empty.getEndTime(), LocalTime.of(19, 00));
  }

  /**
   * get event type
   */
  @Test
  void testGetType() {
    assertEquals(empty.getType(), ActivityType.EVENT);
    assertEquals(event.getType(), ActivityType.EVENT);
  }

  /**
   * test event to string
   */
  @Test
  void testToString() {
    assertEquals("""
      Name: dinner
      CategoryNetworking
      Description:dinner with susan
      Start Time18:00
      End Time19:00""", event.toString());


  }
}
