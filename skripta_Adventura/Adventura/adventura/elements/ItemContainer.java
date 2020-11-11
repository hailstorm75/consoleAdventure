package elements;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ItemContainer extends Item {
  private Set<Item> items = new HashSet<>();
  private int lockId;
  private final String lockedMessage;
  
  /**
   * Default constructor
   *
   * @param name     name of the item container
   * @param description description of the item container
   */
  public ItemContainer(@NotNull String name, @NotNull String description, int lockId, @NotNull String lockedMessage) {
    super(name, false, description);
    this.lockId = lockId;
    this.lockedMessage = lockedMessage;
  }
  
  public ItemContainer(@NotNull String name, @NotNull String description) {
    this(name, description, -1, "");
  }
  
  public ItemContainer add(Item... items) {
    this.items.addAll(Arrays.asList(items));
    
    return this;
  }
  
  /**
   * Retrieves a list of items present in the room
   *
   * @return string representation of the items present in given room
   */
  private String itemNames() {
    return items.stream().map(Item::getName).collect(Collectors.joining("  "));
  }
  
  public Collection<Item> getItems() {
    return items.stream().collect(Collectors.toUnmodifiableList());
  }
 
  public final boolean isLocked() {
    return lockId != -1;
  }
  
  public final Optional<String> getLockedMessage() {
    return isLocked()
        ? Optional.of(lockedMessage)
        : Optional.empty();
  }
  
  public final boolean unlock(Key key) {
    if (!isLocked())
      return false;
    if (lockId != key.getId())
      return false;
    
    lockId = -1;
    return true;
  }
  
  /**
   * Validates whether the given item is present
   *
   * @param name identifier of the item
   * @return true if the item is present
   */
  public final boolean hasItem(@NotNull String name) {
    return items
        .stream()
        .anyMatch(x -> x.getName().equals(name));
  }
  
  /**
   * Removes an item identified by the given name from the room and returns the removed thing
   *
   * @param name name of the item to remove
   * @return the removed item
   */
  public Optional<Item> takeOut(@NotNull String name) {
    var item = items
        .stream()
        .filter(x -> x.getName().equals(name))
        .findFirst();
  
    if (item.isEmpty())
      return Optional.empty();
  
    var extracted = item.get();
    if (extracted.getCanCarry())
      items.remove(extracted);
  
    return Optional.of(extracted);
  }
}
