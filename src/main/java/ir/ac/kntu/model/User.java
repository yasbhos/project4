package ir.ac.kntu.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String username;

    private String password;

    private int totalMatch;

    private int highScore;

    @Serial
    private static final long serialVersionUID = 45L;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.totalMatch = 0;
        this.highScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalMatch() {
        return totalMatch;
    }

    public void addTotalMatch() {
        this.totalMatch++;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
