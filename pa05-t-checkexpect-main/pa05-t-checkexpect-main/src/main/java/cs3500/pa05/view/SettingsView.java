package cs3500.pa05.view;

import cs3500.pa05.Utils;
import cs3500.pa05.model.Settings;
import cs3500.pa05.view.delegates.FormDelegate;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Represents the view for the Settings Pop up
 */
public class SettingsView extends VBox implements FormView {

  private final DatePicker datePicker;
  private final boolean welcome;
  private final Stage stage;
  //fields
  TextField nameInput;
  TextField emailInput;
  TextField eventInput;
  TextField taskInput;
  Button submitButton;
  Settings settings;
  FormDelegate submitDelegate;

  /**
   * Constructor - creates the view
   *
   * @param setting      current settings data
   * @param delegate     controller delegate
   * @param primaryStage stage to view on
   * @param welcome      true / false representing if upon opening program OR within the bullet
   *                     journal
   */
  public SettingsView(Settings setting, FormDelegate delegate, Stage primaryStage,
      boolean welcome) {
    this.nameInput = new TextField();
    this.emailInput = new TextField();
    this.eventInput = new TextField();
    this.taskInput = new TextField();
    this.settings = setting;
    this.stage = primaryStage;
    this.datePicker = new DatePicker();
    this.submitDelegate = delegate;
    this.setSpacing(10);
    this.welcome = welcome;

    //different modes
    if (welcome) {
      this.submitButton = new Button("Submit");
    } else {
      this.submitButton = new Button("Save");
      this.datePicker.setDisable(true);
    }

    if (!welcome) {
      //set default text
      this.nameInput.setText(this.settings.getName());
      this.emailInput.setText(this.settings.getEmail());
      this.eventInput.setText(Integer.toString(this.settings.getEventMax()));
      this.taskInput.setText(Integer.toString(this.settings.getTaskMax()));
      this.datePicker.setValue(this.settings.getLocalDate());
    }

    //add the type boxes to the vbox
    GridPane settings = new GridPane();
    settings.addRow(0, new Label("Name: "), nameInput);
    settings.addRow(1, new Label("Email: "), emailInput);
    settings.addRow(2, new Label("Max Events: "), eventInput);
    settings.addRow(3, new Label("Max Tasks: "), taskInput);
    settings.addRow(4, new Label("Select a week: "), datePicker);

    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(10));
    this.getChildren().addAll(settings);
    this.getChildren().addAll(submitButton);

    //set an on action event
    submitHandling();
  }


  /**
   * Sets the on action for the submit button
   */
  public void submitHandling() {
    submitButton.setOnAction(event -> {
      try {
        getUserInput();
        stage.close();
        this.submitDelegate.submit(this, Settings.getInstance());
      } catch (IllegalArgumentException e) {
        Utils.showAlert("Warning!", e.getMessage());
      }
    });
  }

  /**
   * Checks the user's inputted information and ensures that its all valid
   */
  private void getUserInput() {
    //gets the user's name
    this.settings.setName(nameInput.getText());

    //gets the user entered max event
    String events = eventInput.getText();
    if (Utils.isValidNumber(events)) {
      int eventMax = Integer.parseInt(events);
      this.settings.setEventMax(eventMax);
    } else {
      throw new IllegalArgumentException("Please enter a valid event number.");
    }

    //get user's entered max tasks
    String tasks = taskInput.getText();
    if (Utils.isValidNumber(tasks)) {
      int taskMax = Integer.parseInt(tasks);
      this.settings.setTaskMax(taskMax);
    } else {
      throw new IllegalArgumentException("Please enter a valid task number.");
    }

    //gets user's email
    String email = emailInput.getText();
    if (Utils.isValidEmail(email)) {
      this.settings.setEmail(email);
    } else {
      throw new IllegalArgumentException("Please enter a valid email.");
    }

    //get the week
    if (welcome) {
      LocalDate selectedDate = datePicker.getValue();
      if (selectedDate != null) {
        this.settings.setLocalDate(selectedDate);
      } else {
        throw new IllegalArgumentException("Select a week!");
      }
    }
  }
}
