package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int score;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        score = 0;
    }
    public String getName() {
        return name;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        score += points;
    }

    // Other getters and setters
}
