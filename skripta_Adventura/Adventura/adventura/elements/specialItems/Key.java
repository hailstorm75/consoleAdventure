package elements.specialItems;

import elements.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing a key for locking ItemContainers
 * @author Denis Akopyan
 * @version 1.0
 */
public class Key extends Item {
  private final int keyId;
  
  /**
   * Getter for the Id property
   *
   * @return id value
   */
  public int getKeyId() {
    return keyId;
  }
  
  /**
   * Default constructor
   *
   * @param displayName name of the item
   * @param keyId key id
   * @param description key item description
   */
  public Key(@NotNull String displayName, int keyId, @NotNull String description) {
    super(displayName, true, description);
    this.keyId = keyId;
  }
  
  /**
   * @param displayName name of the item
   * @param matchName key item match string
   * @param keyId key id
   * @param description key item description
   */
  public Key(@NotNull String displayName, @NotNull String matchName, int keyId, @NotNull String description) {
    super(displayName, matchName, true, description);
    this.keyId = keyId;
  }
}
