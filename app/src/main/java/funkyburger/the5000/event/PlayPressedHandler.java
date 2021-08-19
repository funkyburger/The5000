package funkyburger.the5000.event;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.widget.MainToolBar;

public class PlayPressedHandler implements EventHandler {
    private MainActivity mainActivity;

    public PlayPressedHandler(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public EventType getType() {
        return EventType.PlayPressed;
    }

    @Override
    public void handle(Object sender) {
        MainToolBar toolBar = (MainToolBar)sender;
        toolBar.setGameOngoing(true);

        mainActivity.startOrResume();
    }
}
