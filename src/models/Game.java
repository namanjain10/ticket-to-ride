package models;

public class Game {
    private String id;
    private String nextChance;
    private boolean isGameComplete = false;

    public Game(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNextChance() {
        return nextChance;
    }

    public void setNextChance(String nextChance) {
        this.nextChance = nextChance;
    }

    public boolean isGameComplete() {
        return isGameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        isGameComplete = gameComplete;
    }
// TODO: add builder here
}
