package cs3500.pa05.view.delegates;


import cs3500.pa05.view.FormView;

/**
 * represent a delegate interface for FormView the delegator submit the newly created data the
 * delegatee receives the data
 */
public interface FormDelegate {

  /**
   * submit the data to the delegatee for handling
   *
   * @param formView reference to the formView
   * @param object   the newly created object
   */
  void submit(FormView formView, Object object);

}
