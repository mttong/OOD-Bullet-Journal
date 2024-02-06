package cs3500.pa05;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

/**
 * Utils class
 */
public class Utils {

  public static Color BUJO_THEME_COLOR = Color.valueOf("228B22");

  /**
   * Shows a warning / alert pop up
   *
   * @param title   title of the pop up
   * @param message message in the pop up
   */
  public static void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.show();
  }

  /**
   * Checks if a given input is a valid number
   *
   * @param input String input
   * @return true / false
   */
  public static boolean isValidNumber(String input) {
    try {
      int num = Integer.parseInt(input);
      return num >= 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * checks if the given email is valid
   *
   * @param input string
   * @return true / false
   */
  public static boolean isValidEmail(String input) {
    return (input.endsWith(".com") || input.endsWith(".edu")) && input.contains("@");
  }
}