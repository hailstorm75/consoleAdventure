package elements.specialItems;

import elements.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing a note
 * @author Denis Akopyan
 * @version 1.0
 */
public class Note extends Item {
  /**
   * Default constructor
   *
   * @param displayName note display name
   * @param matchName note match name
   * @param description note description
   */
  public Note(@NotNull String displayName, @NotNull String matchName, @NotNull String description) {
    super(displayName, matchName, false, description);
  }
}
