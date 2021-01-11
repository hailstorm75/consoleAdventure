package common;

import org.jetbrains.annotations.NotNull;

import java.util.Formatter;

/**
 * Helper class for working with strings
 * @author Denis Akopyan
 * @version 1.0
 */
public class StringHelper {
  /**
   * Formats a string with parameters
   *
   * @param format string format
   * @param item list of format parameters
   * @param <T> type of parameters in list
   * @return formatted string
   */
  public static <T> String format(@NotNull String format, T[] item) {
    var formatter = new Formatter().format(format, (Object[]) item);

    return formatter.toString();
  }
}
