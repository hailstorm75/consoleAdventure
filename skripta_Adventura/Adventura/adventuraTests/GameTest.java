import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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
  
}