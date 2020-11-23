import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
  private Game game;
  
  @BeforeEach
  void setUp() {
    this.game = new Game();
  }
  
  @AfterEach
  void tearDown() {
    this.game = null;
  }
  
  @Test
  void testGameBasic() {
  
  }
}