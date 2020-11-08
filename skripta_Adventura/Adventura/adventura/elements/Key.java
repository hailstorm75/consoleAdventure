package elements;

import org.jetbrains.annotations.NotNull;

public class Key extends Item {
  private final int id;
  
  public int getId() {
    return id;
  }
  
  /**
   * Default constructor
   *
   * @param name     name of the item
   */
  public Key(@NotNull String name, int id) {
    super(name, true);
    this.id = id;
  }
}
