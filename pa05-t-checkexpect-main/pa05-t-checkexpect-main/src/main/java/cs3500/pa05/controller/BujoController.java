package cs3500.pa05.controller;

import cs3500.pa05.Utils;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.Model;
import cs3500.pa05.model.PersistenceManager;
import cs3500.pa05.model.Settings;
import cs3500.pa05.model.WeeklyStats;
import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.MiniViewer;
import cs3500.pa05.view.SettingsView;
import cs3500.pa05.view.WeeklyStatsView;
import cs3500.pa05.view.activities.ActivitiesButtons;
import cs3500.pa05.view.activities.ActivitySelectionView;
import cs3500.pa05.view.delegates.FormDelegate;
import cs3500.pa05.view.delegates.TableViewDelegate;
import cs3500.pa05.view.tables.TableView;
import java.io.File;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * Represents the controller for the Bullet Journal Scene
 */
public class BujoController implements Controller, TableViewDelegate, FormDelegate {

  //fields
  private final Stage mainStage;
  private final Model model;
  private final Map<DayOfWeek, List<Activity>> activities;
  private final Category filterCategory = null;
  private final TableView weekendView;
  private final TableView taskQueueView;
  private List<Task> taskQueue;

  /**
   * Constructor
   *
   * @param mainStage     main stage of the program
   * @param model         model holding data
   * @param weekendView   the view of all the weekdays
   * @param taskQueueView the view of the task view
   * @param activities    the buttons for event and task
   * @param settings      settings button
   * @param eventStats    button for event stats
   * @param taskStats     button for taskStats
   * @param save          save button
   */
  public BujoController(Stage mainStage, Model model, TableView weekendView,
      TableView taskQueueView, ActivitiesButtons activities, Button settings, Button eventStats,
      Button taskStats, Button save) {

    //assigne fields
    this.mainStage = mainStage;
    this.model = model;
    this.activities = this.model.getActivities(this.filterCategory);
    this.taskQueue = this.model.getTaskQueue(this.filterCategory);
    this.weekendView = weekendView;
    this.weekendView.setDelegate(this);
    this.taskQueueView = taskQueueView;
    this.taskQueueView.setDelegate(this);

    //call event handlers
    handleActivities(activities);
    handleSettings(settings);
    handleEventStats(eventStats);
    handleTaskStats(taskStats);
    handleSave(save);
  }

  /**
   * reload view of entire bullet journal
   */
  public void reloadAllView() {
    this.taskQueue = this.model.getTaskQueue(this.filterCategory);
    this.weekendView.reloadAll();
    this.taskQueueView.reloadAll();
  }

