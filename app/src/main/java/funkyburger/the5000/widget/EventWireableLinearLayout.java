package funkyburger.the5000.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

import funkyburger.the5000.event.EventHandler;
import funkyburger.the5000.event.EventHandlerCollection;
import funkyburger.the5000.event.EventType;

public class EventWireableLinearLayout extends LinearLayout {
    private EventHandlerCollection handlerCollection;

    public EventWireableLinearLayout(Context context) {
        super(context);
        handlerCollection = new EventHandlerCollection(this);
    }

    public EventWireableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handlerCollection = new EventHandlerCollection(this);
    }

    public EventWireableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handlerCollection = new EventHandlerCollection(this);
    }

    public void addEventHandler(EventHandler handler){
        handlerCollection.add(handler);
    }

    protected void trigger(EventType eventType) {
        handlerCollection.trigger(eventType);
    }
}
