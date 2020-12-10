package elements.rooms;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

/**
 * Arguments wrapper for the BattleRoom
 */
public class BattleArguments {
  private final int rounds;
  private final int parameters;
  private final String problemFormat;
  private final Function<List<Integer>, Integer> problemGenerator;
  
  /**
   * Getter for the Rounds property
   *
   * @return rounds value
   */
  public int getRounds() {
    return rounds;
  }
  
  /**
   * Getter for the Parameters property
   *
   * @return parameters value
   */
  public int getParameters() {
    return parameters;
  }
  
  /**
   * Getter for the ProblemFormat property
   *
   * @return problem format value
   */
  public String getProblemFormat() {
    return problemFormat;
  }
  
  /**
   * Getter for the ProblemGenerator property
   *
   * @return problem generator reference
   */
  public Function<List<Integer>, Integer> getProblemGenerator() {
    return problemGenerator;
  }
  
  /**
   * Default constructor
   *
   * @param rounds battle rounds
   * @param parameters battle attack parameters count
   * @param problemFormat battle attack format
   * @param problemGenerator battle attack generator
   */
  public BattleArguments(int rounds, int parameters, @NotNull String problemFormat, @NotNull Function<List<Integer>, Integer> problemGenerator) {
    this.rounds = rounds;
    this.parameters = parameters;
    this.problemFormat = problemFormat;
    this.problemGenerator = problemGenerator;
  }
}
