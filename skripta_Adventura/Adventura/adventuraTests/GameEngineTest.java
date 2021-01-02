import command.Command;
import common.Tuple2;
import elements.ItemContainer;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Game engine test suit
 * @author Denis Akopyan
 * @version 1.0
 */
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
    game.processStep(Command.initialize("go to corridor"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("examine note"));
    game.processStep(Command.initialize("go to kitchen"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("go to study"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("go to the living room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Living room", game.getCurrentRoom().getDisplayName());
    assertEquals(0, game.getPocket().getItems().size());
    game.processStep(Command.initialize("take the key"));
    
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("go to corridor"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("unlock study room"));
    
    assertEquals(0, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter blue room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter squares"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Square room", game.getCurrentRoom().getDisplayName());
    assertEquals(0, game.getPocket().getItems().size());
    
    game
        .getCurrentRoom()
        .getItems()
        .stream()
        .filter(item -> item instanceof ItemContainer)
        .map(item -> (ItemContainer) item)
        .filter(itemContainer -> itemContainer.getItems().size() != 0)
        .map(itemContainer -> new Tuple2<>(itemContainer.getDisplayName(), itemContainer.getItems().stream().findFirst().get().getDisplayName()))
        .forEach(item -> game.processStep(Command.initialize("take " + item.getItem2() + " from " + item.getItem1())));
    
    assertEquals(2, game.getPocket().getItems().size());
    assertEquals(3, game.getLives());
    game.processStep(Command.initialize("eat chocolate"));
    
    assertEquals(1, game.getPocket().getItems().size());
    assertEquals(4, game.getLives());
    game.processStep(Command.initialize("enter blue room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("unlock mystery room"));
    assertEquals(0, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter mystery room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
    assertEquals(3, game.getLives());
  
    assertBattle(game, 3);
  
    assertEquals(0, game.getPocket().getItems().size());
    game.processStep(Command.initialize("take blue key"));
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("take green charcoal"));
    assertEquals(2, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter blue room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Blue room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study", game.getCurrentRoom().getDisplayName());
    assertEquals(2, game.getPocket().getItems().size());
    game.processStep(Command.initialize("unlock green room"));
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter circles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Circles room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("examine note"));
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter triangles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Triangles room", game.getCurrentRoom().getDisplayName());
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("take statue"));
    assertEquals(2, game.getPocket().getItems().size());
    game.processStep(Command.initialize("go to green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter numbers"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Numbers room", game.getCurrentRoom().getDisplayName());
    assertEquals(2, game.getPocket().getItems().size());
    game.processStep(Command.initialize("take ruler"));
    assertEquals(3, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter circles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Circles room", game.getCurrentRoom().getDisplayName());
    assertEquals(3, game.getPocket().getItems().size());
    game.processStep(Command.initialize("drop ruler"));
    assertEquals(2, game.getPocket().getItems().size());
    game.processStep(Command.initialize("drop statue"));
    assertEquals(1, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter mystery room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
  
    assertBattle(game, 4);
  
    assertEquals(game.getPocket().getItems().size(), 1);
    game.processStep(Command.initialize("take green key"));
    assertEquals(game.getPocket().getItems().size(), 2);
    game.processStep(Command.initialize("take yellow charcoal"));
    assertEquals(game.getPocket().getItems().size(), 3);
    game.processStep(Command.initialize("enter circles"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Circles room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter green room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Green room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
    assertEquals(3, game.getPocket().getItems().size());
    game.processStep(Command.initialize("unlock yellow room"));
    assertEquals(2, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter yellow room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Yellow room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter prison"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Prison", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter mystery room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Mystery room", game.getCurrentRoom().getDisplayName());
  
    assertBattle(game, 5);
  
    assertEquals(2, game.getPocket().getItems().size());
    game.processStep(Command.initialize("take yellow key"));
    assertEquals(3, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter prison"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Prison", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter yellow room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Yellow room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter study room"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Study room", game.getCurrentRoom().getDisplayName());
    game.processStep(Command.initialize("enter corridor"));
    
    assertFalse(game.isGameOver());
    
    assertEquals("Corridor", game.getCurrentRoom().getDisplayName());
    assertEquals(3, game.getPocket().getItems().size());
    game.processStep(Command.initialize("unlock kitchen"));
    assertEquals(0, game.getPocket().getItems().size());
    game.processStep(Command.initialize("enter kitchen"));
    
    assertTrue(game.isGameOver());
  }
  
  private static void assertBattle(Game game, int rounds) {
    var battle = game.getBattle();
    assertTrue(battle.isPresent());
    
    var battleUnwrapped = battle.get();
    assertFalse(battleUnwrapped.isDefeated());
    
    for (int i = 0; i < rounds; i++) {
      battleUnwrapped.nextAttack();
      
      var defence = battleUnwrapped.getCurrentSolution();
      
      assertTrue(battleUnwrapped.defend(defence));
    }
    
    assertTrue(battleUnwrapped.isDefeated());
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
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("go to the corridor"))));
    
    // Step 2
    assertEquals("You pick up the paper-note and read what it says:\n" +
            "\"ERTkdfgkhUI*#5fsGO TO?<85Tudy =r00m///8\"\n" +
            "You put the paper-note back to where you've found it\n" +
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("examine the note"))));
    
    // Step 3
    assertEquals("It seems that the kitchen is locked by three mysterious padlocks, each of a different color - blue, green, yellow\n" +
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("head to kitchen"))));
    
    // Step 4
    assertEquals("It seems that the study is locked\n" +
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("enter study"))));
    
    // Step 5
    assertEquals("The living room is silent with nobody around.\nYou notice a key on the coffee table.\n" +
            "From here you can go to: corridor",
        removeStylingChars(game.processStep(Command.initialize("go to the living room"))));
    
    // Step 6
    assertEquals("You take the key and put it inside your pocket\n" +
            "From here you can go to: corridor",
        removeStylingChars(game.processStep(Command.initialize("take the key"))));
    
    // Step 7
    assertEquals("You are in the corridor that joins multiple rooms of the house together.\n" +
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("go to corridor"))));
    
    // Step 8
    assertEquals("You've unlocked the study\n" +
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("unlock study"))));
    
    // Step 9
    assertEquals("You are in the study.\n" +
            "Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "Each of a different color - blue, green, yellow.\n" +
            "Only the blue one had a handle drawn, while the others were missing it.\n" +
            "From here you can go to: blue room, corridor, green room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("enter study"))));
    
    // Step 10
    assertEquals("Upon entering you hear the door slam shut behind your back\n" +
            "You are inside the mysterious blue room. Numbers are written on every wall.\n" +
            "From here you can go to: mystery room, square room, study",
        removeStylingChars(game.processStep(Command.initialize("enter blue room"))));
    
    // Step 11
    assertEquals("The door to the mystery room is locked. Probably needs a key.\n" +
            "From here you can go to: mystery room, square room, study",
        removeStylingChars(game.processStep(Command.initialize("enter mystery room"))));
    
    // Step 12
    assertEquals("You are in a room full of square shapes floating in the air, some are combined into boxes, three to be exact\n" +
            "Each box is of a different size - small, medium and large\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.processStep(Command.initialize("enter square room"))));
    
    // Start random --------------------------------------------------------------------
    // Step 13 - 18
    var items = game
        .getCurrentRoom()
        .getItems()
        .stream()
        .filter(item -> item instanceof ItemContainer)
        .map(item -> (ItemContainer) item)
        .map(itemContainer -> new Tuple2<>(itemContainer.getDisplayName(), itemContainer.getItems().stream().findFirst()))
        .collect(Collectors.toList());
    
    for (var item : items) {
      if (item.getItem2().isEmpty())
        assertEquals("You take a look inside the " +
                item.getItem1().toLowerCase() +
                ". It is empty.\n" +
                "From here you can go to: blue room",
            removeStylingChars(game.processStep(Command.initialize("inspect " + item.getItem1()))));
      else {
        assertEquals("You take a look inside the " +
                item.getItem1().toLowerCase() +
                ". Inside you find a " +
                item.getItem2().get().getDisplayName().toLowerCase() +
                "\n" +
                "From here you can go to: blue room",
            removeStylingChars(game.processStep(Command.initialize("inspect " + item.getItem1()))));
  
        assertEquals("You take the " +
                item.getItem2().get().getDisplayName().toLowerCase() +
                " and put it inside your pocket\n" +
                "From here you can go to: blue room",
            removeStylingChars(game.processStep(Command.initialize("take " + item.getItem2().get().getDisplayName() + " from " + item.getItem1()))));
      }
    }
    // End random ----------------------------------------------------------------------
    
    // Step 19
    assertEquals("You are inside the mysterious blue room. Numbers are written on every wall.\n" +
            "From here you can go to: mystery room, square room, study",
        removeStylingChars(game.processStep(Command.initialize("enter blue room"))));
    
    // Step 20
    assertEquals("You've unlocked the mystery room\n" +
            "From here you can go to: mystery room, square room, study",
        removeStylingChars(game.processStep(Command.initialize("unlock mystery room"))));
    
    // Step 21
    assertEquals("You are inside the mystery room. Darkness. Nothing to see.\n" +
            "You feel something watching you\n" +
            "Suddenly, you are hit by a sharp plus sign\n" +
            "A monster appears!",
        removeStylingChars(game.processStep(Command.initialize("go to the mystery room"))));
    
    // GameBattle start -------------------------------------------------------------------
    
    assertBattle(game, 3);

    // End start ----------------------------------------------------------------------
    
    // Step 22
    assertEquals("You take the blue key and put it inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.processStep(Command.initialize("take blue key"))));
    
    // Step 23
    assertEquals("You take the green charcoal and put it inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.processStep(Command.initialize("take green charcoal"))));
    
    // Step 24
    assertEquals("You are inside the mysterious blue room. Numbers are written on every wall.\n" +
            "From here you can go to: mystery room, square room, study",
        removeStylingChars(game.processStep(Command.initialize("enter blue room"))));
    
    // Step 25
    assertEquals("You are in the study.\n" +
            "From here you can go to: blue room, corridor, green room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("go to the study"))));
    
    // Step 26
    assertEquals("You've unlocked the green room\n" +
            "From here you can go to: blue room, corridor, green room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("unlock the green room"))));
    
    // Step 27
    assertEquals("You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: circles room, numbers room, study, triangles room",
        removeStylingChars(game.processStep(Command.initialize("enter green room"))));
    
    // Step 28
    assertEquals("You are inside the circles room. Everything is nauseatingly round.\n" +
            "You notice a round paper-note lying on the round floor.\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.processStep(Command.initialize("enter circles room"))));
    
    // Step 29
    assertEquals("You pick up the paper-note and read what it says:\n" +
            "\"Bring totems\"\n" +
            "You put the paper-note back to where you've found it\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.processStep(Command.initialize("examine the note"))));
    
    // Step 30
    assertEquals("You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: circles room, numbers room, study, triangles room",
        removeStylingChars(game.processStep(Command.initialize("enter green room"))));
    
    // Step 31
    assertEquals("You are inside the triangles room. The walls and ceiling are made out of spiky triangles.\n" +
            "In the center of the room you notice a miniature statue of a wise man\n" +
            "From here you can go to: green room",
        removeStylingChars(game.processStep(Command.initialize("enter triangles"))));
    
    // Step 32
    assertEquals("You take the statue and put it inside your pocket\n" +
            "The room starts rumbling and the spiky walls slowly coming closer and closer. There isn't much time\n" +
            "From here you can go to: green room",
        removeStylingChars(game.processStep(Command.initialize("take statue"))));
    
    // Step 33
    assertEquals("The triangle room shuts behind you. Thankfully you got out in one piece.\n" +
            "You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: circles room, numbers room, study",
        removeStylingChars(game.processStep(Command.initialize("go to green room"))));
    
    // Step 34
    assertEquals("You are inside the numbers room. You hear voices reciting some sequence of numbers\n" +
            "In the middle of the room you find a golden ruler\n" +
            "From here you can go to: green room",
        removeStylingChars(game.processStep(Command.initialize("go to numbers room"))));
    
    // Step 35
    assertEquals("You take the ruler and put it inside your pocket\n" +
            "The voices stop. The room is in complete silence\n" +
            "From here you can go to: green room",
        removeStylingChars(game.processStep(Command.initialize("take ruler"))));
    
    // Step 36
    assertEquals("The numbers root shuts behind you.\n" +
            "You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: study, circles room",
        removeStylingChars(game.processStep(Command.initialize("go to the green room"))));
    
    // Step 37
    assertEquals("You are inside the circles room. Everything is nauseatingly round.\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.processStep(Command.initialize("enter circles room"))));
    
    // Step 38
    assertEquals("You place the statue on the floor\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.processStep(Command.initialize("drop statue"))));
    
    // Step 39
    assertEquals("You place the ruler on the floor\n" +
            "The mystery rooms door opens in front of you\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.processStep(Command.initialize("drop ruler"))));
    
    // Step 40
    assertEquals("You are inside the mystery room. Darkness. Nothing to see.\n" +
            "You feel something watching you\n" +
            "A monster appears!",
        removeStylingChars(game.processStep(Command.initialize("enter mystery room"))));
    
    // GameBattle start -------------------------------------------------------------------
    
    assertBattle(game, 4);

    // End start ----------------------------------------------------------------------
    
    // Step 41
    assertEquals("You take the green key and put it inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.processStep(Command.initialize("take green key"))));
    
    // Step 42
    assertEquals("You take the yellow charcoal and put it inside your pocket\n" +
            "From here you can go to: blue room",
        removeStylingChars(game.processStep(Command.initialize("take yellow charcoal"))));
    
    // Step 43
    assertEquals("You are inside the circles room. Everything is nauseatingly round.\n" +
            "From here you can go to: green room, mystery room",
        removeStylingChars(game.processStep(Command.initialize("enter circles room"))));
    
    // Step 44
    assertEquals("You are inside the mysterious green room. Letters are written on every wall.\n" +
            "From here you can go to: circles room, study",
        removeStylingChars(game.processStep(Command.initialize("go to the green room"))));
    
    // Step 45
    assertEquals("You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "From here you can go to: blue room, corridor, green room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("go to the study"))));
    
    // Step 46
    assertEquals("You've unlocked the yellow room\n" +
            "From here you can go to: blue room, corridor, green room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("unlock yellow room"))));
    
    // Step 47
    assertEquals("You are inside the mysterious yellow room.\n" +
            "From here you can go to: yellow room, prison",
        removeStylingChars(game.processStep(Command.initialize("enter yellow room"))));
    
    // Step 48
    assertEquals("You are inside the prison.\n" +
            "From here you can go to: mystery room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("go to prison"))));
    
    // Step 49
    assertEquals("You are inside the mystery room\n" +
            "There stands a dark silhouette of a man\n" +
            "He speaks: \"You dare challenge me, the mighty Pythagoras, puny child?\"\n" +
            "From here you can go to: mystery room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("enter mystery room"))));
    
    // GameBattle start -------------------------------------------------------------------
    
    assertBattle(game, 5);

    // End start ----------------------------------------------------------------------
    
    // Step 50
    assertEquals("You take the yellow key and put it inside your pocket\n" +
            "From here you can go to: prison",
        removeStylingChars(game.processStep(Command.initialize("take yellow key"))));
    
    // Step 51
    assertEquals("You are inside the prison.\n" +
            "From here you can go to: yellow room",
        removeStylingChars(game.processStep(Command.initialize("go to prison"))));
    
    // Step 52
    assertEquals("You are inside the mysterious yellow room.\n" +
            "From here you can go to: yellow room, prison",
        removeStylingChars(game.processStep(Command.initialize("enter yellow room"))));
    
    // Step 53
    assertEquals("You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.\n" +
            "From here you can go to: blue room, corridor, green room, yellow room",
        removeStylingChars(game.processStep(Command.initialize("go to study"))));
    
    // Step 54
    assertEquals("You are in the corridor that joins multiple rooms of the house together.\n" +
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("go to corridor"))));
    
    // Step 55
    assertEquals("You've unlocked the kitchen\n" +
            "From here you can go to: bedroom, kitchen, living room, study",
        removeStylingChars(game.processStep(Command.initialize("unlock kitchen"))));
    
    // Step 56
    assertEquals("You wake up\n" +
            "Congratulations! You've won the game.",
        removeStylingChars(game.processStep(Command.initialize("enter kitchen"))));
    
    assertTrue(game.isGameOver());
  }
}