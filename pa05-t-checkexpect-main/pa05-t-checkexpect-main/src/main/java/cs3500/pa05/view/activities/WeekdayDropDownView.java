package cs3500.pa05.view.activities;

import java.time.DayOfWeek;
import javafx.scene.control.ComboBox;


/**
 * Represents the Weekday drop down when adding an event / task
 */
public class WeekdayDropDownView extends ComboBox {

  /**
   * Constructor
   */
  public WeekdayDropDownView() {
    this.getItems()
        .addAll(DayOfWeek.SUNDAY.name(), DayOfWeek.MONDAY.name(), DayOfWeek.TUESDAY.name(),
        DayOfWeek.WEDNESDAY.name(), DayOfWeek.THURSDAY.name(), DayOfWeek.FRIDAY.name(),
        DayOfWeek.SATURDAY.name());
  }

  /**
   * Get the user selected day
   *
   * @return user's selected day
   */
  public DayOfWeek getSelectedWeekDay() {
    //ensure valid choice (not null)
    if (!validateAnswer()) {
      return null;
    }
    return DayOfWeek.valueOf(this.getValue().toString().toUpperCase());
  }

  /**
   * checks if the drop down value is not null (valid)
   *
   * @return true if value, false is not valid
   */
  private boolean validateAnswer() {
    return this.getValue() != null;
  }

  /**
   * Set the default starting value of the drop down to the given day
   *
   * @param day given day to set default to
   */
  public void setDefault(DayOfWeek day) {
    this.getSelectionModel().select(day.name());
  }
}
