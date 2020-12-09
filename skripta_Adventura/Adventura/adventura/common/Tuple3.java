package common;

import org.jetbrains.annotations.NotNull;

public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {
  private final T3 item3;
  
  public T3 getItem3() {
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
