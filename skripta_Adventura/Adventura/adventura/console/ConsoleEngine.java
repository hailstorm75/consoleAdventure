package console;

import common.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Helper class for working with the console
 * @author Denis Akopyan
 * @version 1.0
 */
public class ConsoleEngine {
  private final static ConsoleEngine instance = new ConsoleEngine();
  private Color currentColor = Color.White;
  private TextStyle currentStyle = TextStyle.Normal;
  
  /**
   * Getter for the singleton instance of this class
   *
   * @return singleton instance of the class
   */
  public static ConsoleEngine getInstance() {
    return instance;
  }
  
  /**
   * Default constructor
   *
   * @implNote Instance of the class cannot be constructed outside of class
   */
  private ConsoleEngine() {}
  
  /**
   * Formats the given text style into a code for settings the style of the console output
   *
   * @param style Text style to set
   * @return styling code
   */
  public String formatForegroundStyleCode(TextStyle style) {
    return formatForegroundStyleCode(currentColor, style);
  }
  
  /**
   * Formats the given color and text style into a code for settings the style of the console output
   *
   * @param color Color to set
   * @param style Text style to set
   * @return styling code
   */
  public String formatForegroundStyleCode(Color color, TextStyle style) {
    currentColor = color;
    currentStyle = style;
  
    var colorValue = colorToValue(color);
    var styleValues = styleToValues(style);
    return formatForegroundStyleCode(colorValue + styleValues.getItem2(), styleValues.getItem1());
  }
  
  private String formatForegroundStyleCode(int color, int mode) {
    return String.format("\033[%d;%dm", mode, color);
  }
  
  private static Tuple<Integer, Integer> styleToValues(TextStyle style) {
    return switch (style) {
      case Normal -> new Tuple<>(0, 0);
      case Bold -> new Tuple<>(1, 0);
      case Bright -> new Tuple<>(0, 60);
      case BrightBold -> new Tuple<>(1, 60);
      case Underline -> new Tuple<>(4, 0);
    };
  }
  
  private static int colorToValue(Color color) {
    return switch (color) {
      case Black -> 30;
      case Red -> 31;
      case Green -> 32;
      case Yellow -> 33;
      case Blue -> 34;
      case Magenta -> 35;
      case Cyan -> 36;
      case White -> 37;
    };
  }
  
  /**
   * Resets the console output styling to defaults
   *
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine setDefaultStyle() {
    return setStyle(Color.White, TextStyle.Normal);
  }
  
  /**
   * Sets the color style of the console output
   *
   * @param color Color to set
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine setStyle(Color color) {
    return setStyle(color, currentStyle);
  }
  
  /**
   * Sets the text style of the console output
   *
   * @param style Text style to set
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine setStyle(TextStyle style) {
    return setStyle(currentColor, style);
  }
  
  /**
   * Sets the color and text style of the console output
   *
   * @param color Color to set
   * @param style Text style to set
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine setStyle(Color color, TextStyle style) {
    print(formatForegroundStyleCode(color, style));
    
    return instance;
  }
  
  private ConsoleEngine print(@NotNull String input, Consumer<String> predicate) {
    if (input.length() == 0)
      return instance;
    predicate.accept(input);
    
    return instance;
  }
  
  private ConsoleEngine typeOut(@NotNull String input, boolean newLine) {
    var i = 0;
    var styleCode = false;
    for (; i < input.length(); i++) {
      System.out.print(input.charAt(i));

      if (!styleCode && input.charAt(i) == '\033')
        styleCode = true;
      else if (styleCode && input.charAt(i) == 'm')
        styleCode = false;

      if (!styleCode)
        try {
          TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
    }
    
    if (newLine)
      System.out.println();
  
    return instance;
  }
  
  /**
   * Types out the given input letter by letter with a delay in between to the console
   *
   * @param input text to type out
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine typeOut(@NotNull String input) {
    return typeOut(input, false);
  }
  
  /**
   * Types out the given input letter by letter with a delay in between to the console followed by a newline
   *
   * @param input text to type out
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine typeOutLn(@NotNull String input) {
    return typeOut(input, true);
  }
  
  /**
   * Prints out the given input to the console
   *
   * @param input text to type out
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine print(@NotNull String input) {
    return print(input, System.out::print);
  }
  
  /**
   * Prints out the given input to the console followed by a newline
   *
   * @param input text to type out
   * @return instance of the helper class for method chaining
   */
  public ConsoleEngine println(@NotNull String input) {
    return print(input, System.out::println);
  }
}
