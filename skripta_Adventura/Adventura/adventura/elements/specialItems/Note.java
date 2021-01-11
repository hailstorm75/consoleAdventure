package elements.specialItems;

import elements.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing a note
 * @author Denis Akopyan
 * @version 1.0
 */
public class Note extends Item {
  public Note(@NotNull String displayName, @NotNull String matchName, @NotNull String description) {
    super(displayName, matchName, false, description);
  }
}
