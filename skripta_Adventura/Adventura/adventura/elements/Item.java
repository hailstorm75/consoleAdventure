package elements;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Class representing an item
 * @author Denis Akopyan
 * @version 1.0
 */
public class Item {
  private final String description;
  private final String name;
  private final boolean canCarry;
  
  /**
   * Getter for the name property
   *
   * @return the name of the item
   */
  public String getName() {
    return name;
  }
  
  /**
   * Getter for the CanCarry property
   *
   * @return value representing whether this item can be carried
   */
  public boolean getCanCarry() {
    return canCarry;
  }
  
  /**
   * Default constructor
   *
   * @param name name of the item
   * @param canCarry determines whether the item can be carried
   */
  public Item(@NotNull String name, boolean canCarry) {
    this(name, canCarry, "A " + name);
  }
  
  public Item(@NotNull String name, boolean canCarry, @NotNull String description) {
    this.name = name;
    this.canCarry = canCarry;
    this.description = description;
  }
  
  /**
   * Getter for the description property
   *
   * @return description of the items container
   */
  public String getDescription() {
    return description;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    var item = (Item) o;
    return canCarry == item.canCarry
        && name.equals(item.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name, canCarry);
  }
}
