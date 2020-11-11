import command.Command;
import command.CommandsRepository;
import elements.ItemContainer;
import elements.Key;
import elements.Room;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Collectors;

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
  private ItemContainer backpack = new ItemContainer("my backpack", "A backpack, very handy when it comes to carrying items");
  
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
    var treasury = new Room("treasury", "gold everywhere", 42, "locked behind a thick steel door");
    
    var workTable = new ItemContainer("worktable", "A dusty worktable covered in paper");
    var noteWithCode = new Key("note", 42);
  
    hall.connect(study, corridor, kitchen);
    office.connect(corridor, treasury);
    workTable.add(noteWithCode);
    hall.add(workTable);
    
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
      case Carry -> carry(extracted);
      case Goto -> go(extracted);
      case Unlock -> unlock(extracted);
      case Where -> where();
      case Examine -> examine(extracted);
      case End -> end();
    };
  }
  
  public String getRoomDescription() {
    return currentRoom.getDescription();
  }
  
  private String end() {
    gameOver = true;
    return "game stopped";
  }
  
  private String carry(Command command) {
    if (!command.hasParameter())
      return "Carry what?";
    
    var item = currentRoom.takeOut(command.getParameter());
    
    if (item.isEmpty())
      return "I don't know what that is";
    
    backpack.add(item.get());
    
    return "Put the " + item.get().getName() + " in the backpack";
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
  
  private String examine(@NotNull final Command command) {
    if (!command.hasParameter())
      return "But what?";
    
    var item = currentRoom
        .getItems()
        .stream()
        .filter(x -> x.getName().equalsIgnoreCase(command.getParameter()))
        .findFirst();
    
    if (item.isEmpty())
      return "That's not here";
    return item.get().getDescription();
  }
  
  private String unlock(@NotNull final Command command) {
    if (!command.hasParameter())
      return "Unlock what?";
    
    var name = command.getParameter();
    var room = currentRoom.getRoom(name);
    if (room.isEmpty())
    {
      var item = currentRoom
          .getItems()
          .stream()
          .filter(x -> x instanceof ItemContainer && x.getName().equalsIgnoreCase(name))
          .map(x -> (ItemContainer)x)
          .findFirst();
      
      if (item.isEmpty())
        return "Don't know what that is";
    }

    var roomElement = room.get();
    
    if (!roomElement.isLocked())
      return name + " is not locked";
    else
      for (var key : backpack.getItems().stream().filter(x -> x instanceof Key).map(x -> (Key)x).collect(Collectors.toUnmodifiableList()))
        if (roomElement.unlock(key))
          return name + " is now open";
    
    return "Don't know what that is";
  }
  
  private String where() {
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

    var extracted = nextRoom.get();
    if (extracted.isLocked())
      return extracted.getLockedMessage().get();
    
    currentRoom = extracted;
    return currentRoom.getDescription();
  }
}
