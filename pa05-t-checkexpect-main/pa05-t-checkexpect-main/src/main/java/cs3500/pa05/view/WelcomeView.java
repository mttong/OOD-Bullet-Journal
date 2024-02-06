package cs3500.pa05.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Represents the view when the user first runs the program
 */
public class WelcomeView extends VBox {

  //fields
  Button load;
  Button create;

  /**
   * Constructor - builds the view
   */
  public WelcomeView() {
    //welcome label
    Label welcome = new Label("Welcome to your Bullet Journal!");
    welcome.setFont(Font.font("Bradley Hand", FontWeight.BOLD, 20));
    HBox welcomeText = new HBox(welcome);
    welcomeText.setAlignment(Pos.CENTER);

    //new button
    this.create = new Button("Create New +");
    this.create.setPrefSize(200, 100);
    this.create.setStyle("-fx-background-color: #8FBC8F;");

    //load in current bujo journal
    this.load = new Button("Upload +");
    this.load.setPrefSize(200, 100);
    this.load.setStyle("-fx-background-color: #8FBC8F;");

    //HBox for the buttons
    HBox options = new HBox(this.create, this.load);
    options.setSpacing(15);

    //add all
    this.getChildren().addAll(welcomeText, options);
    this.setAlignment(Pos.CENTER);
    this.setSpacing(15);
    this.setPadding(new Insets(10, 10, 10, 10));
  }

  /**
   * Sets the Event handleer for the create new button
   *
   * @param createAction event handler
   */
  public void setOnActionCreate(EventHandler<ActionEvent> createAction) {
    this.create.setOnAction(createAction);
  }

  /**
   * Sets the event handler for the upload button
   *
   * @param loadAction event handler
   */
  public void setOnActionLoad(EventHandler<ActionEvent> loadAction) {
    this.load.setOnAction(loadAction);
  }


}
