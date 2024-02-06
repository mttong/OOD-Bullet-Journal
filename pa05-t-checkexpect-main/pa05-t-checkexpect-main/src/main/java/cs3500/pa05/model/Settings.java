package cs3500.pa05.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * represents a singleton class of Settings there should be one and only one instance of Settings
 * for the entire project.
 */
public class Settings {

  private static Settings instance;
  private final List<Category> categories;
  //fields
  private String name;
  private String email;
  private int eventMax;
  private int taskMax;
  private String dateString;
  private LocalDate localDate;
  private DayOfWeek startDay;

  /**
   * Constructor
   */
  private Settings() {
    this.categories = new ArrayList<>();
    this.categories.add(new Category("None", Color.WHITE));
    this.categories.add(new Category("School", Color.WHITE));
    this.categories.add(new Category("Work", Color.WHITE));
    this.categories.add(new Category("Fun", Color.WHITE));


    this.startDay = DayOfWeek.SUNDAY; //default
  }

  /**
   * getter for the only instance of Settings
   *
   * @return instance of Settings
   */
  public static Settings getInstance() {
    if (instance == null) {
      instance = new Settings();
    }
    return instance;
  }

  /**
   * resetting the singleton instance
   */
  public static void reset() {
    instance = new Settings();
  }

  /**
   * Gets all the days of the week
   *
   * @return list of the days of the week
   */
  public List<DayOfWeek> getDaysOfWeek() {
    List<DayOfWeek> daysOfWeek = new ArrayList<>();

    for (int i = 0; i < DayOfWeek.values().length; i++) {
      DayOfWeek day = this.startDay.plus(i);
      daysOfWeek.add(day);
    }
    return daysOfWeek;
  }

  /**
   * Gets the date string for the week
   *
   * @return String
   */
  public String getDateString() {
    return dateString;
  }

  /**
   * getter for name
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * setter for name
   *
   * @param name name of user
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * getter for email
   *
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * setter for email
   *
   * @param email email of user
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * getter for max event
   *
   * @return max event
   */
  public int getEventMax() {
    return eventMax;
  }

  /**
   * setter for maximum event
   *
   * @param eventMax max number of events
   */
  public void setEventMax(int eventMax) {
    this.eventMax = eventMax;
  }

  /**
   * getter for max task
   *
   * @return max task
   */
  public int getTaskMax() {
    return taskMax;
  }

  /**
   * setter for maximum task
   *
   * @param taskMax max number of tasks
   */
  public void setTaskMax(int taskMax) {
    this.taskMax = taskMax;
  }

  /**
   * getter for list of categories
   *
   * @return list of categories
   */
  public List<Category> getCategories() {
    return this.categories;
  }

  /**
   * Gets the local date
   *
   * @return LocateDate - date
   */
  public LocalDate getLocalDate() {
    return this.localDate;
  }

  /**
   * Sets the local date of the settings
   *
   * @param localDate given local date to set to
   */
  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    this.dateString = this.localDate.format(formatter);
    this.startDay = this.localDate.getDayOfWeek();
  }
}
