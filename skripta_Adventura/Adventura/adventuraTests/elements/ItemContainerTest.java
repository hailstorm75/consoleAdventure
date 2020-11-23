package elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
    // Assemble
    var item1 = new Item("item1", false, "description");
    var item2 = new Item("item2", false, "description");
    
    var container = new ItemContainer("container", ".*", "description");
    container.add(item1, item2);
    
    // Act
    var result = container.getItems();
    
    // Assert
    assertIterableEquals(new ArrayList<>() { { add(item1); add(item2); } }, result);
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
  
  private static Stream<Arguments> parametersForHasItem() {
    return Stream.of(
        Arguments.of(new String[] { "Item1", "Item2" }, "Item3", false),
        Arguments.of(new String[] { "Item1", "Item2" }, "Item2", true),
        Arguments.of(new String[] { "Item1", "Item2" }, "Item1", true),
        Arguments.of(new String[0], "Item1", false)
    );
  }
  
  @ParameterizedTest(name = "{index} => items={0}, item={1}, expected={2}")
  @MethodSource("parametersForHasItem")
  void hasItem(String[] items, String item, boolean expected) {
    // Assemble
    var container = new ItemContainer("container", ".*", "description");
    for (var name : items)
      container.add(new Item(name, false, "description"));
    
    // Act
    var result = container.hasItem(item);
    
    // Assert
    assertEquals(expected, result);
  }
  
  @Test
  void takeOut() {
  }
}