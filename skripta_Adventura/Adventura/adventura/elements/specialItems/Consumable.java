package elements.specialItems;

import elements.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing a consumable item
 * @author Denis Akopyan
 * @version 1.0
 */
public class Consumable extends Item {
  /**
   * Default constructor
   *
   * @param displayName consumable display name
   * @param matchName consumable match string
   * @param description consumable description
   */
  public Consumable(@NotNull String displayName, @NotNull String matchName, @NotNull String description) {
    super(displayName, matchName, true, description);
  }
}
