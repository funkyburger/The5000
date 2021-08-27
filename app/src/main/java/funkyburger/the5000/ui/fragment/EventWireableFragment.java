package funkyburger.the5000.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;

import funkyburger.the5000.event.EventHandler;
import funkyburger.the5000.event.EventHandlerCollection;
import funkyburger.the5000.event.EventType;

public class EventWireableFragment extends Fragment {
    private EventHandlerCollection handlerCollection;

    public EventWireableFragment() {
        super();
        handlerCollection = new EventHandlerCollection(this);
    }

    public void addEventHandler(EventHandler handler){
        handlerCollection.add(handler);
    }

    protected void trigger(EventType eventType) {
        handlerCollection.trigger(eventType);
    }
}
