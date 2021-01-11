package console;

import common.Tuple2;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
  
  private static Tuple2<Integer, Integer> styleToValues(TextStyle style) {
    return switch (style) {
      case Normal -> new Tuple2<>(0, 0);
      case Bold -> new Tuple2<>(1, 0);
      case Bright -> new Tuple2<>(0, 60);
      case BrightBold -> new Tuple2<>(1, 60);
      case Underline -> new Tuple2<>(4, 0);
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
    if (input.length() != 0) {
      predicate.accept(input);
    }
    
    return instance;
  }
  
  private ConsoleEngine typeOut(@NotNull String input, boolean newLine) {
    var i = 0;
    var styleCode = false;
    for (; i < input.length(); i++) {
      System.out.print(input.charAt(i));

      if (!styleCode && input.charAt(i) == '\033') {
        styleCode = true;
      }
      else if (styleCode && input.charAt(i) == 'm') {
        styleCode = false;
      }

      if (!styleCode) {
        try {
          TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
      }
    }
    
    if (newLine)
      System.out.println();
  
    return instance;
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
  
  /**
   * Retrieves user-input from the terminal
   *
   * @return user-provided input
   */
  public String getInput() {
    // Set the printing color to blue
    setStyle(Color.Blue)
        // Print the input symbol
        .print("> ")
        // Reset the printing style to default
        .setDefaultStyle();
  
    // Initialize the reader
    var input = new BufferedReader(new InputStreamReader(System.in));
    // Attempt to retrieve the user-input..
    try {
      // Get the user-input
      return input.readLine();
    }
    // If the retrieval was unsuccessful..
    catch (java.io.IOException exc) {
      // Print the error
      System.out.println("Failed to read input: " + exc.getMessage());
      
      // Return empty user input
      return "";
    }
  }
  
  /**
   * Retrieves user-input from the terminal with a timeout
   * @param timeout time limit for user input
   * @return user-provided input
   */
  public Optional<String> getInput(int timeout) throws InterruptedException {
    // Set the printing color to red
    setStyle(Color.Red)
        // Print the input symbol
        .print("> ")
        // Reset the printing style to default
        .setDefaultStyle();
    
    // Initialize a new thread
    var ex = Executors.newSingleThreadExecutor();
    // Assume there is no user-input
    Optional<String> input = Optional.empty();
    
    // Attempt to retrieve the user-input..
    try {
      // Run the thread with the input task
      var result = ex.submit(new ConsoleInputTask());
      
      // Attempt to get user-input..
      try {
        // Get user-input in a set amount of time
        input = Optional.ofNullable(result.get(timeout, TimeUnit.SECONDS));
      }
      // If the retrieval was unsuccessful..
      catch (ExecutionException e) {
        // Output the error
        System.out.println("Failed to read input: " + e.getMessage());
      }
      // If the user-input was not provided in the set amount of time..
      catch (TimeoutException e) {
        // Cancel the thread task
        result.cancel(true);
      }
    } finally {
      // Close the thread
      ex.shutdownNow();
    }
    
    // Return the user-input
    return input;
  }
}