  /**
   * Sets up the event handlers for the event and task buttons
   *
   * @param activities event and task buttons wrapped in a singular class
   */
  private void handleActivities(ActivitiesButtons activities) {
    //handle pop up for pushing the buttons creating new activities
    //event button event handling
    activities.setOnActionEventButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s,
        new ActivitySelectionView(ActivityType.EVENT, Settings.getInstance().getCategories(), this,
          s), "New Event");
    });
    //task button event handling
    activities.setOnActionTaskButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s,
        new ActivitySelectionView(ActivityType.TASK, Settings.getInstance().getCategories(), this,
          s), "New Task");
    });
  }

  /**
   * Sets up the event handler for the event stats button
   *
   * @param b event stats button
   */
  private void handleEventStats(Button b) {
    b.setOnAction(event -> {
      Stage s = new Stage();
      WeeklyStats stats = new WeeklyStats(model);
      this.showPopup(this.mainStage, s, new WeeklyStatsView(stats, ActivityType.EVENT),
          "Event Stats");
    });
  }

  /**
   * Sets up the event handler for the task stats button
   *
   * @param b task stats button
   */
  private void handleTaskStats(Button b) {
    b.setOnAction(event -> {
      Stage s = new Stage();
      WeeklyStats stats = new WeeklyStats(model);
      this.showPopup(this.mainStage, s, new WeeklyStatsView(stats, ActivityType.TASK),
          "Task Stats");
    });
  }

  /**
   * Sets up the event handler for the settings button
   *
   * @param settings settings button
   */
  private void handleSettings(Button settings) {
    //handles pop up when pushing the settings button
    settings.setOnAction(event -> {
      Stage popup = new Stage();
      VBox settingsView = new SettingsView(Settings.getInstance(), this, popup, false);
      this.showPopup(this.mainStage, popup, settingsView, "Settings");
    });
  }

  /**
   * Sets up the event handler for the save button
   *
   * @param save save button
   */
  private void handleSave(Button save) {
    save.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save BUJO File");
      ExtensionFilter ef = new ExtensionFilter("BUJO File", "*.bujo");
      fileChooser.getExtensionFilters().add(ef);
      File f = fileChooser.showSaveDialog(this.mainStage);
      if (f != null) {
        PersistenceManager.saveDataTo(f, this.model);
      }
    });
  }

  /**
   * get the title for each column
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @return title in String
   */
  @Override
  public String titleForColumn(TableView tableView, int columnIndex) {
    if (tableView == this.weekendView) {
      return Settings.getInstance().getDaysOfWeek().get(columnIndex).name();
    }
    return String.format("Weekly Tasks: (%d)", this.taskQueue.size());
  }

  /**
   * get number of row for a specific column
   *
   * @param tableView   reference to delegator
   * @param columnIndex column index
   * @return number of element in this column
   */
  @Override
  public int numberOfRowFor(TableView tableView, int columnIndex) {
    if (tableView == this.weekendView) {
      return this.activities.get(Settings.getInstance().getDaysOfWeek().get(columnIndex)).size();
    }
    return this.taskQueue.size();
  }

  /**
   * get the data of nth event/task on a specific day
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @param rowIndex    rowIndex of the table
   * @return an instance of event/task
   */
  @Override
  public Activity getActivityForCellAt(TableView tableView, int columnIndex, int rowIndex) {
    if (tableView == this.weekendView) {
      return this.activities.get(Settings.getInstance().getDaysOfWeek().get(columnIndex))
        .get(rowIndex);
    }
    return this.taskQueue.get(rowIndex);
  }

  /**
   * delegator calls the method when user clicks a cell at a specific index. delegatee handles the
   * user action
   *
   * @param tableView   reference to the delegator
   * @param columnIndex column index of the cell
   * @param rowIndex    row index of the cell
   */
  @Override
  public void didClickOn(TableView tableView, int columnIndex, int rowIndex) {
    Activity activity = this.getActivityForCellAt(tableView, columnIndex, rowIndex);
    Stage s = new Stage();
    MiniViewer miniViewer = new MiniViewer(activity);

    //set the action for the edit button on the mini viewer - will show the editable screen
    miniViewer.editSetOnAction(event -> {
      Stage stage = new Stage();
      Parent v = new ActivitySelectionView(activity, Settings.getInstance().getCategories(), this,
          stage);
      showPopup(this.mainStage, stage, v, "Edit");
      s.close();
    });

    //set the action for the delete button on the mini viewer
    miniViewer.deleteSetOnAction(event -> {
      this.model.removeActivity(activity);
      this.reloadAllView();
      s.close();
    });

    //show the mini viewer in a pop up
    this.showPopup(this.mainStage, s, miniViewer, "Mini Viewer");
  }

  /**
   * submit the data to the delegatee for handling
   *
   * @param formView reference to the formView
   * @param object   the newly created object
   */
  @Override
  public void submit(FormView formView, Object object) {
    if (formView instanceof ActivitySelectionView) {
      Activity activity = (Activity) object;
      if (!Settings.getInstance().getCategories().contains(activity.getCategory())) {
        Settings.getInstance().getCategories()
          .add(new Category(activity.getCategory().getName(), null));
      }
      this.model.addActivity(activity);

    }

    this.reloadAllView();
    this.showCommitmentWarning();
  }

  /**
   * Shows a commitment warning when user trys to add an event / task that exceeds the set max
   */
  private void showCommitmentWarning() {
    List<DayOfWeek> weeks = this.model.shouldDisplayCommitmentWarning();
    if (!weeks.isEmpty()) {
      StringBuilder builder = new StringBuilder();
      for (DayOfWeek week : weeks) {
        builder.append(week.toString().charAt(0))
            .append(week.toString().substring(1).toLowerCase()).append(", ");
      }
      String s = builder.toString();
      Utils.showAlert("Commitment Warning!",
          String.format("You are overbooked on %s!", s.substring(0, s.length() - 2)));
    }
  }
}
