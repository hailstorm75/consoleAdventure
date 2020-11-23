import command.Command;
import command.CommandType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
  @Test
  void getInstance() {
    // Assemble
    var instanceA = GameEngine.getInstance();
    var instanceB = GameEngine.getInstance();
    
    // Assert
    assertEquals(instanceA, instanceB);
  }
  
  @Test
  void gameRun() {
    var game = GameEngine.getGameInstance();
    
    assertEquals("Bedroom", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("go to corridor"));
    
    assertFalse(game.isGameOver());
  
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("go to living room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Living room", game.getCurrentRoom().getDisplayName());
//    game.process(Command.initialize("take the key"));
    game.process(Command.initialize("go to corridor"));
  
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
//    game.process(Command.initialize("unlock study"));
    game.process(Command.initialize("enter study room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter blue room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter squares"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Squares", game.getCurrentRoom().getDisplayName());
    // TODO: Randomized logic
    game.process(Command.initialize("enter blue room"));
    
    assertFalse(game.isGameOver());
  
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter mystery room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
    // TODO: Battle
    game.process(Command.initialize("enter blue room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
//    game.process(Command.initialize("unlock green room"));
    game.process(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
  
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter circles"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Circles", game.getCurrentRoom().getDisplayName());
    // TODO: Read note
    game.process(Command.initialize("enter green room"));
  
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter triangles"));
    
    assertFalse(game.isGameOver());
  
    assertEquals("Triangles", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
  
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter numbers"));
  
    assertFalse(game.isGameOver());
    
    assertEquals("Numbers", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter green room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter circles"));
  
    assertFalse(game.isGameOver());
    
    assertEquals("Circles", game.getCurrentRoom().getDisplayName());
//    game.process(Command.initialize("drop ruler"));
//    game.process(Command.initialize("drop statue"));
    game.process(Command.initialize("enter mystery room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
    // TODO: Battle
    game.process(Command.initialize("enter circles"));
  
    assertFalse(game.isGameOver());
    
    assertEquals("Circles", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
  
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter study room"));
  
    assertFalse(game.isGameOver());
    
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
//    game.process(Command.initialize("unlock yellow room"));
    game.process(Command.initialize("enter yellow room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Yellow room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter prison"));
    
    assertFalse(game.isGameOver());
  
    assertEquals("Prison", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter mystery room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
    // TODO: Battle
    game.process(Command.initialize("enter prison"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Prison", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter yellow room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Yellow room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter study room"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter corridor"));
  
    assertFalse(game.isGameOver());
  
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
//    game.process(Command.initialize("unlock kitchen"));
    game.process(Command.initialize("enter kitchen"));
    
    assertTrue(game.isGameOver());
  }
}