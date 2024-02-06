package cs3500.pa05.view.delegates;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.view.tables.TableView;

/**
 * represent a delegate interface for TableView the delegator ask for data of each activity the
 * delegatee provides data
 */
public interface TableViewDelegate {

  /**
   * get the title for each column
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @return title in String
   */
  String titleForColumn(TableView tableView, int columnIndex);

  /**
   * get number of row for a specific column
   *
   * @param tableView   reference to delegator
   * @param columnIndex column index
   * @return number of element in this column
   */
  int numberOfRowFor(TableView tableView, int columnIndex);

  /**
   * get the data of nth event/task on a specific day
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @param rowIndex    rowIndex of the table
   * @return an instance of event/task
   */
  Activity getActivityForCellAt(TableView tableView, int columnIndex, int rowIndex);

  /**
   * delegator calls the method when user clicks a cell at a specific index. delegatee handles the
   * user action
   *
   * @param tableView   reference to the delegator
   * @param columnIndex column index of the cell
   * @param rowIndex    row index of the cell
   */
  void didClickOn(TableView tableView, int columnIndex, int rowIndex);
}
