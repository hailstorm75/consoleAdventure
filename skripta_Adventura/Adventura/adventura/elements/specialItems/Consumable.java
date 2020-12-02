package elements.specialItems;

import elements.Item;
import org.jetbrains.annotations.NotNull;

public class Consumable extends Item {
  public Consumable(@NotNull String displayName, @NotNull String matchName, @NotNull String description) {
    super(displayName, matchName, true, description);
  }
}
