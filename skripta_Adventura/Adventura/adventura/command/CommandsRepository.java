package command;

import common.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Denis Akopyan
 * @version 3.0
 */
public final class CommandsRepository {
  private final Map<CommandType, String> commandsManual;
  
  private static final Map<Pattern, CommandType> commands = new HashMap<>() {{
    put(Pattern.compile("(?<COMMAND>(((go|head|move)((\\s+)to|))|enter)((\\s+)the|))((\\s+)(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Goto);
    put(Pattern.compile("(?<COMMAND>(where(|(\\s+)am I)|look around|location))", Pattern.CASE_INSENSITIVE), CommandType.Where);
    put(Pattern.compile("(?<COMMAND>(help|what((\\s+)is|)|describe|explain))((\\s+)(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Help);
    put(Pattern.compile("(?<COMMAND>(carry|take)(|(\\s+)the))((\\s+)(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Carry);
    put(Pattern.compile("(?<COMMAND>(unlock|open)(|(\\s+)the))((\\s+)(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Unlock);
    put(Pattern.compile("(?<COMMAND>((examine|inspect)(((\\s+)the)|))|(look(\\s+)at))(\\s+(?<INPUT>.+))?", Pattern.CASE_INSENSITIVE), CommandType.Examine);
    put(Pattern.compile("(quit|stop|end|exit)(((\\s+)the|)(\\s+)game|)", Pattern.CASE_INSENSITIVE), CommandType.End);
  }};
  
  /**
   * Default constructor
   */
  public CommandsRepository() {
    commandsManual = new HashMap<>();
    commandsManual.put(CommandType.Help, "Displays help for the game or a command");
    commandsManual.put(CommandType.End, "Ends the game");
    commandsManual.put(CommandType.Examine, "Examines a given item");
    commandsManual.put(CommandType.Carry, "Put the given item into the backpack");
    commandsManual.put(CommandType.Where, "Examines the current player location");
    commandsManual.put(CommandType.Goto, "The player will enter the specified room. Example: go to the kitchen");
    commandsManual.put(CommandType.Unlock, "Unlocks a given room or container");
  }
  
  private static Optional<String> findInputGroup(@NotNull final Matcher matcher) {
    try {
      return Optional.ofNullable(matcher.group("INPUT"));
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
  public static Optional<Tuple<CommandType, Optional<String>>> identifyCommand(@NotNull String input) {
    for (var command : commands.entrySet()) {
      var match = command.getKey().matcher(input);
      if (!match.matches())
        continue;
      
      return Optional.of(new Tuple<>(command.getValue(), findInputGroup(match)));
    }
    
    return Optional.empty();
  }
  
  public boolean isValidCommand(@NotNull String command) {
    return Command
        .initialize(command)
        .isPresent();
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
        .keySet()
        .stream()
        .map(x -> "'" + x.getName() + "'")
        .collect(Collectors.joining(" "));
  }
}
