import command.Command;
import command.CommandsRepository;
import common.GameBattle;
import console.ConsoleEngine;
import console.TextStyle;
import elements.*;
import elements.rooms.*;
import elements.specialItems.Consumable;
import elements.specialItems.Key;
import elements.specialItems.Note;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class containing the game logic
 *
 * @author Denis Akopyan
 * @version 3.0
 */
public final class Game {
  private final CommandsRepository validCommands;
  private boolean gameOver;
  private Room currentRoom;
  private Room winRoom;
  private int lives = 3;
  private final static String roomDesc = "You are in ";
  private final ItemContainer pocket = new ItemContainer("my backpack",
      "(my(\\s+))|(backpack)",
      "A backpack, very handy when it comes to carrying items");
  
  public Optional<GameBattle> getBattle() {
    return currentRoom instanceof BattleRoom
      ? Optional.of(((BattleRoom) currentRoom).getBattle())
      : Optional.empty();
  }
  
  /**
   * Getter for the CurrentRoom property
   *
   * @return reference to the current room
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }
  
  /**
   * Getter for the Pocket property
   *
   * @return reference to the pocket container
   */
  public ItemContainer getPocket() {
    return pocket;
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
    // --- Initialize items -----------------------------------------------------------------------------
    
    var corridorNote = new Note("paper-note", "(|paper(\\s+|-))(note)", "\"ERTkdfgkhUI*#5fsGO TO?<85Tudy =r00m///8\"");
    var studyRoomKey = new Key("key", 1, "a key");
    var bossRoomBlueKey = new Key("key", "((myster(y|ious)\\s+|)key)", 2, "mysterious key");
    var chocolateSquares = new Consumable("chocolate", "choco(|late)", "a delicious bar of chocolate");
    var chocolatePrison = new Consumable("chocolate", "choco(|late)", "a delicious bar of chocolate");
    var smallBox = new ItemContainer("Small box", "small(|\\s+box)", "a small floating box");
    var mediumBox = new ItemContainer("Medium box", "medium(|\\s+box)", "a medium floating box");
    var largeBox = new ItemContainer("Large box", "large(|\\s+box)", "a large floating box");
    
    var yellowKey = new Key("yellow key", "yellow(|\\s+key)", 69, "a mysterious yellow key");
    var greenKey = new Key("green key", "green(|\\s+key)", 34, "a mysterious green key");
    var blueKey = new Key("blue key", "blue(|\\s+key)", 42, "a mysterious blue key");
    
    var greenCharcoal = new Key("green charcoal", "green(|\\s+charcoal)", 55, "a bright green stick of charcoal");
    var yellowCharcoal = new Key("yellow charcoal", "yellow(|\\s+charcoal)", 66, "a bright yellow stick of charcoal");
    
    var boxes = new ArrayList<ItemContainer>() {
      {
        add(smallBox);
        add(mediumBox);
        add(largeBox);
      }
    };
    Collections.shuffle(boxes);
    boxes.get(0).add(chocolateSquares);
    boxes.get(1).add(bossRoomBlueKey);
    boxes.clear();
    
    // --- Initialize rooms -------------------------------------------------------------------------------
    
    var bedroom = new Room("Bedroom", roomDesc + "your bedroom", "");
    var kitchen = new WinRoom("Kitchen",
        "kitchen",
        new ArrayList<>() {
          {
            add(42);
            add(34);
            add(69);
          }
        },
        "It seems that the kitchen is locked by three mysterious padlocks, each of a different color - "
            + ConsoleEngine.getInstance().formatForegroundStyleCode(TextStyle.Underline)
            + "blue, green, yellow"
            + ConsoleEngine.getInstance().formatForegroundStyleCode(TextStyle.Normal));
    var corridor = new Room("Corridor",
        roomDesc + "the corridor that joins multiple rooms of the house together.",
        "You notice a paper-note lying on the floor.");
    var livingRoom = new Room("Living room",
        "(living)((\\s+room)|)",
        "The living room is silent with nobody around.",
        "You notice a key on the coffee table.");
    var studyRoom = new Room("Study",
        "(study)((\\s+room)|)",
        roomDesc + "the study.",
        "Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "Each of a different color - blue, green, yellow.\n" +
            "Only the blue one had a handle drawn, while the others were missing it.",
        1,
        "It seems that the study is locked");
    
    var bossRoom1 = new BattleRoom("Mystery room",
        "(mystery)((\\s+room)|)",
        "You are inside the mystery room. Darkness. Nothing to see.",
        "You feel something watching you\n" +
            "Suddenly, you are hit by a sharp plus sign\n" +
            "A monster appears!",
        this::removeLives,
        new GameBattle(3, 3, "%d + %d - %d", generator -> generator.get(0) + generator.get(1) - generator.get(2)),
        2,
        "The door to the mystery room is locked. Probably needs a key.");
    var blueRoom = new AutoLockRoom("Blue room",
        "(blue)((\\s+room)|)",
        "You are inside the mysterious blue room. Numbers are written on every wall.",
        "Upon entering you hear the door slam shut behind your back",
        "The door won't budge",
        () -> true);
    var squaresRoom = new Room("Square room",
        "(square(s|))((\\s+room)|)",
        roomDesc + "a room full of square shapes floating in the air, some are combined into boxes, three to be exact\nEach box is of a different size - small, medium and large",
        "");
    
    var greenRoom = new Room("Green room",
        "(green)((\\s+room)|)",
        "You are inside the mysterious green room. Letters are written on every wall.",
        "",
        55,
        "You can't open a door without a handle");
    var circlesRoom = new Room("Circles",
        "(circle(s|))((\\s+room)|)",
        "",
        "");
    var trianglesRoom = new TrapRoom("Triangles",
        "(triangle(s|))((\\s+room)|)",
        "",
        "");
    var numbersRoom = new Room("Numbers",
        "(number(s|))((\\s+room)|)",
        "",
        "");
    var bossRoom2 = new BattleRoom("Mystery room",
        "(mystery)((\\s+room)|)",
        "",
        "",
        null,
        new GameBattle(4, 2, "%d * %d", generator -> generator.get(0) * generator.get(1)),
        666666,
        "");
    
    var yellowRoom = new Room("Yellow room",
        "(yellow)((\\s+room)|)",
        "",
        "",
        66,
        "You can't open a door without a handle");
    var prison = new Room("Prison",
        "prison((\\s+room)|)",
        "",
        "");
    var bossRoom3 = new BattleRoom("Mystery room",
        "(mystery)((\\s+room)|)",
        "",
        "",
        null,
        new GameBattle(5, 3, "%d * %d + %d", generator -> generator.get(0) * generator.get(1) + generator.get(2)),
        666666,
        "");
    
    // --- Map rooms and items ----------------------------------------------------------------------------
    
    corridor.add(corridorNote, chocolateSquares);
    livingRoom.add(studyRoomKey);
    squaresRoom.add(smallBox, mediumBox, largeBox);
    prison.add(chocolatePrison);
    bossRoom1.add(blueKey, greenCharcoal);
    bossRoom2.add(greenKey, yellowCharcoal);
    bossRoom3.add(yellowKey);
    
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
  
  /**
   * Increases the players health
   */
  public void addLives() {
    if (lives == 6)
      return;
    ++lives;
  }
  
  /**
   * Decreases the players health
   *
   * @return returns false is the player has died
   */
  public boolean removeLives() {
    var isAlive = --lives != 0;
    if (!isAlive)
      gameOver = true;
    return isAlive;
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
  
  /**
   * Process a game step
   *
   * @param command Step command
   * @return processing command result
   */
  public String processStep(@NotNull Optional<Command> command) {
    // Process the step command
    var result = processCommand(command);
    // If the player is in the win room..
    if (currentRoom == winRoom) {
      // End the game
      gameOver = true;
      
      // Notify the user
      return "You wake up\nCongratulations! You've won the game.";
    }
    
    // return processing result
    return result;
  }
  
  /**
   * Process a command
   *
   * @param command Command to process
   * @return processing result
   */
  private String processCommand(@NotNull final Optional<Command> command) {
    // If the command is invalid..
    if (command.isEmpty())
      // notify the user using a random message
      return switch ((int) ((Math.random() * (5 - 1)) + 1)) {
        case 1 -> "Come again?";
        case 2 -> "Didn't catch that.";
        case 3 -> "I don't understand.";
        case 4 -> "Not sure what you mean by that.";
        default -> "Huh?";
      };
    
    // Extract the command
    var extracted = command.get();
    
    // Process the command based on its category
    return switch (extracted.getType()) {
      case Help -> addExists(manual(extracted));
      case Carry -> addExists(carry(extracted));
      case Goto -> go(extracted);
      case Unlock -> addExists(unlock(extracted));
      case Where -> addExists(where());
      case Examine -> addExists(examine(extracted));
      case Eat -> addExists(eat(extracted));
      case End -> end();
    };
  }
  
  /**
   * Gets the description of the current room
   *
   * @return current room description
   */
  public String getRoomDescription() {
    return currentRoom.getDescription();
  }
  
  /**
   * Gets the names of the connected rooms
   *
   * @return names of the connected rooms
   */
  public String getRoomExists() {
    return currentRoom.getRoomNames();
  }
  
  private String addExists(String message) {
    return message + '\n' + currentRoom.getRoomNames();
  }
  
  // --- Commands ------------------------------------------------------------------------------------------------------
  
  /**
   * Command for ending the game
   *
   * @return processing result
   */
  private String end() {
    // Set the game state
    gameOver = true;
    // Notify the user
    return "game stopped";
  }
  
  /**
   * Command for eating consumables that are carried in the players pocket
   *
   * @param command eat command source
   * @return processing result
   */
  private String eat(@NotNull final Command command) {
    // If the command is missing the parameter..
    if (command.hasNoFirstParameter())
      // ask the user to specify it
      return "Eat what?";
    
    // Try retrieve the item from the pocket
    var item = pocket.takeOut(command.getFirstParameter());
    
    // If no item was found
    if (item.isEmpty())
      // notify the user
      return "I don't know what that is";
    
    // If the item is consumable..
    if (item.get() instanceof Consumable) {
      // Increase health
      addLives();
      
      // Notify the user
      return "You take the " + item.get().getDisplayName() + " and eat it";
    }
    
    // Notify the user
    return "Can't eat that";
  }
  
  /**
   * Command for carrying items
   *
   * @param command carry command source
   * @return processing result
   */
  private String carry(@NotNull final Command command) {
    // If the command is missing the parameter..
    if (command.hasNoFirstParameter())
      // ask the user to specify it
      return "Carry what?";
    
    if (command.hasNoSecondParameter()) {
      // Try to get the item to carry
      var item = currentRoom.takeOut(command.getFirstParameter());
      
      // If no item was found..
      if (item.isEmpty())
        // notify the user
        return "I don't know what that is";
      
      // Put the item in the pocket
      pocket.add(item.get());
      
      // Notify the user
      return "You take the " + item.get().getDisplayName() + " and put it inside your pocket";
    }
    
    var container = currentRoom
        .getItems()
        .stream()
        .filter(item -> item instanceof ItemContainer)
        .map(item -> (ItemContainer) item)
        .filter(c -> c.isMatch(command.getSecondParameter()))
        .findFirst();
    
    if (container.isEmpty())
      return "Don't know where to take " + command.getFirstParameter() + " from";
    
    var extractedC = container.get();
    var item = extractedC.takeOut(command.getFirstParameter());
    
    if (item.isEmpty())
      return "There is no such item inside " + command.getSecondParameter();
    
    pocket.add(item.get());
    
    return "You take the " + item.get().getDisplayName() + " and put it inside your pocket";
  }
  
  /**
   * Command for retrieving the manual
   *
   * @param command manual command source
   * @return processing result
   */
  private String manual(@NotNull final Command command) {
    return command.hasNoFirstParameter()
        ? "Type what you wish to do and press enter (more than one way to enter a command is supported)"
        + "\n"
        + "The following command types are available:\n"
        + validCommands.getCommandsManual()
        : validCommands.getCommandManual(command.getFirstParameter());
  }
  
  /**
   * Command for examining items
   *
   * @param command examine command source
   * @return processing result
   */
  private String examine(@NotNull final Command command) {
    // If the command is missing the parameter..
    if (command.hasNoFirstParameter())
      // ask the user to specify it
      return "But what?";
    
    // Try get the item to examine
    var item = currentRoom
        // Get all of the room items
        .getItems()
        // Iterate over each item
        .stream()
        // Find the items that match the request
        .filter(x -> x.isMatch(command.getFirstParameter()))
        // Try get the first item
        .findFirst();
    
    // If no item was found..
    if (item.isEmpty())
      // notify the player
      return "That's not here";
    
    var extracted = item.get();
    
    if (extracted instanceof ItemContainer) {
      var container = (ItemContainer) extracted;
      var size = container.getItems().size();
      
      return "You take a look inside the "
          + container.getDisplayName().toLowerCase()
          + ". "
          + (size == 0
          ? "It is empty."
          : "Inside you find" + (size > 1 ? " " : " a ") + container.itemNames());
    } else if (extracted instanceof Note) {
      return "You pick up the " + extracted.getDisplayName().toLowerCase() + " and read what it says:\n"
          + extracted.getDescription() + "\n"
          + "You put the " + extracted.getDisplayName().toLowerCase() + " back to where you've found it";
    }
    
    // Get the item description
    return extracted.getDescription();
  }
  
  /**
   * Command for unlocking rooms and item containers
   *
   * @param command unlock command source
   * @return processing result
   */
  private String unlock(@NotNull final Command command) {
    // If the command is missing the parameter..
    if (command.hasNoFirstParameter())
      // ask the user to specify it
      return "Unlock what?";
    
    // Get the entity name to unlock
    var name = command.getFirstParameter();
    
    // Get all of the keys
    var keys = pocket
        // Get all carried items
        .getItems()
        // Iterate over every item
        .stream()
        // Materialize a collection of key items
        .filter(x -> x instanceof Key).map(x -> (Key) x).collect(Collectors.toUnmodifiableList());
    
    // Try to get a room
    var room = currentRoom.getRoom(name);
    // If no room was found..
    if (room.isEmpty()) {
      // Get an lockable item
      var item = currentRoom
          // Get all room items
          .getItems()
          // Iterate over all items
          .stream()
          // Find matching items that are containers
          .filter(x -> x instanceof ItemContainer && x.isMatch(name))
          // Cast the items to item containers
          .map(x -> (ItemContainer) x)
          // Try get the first lockable item
          .findFirst();
      
      // If no item was found..
      if (item.isEmpty())
        // notify the user
        return "Don't know what that is";
      
      // For every key..
      for (var key : keys)
        // Try to unlock the container with it
        if (item.get().unlock(key)) {
          pocket.takeOut(key.getDisplayName());
          
          // notify the user
          return "You've unlocked the " + name;
        }
      
    }
    // Otherwise..
    else {
      // Get the room to unlock
      var roomElement = room.get();
      
      // If the room is not locked..
      if (!roomElement.isLocked())
        // notify the user
        return name + " is not locked";
        // Otherwise..
      else {
        // If the room is the win room..
        if (roomElement instanceof WinRoom) {
          // Cast the room
          var winRoom = (WinRoom) roomElement;
          
          // If the room was unlocked..
          if (winRoom.unlock(keys))
            // notify the user
            return "You've unlocked the " + name;
            // Otherwise
          else
            // notify the user
            return name + " won't open. Probably needs more keys";
        }
        // Otherwise..
        else
          // For every key..
          for (var key : keys)
            // Try to unlock the room with it
            if (roomElement.unlock(key)) {
              pocket.takeOut(key.getDisplayName());
              
              // notify the user
              return "You've unlocked the " + name;
            }
      }
    }
    
    // notify the user
    return "Don't know what that is";
  }
  
  /**
   * Command for returning the current room description
   *
   * @return current room description
   */
  private String where() {
    // Get the current room description
    return currentRoom.getDescription();
  }
  
  /**
   * Command for navigating to a different room
   *
   * @param command go command source
   * @return processing result
   */
  private String go(@NotNull final Command command) {
    // If the command is missing the parameter..
    if (command.hasNoFirstParameter())
      // ask the user to specify it
      return addExists("Where to? Specify the room name");
    
    // Get the specified room name
    var room = command.getFirstParameter();
    
    // Try to get the next room
    var nextRoom = currentRoom.getRoom(room);
    
    // If no room was found..
    if (nextRoom.isEmpty())
      // notify the user with a random message
      return switch ((int) ((Math.random() * (4 - 1)) + 1)) {
        case 1 -> "Can't see where that is";
        case 2 -> "I don't know where that is.";
        case 3 -> "Can't go there.";
        default -> "Where's that?";
      };
    
    // Unwrap the found room
    var extracted = nextRoom.get();
    // If the room is locked..
    if (extracted.isLocked())
      // get the room locked message
      //noinspection OptionalGetWithoutIsPresent
      return addExists(extracted.getLockedMessage().get());
    
    // Set the next room as the current
    currentRoom = extracted;
    
    // Describe the newly entered room
    var description = currentRoom.getDescription();
    // Mark the room as discovered
    currentRoom.discover();
  
    return currentRoom instanceof BattleRoom
      ? description
      : addExists(description);
  }
}
