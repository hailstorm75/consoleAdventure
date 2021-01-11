package elements.rooms;

import elements.Item;
import elements.ItemContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Class representing a room which triggers a death trap upon extracting an object
 *
 * @author Denis Akopyan
 * @version 1.0
 */
public class TrapRoom extends Room {
  private final String trapMessage;
  private final String exitMessage;
  private boolean trapActivated;
  
  /**
   * Getter for the TrapMessage property
   *
   * @return trap message
   */
  public String getTrapMessage() {
    return trapMessage;
  }
  
  /**
   * Getter for the ExitMessage property
   *
   * @return exit message
   */
  public String getExitMessage() {
    return exitMessage;
  }
  
  /**
   * Determines whether the trap had been activated
   *
   * @return true if the trap had been activated
   */
  public boolean isTrapActivated() {
    return trapActivated;
  }
  
  /**
   * Default constructor
   *
   * @param displayName    item display name
   * @param matchName      item match string
   * @param description    item description
   * @param firstEntryDesc message to display upon first room entry
   * @param trapMessage    message to display when the trap is triggered
   * @param exitMessage    message to display when leaving the room
   */
  public TrapRoom(@NotNull String displayName,
                  @NotNull String matchName,
                  @NotNull String description,
                  @NotNull String firstEntryDesc,
                  @NotNull String trapMessage,
                  @NotNull String exitMessage) {
    super(displayName, matchName, description, firstEntryDesc);
    this.trapMessage = trapMessage;
    this.exitMessage = exitMessage;
  }
  
  @Override
  public Optional<Item> takeOut(@NotNull String name) {
    var item = super.takeOut(name);
    if (item.isPresent()) {
      trapActivated = true;
    }
    
    return item;
  }
  
  /**
   * Adds given items to the container
   *
   * @param items Items to add
   * @return instance to the items container for method chaining
   */
  @Override
  public ItemContainer add(Item... items) {
    // If the more than one item is attempted to be added to the given room..
    if (items.length != 1 || this.getItems().size() > 0) {
      // throw an exception
      throw new UnsupportedOperationException("Only one item can be added to the room");
    }
    
    // Add the item
    return super.add(items);
  }
}
