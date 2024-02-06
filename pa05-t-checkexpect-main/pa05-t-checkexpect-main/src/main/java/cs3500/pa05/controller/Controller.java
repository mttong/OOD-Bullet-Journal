package cs3500.pa05.controller;

import cs3500.pa05.view.FormView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Represents the Controller Interface
 */
public interface Controller {

  /**
   * Shows a popup on the main stage
   *
   * @param ownerStage mainstage
   * @param popupStage the pop up stage
   * @param popUp      the item to be popped up / view
   * @param title      title for the pop up
   */
  default void showPopup(Stage ownerStage, Stage popupStage, Parent popUp, String title) {
    popupStage.initOwner(ownerStage);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.setTitle(title);
    Scene popupScene = new Scene(popUp);
    popupStage.setScene(popupScene);
    popupStage.showAndWait();
  }


}
