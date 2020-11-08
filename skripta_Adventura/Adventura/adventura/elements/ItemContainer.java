package elements;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ItemContainer extends Item {
  private final String description;
  private Set<Item> items = new HashSet<>();
  
  /**
   * Getter for the description property
   *
   * @return description of the items container
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * Default constructor
   *
   * @param name     name of the item container
   * @param description description of the item container
   */
  public ItemContainer(@NotNull String name, @NotNull String description) {
    super(name, false);
    this.description = description;
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
