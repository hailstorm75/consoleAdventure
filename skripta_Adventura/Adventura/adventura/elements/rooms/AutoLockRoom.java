package elements.rooms;

import console.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Class representing a room which locks off exits upon entry
 * @author Denis Akopyan
 * @version 1.0
 */
public class AutoLockRoom extends Room {
  
  private final Supplier<Boolean> unlockCondition;
  private final Set<Room> lockableExits = new HashSet<>();
  
  /**
   * Default constructor
   *
   * @param displayName room name
   * @param matchName room match name
   * @param description room description
   * @param firstEntryDescription first entry message
   * @param lockedMessage locked message
   * @param unlockCondition condition for unlocking the exits
   */
  public AutoLockRoom(@NotNull String displayName,
                      @NotNull String matchName,
                      @NotNull String description,
                      @NotNull String firstEntryDescription,
                      @NotNull String lockedMessage,
                      Supplier<Boolean> unlockCondition) {
    super(displayName, matchName, description, firstEntryDescription, -1, lockedMessage);
    this.unlockCondition = unlockCondition;
  }
  
  /**
   * Adds the exits to lock
   *
   * @param room exit to add
   */
  public final void addLockableExit(Room room) {
    lockableExits.add(room);
  }
  
  /**
   * Checks if the given exit is auto-locked
   *
   * @param exit room to check
   * @return true if locked
   */
  public final boolean isExitLocked(Room exit) {
    return lockableExits.contains(exit) && isDiscovered() && !unlockCondition.get();
  }
  
  /**
   * Getter for the description property
   *
   * @return description of the room
   */
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
