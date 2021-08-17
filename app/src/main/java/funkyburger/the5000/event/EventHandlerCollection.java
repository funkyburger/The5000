package funkyburger.the5000.event;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerCollection {
    private List<EventHandler> eventHandlers;
    private Object sender;

    public  EventHandlerCollection(Object sender){
        eventHandlers = new ArrayList<>();
        this.sender = sender;
    }

    public void add(EventHandler handler){
        eventHandlers.add(handler);
    }

    public void trigger(EventType eventType){
        eventHandlers.stream().filter(h -> h.getType() == eventType)
                .forEach(h -> h.handle(sender));
    }
}
