package elements.rooms;

import org.jetbrains.annotations.NotNull;

/**
 * Class representing a room which triggers a death trap upon extracting an object
 * @author Denis Akopyan
 * @version 1.0
 */
public class TrapRoom extends Room {
  public TrapRoom(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDescription) {
    super(displayName, matchName, description, firstEntryDescription);
  }
  
  public TrapRoom(@NotNull String displayName, @NotNull String description, @NotNull String firstEntryDescription) {
    super(displayName, description, firstEntryDescription);
  }
  
  public TrapRoom(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDescription, int lockId, @NotNull String lockedMessage) {
    super(displayName, matchName, description, firstEntryDescription, lockId, lockedMessage);
  }
}
