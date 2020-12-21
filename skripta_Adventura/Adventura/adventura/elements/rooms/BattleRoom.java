package elements.rooms;

import common.GameBattle;
import org.jetbrains.annotations.NotNull;

/**
 * Room with a battle sequence
 *
 * @author Denis Akopyan
 */
public class BattleRoom extends Room {
  private final Runnable firstEntryAction;
  private final GameBattle battle;
  
  public GameBattle getBattle() {
    return battle;
  }
  
  public boolean isDefeated() {
    return battle.isDefeated();
  }
  
  /**
   * Default constructor
   * @param displayName room name
   * @param matchName room match name
   * @param description room description
   * @param firstEntryDescription first entry message
   * @param firstEntryAction first action to perform upon room entry
   * @param battle room battle logic wrapper
   * @param lockId lock key id
   * @param lockedMessage locked message
   */
  public BattleRoom(@NotNull String displayName,
                    @NotNull String matchName,
                    @NotNull String description,
                    @NotNull String firstEntryDescription,
                    Runnable firstEntryAction,
                    GameBattle battle,
                    int lockId,
                    @NotNull String lockedMessage) {
    super(displayName, matchName, description, firstEntryDescription, lockId, lockedMessage);
  
    this.firstEntryAction = firstEntryAction;
    this.battle = battle;
  }
  
  private void firstEntry() {
    if (firstEntryAction != null)
      firstEntryAction.run();
  }
  
  @Override
  public String getDescription() {
    if (!isDiscovered())
      firstEntry();
    
    return super.getDescription();
  }
}
