package ca.sheridancollege.project;

import java.util.Scanner;

public class WarGame {
    private static final int NUM_PLAYERS = 2;
    private static final int NUM_ROUNDS = 4;

    private Player[] players;
    private Deck deck;

    public WarGame(String player1Name, String player2Name) {
        players = new Player[NUM_PLAYERS];
        players[0] = new Player(player1Name);
        players[1] = new Player(player2Name);
        deck = new Deck();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            deck.shuffle();
            for (int round = 1; round <= NUM_ROUNDS; round++) {
                System.out.println("Round " + round);
                Card[] cardsInPlay = new Card[NUM_PLAYERS];
                for (int i = 0; i < NUM_PLAYERS; i++) {
                    Card card = deck.dealCard();
                    players[i].addCardToHand(card);
                    cardsInPlay[i] = card;
                }
                determineRoundWinner(cardsInPlay);

                // Prompt to continue or quit after each round
                System.out.print("Enter 'q' to quit or any other key to continue: ");
                input = scanner.nextLine();
                if ("q".equalsIgnoreCase(input)) {
                    break;
                }
            }
            displayFinalScores();
            determineGameWinner();
            System.out.print("Do you want to play again? (yes/no): ");
            input = scanner.nextLine();
        } while ("yes".equalsIgnoreCase(input));

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private void determineRoundWinner(Card[] cardsInPlay) {
        if (cardsInPlay[0].getRank().equals(cardsInPlay[1].getRank())) {
            System.out.println("War!");
            // In the case of a tie, implement logic for war (optional)
        } else {
            int winnerIndex = cardsInPlay[0].getRank().compareTo(cardsInPlay[1].getRank()) > 0 ? 0 : 1;
            players[winnerIndex].updateScore(1);
            System.out.println(players[winnerIndex].getName() + " wins the round!");
        }
    }

    private void displayFinalScores() {
        System.out.println("Final Scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }

    private void determineGameWinner() {
        Player winner = players[0].getScore() > players[1].getScore() ? players[0] : players[1];
        System.out.println("Game Winner: " + winner.getName() + "!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Player 1's name: ");
        String player1Name = scanner.nextLine();

        System.out.print("Enter Player 2's name: ");
        String player2Name = scanner.nextLine();

        WarGame game = new WarGame(player1Name, player2Name);
        game.playGame();

        scanner.close();
    }
}
