import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {
  @Test
  void getInstance() {
    // Assemble
    var instanceA = GameEngine.getInstance();
    var instanceB = GameEngine.getInstance();
    
    // Assert
    assertEquals(instanceA, instanceB);
  }
}