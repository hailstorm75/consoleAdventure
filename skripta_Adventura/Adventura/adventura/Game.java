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
  private final ItemContainer backpack = new ItemContainer("my backpack", "(my(\\s+))|(backpack)", "A backpack, very handy when it comes to carrying items");
  
  public Room getCurrentRoom() {
    return currentRoom;
  }
  
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
    // --- Initialize rooms -------------------------------------------------------------------------------
    
    var bedroom = new Room("Bedroom", "", "");
    var kitchen = new Room("Kitchen", "", "");
    var corridor = new Room("Corridor", "the corridor that joins multiple rooms of the house together.", "You notice a paper-note lying on the floor.");
    var livingRoom = new Room("Living room", "(living)((\\s+room)|)", "The living room is silent with nobody around.", "You notice a key on the coffee table.");
    var studyRoom = new Room("Study room", "(study)((\\s+room)|)", "the study.", "Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
        "\n" +
        "Each of a different color - blue, green, yellow.\n" +
        "\n" +
        "Only the blue one had a handle drawn, while the others were missing it.");
    
    var blueRoom = new Room("Blue room", "(blue)((\\s+room)|)", "", "");
    var squaresRoom = new Room("Squares", "(square(s|))((\\s+room)|)", "", "");
    var bossRoom1 = new Room("Mystery room", "(mystery)((\\s+room)|)", "", "");
    
    var greenRoom = new Room("Green room", "(green)((\\s+room)|)", "", "");
    var circlesRoom = new Room("Circles", "(circle(s|))((\\s+room)|)", "", "");
    var trianglesRoom = new Room("Triangles", "(triangle(s|))((\\s+room)|)", "", "");
    var numbersRoom = new Room("Numbers", "(number(s|))((\\s+room)|)", "", "");
    var bossRoom2 = new Room("Mystery room", "(mystery)((\\s+room)|)", "", "");
    
    var yellowRoom = new Room("Yellow room", "(yellow)((\\s+room)|)", "", "");
    var prison = new Room("Prison", "prison((\\s+room)|)", "", "");
    var bossRoom3 = new Room("Mystery room", "(mystery)((\\s+room)|)", "", "");
    
    // ----------------------------------------------------------------------------------------------------
    
    corridor.connect(bedroom, kitchen, livingRoom, studyRoom);
    studyRoom.connect(blueRoom, greenRoom, yellowRoom);
    blueRoom.connect(bossRoom1, squaresRoom);
    greenRoom.connect(circlesRoom, trianglesRoom, numbersRoom);
    circlesRoom.connect(bossRoom2);
    prison.connect(yellowRoom, bossRoom3);
    
    // --- Set end and start rooms ------------------------------------------------------------------------
    
    currentRoom = bedroom;
    winRoom = kitchen;
  }
  
  public void addLives() {
    ++lives;
  }
  
  public boolean removeLives() {
    return --lives != 0;
  }
  
  /**
   * Gets the introduction to the game
   */
  public String getIntroduction() {
    return "You wake up in your room, bright light from the sun is shing inside. Today is going to be a great day!\n" +
        "Just need to grab a snack, pack the books and head to school.\n" +
        "Hopefully, there won't be a math test today, those stick.\n";
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
    var result = processCommand(command);
    if (currentRoom == winRoom)
      gameOver = true;
    
    return result;
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
  
  public String getRoomExists() {
    return currentRoom.getRoomNames();
  }
  
  private String end() {
    gameOver = true;
    return "game stopped";
  }
  
  private String carry(Command command) {
    if (command.hasNoParameter())
      return "Carry what?";
    
    var item = currentRoom.takeOut(command.getParameter());
    
    if (item.isEmpty())
      return "I don't know what that is";
    
    backpack.add(item.get());
    
    return "Put the " + item.get().getDisplayName() + " in the backpack";
  }
  
  private String manual(@NotNull final Command command) {
    return command.hasNoParameter()
        ? "Type what you wish to do and press enter (more than one way to enter a command is supported)"
        + "\n"
        + "The following command types are available:\n"
        + validCommands.getCommandsManual()
        : validCommands.getCommandManual(command.getParameter());
  }
  
  private String examine(@NotNull final Command command) {
    if (command.hasNoParameter())
      return "But what?";
    
    var item = currentRoom
        .getItems()
        .stream()
        .filter(x -> x.getDisplayName().equalsIgnoreCase(command.getParameter()))
        .findFirst();
    
    if (item.isEmpty())
      return "That's not here";
    return item.get().getDescription();
  }
  
  private String unlock(@NotNull final Command command) {
    if (command.hasNoParameter())
      return "Unlock what?";
    
    var name = command.getParameter();
    var room = currentRoom.getRoom(name);
    if (room.isEmpty()) {
      var item = currentRoom
          .getItems()
          .stream()
          .filter(x -> x instanceof ItemContainer && x.getDisplayName().equalsIgnoreCase(name))
          .map(x -> (ItemContainer) x)
          .findFirst();
      
      if (item.isEmpty())
        return "Don't know what that is";
    }
    else
    {
      var roomElement = room.get();
  
      if (!roomElement.isLocked())
        return name + " is not locked";
      else
        for (var key : backpack.getItems().stream().filter(x -> x instanceof Key).map(x -> (Key) x).collect(Collectors.toUnmodifiableList()))
          if (roomElement.unlock(key))
            return name + " is now open";
    }
    
    return "Don't know what that is";
  }
  
  private String where() {
    return currentRoom.getDescription();
  }
  
  private String go(@NotNull final Command command) {
    // If the command is missing the parameter..
    if (command.hasNoParameter())
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
      //noinspection OptionalGetWithoutIsPresent
      return extracted.getLockedMessage().get();
    
    currentRoom = extracted;
    return currentRoom.getDescription();
  }
}
