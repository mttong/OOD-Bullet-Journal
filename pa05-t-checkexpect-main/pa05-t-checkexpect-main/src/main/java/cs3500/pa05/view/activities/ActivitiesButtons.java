package cs3500.pa05.view.activities;

import cs3500.pa05.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Represents an HBox with the Add Event and Add Task button
 */
public class ActivitiesButtons extends HBox {

  //fields
  Button event;
  Button task;

  /**
   * Constructor - creates and customizes the Hbox
   */
  public ActivitiesButtons() {
    //event button
    this.event = new Button("Event +");
    this.event.setPrefSize(150, 40);
    this.event.setStyle("-fx-background-color: #8FBC8F;");

    //task button
    this.task = new Button("Task +");
    this.task.setPrefSize(150, 40);
    this.task.setStyle("-fx-background-color: #8FBC8F;");

    //customize the hbox itself
    this.setPrefSize(150, 40);
    this.setSpacing(10);
    this.getChildren().add(event);
    this.getChildren().add(task);
  }

  /**
   * Sets the event handling for the add event button
   *
   * @param action event handler for the add event button
   */
  public void setOnActionEventButton(EventHandler<ActionEvent> action) {
    this.event.setOnAction(action);
  }

  /**
   * Sets the event handling for the add task button
   *
   * @param action event handler for the add task button
   */
  public void setOnActionTaskButton(EventHandler<ActionEvent> action) {
    this.task.setOnAction(action);
  }


}
