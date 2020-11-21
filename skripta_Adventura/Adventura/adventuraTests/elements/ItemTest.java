package elements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
  @Test
  @DisplayName("Items cannot be constructed with a null displayName argument")
  void throwsOnNullDisplayName() {
    //noinspection ConstantConditions
    assertThrows(Exception.class, () -> new Item(null, ".*", true, "."));
  }
  
  @Test
  @DisplayName("Items cannot be constructed with an empty displayName argument")
  void throwsOnInvalidDisplayName() {
    assertThrows(Exception.class, () -> new Item("", ".*", true, "."));
  }
  
  @ParameterizedTest
  @ValueSource(strings = { "", "[", "**", "\\", "AABB???", "AA(C(B)A", "AA(C)B)A" })
  @DisplayName("Items cannot be constructed with an invalid RegEx for the matchName argument")
  void throwsOnInvalidMatchName(String matchName) {
    assertThrows(IllegalArgumentException.class, () -> new Item(".", matchName, true, "."));
  }
  
  @Test
  @DisplayName("Items with no matchName argument will propagate the value from the displayName argument")
  void displayNamePropagatesToMatchName() {
    // Assemble
    var item = new Item("name", true, ".");
    
    // Act
    var match = item.isMatch(item.getDisplayName());
    
    // Assert
    assertTrue(match);
  }
  
  @Test
  @DisplayName("Item displayName getter will return the value set via the constructor")
  void getDisplayName() {
    // Assemble
    var expected = "name";
    var item = new Item(expected, true, ".");
    
    // Act
    var displayName = item.getDisplayName();
    
    // Assert
    assertEquals(expected, displayName);
  }
  
  @ParameterizedTest
  @ValueSource(strings = { "true", "false" })
  @DisplayName("Item canCarry getter will return the value set via the constructor")
  void getCanCarry(String carryValue) {
    // Assemble
    var canCarry = Boolean.parseBoolean(carryValue);
    
    // Act
    var item = new Item(".", canCarry, ".");
    
    // Assert
    assertEquals(canCarry, item.getCanCarry());
  }
  
  @Test
  @DisplayName("Item description getter will return the value set via the constructor")
  void getDescription() {
    // Assemble
    var expected = "description";
    var item = new Item(".", true, expected);
  
    // Act
    var displayName = item.getDescription();
  
    // Assert
    assertEquals(expected, displayName);
  }
  
  private static Stream<Arguments> parametersForIsMatch() {
    return Stream.of(
        Arguments.of("square(|s)(|(\\s+room))", "square"),
        Arguments.of("square(|s)(|(\\s+room))", "squares room"),
        Arguments.of("square(|s)(|(\\s+room))", "squares   room"),
        Arguments.of("square(|s)(|(\\s+room))", "square room"),
        Arguments.of("square(|s)(|(\\s+room))", "square    room")
    );
  }
  
  @ParameterizedTest(name = "{index} => regex={0}, value={1}")
  @MethodSource("parametersForIsMatch")
  @DisplayName("Item can be matched using multiple name variations")
  void isMatch(String regex, String value) {
    // Assemble
    var item = new Item(".", regex, false, ".");
    
    // Act
    var match = item.isMatch(value);
    
    // Assert
    assertTrue(match);
  }
  
  private static Stream<Arguments> parametersForGetHash() {
    return Stream.of(
        Arguments.of("item", true, "some item"),
        Arguments.of("otherItem", false, "some other item")
    );
  }
  
  @ParameterizedTest(name = "{index} => displayName={0}, canCarry={1}, description={2}")
  @MethodSource("parametersForGetHash")
  @DisplayName("Items generate valid hashes")
  void hashCode(String displayName, boolean canCarry, String description) {
    // Assemble
    var item = new Item(displayName, canCarry, description);
    var hash = Objects.hash(displayName, canCarry, description);
    
    // Act
    var value = item.hashCode();
    
    // Assert
    assertEquals(hash, value);
  }
}