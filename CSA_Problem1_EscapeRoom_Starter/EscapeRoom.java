/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;
import java.util.*;
/**
 * Create an escape room game where the player must navigate to the other side
 * of the screen in the fewest steps, while avoiding obstacles and collecting
 * prizes.
 */

public class EscapeRoom {
  // public int score;
  private static int xCell = 1;
  private static int yCell = 1;
  // describe the game with brief welcome message
  // determine the size (length and width) a player must move to stay within the
  // grid markings
  // Allow game commands:
  // right, left, up, down: if you try to go off grid or bump into wall, score
  // decreases
  // jump over 1 space: you cannot jump over walls
  // if you land on a trap, spring a trap to increase score: you must first check
  // if there is a trap, if none exists, penalty
  // pick up prize: score increases, if there is no prize, penalty
  // help: display all possible commands
  // end: reach the far right wall, score increase, game ends, if game ended
  // without reaching far right wall, penalty
  // replay: shows number of player steps and resets the board, you or another
  // player can play the same board
  // Note that you must adjust the score with any method that returns a score
  // Optional: create a custom image for your player use the file player.png on
  // disk

  /****
   * provided code: // set up the game boolean play = true; while (play) { // get
   * user input and call game methods to play play = false; }
   */

  public static boolean check_if_in(String[] arr, String value) {
    boolean exists = false;
    for (String element : arr) {
      if (value.equals(element)) {
        exists = true;
        break;
      }
    }
    return exists;
  }

  public static boolean movePlayer(String move, GameGUI game, String[] commands, int score) {
    if (move.equals("right") || move.equals("r")) {
      if (game.movePlayer(60, 0) == 0) {
        EscapeRoom.xCell += 1;
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      }
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("left") || move.equals("l")) {
      if (game.movePlayer(-60, 0) == 0) {
        EscapeRoom.xCell -= 1;
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      }
      score += 10;
      System.out.println("Your score was: "+score);
    }
    if (move.equals("down") || move.equals("d")) {
      if (game.movePlayer(0, 60) == 0) {
        EscapeRoom.yCell += 1;
      }
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("up") || move.equals("u")) {
      if (game.movePlayer(0, -60) == 0) {
        EscapeRoom.yCell -= 1;
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      }
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("jump") || move.equals("jr")) {
      if (game.movePlayer(123, 0) == 0) {
        EscapeRoom.xCell += 2;
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      }
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("jumpleft") || move.equals("jl")) {
      if (game.movePlayer(-123, 0) == 0) {
        EscapeRoom.xCell -= 2;
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      }
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("jumpup") || move.equals("ju")) {
      if (game.movePlayer(0, -123) == 0) {
        EscapeRoom.yCell += 2;
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      }
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("jumpdown") || move.equals("jd")) {
      if (game.movePlayer(0, 123) == 0) {
        EscapeRoom.yCell -= 2;
    //System.out.println("x: " + EscapeRoom.xCell + ", y: " + EscapeRoom.yCell);
      }
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("pickup") || move.equals("p")) {
      game.pickupPrize();
      score += 10;
      System.out.println("Your score is: "+score);
    }
    if (move.equals("quit") || move.equals("q")) {
      System.out.println("Your score was: " + score);
      game.endGame();
    }
    if (move.equals("replay")) {
      System.out.println("Your score was: " + score);
      game.replay();
    }
    if (move.equals("help me") || move.equals("?")) {
      System.out.println(Arrays.toString(commands));
    }
    if (EscapeRoom.xCell == GameGUI.getGridW() && EscapeRoom.yCell == GameGUI.getGridH()) {
      game.endGame();
    }
    return true;
  }

  public static void main(String[] args) {
    // welcome message
    System.out.println("Welcome to PUMP's EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    System.out.println(
        "To move right, type right or r. To move left, type left or l. To move up, type u or up. To move down, type down or d.");
    System.out.println(
        "To jump right, type jump or jr. To jump left, type jumpleft or jl.To jump up, type jumpup or ju.To jump right, type jumpdown or jd.");
    System.out.println(
        "To pick up an object, type p or pickup. To quit, type quit or q. To replay, type replay. For help, type help or ?");
    System.out.println("Good luck!");

    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60;
    // individual player moves
    int px = 0;
    int py = 0;

    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d", "jump", "jr", "jumpleft", "jl",
        "jumpup", "ju", "jumpdown", "jd", "pickup", "p", "quit", "q", "replay", "help me", "?" };

    // set up game
    boolean play = true;
    while (play) {
      /* TODO: get all the commands working */
      /* Your code here */

      // take input
      System.out.print("Enter a Move: ");
      String player_move = in.nextLine();
      //System.out.println("The move you entered is: " + player_move);

      // check if input is valid
      boolean check = check_if_in(validCommands, player_move);
      if (check == true) {
        System.out.println("Valid Command");
        if (movePlayer(player_move, game, validCommands, score) == true) {
          score += 10;
        }
      } else {
        System.out.println("Not a valid command");
        score = score - 30;
        System.out.println("Your score is: "+score);
      }

    }

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}
