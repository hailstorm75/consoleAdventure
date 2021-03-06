package command;

import common.Tuple3;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Repository of supported game commands
 *
 * @author Denis Akopyan
 * @version 3.0
 */
public final class CommandsRepository {
  private final Map<CommandType, String> commandsManual;
  
  private static final Map<Pattern, CommandType> commands = new HashMap<>() {{
    put(Pattern.compile("(?<COMMAND>(((go|head|move)(\\s+to|))|enter)(\\s+the|))(\\s+(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Goto);
    put(Pattern.compile("(?<COMMAND>(where(|\\s+am I)|look around|location))", Pattern.CASE_INSENSITIVE), CommandType.Where);
    put(Pattern.compile("(?<COMMAND>(help|what(\\s+is|)|describe|explain))(\\s+(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Help);
    put(Pattern.compile("(?<COMMAND>(?:carry|take)(?:\\s+the)?)(?:\\s+(?<INPUT>(?:.(?!from))+)(?:\\s+from\\s+(?:the\\s+)?(?<INPUT2>.+))?)?", Pattern.CASE_INSENSITIVE), CommandType.Carry);
    put(Pattern.compile("(?<COMMAND>(?:drop)(?:\\s+the)?)(?:\\s+(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Drop);
    put(Pattern.compile("(?<COMMAND>(?:unlock|open)(?:\\s+the)?)(?:\\s+(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Unlock);
    put(Pattern.compile("(?<COMMAND>(?:(?:examine|inspect)(?:\\s+the)?)|(?:look\\s+at))(?:\\s+(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Examine);
    put(Pattern.compile("(?<COMMAND>(?:(?:eat|consume|devour)(?:\\s+the)?))(?:\\s+(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Eat);
    put(Pattern.compile("(?:quit|stop|end|exit)(?:(?:\\s+the|)\\s+game|)", Pattern.CASE_INSENSITIVE), CommandType.End);
  }};
  
  /**
   * Default constructor
   */
  public CommandsRepository() {
    commandsManual = new HashMap<>();
    commandsManual.put(CommandType.Help, "Displays help for the game or a command");
    commandsManual.put(CommandType.End, "Ends the game");
    commandsManual.put(CommandType.Examine, "Examines a given item");
    commandsManual.put(CommandType.Carry, "Puts the given item into the pocket. Example1: take key. Example2: take key from box");
    commandsManual.put(CommandType.Drop, "Takes the given item from the pocket and drops it on the floor of the current room. Example: drop key");
    commandsManual.put(CommandType.Where, "Examines the current player location");
    commandsManual.put(CommandType.Goto, "The player will enter the specified room. Example: go to the kitchen");
    commandsManual.put(CommandType.Unlock, "Unlocks a given room or container");
    commandsManual.put(CommandType.Eat, "Consumes the given item");
  }
  
  private static Optional<String> findInputGroup(@NotNull final Matcher matcher, @NotNull String name) {
    try {
      return Optional.ofNullable(matcher.group(name));
    } catch (IllegalArgumentException ex) {
      return Optional.empty();
    }
  }
  
  /**
   * Identifies the command based on the provided input
   *
   * @param input input based on which the command is to be identified
   * @return command type and parameter. Empty if the command is unknown
   */
  public static Optional<Tuple3<CommandType, Optional<String>, Optional<String>>> identifyCommand(@NotNull String input) {
    // For every known command..
    for (var command : commands.entrySet()) {
      // Try to match it with the user input
      var match = command.getKey().matcher(input);
      // If there is no match..
      if (!match.matches()) {
        // move to the next command
        continue;
      }
      
      // Parse the user-input
      return Optional.of(new Tuple3<>(command.getValue(), findInputGroup(match, "INPUT"), findInputGroup(match, "INPUT2")));
    }
    
    // Return nothing
    return Optional.empty();
  }
  
  /**
   * Gets the manual for the given command
   *
   * @param command command for which the documenting manual is to be retrieved
   * @return command manual
   */
  public String getCommandManual(@NotNull String command) {
    var parameterCommand = Command.initialize(command);

    return parameterCommand.isEmpty()
      ? "Command '" + command + "' is unknown"
      : commandsManual.get(parameterCommand.get().getType());
  }
  
  /**
   * Gets the list of available commands
   *
   * @return string output of the available commands
   */
  public String getCommandsManual() {
    return commandsManual
        // Get all commands
        .keySet()
        // Iterate over commands
        .stream()
        // Wrap the command names
        .map(x -> "'" + x.getName() + "'")
        // Materialize the command names into a string
        .collect(Collectors.joining(" "));
  }
}
