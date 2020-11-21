package elements;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Class representing an item
 * @author Denis Akopyan
 * @version 1.0
 */
public class Item {
  private final String description;
  private final String displayName;
  private final Pattern matcher;
  private final boolean canCarry;
  
  /**
   * Getter for the DisplayName property
   *
   * @return the display name of the item
   */
  public String getDisplayName() {
    return displayName;
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
   * Getter for the description property
   *
   * @return description of the items container
   */
  public String getDescription() {
    return description;
  }
  
  public Item(@NotNull String displayName, boolean canCarry, @NotNull String description) {
    this(displayName, displayName, canCarry, description);
  }
  
  public Item(@NotNull String displayName, @NotNull String matchName, boolean canCarry, @NotNull String description) {
    if (displayName.length() == 0)
      throw new IllegalArgumentException("displayName cannot be empty");
    if (matchName.length() == 0)
      throw new IllegalArgumentException("matchName cannot be empty");
    
    this.displayName = displayName;
    this.canCarry = canCarry;
    this.description = description;
    this.matcher = Pattern.compile(matchName, Pattern.CASE_INSENSITIVE);
  }
  
  public boolean isMatch(@NotNull String name) {
    return matcher.matcher(name).matches();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    var item = (Item) o;
    return canCarry == item.canCarry
        && displayName.equals(item.displayName)
        && matcher.pattern().equals(item.matcher.pattern());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(displayName, canCarry, description);
  }
}
