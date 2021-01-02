package elements.specialItems;

import org.jetbrains.annotations.NotNull;

/**
 * Class representing a droppable key
 * @author Denis Akopyan
 * @version 1.0
 */
public class Totem extends Key {
  public Totem(@NotNull String displayName, @NotNull String matchName, int id, @NotNull String description) {
    super(displayName, matchName, id, description);
  }
  
  public Totem(@NotNull String name, int id, @NotNull String description) {
    super(name, id, description);
  }
}
