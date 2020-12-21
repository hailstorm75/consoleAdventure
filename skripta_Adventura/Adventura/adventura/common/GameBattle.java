package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class GameBattle {
  private static final Random numberGenerator = new Random();
  
  private int currentSolution;
  private boolean defeated;
  private int hits;
  private final int rounds;
  private final int parameters;
  private final String problemFormat;
  private final Function<List<Integer>, Integer> problemGenerator;
  
  public int getCurrentSolution() {
    return currentSolution;
  }
  
  public boolean isDefeated() {
    return defeated;
  }
  
  public GameBattle(int rounds, int parameters, String problemFormat, Function<List<Integer>, Integer> problemGenerator) {
    this.rounds = rounds;
    this.parameters = parameters;
    this.problemFormat = problemFormat;
    this.problemGenerator = problemGenerator;
  }
  
  public boolean defend(int input) {
    var result = input == currentSolution;
    if (result && ++hits == rounds)
      defeated = true;
    
    return result;
  }
  
  /**
   * Generates the problem and the expected result
   *
   * @return tuple of the problem format and expected result
   */
  public String nextAttack() {
    // Prepare the parameters collection
    var parameters = new ArrayList<Integer>();
    // For the number of required parameters..
    for (int i = 0; i < this.parameters; i++)
      // populate the parameters with a random number
      parameters.add(numberGenerator.nextInt(50));
    
    currentSolution = problemGenerator.apply(parameters);
    
    // Materialize the result
    return StringHelper.format(problemFormat, parameters.toArray());
  }
}
