package cs3500.pa05.view;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import java.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents the view for the mini viewer of an activity
 */
public class MiniViewer extends VBox {

  //fields
  private final Button edit;
  private final Button delete;
  private final Activity activity;

  /**
   * Constructor
   *
   * @param activity activity to create mini viewer for
   */
  public MiniViewer(Activity activity) {
    this.activity = activity;

    //buttons
    this.edit = new Button("Edit");
    this.edit.setPrefSize(100, 25);
    this.delete = new Button("Delete");
    this.delete.setPrefSize(100, 25);
    HBox buttons = new HBox(this.edit, this.delete);
    buttons.setAlignment(Pos.CENTER);
    buttons.setSpacing(15);

    this.setPadding(new Insets(10, 10, 10, 10));
    this.setPrefWidth(300);

    //name
    Text name = new Text(activity.getName());
    name.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

    //category
    Text category = new Text(activity.getCategory().getName());
    category.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));

    //weekday
    Text weekday = new Text("Day: " + activity.getWeekday().name());
    weekday.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));

    //description
    Text description = new Text("Description: " + activity.getDescription());
    description.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
    description.setWrappingWidth(200);
    this.getChildren().addAll(name, category, weekday);

    if (activity.getType().equals(ActivityType.EVENT)) {
      handleEvent();
    } else {
      handleTask();
    }
    this.getChildren().addAll(description, buttons);
    this.setSpacing(10);
  }

  /**
   * Handles adding the event specific information to the mini-viewer
   */
  private void handleEvent() {
    Event e = (Event) activity;
    Text startTime = new Text("Start: " + convertTime(e.getStartTime()));
    startTime.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
    Text endTime = new Text("End: " + convertTime(e.getEndTime()));
    endTime.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
    this.getChildren().addAll(startTime, endTime);
  }

  /**
   * Handles adding the task specific information to the mini-viewer
   */
  private void handleTask() {
    Task e = (Task) activity;
    Text completionStatus = new Text(e.getStatus().getName());
    completionStatus.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));
    this.getChildren().add(completionStatus);
  }

  /**
   * Converts a given time from military time to normal clock time
   *
   * @param time LocalTime in miltary time
   * @return String representation of the time
   */
  private String convertTime(LocalTime time) {
    int hour = time.getHour();
    String minutes = Integer.toString(time.getMinute());
    String amPm = "am";
    //handle the hours
    if (time.getHour() > 12) {
      hour = hour - 12;
      amPm = "pm";
    } else if (time.getHour() == 0) {
      hour = 12;
      amPm = "am";
    }

    //handle the 0 case of the minutes
    if (minutes.equals("0")) {
      minutes = "00";
    }
    return hour + ":" + minutes + " " + amPm;
  }

  /**
   * Sets event handlers for the edit button
   *
   * @param action event handler
   */
  public void editSetOnAction(EventHandler<ActionEvent> action) {
    this.edit.setOnAction(action);
  }

  /**
   * Sets event handlers for the delete button
   *
   * @param action event handler
   */
  public void deleteSetOnAction(EventHandler<ActionEvent> action) {
    this.delete.setOnAction(action);
  }

}
