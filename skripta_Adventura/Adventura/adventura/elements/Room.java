package elements;

import console.Color;
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
public class Room {
  private final String name;
  private final String description;
  private final Set<Room> rooms;
  private final Set<Item> items;
  
  /**
   * Default constructor
   *
   * @param name name of the given room
   * @param description room description
   */
  public Room(@NotNull String name, @NotNull String description) {
    this.name = name;
    this.description = description;
    rooms = new HashSet<>();
    items = new HashSet<>();
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
      return name.equals(room.name);
    }

    return false;
  }
  
  @Override
  public int hashCode() {
    return name.hashCode();
  }
  
  public final String getName() {
    return name;
  }
  
  public String getDescription() {
    return "You are in "
    + ConsoleEngine
        .getInstance()
        .formatForegroundStyleCode(TextStyle.Underline)
    + description
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
  
  /**
   * Adds a new item to the room
   *
   * @param item item to add
   */
  public final void addThing(@NotNull final Item item) {
    items.add(item);
  }
  
  /**
   * Retrieves a list of items present in the room
   *
   * @return string representation of the items present in given room
   */
  private String thingsSet() {
    return items.stream().map(Item::getName).collect(Collectors.joining("  "));
  }
  
  /**
   * Removes a thing identified by the given name from the room and returns the removed thing
   *
   * @param name name of the thing to remove
   * @return the removed thing
   */
  public Optional<Item> removeThing(@NotNull String name) {
    var thing = items
        .stream()
        .filter(x -> x.getName().equals(name))
        .findFirst();
  
    if (thing.isEmpty())
      return Optional.empty();

    var extracted = thing.get();
    if (extracted.getCanCarry())
      items.remove(extracted);
  
    return Optional.of(extracted);
  }
  
  /**
   * Validates whether the given thing is present
   *
   * @param name identifier of the thing
   * @return true if the thing is present
   */
  public final boolean hasThing(@NotNull String name) {
    return items
        .stream()
        .anyMatch(x -> x.getName().equals(name));
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

