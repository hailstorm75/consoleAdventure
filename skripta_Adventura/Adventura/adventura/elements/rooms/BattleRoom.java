package elements.rooms;

import common.Tuple2;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Room with a battle sequence
 *
 * @author Denis Akopyan
 */
public class BattleRoom extends Room {
  private static final Random numberGenerator = new Random();
  private final Function<List<Integer>, Integer> problemGenerator;
  private final String problemFormat;
  private final int parameters;
  private final int rounds;
  
  /**
   * Getter for the Rounds property
   *
   * @return battle rounds count
   */
  public int getRounds() {
    return rounds;
  }
  
  /**
   * Default constructor
   *
   * @param displayName room name
   * @param matchName room match name
   * @param description room description
   * @param firstEntryDescription first entry message
   * @param battleArguments arguments for the battle room
   * @param lockId lock key id
   * @param lockedMessage locked message
   */
  public BattleRoom(@NotNull String displayName,
                    @NotNull String matchName,
                    @NotNull String description,
                    @NotNull String firstEntryDescription,
                    @NotNull BattleArguments battleArguments,
                    int lockId,
                    @NotNull String lockedMessage) {
    super(displayName, matchName, description, firstEntryDescription, lockId, lockedMessage);
    
    this.rounds = battleArguments.getRounds();
    this.parameters = battleArguments.getParameters();
    this.problemFormat = battleArguments.getProblemFormat();
    this.problemGenerator = battleArguments.getProblemGenerator();
  }
  
  /**
   * Generates the problem and the expected result
   *
   * @return tuple of the problem format and expected result
   */
  public Tuple2<String, Integer> generateProblem() {
    // Prepare the parameters collection
    var parameters = new LinkedList<Integer>();
    // For the number of required parameters..
    for (int i = 0; i < this.parameters; i++)
      // populate the parameters with a random number
      parameters.add(numberGenerator.nextInt(50));
    
    // Materialize the result
    // TODO: Java cannot format a list of params....
    return new Tuple2<>(String.format(problemFormat, parameters), problemGenerator.apply(parameters));
  }
}
