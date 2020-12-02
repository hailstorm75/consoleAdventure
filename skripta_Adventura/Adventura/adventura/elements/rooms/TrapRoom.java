package elements.rooms;

import org.jetbrains.annotations.NotNull;

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
