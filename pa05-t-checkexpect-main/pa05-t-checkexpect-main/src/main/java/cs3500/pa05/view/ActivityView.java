package cs3500.pa05.view;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * Represents the view for a single event or task in the bullet journal
 */
public class ActivityView extends VBox {

  Label title;
  Label category;

  /**
   * Constructor
   *
   * @param activity activity to create a view for
   */
  public ActivityView(Activity activity) {
    this.setSpacing(2);
    this.setPadding(new Insets(10));
    this.setAlignment(Pos.CENTER);

    //display title
    this.title = new Label(activity.getName());
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    title.setWrapText(true);

    //display category
    this.category = new Label(activity.getCategory().getName());
    category.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
    category.setWrapText(true);

    //add
    this.getChildren().addAll(this.title, this.category);

    //if a task add the completion status
    if (activity.getType().equals(ActivityType.TASK)) {
      Task e = (Task) activity;
      Text completionStatus = new Text(e.getStatus().getName());
      completionStatus.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
      this.getChildren().add(completionStatus);
    }
    //set a border
    CornerRadii cornerRadii = new CornerRadii(7);
    BorderStroke borderStroke = new BorderStroke(Color.valueOf("8FBC8F"), BorderStrokeStyle.SOLID,
        cornerRadii, new BorderWidths(1));
    Border border = new Border(borderStroke);
    this.setBorder(border);
  }
}
