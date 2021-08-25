package funkyburger.the5000.event;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.object.Player;
import funkyburger.the5000.utils.StreamUtil;
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

        if (hasPlayerChangedSinceLastFetch()) {
           mainActivity.setPlayers(mainActivity.getPlayerSelectFragment().getPlayers().collect(Collectors.toList()));
        }

        mainActivity.startOrResume();
    }

    private boolean hasPlayerChangedSinceLastFetch() {
        if(mainActivity.getPlayers() == null) {
            return true;
        }

        return !StreamUtil.areEqual(mainActivity.getPlayers().stream().map(p -> p.getName())
                , mainActivity.getPlayerSelectFragment().getPlayers().map(p -> p.getName()));
    }
}
