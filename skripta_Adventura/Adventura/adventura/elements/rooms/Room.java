package elements.rooms;

import console.ConsoleEngine;
import console.TextStyle;
import elements.ItemContainer;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing a room/level
 *
 * @author Denis Akopyan
 * @version 3.0
 */
public class Room extends ItemContainer {
  private final Set<Room> rooms;
  private final String firstEntryDesc;
  private boolean discovered;
  
  protected String getFirstEntryDesc() {
    return firstEntryDesc;
  }
  
  protected boolean isDiscovered() { return discovered; }
  
  /**
   * Default constructor
   *
   * @param displayName name of the given room
   * @param description room description
   */
  public Room(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDesc) {
    this(displayName, matchName, description, firstEntryDesc, -1, "");
  }
  
  public Room(@NotNull String displayName, @NotNull String description, @NotNull String firstEntryDesc) {
    this(displayName, displayName.toLowerCase(), description, firstEntryDesc, -1, "");
  }
  
  public Room(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDesc, int lockId, @NotNull String lockedMessage) {
    super(displayName, matchName, description, lockId, lockedMessage);
    this.rooms = new HashSet<>();
    this.firstEntryDesc = firstEntryDesc;
  }
  
  /**
   * Connects rooms together (both ways)
   *
   * @param rooms rooms to connect to this instance
   */
  public final void connect(@NotNull final Room... rooms) {
    // For every room to connect
    for (var room : rooms) {
      // Add it to the connections
      this.rooms.add(room);
      // If the room is not connected back..
      if (!room.rooms.contains(this)) {
        // complete the connection
        room.connect(this);
      }
    }
  }
  
  public final void disconnect(@NotNull final Room room) {
    this.rooms.remove(room);
    room.rooms.remove(this);
  }
  
  @Override
  public boolean equals(@NotNull final Object obj) {
    // If the compared object is a room..
    if (obj instanceof Room) {
      // Cast it
      var room = (Room) obj;
      // Compare the rooms by names
      return getDisplayName().equals(room.getDisplayName());
    }

    // The input is not equal
    return false;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), rooms, firstEntryDesc);
  }
  
  /**
   * Retrieves the room description
   *
   * @return room description
   */
  @Override
  public String getDescription() {
    return ConsoleEngine
        .getInstance()
        .formatForegroundStyleCode(TextStyle.Underline)
    + super.getDescription()
    + ConsoleEngine
        .getInstance()
        .formatForegroundStyleCode(TextStyle.Normal)
    + (discovered || firstEntryDesc.equals("") ? "" : "\n" + firstEntryDesc);
  }
  
  /**
   * Retrieves the concatenated list of room names
   *
   * @return concatenated list of room names
   */
  public String getRoomNames() {
    return rooms.isEmpty()
      ? ""
      : "From here you can go to: " + rooms.stream().map(room -> room.getDisplayName().toLowerCase()).sorted().collect(Collectors.joining(", "));
  }
  
  /**
   * Attempts to find a room based on the given room name
   *
   * @param name name of the room to find
   * @return found room
   */
  public Optional<Room> getRoom(@NotNull String name) {
    // If the name is empty..
    if (name.length() == 0) {
      // return nothing
      return Optional.empty();
    }

    // For every connected room..
    for (var room : rooms) {
      // If the room matches..
      if (room.isMatch(name)) {
        // return it
        return Optional.of(room);
      }
    }

    // return nothing
    return Optional.empty();
  }
  
  /**
   * Mark this room as discovered
   */
  public void discover() {
    discovered = true;
  }
}

