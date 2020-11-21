package elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ItemContainerTest {
  
  @Test
  void getDisplayName() {
    // Assemble
    var expected = "name";
    var container = new ItemContainer(expected, ".*", ".");
    
    // Act
    var result = container.getDisplayName();
    
    // Assert
    assertEquals(expected, result);
  }
  
  @Test
  void getCanCarry() {
    // Assemble
    var container = new ItemContainer(".", ".*", ".");
    
    // Act
    var result = container.getCanCarry();
    
    // Assert
    assertFalse(result);
  }
  
  @Test
  void getDescription() {
    // Assemble
    var expected = "description";
    var container = new ItemContainer(".", ".*", expected);
  
    // Act
    var result = container.getDescription();
  
    // Assert
    assertEquals(expected, result);
  }
  
  @Test
  void isMatch() {
  }
  
  @Test
  void add() {
  }
  
  @Test
  void getItems() {
  }
  
  private static Stream<Arguments> parametersForIsLocked() {
    return Stream.of(
        Arguments.of(-1, false),
        Arguments.of(1, true),
        Arguments.of(0, false),
        Arguments.of(2, true)
    );
  }
  
  @ParameterizedTest(name = "{index} => lock={0}, expected={1}")
  @MethodSource("parametersForIsLocked")
  void isLocked(int lock, boolean expected) {
    // Assemble
    var container = new ItemContainer(".", ".*", ".", lock, ".");
    
    // Act
    var result = container.isLocked();
    
    // Assert
    assertEquals(expected, result);
  }
  
  @ParameterizedTest(name = "{index} => lock={0}, expected={1}")
  @MethodSource("parametersForIsLocked")
  void getLockedMessage(int lock, boolean expected) {
    // Assemble
    var container = new ItemContainer(".", ".*", ".", lock, "lock");
  
    // Act
    var result = container.getLockedMessage();
  
    // Assert
    assertEquals(expected, result.isPresent() && result.get().equals("lock"));
  }
  
  @Test
  void unlock() {
  }
  
  @Test
  void hasItem() {
  }
  
  @Test
  void takeOut() {
  }
}