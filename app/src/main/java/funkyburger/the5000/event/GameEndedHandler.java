package funkyburger.the5000.event;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.widget.MainToolBar;

public class GameEndedHandler implements EventHandler {
    private MainActivity mainActivity;

    public GameEndedHandler(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public EventType getType() {
        return EventType.GameEnded;
    }

    @Override
    public void handle(Object sender) {
        MainToolBar toolBar = (MainToolBar)sender;
        toolBar.setGameOngoing(false);

        mainActivity.stop();
    }
}
