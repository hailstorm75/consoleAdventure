package command;

/**
 * All supported commands
 * @author Denis Akopyan
 * @version 1.0
 */
public enum CommandType {
  /**
   * Displays the game manual
   *
   * @apiNote if a command name is provided via a parameter, documentation is displayed for the given command
   */
  Help("Help"),
  /**
   * Unlocks a given item container
   */
  Unlock("Unlock"),
  /**
   * Advances the player to the room provided via a parameter
   */
  Goto("Go to"),
  /**
   * Described where the player is
   */
  Where("Where"),
  /**
   * Ends the game
   */
  End("End");
  
  private final String name;
  
  /**
   * Default constructor
   *
   * @param name string representation of the enum
   */
  CommandType(String name) {
    this.name = name;
  }
  
  /**
   * Getter for the name property
   *
   * @return enum string representation
   */
  public String getName() {
    return name;
  }
}
