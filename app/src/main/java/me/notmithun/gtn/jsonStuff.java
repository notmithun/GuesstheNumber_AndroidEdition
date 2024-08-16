package me.notmithun.gtn;

public class jsonStuff {
    String firebaseId;
    int totalGuess;
    int totalWins;
    int lastGuesses;

    public int getLastGuesses() {
        return lastGuesses;
    }

    public int getTotalGuess() {
        return totalGuess;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public void setLastGuesses(int lastGuesses) {
        this.lastGuesses = lastGuesses;
    }

    public void setTotalGuess(int totalGuess) {
        this.totalGuess = totalGuess;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }
}
