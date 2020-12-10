import command.Command;
import console.Color;
import console.ConsoleEngine;
import console.TextStyle;
import elements.rooms.BattleRoom;
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
      // Get the user-input
      var input = ConsoleEngine.getInstance().getInput();
      // Compose the input into a command
      var command = Command.initialize(input);
      
      // Process the command and get result
      var output = game.processStep(command);
      // Draw the game HUD
      drawHud();
      // Print the command result
      ConsoleEngine.getInstance().typeOutLn(output);
      
      if (getGameInstance().getCurrentRoom() instanceof BattleRoom)
        runBattle();
    }
  }
  
  private void runBattle() throws InterruptedException {
    var battleRoom = (BattleRoom) getGameInstance().getCurrentRoom();
    var hits = 0;
    while (hits < battleRoom.getRounds()) {
      // Draw the game HUD
      drawHud();
      
      var problem = battleRoom.generateProblem();
      var attack = ConsoleEngine
          .getInstance()
          .println(problem.getItem1())
          .getInput(10);
      
      if (attack.isEmpty()) {
        if (game.removeLives())
          continue;
        break;
      }
      
      var answer = TryParse(attack.get());
      if ((answer.isEmpty() || !answer.get().equals(problem.getItem2()))) {
        if (game.removeLives())
          continue;
        break;
      }
      
      ++hits;
    }
  }
  
  private static Optional<Integer> TryParse(@NotNull String value) {
    try {
      return Optional.of(Integer.valueOf(value));
    } catch (NumberFormatException x) {
      return Optional.empty();
    }
  }
}
