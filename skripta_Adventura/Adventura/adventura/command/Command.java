package command;

import org.jetbrains.annotations.NotNull;

import java.text.Normalizer;
import java.util.Optional;

/**
 * Class representing a command requested by the user
 *
 * @author Denis Akopyan
 * @version 3.0
 */
public final class Command {
  private final CommandType type;
  private final String parameterA;
  private final String parameterB;
  
  /**
   * Default constructor
   *
   * @implNote instanced of commands can be initialized only using the {@link #initialize(String)} method
   * @param type command type
   * @param parameterA command first parameter
   * @param parameterB command second parameter
   */
  private Command(@NotNull CommandType type, @NotNull String parameterA, @NotNull String parameterB) {
    this.type = type;
    this.parameterA = parameterA;
    this.parameterB = parameterB;
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
    // Parse the user-input
    var parsed = CommandsRepository.identifyCommand(normalizeString(input));
    // If the parsing failed..
    if (parsed.isEmpty()) {
      // return nothing
      return Optional.empty();
    }
    
    // Extract the data
    var data = parsed.get();
    
    // Materialize the data into a command
    return Optional.of(new Command(data.getItem1(), data.getItem2().orElse(""), data.getItem3().orElse("")));
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
   * Getter for the FirstParameter property
   *
   * @return first parameter value
   */
  public String getFirstParameter() {
    return parameterA;
  }
  
  /**
   * Getter for the SecondParameter property
   *
   * @return second parameter value
   */
  public String getSecondParameter() {
    return parameterB;
  }
  
  /**
   * Validates whether this instance has the first parameter
   *
   * @return true if the command has the first parameter
   */
  public boolean hasNoFirstParameter() {
    return parameterA.length() == 0;
  }
  
  /**
   * Validates whether this instance has the second parameter
   *
   * @return true if the command has the second parameter
   */
  public boolean hasNoSecondParameter() {
    return parameterB.length() == 0;
  }
}

