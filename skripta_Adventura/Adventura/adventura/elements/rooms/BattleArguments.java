package elements.rooms;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

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
  
  public Function<List<Integer>, Integer> getProblemGenerator() {
    return problemGenerator;
  }
  
  public BattleArguments(int rounds, int parameters, @NotNull String problemFormat, @NotNull Function<List<Integer>, Integer> problemGenerator) {
    this.rounds = rounds;
    this.parameters = parameters;
    this.problemFormat = problemFormat;
    this.problemGenerator = problemGenerator;
  }
}
