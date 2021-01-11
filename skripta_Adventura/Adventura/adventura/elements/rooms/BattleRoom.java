package elements.rooms;

import common.GameBattle;
import org.jetbrains.annotations.NotNull;

/**
 * Room with a battle sequence
 *
 * @author Denis Akopyan
 * @version 1.0
 */
public class BattleRoom extends Room {
  private final Runnable firstEntryAction;
  private final GameBattle battle;
  
  /**
   * Getter for the Battle property
   *
   * @return room battle logic
   */
  public GameBattle getBattle() {
    return battle;
  }
  
  /**
   * Determines whether the room battle is defeated
   *
   * @return is the room battle defeated
   */
  public boolean isDefeated() {
    return battle.isDefeated();
  }
  
  /**
   * Default constructor
   * @param displayName room name
   * @param matchName room match name
   * @param description room description
   * @param firstEntryDesc first entry message
   * @param firstEntryAction first action to perform upon room entry
   * @param battle room battle logic wrapper
   * @param lockId lock key id
   * @param lockedMessage locked message
   */
  public BattleRoom(@NotNull String displayName,
                    @NotNull String matchName,
                    @NotNull String description,
                    @NotNull String firstEntryDesc,
                    Runnable firstEntryAction,
                    GameBattle battle,
                    int lockId,
                    @NotNull String lockedMessage) {
    super(displayName, matchName, description, firstEntryDesc, lockId, lockedMessage);
  
    this.firstEntryAction = firstEntryAction;
    this.battle = battle;
  }
  
  private void firstEntry() {
    // If there is a first entry action..
    if (firstEntryAction != null) {
      // Run the entry action
      firstEntryAction.run();
    }
  }
  
  /**
   * Retrieves the room description
   *
   * @return room description
   */
  @Override
  public String getDescription() {
    // If the room hadn't yet been discovered..
    if (!isDiscovered()) {
      // Display the first entry message
      firstEntry();
    }
    
    return super.getDescription();
  }
}
