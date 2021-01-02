package elements.specialItems;

import elements.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing a key for locking ItemContainers
 * @author Denis Akopyan
 * @version 1.0
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
   * @param displayName name of the item
   * @param id key id
   * @param description key item description
   */
  public Key(@NotNull String displayName, int id, @NotNull String description) {
    super(displayName, true, description);
    this.id = id;
  }
  
  /**
   * @param displayName name of the item
   * @param matchName key item match string
   * @param id key id
   * @param description key item description
   */
  public Key(@NotNull String displayName, @NotNull String matchName, int id, @NotNull String description) {
    super(displayName, matchName, true, description);
    this.id = id;
  }
}
