package elements.specialItems;

import org.jetbrains.annotations.NotNull;

/**
 * Class representing a droppable key
 * @author Denis Akopyan
 * @version 1.0
 */
public class Totem extends Key {
  /**
   * Totem constructor
   *
   * @param displayName totem display name
   * @param matchName totem match string
   * @param totemId totem key id
   * @param description totem description
   */
  public Totem(@NotNull String displayName, @NotNull String matchName, int totemId, @NotNull String description) {
    super(displayName, matchName, totemId, description);
  }
  
  /**
   * Totem constructor
   *
   * @param displayName totem display name
   * @param totemId totem key id
   * @param description totem description
   */
  public Totem(@NotNull String displayName, int totemId, @NotNull String description) {
    super(displayName, totemId, description);
  }
}
