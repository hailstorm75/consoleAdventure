import command.Command;
import console.Color;
import console.ConsoleEngine;
import console.TextStyle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class for running the game
 *
 * @author Denis Akopyan
 * @version 3.0
 */
public final class GameEngine {
  private final Game game;
  private final static GameEngine instance = new GameEngine();
  
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
  public void run() {
    drawHud();
  
    ConsoleEngine
        .getInstance()
        .setStyle(Color.Yellow)
        .typeOutLn(game.getIntroduction())
        .setDefaultStyle()
        .println(game.getRoomExists());
  
    while (!game.isGameOver()) {
      var input = getInput();
      var command = Command.initialize(input);
  
      var output = game.processStep(command);
      drawHud();
      ConsoleEngine.getInstance().typeOutLn(output);
    }
    
    System.out.println(game.getEpilogue());
  }
  
  /**
   * Retrieves user-input from the terminal
   *
   * @return user-provided input
   */
  private String getInput() {
    ConsoleEngine
        .getInstance()
        .setStyle(Color.Blue)
        .print("> ")
        .setDefaultStyle();
    
    var input = new BufferedReader(new InputStreamReader(System.in));
    try {
      return input.readLine();
    }
    catch (java.io.IOException exc) {
      System.out.println("Failed to read input: " + exc.getMessage());
      
      return "";
    }
  }
}
