package cs3500.pa05.view;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.WeeklyStats;
import cs3500.pa05.model.enums.ActivityType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Represents the view for the Stats (event stats and task tasks)
 */
public class WeeklyStatsView extends VBox {

  private final WeeklyStats stats;

  /**
   * Constructor - creates the view
   *
   * @param weeklyStats  current stats
   * @param activityType the activity type
   */
  public WeeklyStatsView(WeeklyStats weeklyStats, ActivityType activityType) {
    this.stats = weeklyStats;
    //fields
    this.setSpacing(5);

    Map<Category, Integer> eventHash = stats.getEventCategoryValues();
    Map<Category, Integer> taskHash = stats.getTaskCategoryValues();

    VBox info;
    GridPane categories;
    if (activityType.equals(ActivityType.EVENT)) {
      info = eventPopUp();
      categories = categoriesPopUp(eventHash);
    } else {
      info = taskPopUp();
      categories = categoriesPopUp(taskHash);
    }
    this.getChildren().addAll(info, categories);
  }

  /**
   * Handles the categories pop up
   *
   * @param hash hashmap with category data
   * @return view - GridPane
   */
  private GridPane categoriesPopUp(Map<Category, Integer> hash) {
    GridPane info = new GridPane();
    info.setPadding(new Insets(10));
    info.setHgap(10);
    info.setVgap(5);
    Label label = new Label("Category Breakdown: ");
    label.setFont(Font.font("verdana", FontWeight.BOLD, 10));
    info.addRow(0, label, new Label(""));
    List<Category> keys = new ArrayList<>(hash.keySet());

    //total events
    for (int i = 1; i < hash.size() + 1; i++) {
      Category category = keys.get(i - 1);
      Integer value = hash.get(category);
      Label categoryLabel = new Label(category.getName() + ": ");
      //categoryLabel.setTextFill(category.getColor());
      info.addRow(i, categoryLabel, new Label(String.valueOf(value)));
    }
    return info;
  }

  /**
   * Handles stats for the tasks
   *
   * @return View - vbox with information
   */
  private VBox taskPopUp() {
    VBox box = new VBox();
    box.setPadding(new Insets(10));
    Label label = new Label("Total Tasks: " + stats.getTotalTasks());
    label.setFont(Font.font("verdana", FontWeight.BLACK, 10));

    HBox progress = new HBox();
    progress.setPadding(new Insets(10));
    progress.setSpacing(2.0);

    double percent = stats.getPercentDone();
    DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(2));
    String formattedPercent = decimalFormat.format(percent * 100);

    ProgressBar progressBar = new ProgressBar();
    progressBar.setProgress(percent);
    progressBar.setStyle("-fx-accent: green;");

    Label percentLabel = new Label(formattedPercent + "% done!");
    progress.getChildren().addAll(progressBar, percentLabel);

    box.getChildren().addAll(label, progress);
    return box;
  }

  /**
   * Handles stats for the events
   *
   * @return View - vbox with information
   */
  private VBox eventPopUp() {
    VBox box = new VBox();
    Label label = new Label("Total Events: " + stats.getTotalEvents());
    label.setPadding(new Insets(10));
    label.setFont(Font.font("verdana", FontWeight.BLACK, 10));

    box.getChildren().add(label);
    return box;
  }


}
