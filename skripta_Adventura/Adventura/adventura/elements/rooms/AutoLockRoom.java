package elements.rooms;

import console.ConsoleEngine;
import console.TextStyle;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AutoLockRoom extends Room {
  
  private final Supplier<Boolean> unlockCondition;
  
  public AutoLockRoom(@NotNull String displayName,
                      @NotNull String matchName,
                      @NotNull String description,
                      @NotNull String firstEntryDescription,
                      @NotNull String lockedMessage,
                      Supplier<Boolean> unlockCondition) {
    super(displayName, matchName, description, firstEntryDescription, -1, lockedMessage);
    this.unlockCondition = unlockCondition;
  }
  
  @Override
  public boolean isLocked() {
    return isDiscovered() && !unlockCondition.get();
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
