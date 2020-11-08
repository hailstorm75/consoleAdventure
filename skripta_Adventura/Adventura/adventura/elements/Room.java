package elements;

import console.ConsoleEngine;
import console.TextStyle;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Class representing a room/level
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Denis Akopyan
 * @version 3.0
 */
public class Room extends ItemContainer {
  private final Set<Room> rooms;
  private int lockId;
  private final String lockedMessage;
  
  /**
   * Default constructor
   *
   * @param name name of the given room
   * @param description room description
   */
  public Room(@NotNull String name, @NotNull String description) {
    this(name, description, -1, "");
  }
  
  public Room(@NotNull String name, @NotNull String description, int lockId, @NotNull String lockedMessage) {
    super(name, description);
    this.rooms = new HashSet<>();
    this.lockId = lockId;
    this.lockedMessage = lockedMessage;
  }
  
  public final boolean isLocked() {
    return lockId != -1;
  }
  
  public final Optional<String> getLockedMessage() {
    return isLocked()
      ? Optional.of(lockedMessage)
      : Optional.empty();
  }
  
  public final boolean unlock(Key key) {
    if (!isLocked())
      return false;
    if (lockId != key.getId())
      return false;
    
    lockId = -1;
    return true;
  }
  
  /**
   * Connects rooms together (both ways)
   *
   * @param rooms rooms to connect to this instance
   */
  public final void connect(@NotNull final Room... rooms) {
    for (var room : rooms) {
      this.rooms.add(room);
      if (!room.rooms.contains(this))
        room.connect(this);
    }
  }
  
  @Override
  public boolean equals(@NotNull final Object input) {
    if (input instanceof Room) {
      var room = (Room) input;
      return getName().equals(room.getName());
    }

    return false;
  }
  
  @Override
  public String getDescription() {
    return "You are in "
    + ConsoleEngine
        .getInstance()
        .formatForegroundStyleCode(TextStyle.Underline)
    + super.getDescription()
    + ConsoleEngine
        .getInstance()
        .formatForegroundStyleCode(TextStyle.Normal)
    + ".\n"
    + getRoomNames();
  }
  
  private String getRoomNames() {
    if (rooms.size() == 0)
      return "";
    
    var builder = new StringBuilder();
    builder.append("Exits:");
    
    for (var room : rooms)
      builder
          .append(' ')
          .append(room.getName());
    return builder.toString();
  }
  
  public Optional<Room> getRoom(@NotNull String name) {
    if (name.length() == 0)
      return Optional.empty();

    for (var room : rooms)
      if (room.getName().equals(name))
        return Optional.of(room);

    return Optional.empty();
  }
}

