
/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate to the other side
 * of the screen in the fewest steps, while avoiding obstacles and collecting
 * prizes.
 */
public class EscapeRoom {

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

  public static int movePlayer(String move, GameGUI game, String[] commands, int score) {
    if (move.equals("right") || move.equals("r")) {
      game.movePlayer(60, 0);
      score += 10;
    }
    if (move.equals("left") || move.equals("l")) {
      game.movePlayer(-60, 0);
      score += 10;
    }
    if (move.equals("down") || move.equals("d")) {
      game.movePlayer(0, 60);
      score += 10;
    }
    if (move.equals("up") || move.equals("u")) {
      game.movePlayer(0, -60);
      score += 10;
    }
    if (move.equals("jump") || move.equals("jr")) {
      game.movePlayer(123, 0);
      score += 10;
    }
    if (move.equals("jumpleft") || move.equals("jl")) {
      game.movePlayer(-123, 0);
      score += 10;
    }
    if (move.equals("jumpup") || move.equals("ju")) {
      game.movePlayer(0, -123);
      score += 10;
    }
    if (move.equals("jumpdown") || move.equals("jd")) {
      game.movePlayer(0, 123);
      score += 10;
    }
    if (move.equals("pickup")) {
      game.pickupPrize();
      score += 10;
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
      System.out.println(commands);
    }
    return score;
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
      System.out.println("The move you entered is: " + player_move);

      // check if input is valid
      boolean check = check_if_in(validCommands, player_move);
      if (check == true) {
        System.out.println("Valid Command");
        score += movePlayer(player_move, game, validCommands, score);
      } else {
        System.out.println("Not a valid command");
      }

    }

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}
