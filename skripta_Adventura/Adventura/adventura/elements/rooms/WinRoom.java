package elements.rooms;

import elements.specialItems.Key;
import org.jetbrains.annotations.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing the winning room
 *
 * @author Denis Akopyan
 * @version 1.0
 */
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
    if (locks.isEmpty()) {
      // throw an exception
      throw new IllegalArgumentException("Argument locks cannot be empty");
    }
  
    this.locks = new HashSet<>(locks);
  }
  
  /**
   * Attempts to unlock the current entity
   *
   * @implNote Not supported
   * @param key key to use to unlock
   * @return true if unlocked
   */
  @Override
  public boolean unlock(@NotNull Key key) {
    return false;
  }
  
  /**
   * Attempts to unlock the current entity
   *
   * @param keys keys to use to unlock
   * @return true if unlocked
   */
  public boolean unlock(@NotNull List<Key> keys) {
    var result = keys
        .stream()
        .filter(x -> locks.contains(x.getKeyId()))
        .collect(Collectors.toList());
    
    isLocked = !(result.size() == locks.size());
    
    if (!isLocked) {
      keys.removeAll(result);
    }
    
    return !isLocked;
  }
  
  /**
   * Checks if the entity is locked
   *
   * @return true if the entity is locked
   */
  @Override
  public boolean isLocked() {
    return isLocked;
  }
}
