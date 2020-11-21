package elements;

import console.ConsoleEngine;
import console.TextStyle;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing a room/level
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Denis Akopyan
 * @version 3.0
 */
public class Room extends ItemContainer {
  private final Set<Room> rooms;
  private final String firstEntryDescription;
  
  public String getFirstEntryDescription() {
    return firstEntryDescription;
  }
  
  /**
   * Default constructor
   *
   * @param displayName name of the given room
   * @param description room description
   */
  public Room(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDescription) {
    this(displayName, matchName, description, firstEntryDescription, -1, "");
  }
  
  public Room(@NotNull String displayName, @NotNull String description, @NotNull String firstEntryDescription) {
    this(displayName, displayName.toLowerCase(), description, firstEntryDescription, -1, "");
  }
  
  public Room(@NotNull String displayName, @NotNull String matchName, @NotNull String description, @NotNull String firstEntryDescription, int lockId, @NotNull String lockedMessage) {
    super(displayName, matchName, description, lockId, lockedMessage);
    this.rooms = new HashSet<>();
    this.firstEntryDescription = firstEntryDescription;
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
      return getDisplayName().equals(room.getDisplayName());
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
    return rooms.size() != 0
      ? "Exits:" + rooms.stream().map(Room::getDisplayName).collect(Collectors.joining(", "))
      : "";
  }
  
  public Optional<Room> getRoom(@NotNull String name) {
    if (name.length() == 0)
      return Optional.empty();

    for (var room : rooms)
      if (room.getDisplayName().equalsIgnoreCase(name))
        return Optional.of(room);

    return Optional.empty();
  }
}

