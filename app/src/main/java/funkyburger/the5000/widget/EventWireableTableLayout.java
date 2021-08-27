package funkyburger.the5000.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

import funkyburger.the5000.event.EventHandler;
import funkyburger.the5000.event.EventHandlerCollection;
import funkyburger.the5000.event.EventType;

public class EventWireableTableLayout extends TableLayout {
    private EventHandlerCollection handlerCollection;

    public EventWireableTableLayout(Context context) {
        super(context);
        handlerCollection = new EventHandlerCollection(this);
    }

    public EventWireableTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        handlerCollection = new EventHandlerCollection(this);
    }

    public void addEventHandler(EventHandler handler){
        handlerCollection.add(handler);
    }

    protected void trigger(EventType eventType) {
        handlerCollection.trigger(eventType);
    }
}
