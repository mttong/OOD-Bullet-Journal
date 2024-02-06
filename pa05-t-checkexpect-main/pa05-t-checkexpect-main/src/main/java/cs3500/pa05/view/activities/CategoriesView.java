package cs3500.pa05.view.activities;

import cs3500.pa05.model.Category;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

/**
 * Represents the dropdown for the Category section in the event / task pop up to edit
 */
public class CategoriesView extends ComboBox {

  //fields
  private final List<Category> existingCategories;

  /**
   * Constructor
   *
   * @param categories list of exisiting categories
   */
  public CategoriesView(List<Category> categories) {
    this.existingCategories = categories;
    //add exisiting categories to the combo box drop down
    for (Category category : categories) {
      this.getItems().add(category.getName());
    }
    // allow the user to type a new category
    this.setEditable(true);
  }

  /**
   * Gets the user's chosen category and validates that they chose one
   *
   * @return Category - the chosen category
   */
  public Category getChosenCategory() {
    //ensures not null
    if (!validateAnswer()) {
      return null;
    } else {
      //finds the ref to the category
      String category = this.getValue().toString();
      Category userChoice = null;
      boolean found = false;
      // see if it's an existing category
      for (Category existingCategory : existingCategories) {
        if (category.equals(existingCategory)) {
          userChoice = new Category(existingCategory.getName(), existingCategory.getColor());
          found = true;
        }
      }
      // not an existing cat-> create one
      //default color is red for now
      if (!found) {
        userChoice = new Category(category, Color.WHITE);
      }
      return userChoice;
    }
  }

  /**
   * Ensure that that the chosen category is not null
   *
   * @return true if null, false if not
   */
  private boolean validateAnswer() {
    return this.getValue() != null;
  }

  /**
   * Sets the default value of the category drop down
   *
   * @param name given category to set to default
   */
  public void setDefaultStartValue(Category name) {
    this.getSelectionModel().select(name.getName());
  }

}
