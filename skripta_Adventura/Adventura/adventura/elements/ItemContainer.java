package elements;

import elements.specialItems.Key;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ItemContainer extends Item {
  private final Set<Item> items = new HashSet<>();
  private int lockId;
  private final String lockedMessage;
  
  /**
   * Default constructor
   *
   * @param displayName     name of the item container
   * @param description description of the item container
   */
  public ItemContainer(@NotNull String displayName, @NotNull String matchName, @NotNull String description, int lockId, @NotNull String lockedMessage) {
    super(displayName, matchName, false, description);
    this.lockId = lockId;
    this.lockedMessage = lockedMessage;
  }
  
  public ItemContainer(@NotNull String displayName, @NotNull String matchName, @NotNull String description) {
    this(displayName, matchName, description, -1, "");
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
    return items
        // Iterate over items
        .stream()
        // Retrieve item names
        .map(Item::getDisplayName)
        // Materialize the item names into a string
        .collect(Collectors.joining("  "));
  }
  
  /**
   * Get the contained items
   *
   * @return contained items collection
   */
  public Collection<Item> getItems() {
    return items
        // Iterate over items
        .stream()
        // Materialize them into an unmodifiable collection
        .collect(Collectors.toUnmodifiableList());
  }
  
  /**
   * Checks if the entity is locked
   *
   * @return true if the entity is locked
   */
  public boolean isLocked() {
    return lockId > 0;
  }
  
  /**
   * Getter for the LockedMessage property
   *
   * @return locked message
   */
  public final Optional<String> getLockedMessage() {
    return isLocked()
        ? Optional.of(lockedMessage)
        : Optional.empty();
  }
  
  /**
   * Attempts to unlock the current entity
   *
   * @param key key to use to unlock
   * @return true if unlocked
   */
  public boolean unlock(Key key) {
    // If the entity is already unlocked..
    if (!isLocked())
      // nothing was unlocked
      return false;
    
    // If the key doesn't fit..
    if (lockId != key.getId())
      // nothing was unlocked
      return false;
    
    // Set the locked state
    lockId = -1;
    // The entity was unlocked
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
        // Iterate over items
        .stream()
        // Check if an item with the given name exists
        .anyMatch(x -> x.isMatch(name));
  }
  
  /**
   * Removes an item identified by the given name from the room and returns the removed thing
   *
   * @param name name of the item to remove
   * @return the removed item
   */
  public Optional<Item> takeOut(@NotNull String name) {
    // Try get the item to take out
    var item = items
        // Iterate over items
        .stream()
        // Filter out the matching items
        .filter(x -> x.isMatch(name))
        // Try get the matching item
        .findFirst();
  
    // If no item was found..
    if (item.isEmpty())
      // return empty
      return Optional.empty();
  
    // Extract the found item
    var extracted = item.get();
    // If the item can't be carried..
    if (!extracted.getCanCarry())
      // return empty
      return Optional.empty();
    
    // remove the item from the container
    items.remove(extracted);
    // return the wrapped item
    return Optional.of(extracted);
  }
}
