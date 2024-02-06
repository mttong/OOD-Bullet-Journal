package cs3500.pa05.view.tables;

import cs3500.pa05.Utils;
import cs3500.pa05.model.Settings;
import cs3500.pa05.view.ActivityView;
import cs3500.pa05.view.delegates.TableViewDelegate;
import java.time.DayOfWeek;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;





/**
 * represents a weekday view class
 */
public class WeekdaysView extends GridPane implements TableView {

  private final int columnWidth = 200;
  //delegate for the delegate
  private TableViewDelegate delegate;


  /**
   * Constructor
   */
  public WeekdaysView() {
    //make view
    super();
    this.setPadding(new Insets(10));
    this.setHgap(7);
    this.setVgap(7);
    this.setAlignment(Pos.TOP_LEFT);
    for (int i = 0; i < DayOfWeek.values().length; i++) {
      ColumnConstraints cons = new ColumnConstraints();
      cons.setPrefWidth(columnWidth);
      this.getColumnConstraints().add(cons);
    }

    //add a border
    CornerRadii cornerRadii = new CornerRadii(7);

    BorderStroke borderStroke = new BorderStroke(Utils.BUJO_THEME_COLOR,
            BorderStrokeStyle.SOLID, cornerRadii, new BorderWidths(2));
    Border border = new Border(borderStroke);
    this.setBorder(border);
  }

  /**
   * set the delegate for table view
   *
   * @param delegate a delegate that provide data to the table view
   */
  @Override
  public void setDelegate(TableViewDelegate delegate) {
    this.delegate = delegate;
    this.reloadAll();
  }

  /**
   * helper method to render a specific cell
   *
   * @param colIndex column index of the cell
   * @param rowIndex row index of the cell
   */
  private void renderCell(int colIndex, int rowIndex) {

    Button invisible = new Button();
    invisible.setOpacity(0.0);
    invisible.setBackground(null);
    invisible.setPrefWidth(this.columnWidth);
    //invisible.setPrefHeight(100);
    //set on action
    invisible.setPrefHeight(80);
    invisible.setOnAction(event -> this.delegate.didClickOn(this, colIndex, rowIndex));

    VBox v = new ActivityView(this.delegate.getActivityForCellAt(this, colIndex, rowIndex));
    this.add(new StackPane(v, invisible), colIndex, rowIndex + 1);
  }


  /**
   * delegatee calls this method to reload all cells in the table view
   */
  @Override
  public void reloadAll() {
    this.getChildren().clear();
    List<DayOfWeek> days = Settings.getInstance().getDaysOfWeek();
    for (int i = 0; i < days.size(); i++) {
      Text name = new Text(this.delegate.titleForColumn(this, i));
      name.setFill(Utils.BUJO_THEME_COLOR);
      name.setFont(Font.font("Bradley Hand", FontWeight.BOLD, 20));
      HBox day = new HBox(name);
      day.setAlignment(Pos.CENTER);
      day.setPrefWidth(columnWidth);

      this.add(day, i, 0);
      for (int j = 0; j < this.delegate.numberOfRowFor(this, i); j++) {
        this.renderCell(i, j);
      }
    }
  }
}
