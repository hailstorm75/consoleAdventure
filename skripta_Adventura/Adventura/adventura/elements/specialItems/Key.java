package elements.specialItems;

import elements.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing a key for locking ItemContainers
 */
public class Key extends Item {
  private final int id;
  
  /**
   * Getter for the Id property
   *
   * @return id value
   */
  public int getId() {
    return id;
  }
  
  /**
   * Default constructor
   *
   * @param name name of the item
   */
  public Key(@NotNull String name, int id, @NotNull String description) {
    super(name, true, description);
    this.id = id;
  }
  
  public Key(@NotNull String displayName, @NotNull String matchName, int id, @NotNull String description) {
    super(displayName, matchName, true, description);
    this.id = id;
  }
}
