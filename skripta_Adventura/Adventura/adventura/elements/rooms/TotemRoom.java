package elements.rooms;

import elements.Item;
import elements.ItemContainer;
import elements.specialItems.Totem;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Room with a totem-activated action
 *
 * @author Denis Akopyan
 * @version 1.0
 */
public class TotemRoom extends Room {
  private final Set<Integer> totemIds;
  private final Supplier<String> totemSuppliedAction;
  private boolean areTotemsPresent;
  
  /**
   * Checks if all required totems are present in the room
   *
   * @return true if all required totems are present in the room
   */
  public boolean areTotemsPresent() {
    return areTotemsPresent;
  }
  
  /**
   * Default constructor
   *
   * @param displayName name of the room
   * @param matchName regex match for the room
   * @param description description of the room
   * @param firstEntryDesc message to display when first entering the room
   * @param lockId room lock id
   * @param lockedMessage message to display when attempting to open this room
   * @param totemIds ids of the required totems for the action to execute
   * @param totemSuppliedAction action to execute once all totems are supplied
   */
  public TotemRoom(@NotNull String displayName,
                   @NotNull String matchName,
                   @NotNull String description,
                   @NotNull String firstEntryDesc,
                   int lockId,
                   @NotNull String lockedMessage,
                   List<Integer> totemIds,
                   Supplier<String> totemSuppliedAction) {
    super(displayName, matchName, description, firstEntryDesc, lockId, lockedMessage);
    this.totemIds = new HashSet<>(totemIds);
    this.totemSuppliedAction = totemSuppliedAction;
  }
  
  /**
   * Attempts to run the totem action
   *
   * @return totem action result
   */
  public Optional<String> tryRunTotemAction() {
    return areTotemsPresent
      ? Optional.of(totemSuppliedAction.get())
      : Optional.empty();
  }
  
  /**
   * Adds given items to the container
   *
   * @param items Items to add
   * @return instance to the items container for method chaining
   */
  @Override
  public ItemContainer add(Item... items) {
    super.add(items);
    
    // Gets the current totems in the room
    var totems = getItems()
        // Iterate over the items
        .stream()
        // Filter the totem items
        .filter(x -> x instanceof Totem)
        // Get the totem ids
        .map(x -> ((Totem)x).getKeyId())
        // Materialize the ids to a set
        .collect(Collectors.toSet());
    
    // Determine whether all of required totems are present
    areTotemsPresent = totems.equals(totemIds);
    
    // Chain instance
    return this;
  }
}
