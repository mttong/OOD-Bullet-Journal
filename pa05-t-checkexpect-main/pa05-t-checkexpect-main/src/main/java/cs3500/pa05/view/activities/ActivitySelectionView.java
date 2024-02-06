package cs3500.pa05.view.activities;

import cs3500.pa05.Utils;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.delegates.FormDelegate;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Represents the pop up view when adding a task or event
 */
public class ActivitySelectionView extends VBox implements FormView {

  private final TextField name;
  private final TextField description;
  private final CategoriesView categories;
  private final WeekdayDropDownView weekdays;
  private final FormDelegate submitDelegate;
  private final ActivityType activityType;
  //fields
  private Label event;
  private TimeView startTime;
  private TimeView endTime;
  private ComboBox completion;
  private Activity activity;

  /**
   * Constructor that constructs the view of the activity pop up
   *
   * @param type       activity type (event or task)
   * @param categories list of categories already present in the journal
   * @param delegate   delegate controller
   * @param popupStage stage that the view will present / write on
   */
  public ActivitySelectionView(ActivityType type, List<Category> categories, FormDelegate delegate,
      Stage popupStage) {
    //elements on the event pop up
    this.activityType = type;
    this.name = new TextField();
    this.description = new TextField();
    this.categories = new CategoriesView(categories);

    this.weekdays = new WeekdayDropDownView();
    this.submitDelegate = delegate;
    this.setSpacing(10);

    GridPane info;
    if (type.equals(ActivityType.EVENT)) {
      this.activity = new Event();
      info = eventPopUp();
    } else {
      info = taskPopUp();
      this.activity = new Task();
    }
    //change font
    this.event.setFont(Font.font("verdana", FontWeight.BOLD, 15));
    //add the title
    this.getChildren().add(this.event);
    //add gridpane
    this.getChildren().add(info);
    //add submit button
    HBox hbox = new HBox();

    Button submit = new Button("Submit");
    hbox.getChildren().add(submit);
    hbox.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
    HBox.setHgrow(submit, Priority.ALWAYS);
    this.getChildren().add(hbox);

    //when the submit button is pressed
    submit.setOnAction(event -> {
      try {
        submitHandling();
        popupStage.close();
        this.submitDelegate.submit(this, this.activity);
      } catch (IllegalArgumentException e) {
        Utils.showAlert("Warning", e.getMessage());

      }
    });
  }

  /**
   * Constructor for EDITING a current activity / use this constructor if you want to update an
   * event
   *
   * @param activity   activity being edited
   * @param categories list of current activitiies
   * @param delegate   delegate controller
   * @param popupStage stage that the view will present / write on
   */

  public ActivitySelectionView(Activity activity, List<Category> categories, FormDelegate delegate,
      Stage popupStage) {
    this(activity.getType(), categories, delegate, popupStage);

    //update the pop up to the current values
    this.name.setText(activity.getName());
    this.description.setText(activity.getDescription());
    this.categories.setDefaultStartValue(activity.getCategory());
    this.weekdays.setDefault(activity.getWeekday());

    //add specific things based on event or task
    if (activity.getType().equals(ActivityType.EVENT)) {
      Event event = (Event) activity;
      this.startTime.setDefault(event.getStartTime());
      this.endTime.setDefault(event.getEndTime());
    } else {
      Task task = (Task) activity;
      this.completion.getSelectionModel().select(task.getStatus().getName());
    }
    this.activity = activity;
  }

  /**
   * Handles creating the grid pane with all necessary fields for an event
   *
   * @return GridPane with all event information
   */
  private GridPane eventPopUp() {
    //event specific calls
    this.event = new Label("New Event");
    this.startTime = new TimeView();
    this.endTime = new TimeView();

    //make the grid pane
    GridPane info = new GridPane();
    info.addRow(0, new Label("Event Name: "), this.name);
    info.addRow(1, new Label("Category: "), this.categories);
    info.addRow(2, new Label("Weekday:"), this.weekdays);
    info.addRow(3, new Label("Start Time: "), this.startTime);
    info.addRow(4, new Label("End Time: "), this.endTime);
    info.addRow(5, new Label("Description: "), this.description);
    return info;
  }

  /**
   * Handles creating the grid pane with all necessary fields for a task
   *
   * @return GridPane with all task information
   */
  private GridPane taskPopUp() {
    this.event = new Label("New Task");
    //completion status combo box
    this.completion = new ComboBox<>();
    this.completion.getItems()
        .addAll(CompletionStatus.NOT_STARTED.getName(), CompletionStatus.IN_PROGRESS.getName(),
        CompletionStatus.COMPLETED.getName());
    //default value is always to not started when first creating a task
    this.completion.getSelectionModel().select(CompletionStatus.NOT_STARTED.getName());
    //this.label = new Label("New Task");
    GridPane info = new GridPane();
    info.addRow(0, new Label("Task Name: "), this.name);
    info.addRow(1, new Label("Category: "), this.categories);
    info.addRow(2, new Label("Weekday:"), this.weekdays);
    info.addRow(3, new Label("Status:"), this.completion);
    info.addRow(4, new Label("Description: "), this.description);
    return info;
  }

  /**
   * Even handler for the submit button
   */
  public void submitHandling() {
    //Activity activity;
    //validate the user answers and show pop up
    if (!validateAnswers()) {
      throw new IllegalArgumentException("You must fill out all the information!");
    } else {
      //get all the user inputed info
      this.activity.setName(this.name.getText());
      this.activity.setDescription(this.description.getText());
      this.activity.setWeekday(this.weekdays.getSelectedWeekDay());
      this.activity.setCategory(categories.getChosenCategory());
      if (this.activityType.equals(ActivityType.EVENT)) {
        Event event = (Event) this.activity;
        event.setStartTime(this.startTime.getTime());
        event.setEndTime(this.endTime.getTime());
      } else {
        Task task = (Task) this.activity;
        task.setStatus(CompletionStatus.valueOf(
            this.completion.getValue().toString().toUpperCase().replace(" ", "_")));
      }
    }

  }

  /**
   * Checks to ensure that the user enters valid inputs (also not null)
   *
   * @return true if valid, false if not
   */
  private boolean validateAnswers() {
    boolean validated = !this.name.equals("") && this.weekdays.getSelectedWeekDay() != null
        && this.categories.getChosenCategory() != null;
    if (this.activityType.equals(ActivityType.EVENT)) {
      if (this.startTime.getTime() == null || this.endTime.getTime() == null) {
        validated = false;
      }
    }

    return validated;
  }

}
