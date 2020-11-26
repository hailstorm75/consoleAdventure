import command.Command;
import common.Tuple;
import elements.Item;
import elements.ItemContainer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    game.process(Command.initialize("examine note"));
    game.process(Command.initialize("go to kitchen"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("go to study"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("go to the living room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Living room", game.getCurrentRoom().getDisplayName());
    assertEquals(0, game.getBackpack().getItems().size());
    game.process(Command.initialize("take the key"));
    
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("go to corridor"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("unlock study room"));
    
    assertEquals(0, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter blue room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter squares"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Squares", game.getCurrentRoom().getDisplayName());
    assertEquals(0, game.getBackpack().getItems().size());
    
    game
        .getCurrentRoom()
        .getItems()
        .stream()
        .filter(item -> item instanceof ItemContainer)
        .map(item -> (ItemContainer) item)
        .filter(itemContainer -> itemContainer.getItems().size() != 0)
        .map(itemContainer -> new Tuple<>(itemContainer.getDisplayName(), itemContainer.getItems().stream().findFirst().get()))
        .forEach(item -> game.process(Command.initialize("take " + item.getItem2() + " from " + item.getItem1())));
    
    assertEquals(2, game.getBackpack().getItems().size());
    assertEquals(3, game.getLives());
    game.process(Command.initialize("eat chocolate"));
    
    assertEquals(1, game.getBackpack().getItems().size());
    assertEquals(4, game.getLives());
    game.process(Command.initialize("enter blue room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("unlock mystery room"));
    assertEquals(0, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter mystery room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals(4, game.getLives());
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
    assertEquals(3, game.getLives());
    
    for (int i = 0; i < 3; i++) {
      // TODO: Get generated math problem with addition
      
      // TODO: Calculate correct result
      
      // TODO: Feed result to battleProcessor
    }
    
    assertEquals(0, game.getBackpack().getItems().size());
    game.process(Command.initialize("take blue key"));
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("take green charcoal"));
    assertEquals(2, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter blue room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
    assertEquals(2, game.getBackpack().getItems().size());
    game.process(Command.initialize("unlock green room"));
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter circles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Circles", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("examine note"));
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter triangles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Triangles", game.getCurrentRoom().getDisplayName());
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("take statue"));
    assertEquals(2, game.getBackpack().getItems().size());
    game.process(Command.initialize("go to green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter numbers"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Numbers", game.getCurrentRoom().getDisplayName());
    assertEquals(2, game.getBackpack().getItems().size());
    game.process(Command.initialize("take ruler"));
    assertEquals(3, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter circles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Circles", game.getCurrentRoom().getDisplayName());
    assertEquals(3, game.getBackpack().getItems().size());
    game.process(Command.initialize("drop ruler"));
    assertEquals(2, game.getBackpack().getItems().size());
    game.process(Command.initialize("drop statue"));
    assertEquals(1, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter mystery room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
    
    for (int i = 0; i < 4; i++) {
      // TODO: Get generated math problem with multiplication
      
      // TODO: Calculate correct result
      
      // TODO: Feed result to battleProcessor
    }
    
    assertEquals(game.getBackpack().getItems().size(), 1);
    game.process(Command.initialize("take green key"));
    assertEquals(game.getBackpack().getItems().size(), 2);
    game.process(Command.initialize("take yellow charcoal"));
    assertEquals(game.getBackpack().getItems().size(), 3);
    game.process(Command.initialize("enter circles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Circles", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
    assertEquals(3, game.getBackpack().getItems().size());
    game.process(Command.initialize("unlock yellow room"));
    assertEquals(2, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter yellow room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Yellow room", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter prison"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Prison", game.getCurrentRoom().getDisplayName());
    game.process(Command.initialize("enter mystery room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
    
    for (int i = 0; i < 5; i++) {
      // TODO: Get generated math problem with multiplication and addition
      
      // TODO: Calculate correct result
      
      // TODO: Feed result to battleProcessor
    }
  
    assertEquals(2, game.getBackpack().getItems().size());
    game.process(Command.initialize("take yellow key"));
    assertEquals(3, game.getBackpack().getItems().size());
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
    assertEquals(3, game.getBackpack().getItems().size());
    game.process(Command.initialize("unlock kitchen"));
    assertEquals(0, game.getBackpack().getItems().size());
    game.process(Command.initialize("enter kitchen"));
    
    assertTrue(game.isGameOver());
  }
  
  private static String removeStylingChars(@NotNull String string) {
    return string.replaceAll("\\033\\[\\d+;\\d+m", "");
  }
  
  @Test
  void gameWinnable() {
    var game = GameEngine.getGameInstance();
    
    // Introduction
    assertEquals("You wake up in your room, bright light from the sun is shing inside. Today is going to be a great day!\n" +
            "Just need to grab a snack, pack the books and head to school.\n" +
            "Hopefully, there won't be a math test today, those stick.\n",
        game.getIntroduction());
    
    assertEquals("From here you can go to: corridor", game.getRoomExists());
    
    // Step 1
    assertEquals("You are in the corridor that joins multiple rooms of the house together.\n" +
            "You notice a paper-note lying on the floor.\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("go to the corridor"))));
    
    // Step 2
    assertEquals("You pick up the paper-note and read what it says:\n" +
            "\"ERTkdfgkhUI*#5fsGO TO?<85Tudy =r00m///8\"\n" +
            "You put paper-note back to where you've found it\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("examine the note"))));
    
    // Step 3
    assertEquals("It seems that the kitchen is locked by three mysterious padlocks, each of a different color - blue, green, yellow\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("head to kitchen"))));
    
    // Step 4
    assertEquals("It seems that the study is locked\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("enter study"))));
    
    // Step 5
    assertEquals("The living room is silent with nobody around. You notice a key on the coffee table.\n" +
            "From here you can go to: corridor",
        removeStylingChars(game.process(Command.initialize("go to the living room"))));
    
    // Step 6
    assertEquals("You take key and put in inside your pocket\n" +
            "From here you can go to: corridor",
        removeStylingChars(game.process(Command.initialize("take the key"))));
    
    // Step 7
    assertEquals("You are in the corridor that joins multiple rooms of the house together.\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("go to corridor"))));
    
    // Step 8
    assertEquals("You've unlocked the study room\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("unlock study"))));
    
    // Step 9
    assertEquals("You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "Each of a different color - blue, green, yellow.\n" +
            "Only the blue one had a handle drawn, while the others were missing it.\n" +
            "From here you can go to: blue room, green room, yellow room, corridor",
        removeStylingChars(game.process(Command.initialize("enter study"))));
    
    // Step 10
    assertEquals("Upon entering you hear the door slam shut behind your back\n" +
            "You are inside the mysterious blue room. Numbers are written on every wall.\n" +
            "From here you can go to: study, square room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter blue room"))));
    
    // Step 11
    assertEquals("The door to the mystery room is locked. Probably needs a key.\n" +
            "From here you can go to: study, square room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter mystery room"))));
    
    // Step 12
    assertEquals("You are in a room full of square shapes floating in the air, some are combined into boxes, three to be exact\n" +
            "Each box is of a different size - small, medium and large\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.process(Command.initialize("enter square room"))));
    
    // Start random --------------------------------------------------------------------
    // Step 13 - 18
    var items = game
        .getCurrentRoom()
        .getItems()
        .stream()
        .filter(item -> item instanceof ItemContainer)
        .map(item -> (ItemContainer) item)
        .map(itemContainer -> new Tuple<>(itemContainer.getDisplayName(), itemContainer.getItems().stream().findFirst()))
        .collect(Collectors.toList());
    
    for (var item : items) {
      if (item.getItem2().isEmpty())
        assertEquals("You take a look at the " +
                item.getItem1().toLowerCase() +
                ". It is empty.\n" +
                "From here you can go to: study, square room, mystery room",
            removeStylingChars(game.process(Command.initialize("inspect " + item.getItem1()))));
      else {
        assertEquals("You take a look at the " +
                item.getItem1().toLowerCase() +
                ". Inside you find a" +
                item.getItem2().get().getDisplayName().toLowerCase() +
                ".\n" +
                "From here you can go to: study, square room, mystery room",
            removeStylingChars(game.process(Command.initialize("inspect " + item.getItem1()))));
  
        assertEquals("You take the" +
                item.getItem2().get().getDisplayName().toLowerCase() +
                " and put it inside your pocket\n" +
                "From here you can go to: study, square room, mystery room",
            removeStylingChars(game.process(Command.initialize("take " + item.getItem2().get().getDisplayName() + " from " + item.getItem1()))));
      }
    }
    // End random ----------------------------------------------------------------------
    
    // Step 19
    assertEquals("You are inside the mysterious blue room. Numbers are written on every wall.\n" +
            "From here you can go to: study, square room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter blue room"))));
    
    // Step 20
    assertEquals("You are inside the mysterious blue room. Numbers are written on every wall.\n" +
            "From here you can go to: study, square room, mystery room",
        removeStylingChars(game.process(Command.initialize("unlock mystery room"))));
    
    // Step 21
    assertEquals("You are inside the mystery room. Darkness. Nothing to see.\n" +
            "You feel something watching you\n" +
            "Suddenly, you are hit by a sharp plus sign\n" +
            "A monster appears!",
        removeStylingChars(game.process(Command.initialize("go to the mystery room"))));
    
    // Battle start -------------------------------------------------------------------
    // TODO
    // To beat the monster, the user must input the correct result to the randomly generated math problem
    // This battle has problems using addition
    // End start ----------------------------------------------------------------------
    
    // Step 22
    assertEquals("You take blue key and put in inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.process(Command.initialize("take blue key"))));
    
    // Step 23
    assertEquals("You take green charcoal and put in inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.process(Command.initialize("take green charcoal"))));
    
    // Step 24
    assertEquals("You are inside the mysterious blue room. Numbers are written on every wall.\n" +
            "From here you can go to: study, square room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter blue room"))));
    
    // Step 25
    assertEquals("You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "From here you can go to: blue room, green room, yellow room, corridor",
        removeStylingChars(game.process(Command.initialize("go to the study"))));
    
    // Step 26
    assertEquals("You've unlocked the green room\n" +
            "From here you can go to: blue room, green room, yellow room, corridor",
        removeStylingChars(game.process(Command.initialize("unlock the green room"))));
    
    // Step 27
    assertEquals("You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: study, numbers room, circles room, triangles room",
        removeStylingChars(game.process(Command.initialize("enter green room"))));
    
    // Step 28
    assertEquals("You are inside the circles room. Everything is nauseatingly round.\n" +
            "You notice a round paper-note lying on the round floor.\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter circles room"))));
    
    // Step 29
    assertEquals("You pick up the paper-note and read what it says:\n" +
            "\"Bring totems\"\n" +
            "You put paper-note back to where you've found it\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.process(Command.initialize("examine the note"))));
    
    // Step 30
    assertEquals("You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: study, numbers room, circles room, triangles room",
        removeStylingChars(game.process(Command.initialize("enter green room"))));
    
    // Step 31
    assertEquals("You are inside the triangles green room. The walls and ceiling are made out of spiky triangles.\n" +
            "In the center of the room you notice a miniature statue of a wise man\n" +
            "From here you can go to: green room",
        removeStylingChars(game.process(Command.initialize("enter triangles"))));
    
    // Step 32
    assertEquals("You take the statue and put in inside your pocket\n" +
            "The room starts rumbling and the spiky walls slowly coming closer and closer. There isn't much time\n" +
            "From here you can go to: green room",
        removeStylingChars(game.process(Command.initialize("take statue"))));
    
    // Step 33
    assertEquals("The triangle room shuts behind you. Thankfully you got out in one piece.\n" +
            "You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: study, numbers room, circles room",
        removeStylingChars(game.process(Command.initialize("go to green room"))));
    
    // Step 34
    assertEquals("You are inside the numbers room. You hear voices reciting some sequence of numbers\n" +
            "In the middle of the room you find a golden ruler\n" +
            "From here you can go to: green room",
        removeStylingChars(game.process(Command.initialize("go to numbers room"))));
    
    // Step 35
    assertEquals("You take the ruler and put in inside your pocket\n" +
            "The voices stop. The room is in complete silence\n" +
            "From here you can go to: green room",
        removeStylingChars(game.process(Command.initialize("take ruler"))));
    
    // Step 36
    assertEquals("The numbers root shuts behind you.\n" +
            "You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: study, circles room",
        removeStylingChars(game.process(Command.initialize("go to the green room"))));
    
    // Step 37
    assertEquals("You are inside the circles room. Everything is nauseatingly round.\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter circles room"))));
    
    // Step 38
    assertEquals("You place the statue on the floor\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.process(Command.initialize("drop statue"))));
    
    // Step 39
    assertEquals("You place the ruler on the floor\n" +
            "The mystery rooms door opens in front of you\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.process(Command.initialize("drop ruler"))));
    
    // Step 40
    assertEquals("You are inside the mystery room. Darkness. Nothing to see.\n" +
            "You feel something watching you\n" +
            "A monster appears!",
        removeStylingChars(game.process(Command.initialize("enter mystery room"))));
    
    // Battle start -------------------------------------------------------------------
    // TODO
    // To beat the monster, the user must input the correct result to the randomly generated math problem
    // This battle has problems using multiplication
    // End start ----------------------------------------------------------------------
    
    // Step 41
    assertEquals("You take green key and put in inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.process(Command.initialize("take green key"))));
    
    // Step 42
    assertEquals("You take yellow charcoal and put in inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.process(Command.initialize("take yellow charcoal"))));
    
    // Step 43
    assertEquals("You are inside the circles room. Everything is nauseatingly round.\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter circles room"))));
    
    // Step 44
    assertEquals("You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: study, circles room",
        removeStylingChars(game.process(Command.initialize("go to the green room"))));
    
    // Step 45
    assertEquals("You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "From here you can go to: blue room, green room, yellow room, corridor",
        removeStylingChars(game.process(Command.initialize("go to the study"))));
    
    // Step 46
    assertEquals("You've unlocked the yellow room\n" +
            "From here you can go to: blue room, green room, yellow room, corridor",
        removeStylingChars(game.process(Command.initialize("unlock yellow room"))));
    
    // Step 47
    assertEquals("You are inside the mysterious yellow room.\n" +
            "From here you can go to: yellow room, prison",
        removeStylingChars(game.process(Command.initialize("enter yellow room"))));
    
    // Step 48
    assertEquals("You are inside the prison.\n" +
            "From here you can go to: yellow room, mystery room",
        removeStylingChars(game.process(Command.initialize("go to prison"))));
    
    // Step 49
    assertEquals("You are inside the mystery room\n" +
            "There stands a dark silhouette of a man\n" +
            "He speaks: \"You dare challenge me, the mighty Pythagoras, puny child?\"\n" +
            "From here you can go to: yellow room, mystery room",
        removeStylingChars(game.process(Command.initialize("enter mystery room"))));
    
    // Battle start -------------------------------------------------------------------
    // TODO
    // To beat the monster, the user must input the correct result to the randomly generated math problem
    // This battle has problems using multiplication and addition
    // End start ----------------------------------------------------------------------
    
    // Step 50
    assertEquals("You take yellow key and put in inside your pocket\n" +
            "From here you can go to: prison",
        removeStylingChars(game.process(Command.initialize("take yellow key"))));
    
    // Step 51
    assertEquals("You are inside the prison.\n" +
            "From here you can go to: yellow room",
        removeStylingChars(game.process(Command.initialize("go to prison"))));
    
    // Step 52
    assertEquals("You are inside the mysterious yellow room.\n" +
            "From here you can go to: yellow room, prison",
        removeStylingChars(game.process(Command.initialize("enter yellow room"))));
    
    // Step 53
    assertEquals("You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "From here you can go to: blue room, green room, yellow room, corridor",
        removeStylingChars(game.process(Command.initialize("go to study"))));
    
    // Step 54
    assertEquals("You are in the corridor that joins multiple rooms of the house together.\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("go to corridor"))));
    
    // Step 55
    assertEquals("You've unlocked the kitchen\n" +
            "From here you can go to: bedroom, kitchen, study, living room",
        removeStylingChars(game.process(Command.initialize("unlock kitchen"))));
    
    // Step 56
    assertEquals("You wake up\n" +
            "Congratulations! You've won the game.",
        removeStylingChars(game.process(Command.initialize("enter kitchen"))));
    
    assertTrue(game.isGameOver());
  }
}