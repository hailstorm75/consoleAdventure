package elements.rooms;

import console.ConsoleEngine;
import console.TextStyle;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class AutoLockRoom extends Room {
  
  private final Supplier<Boolean> unlockCondition;
  private final Set<Room> lockableExits = new HashSet<>();
  
  public AutoLockRoom(@NotNull String displayName,
                      @NotNull String matchName,
                      @NotNull String description,
                      @NotNull String firstEntryDescription,
                      @NotNull String lockedMessage,
                      Supplier<Boolean> unlockCondition) {
    super(displayName, matchName, description, firstEntryDescription, -1, lockedMessage);
    this.unlockCondition = unlockCondition;
  }
  
  public final AutoLockRoom addLockableExit(Room room) {
    lockableExits.add(room);
    
    return this;
  }
  
  public final boolean isExitLocked(Room exit) {
    return lockableExits.contains(exit) && isDiscovered() && !unlockCondition.get();
  }
  
  @Override
  public String getDescription() {
    return
        (isDiscovered() ? "" : getFirstEntryDescription() + '\n')
        + ConsoleEngine
            .getInstance()
            .formatForegroundStyleCode(TextStyle.Underline)
        + this.getDescriptionFinal()
        + ConsoleEngine
            .getInstance()
            .formatForegroundStyleCode(TextStyle.Normal);
  }
}
