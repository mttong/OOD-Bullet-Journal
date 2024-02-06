package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.controller.WelcomeController;
import cs3500.pa05.model.WeekdaysModel;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.WelcomeView;
import cs3500.pa05.view.activities.ActivitiesButtons;
import cs3500.pa05.view.tables.TaskQueueView;
import cs3500.pa05.view.tables.WeekdaysView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * MainStage for Bullet Journal
 */
public class BujoMainStage extends Application {

  private final int height = 1000;
  private final int width = 2000;

  @Override
  public void start(Stage primaryStage) {
    try {
      Button settings = new Button();
      Button save = new Button();

      setButtonIcon(settings, "settings.png");
      setButtonIcon(save, "save.png");

      //buttons
      Button eventStats = new Button("Event Stats");
      eventStats.setStyle("-fx-background-color: #8FBC8F;");
      Button taskStats = new Button("Task Stats");
      taskStats.setStyle("-fx-background-color: #8FBC8F;");
      eventStats.setPrefSize(200, 50);
      taskStats.setPrefSize(200, 50);

      ActivitiesButtons addActivities = new ActivitiesButtons();
      WeekdaysView weekdaysView = new WeekdaysView();
      TaskQueueView taskQueueView = new TaskQueueView();

      HBox weekOfLabel = new HBox();
      BujoView bujo = new BujoView(addActivities, settings, save, weekdaysView, taskQueueView,
          eventStats, taskStats, weekOfLabel);

      WeekdaysModel model = new WeekdaysModel();
      WelcomeView welcomeView = new WelcomeView();
      //init the controller
      BujoController controllerBujo = new BujoController(primaryStage, model, weekdaysView,
          taskQueueView, addActivities, settings, eventStats, taskStats, save);
      WelcomeController controllerWelcome =
              new WelcomeController(model, welcomeView, primaryStage,
                      bujo, controllerBujo, weekOfLabel);

      //show the welcome scene
      Scene scene = new Scene(welcomeView);
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void setButtonIcon(Button button, String url) {
    ImageView icon = new ImageView(url);
    icon.setFitWidth(20);
    icon.setFitHeight(20);
    button.setGraphic(icon);
  }
}
