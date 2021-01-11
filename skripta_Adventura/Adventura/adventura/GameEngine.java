import command.Command;
import common.GameBattle;
import console.Color;
import console.ConsoleEngine;
import console.TextStyle;
import elements.rooms.TrapRoom;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Class for running the game
 *
 * @author Denis Akopyan
 * @version 3.0
 */
public final class GameEngine {
  private final Game game;
  private final static GameEngine instance = new GameEngine();
  
  /**
   * Getter for the singleton GameEngine instance
   *
   * @return singleton instance
   */
  public static Game getGameInstance() {
    return getInstance().game;
  }
  
  /**
   * Getter of the singleton instance of the game engine
   *
   * @return singleton game engine instance
   */
  public static GameEngine getInstance() {
    return instance;
  }
  
  /**
   * Default constructor
   */
  private GameEngine() {
    game = new Game();
  }
  
  private void drawHud() {
    var lives = game.getLives();
    
    ConsoleEngine
        .getInstance()
        .setStyle(TextStyle.Bold)
        .print("HP: ")
        .setStyle(Color.Red, TextStyle.Bright)
        .print("<3".repeat(lives - 1))
        .setStyle(TextStyle.BrightBold)
        .print("<3")
        .setDefaultStyle()
        .print(" ".repeat(20 - lives * 2))
        .setStyle(TextStyle.Bold)
        .print("Inventory: ")
        .setDefaultStyle()
        .print(game.getPocket().getItems().size() + "")
        .print("\n");
    
    System.out.println();
  }
  
  /**
   * Runs the game loop
   */
  public void run() throws InterruptedException {
    drawHud();
    
    ConsoleEngine
        .getInstance()
        .setStyle(Color.Yellow)
        .typeOutLn(game.getIntroduction())
        .setDefaultStyle()
        .println(game.getRoomExists());
    
    // Until the game is over..
    while (!game.isGameOver()) {
      var input = "";
      
      if (game.getCurrentRoom() instanceof TrapRoom && ((TrapRoom) game.getCurrentRoom()).isTrapActivated())
      {
        var inputTry = ConsoleEngine.getInstance().getInput(10);
        if (inputTry.isPresent())
          input = inputTry.get();
        else
        {
          ConsoleEngine.getInstance().println("You've been killed by a trap");
          return;
        }
      }
      // Otherwise..
      else
        // Get the user-input
        input = ConsoleEngine.getInstance().getInput();
      
      // Compose the input into a command
      var command = Command.initialize(input);
      
      // Process the command and get result
      var output = game.processStep(command);
      // Draw the game HUD
      drawHud();
      // Print the command result
      ConsoleEngine.getInstance().typeOutLn(output);
      
      var battle = getGameInstance().getBattle();
      if (battle.isPresent())
        if (runBattle(battle.get()))
        {
          ConsoleEngine.getInstance().println("You've been killed in battle");
          return;
        }
    }
  }
  
  private boolean runBattle(GameBattle battle) throws InterruptedException {
    while (!battle.isDefeated()) {
      // Draw the game HUD
      drawHud();

      var problem = battle.nextAttack();
      var attack = ConsoleEngine
          .getInstance()
          .println(problem)
          .getInput(10);

      if (attack.isEmpty()) {
        if (game.removeLives())
          continue;
        return false;
      }

      var answer = TryParse(attack.get());
      if ((answer.isEmpty() || !battle.defend(answer.get()))) {
        if (game.removeLives())
          continue;
        return false;
      }
    }
  
    return true;
  }
  
  private static Optional<Integer> TryParse(@NotNull String value) {
    try {
      return Optional.of(Integer.valueOf(value));
    } catch (NumberFormatException x) {
      return Optional.empty();
    }
  }
}
