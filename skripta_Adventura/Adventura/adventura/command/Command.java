package command;

import org.jetbrains.annotations.NotNull;

import java.text.Normalizer;
import java.util.Optional;

/**
 * Class representing a command requested by the user
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Denis Akopyan
 * @version 3.0
 */
public class Command {
  private final CommandType type;
  private final String parameter;
  
  /**
   * Default constructor
   *
   * @implNote instanced of commands can be initialized only using the {@link #initialize(String)} method
   * @param type command type
   * @param parameter command parameter
   */
  private Command(@NotNull CommandType type, @NotNull String parameter) {
    this.type = type;
    this.parameter = parameter;
  }
  
  private static String normalizeString(@NotNull String input) {
    return Normalizer
        .normalize(input.toLowerCase(), Normalizer.Form.NFD)
        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
  }
  
  /**
   * Initializes a new command from the given string input
   *
   * @param input input from which the command is to be initialized
   * @return initialized command. Empty if the command was unrecognized
   */
  public static Optional<Command> initialize(@NotNull String input) {
    var parsed = CommandsRepository.identifyCommand(normalizeString(input));
    if (parsed.isEmpty())
      return Optional.empty();
    
    var data = parsed.get();
    
    return Optional.of(new Command(data.getItem1(), data.getItem2().orElse("")));
  }
  
  /**
   * Getter for the command type property
   *
   * @return the command type
   */
  public CommandType getType() {
    return type;
  }
  
  /**
   * Getter for the parameter property
   *
   * @return the command parameter
   */
  public String getParameter() {
    return parameter;
  }
  
  /**
   * Validates whether this instance has a parameter
   *
   * @return true if the command has a parameter
   */
  public boolean hasNoParameter() {
    return (parameter.length() == 0);
  }
}

