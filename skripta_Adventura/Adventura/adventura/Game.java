import command.Command;
import command.CommandsRepository;
import elements.Room;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Class containing the game logic
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Denis Akopyan
 * @version 3.0
 */
public final class Game {
  private final CommandsRepository validCommands;
  private boolean gameOver;
  private Room currentRoom;
  private Room winRoom;
  private int lives = 3;
  
  /**
   * Getter for the lives property
   *
   * @return number of lives of the player
   */
  public int getLives() {
    return lives;
  }
  
  /**
   * Default constructor
   */
  public Game() {
    initializeRooms();
    validCommands = new CommandsRepository();
  }
  
  /**
   * Initializes the individual rooms and weaves them together into a singular map
   */
  private void initializeRooms() {
    var hall = new Room("hall", "entrance hallway of the mansion");
    var study = new Room("study", "study room full of dusty books, cobwebs and probably spiders");
    var kitchen = new Room("kitchen", "a very dirty kitchen, rusty pots scattered everywhere");
    var corridor = new Room("corridor", "long corridor leading into the unknown");
    var office = new Room("office", "an old office with a chair, worktable covered in paper, couple of cupboards");
    var treasury = new Room("treasury", "gold everywhere");
    
    hall.connect(study, corridor, kitchen);
    office.connect(corridor, treasury);
    
    currentRoom = hall;
    winRoom = treasury;
  }
  
  /**
   * Gets the introduction to the game
   */
  public String getIntroduction() {
    return "Welcome!\n" +
        "This is just another remarkably boring adventure game.\n" +
        "Type 'help' for instructions.";
  }
  
  /**
   * Gets the game epilogue
   */
  public String getEpilogue() {
    return currentRoom == winRoom
        ? "Congratulations"
        : "Thank you for playing.";
  }
  
  /**
   * Checks whether the game is over
   *
   * @return true if the game is over
   */
  public boolean isGameOver() {
    return gameOver;
  }
  
  public String process(@NotNull Optional<Command> command) {
    if (currentRoom == winRoom)
      gameOver = true;
    
    return processCommand(command);
  }
  
  private String processCommand(@NotNull final Optional<Command> command) {
    if (command.isEmpty())
      return switch ((int) ((Math.random() * (5 - 1)) + 1)) {
        case 1 -> "Come again?";
        case 2 -> "Didn't catch that.";
        case 3 -> "I don't understand.";
        case 4 -> "Not sure what you mean by that.";
        default -> "Huh?";
      };
    
    var extracted = command.get();
    
    return switch (extracted.getType()) {
      case Help -> manual(extracted);
      case Goto -> go(extracted);
      case Where -> where(extracted);
      case End -> end(extracted);
    };
  }
  
  public String getRoomDescription() {
    return currentRoom.getDescription();
  }
  
  private String end(@NotNull final Command command) {
    gameOver = true;
    return "game stopped";
  }
  
  private String manual(@NotNull final Command command) {
    return !command.hasParameter()
        ? "Ztratil ses. Jsi sam(a). Toulas se\n"
        + "po arealu skoly na Jiznim meste.\n"
        + "\n"
        + "Type what you wish to do and press enter (more than one way to enter a command is supported)"
        + "\n"
        + "The following command types are available:\n"
        + validCommands.getCommandsManual()
        : validCommands.getCommandManual(command.getParameter());
  }
  
  private String where(Command extracted) {
    return currentRoom.getDescription();
  }
  
  private String go(@NotNull final Command command) {
    // If the command is missing the parameter..
    if (!command.hasParameter())
      // ask the user to specify it
      return "Where to? Specify the room name";
    
    // Get the specified room name
    var room = command.getParameter();
    
    var nextRoom = currentRoom.getRoom(room);
    
    if (nextRoom.isEmpty())
      return switch ((int) ((Math.random() * (4 - 1)) + 1)) {
        case 1 -> "Can't see where that is";
        case 2 -> "I don't know where that is.";
        case 3 -> "Can't go there.";
        default -> "Where's that?";
      };
    
    currentRoom = nextRoom.get();
    return currentRoom.getDescription();
  }
}
