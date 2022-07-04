package ir.ac.kntu.model;

import ir.ac.kntu.constant.GameSpeed;
import ir.ac.kntu.constant.MusicType;

public class Setting {
    private int virusLevel;

    private GameSpeed gameSpeed;

    private MusicType musicType;

    public Setting(int virusLevel, GameSpeed gameSpeed, MusicType musicType) {
        this.virusLevel = virusLevel;
        this.gameSpeed = gameSpeed;
        this.musicType = musicType;
    }

    public int getVirusLevel() {
        return virusLevel;
    }

    public void setVirusLevel(int virusLevel) {
        this.virusLevel = virusLevel;
    }

    public GameSpeed getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(GameSpeed gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public MusicType getMusicType() {
        return musicType;
    }

    public void setMusicType(MusicType musicType) {
        this.musicType = musicType;
    }
}
