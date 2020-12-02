package elements.rooms;

import org.jetbrains.annotations.NotNull;

public class WinRoom extends Room {
  public WinRoom(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDescription) {
    super(displayName, matchName, description, firstEntryDescription);
  }
  
  public WinRoom(@NotNull String displayName, @NotNull String description, @NotNull String firstEntryDescription) {
    super(displayName, description, firstEntryDescription);
  }
  
  public WinRoom(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDescription, int lockId, @NotNull String lockedMessage) {
    super(displayName, matchName, description, firstEntryDescription, lockId, lockedMessage);
  }
}
