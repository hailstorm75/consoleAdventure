package console;

/**
 * Enum for representing console text styles
 * @author Denis Akopyan
 * @version 1.0
 */
public enum TextStyle {
  /**
   * Normal text
   */
  Normal,
  /**
   * Bold text
   */
  Bold,
  /**
   * Bright text
   * <p>
   * Affects the text {@link Color}
   */
  Bright,
  /**
   * Bold and bright text
   * <p>
   * Affects the text {@link Color}
   */
  BrightBold,
  /**
   * Underlined text
   */
  Underline,
}
