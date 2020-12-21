package common;

import org.jetbrains.annotations.NotNull;

import java.util.Formatter;

public class StringHelper {
  public static <T> String format(@NotNull String format, T[] item) {
    var formatter = new Formatter().format(format, (Object[]) item);

    return formatter.toString();
  }
}
