package elements.rooms;

import elements.specialItems.Key;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public class WinRoom extends Room {
  private final Set<Integer> locks;
  private boolean isLocked = true;
  
  /**
   * Default constructor
   *
   * @param displayName room name
   * @param matchName room regex matcher
   * @param locks room locks
   * @param lockedMessage locked message
   */
  public WinRoom(@NotNull String displayName, @NotNull String matchName, @NotNull List<Integer> locks, @NotNull String lockedMessage) {
    super(displayName, matchName, "", "", 0, lockedMessage);
    // If there are no locks..
    if (locks.size() == 0)
      // throw an exception
      throw new IllegalArgumentException("Argument locks cannot be empty");
  
    this.locks = new HashSet<>(locks);
  }
  
  @Override
  public boolean unlock(@NotNull Key key) {
    return false;
  }
  
  public boolean unlock(@NotNull List<Key> keys) {
    var result = keys
        .stream()
        .map(Key::getId)
        .filter(locks::contains)
        .count() == locks.size();
    
    isLocked = !result;
    
    return result;
  }
  
  @Override
  public boolean isLocked() {
    return isLocked;
  }
}
