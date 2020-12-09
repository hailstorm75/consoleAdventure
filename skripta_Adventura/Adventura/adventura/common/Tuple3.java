package common;

import org.jetbrains.annotations.NotNull;

/**
 * Data structure for representing a set of data
 *
 * @param <T1> type of the first tuple item
 * @param <T2> type of the second tuple item
 * @param <T3> type of the third tuple item
 *
 * @author Denis Akopyan
 * @version 1.0
 */
public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {
  private final T3 item3;
  
  /**
   * Getter for the third item property
   *
   * @return the value of the third item
   */
  public final T3 getItem3() {
    return item3;
  }
  
  /**
   * Default constructor
   *
   * @param item1 value of the first item
   * @param item2 value of the second item
   * @param item2 value of the third item
   */
  public Tuple3(@NotNull T1 item1, @NotNull T2 item2, @NotNull T3 item3) {
    super(item1, item2);
    
    this.item3 = item3;
  }
}
