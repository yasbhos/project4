package ir.ac.kntu.model.settingpage;

import ir.ac.kntu.constant.GameSpeed;
import ir.ac.kntu.constant.MusicType;
import ir.ac.kntu.model.Setting;
import javafx.application.Application;
import javafx.stage.Stage;

public class SettingMenu extends Application {
    private Setting setting = new Setting(1, GameSpeed.LOW, MusicType.CHILL);

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public Setting getSetting() {
        return setting;
    }
}
