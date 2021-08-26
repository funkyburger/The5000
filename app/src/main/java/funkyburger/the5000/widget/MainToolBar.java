package funkyburger.the5000.widget;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toolbar;

import funkyburger.the5000.R;
import funkyburger.the5000.event.EventHandler;
import funkyburger.the5000.event.EventHandlerCollection;
import funkyburger.the5000.event.EventType;

public class MainToolBar extends Toolbar {
    private EventHandlerCollection eventHandlers = new EventHandlerCollection(this);

    private ImageView playIcon;
    private ImageView pauseIcon;

    private boolean gameOngoing = false;

    public MainToolBar(Context context) {
        super(context);
        initialize();
    }

    public MainToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MainToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void addEventHandler(EventHandler handler) {
        eventHandlers.add(handler);
    }

    public boolean isGameOngoing() {
        return gameOngoing;
    }

    public void setGameOngoing(boolean gameOngoing) {
        this.gameOngoing = gameOngoing;
        refresh();
    }

    private void refresh() {
        removeAllViews();

        if(gameOngoing){
            addView(pauseIcon);
        } else {
            addView(playIcon);
        }
    }

    private void initialize(){
        playIcon = new ImageView(getContext());
        playIcon.setImageResource(android.R.drawable.ic_media_play);
        playIcon.setOnClickListener(i -> eventHandlers.trigger(EventType.PlayPressed));

        pauseIcon = new ImageView(getContext());
        pauseIcon.setImageResource(android.R.drawable.ic_media_pause);
        pauseIcon.setOnClickListener(i -> eventHandlers.trigger(EventType.PausePressed));

        refresh();
    }
}
