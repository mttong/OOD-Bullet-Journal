package cs3500.pa05.view.tables;

import cs3500.pa05.view.delegates.TableViewDelegate;

/**
 * represents a TableView interface any view that has the behavior of a table view (dynamically
 * display a collection of data) will implement this interface
 */
public interface TableView {

  /**
   * set the delegate for table view
   *
   * @param delegate a delegate that provide data to the table view
   */
  void setDelegate(TableViewDelegate delegate);

  /**
   * delegatee calls this method to reload all cells in the table view
   */
  void reloadAll();


}
