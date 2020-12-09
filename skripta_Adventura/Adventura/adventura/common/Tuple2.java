package common;

import org.jetbrains.annotations.NotNull;

/**
 * Data structure for representing a set of data
 *
 * @param <T1> type of the first tuple item
 * @param <T2> type of the second tuple item
 *
 * @author Denis Akopyan
 * @version 1.0
 */
public class Tuple2<T1, T2> {
  private final T1 item1;
  private final T2 item2;
  
  /**
   * Default constructor
   *
   * @param item1 value of the first item
   * @param item2 value of the second item
   */
  public Tuple2(@NotNull final T1 item1, @NotNull final T2 item2) {
    this.item1 = item1;
    this.item2 = item2;
  }
  
  /**
   * Getter for the first item property
   *
   * @return the value of the first item
   */
  public final T1 getItem1() {
    return item1;
  }
  
  /**
   * Getter for the second item property
   *
   * @return the value of the second item
   */
  public final T2 getItem2() {
    return item2;
  }
}
