package funkyburger.the5000.event;

public interface EventHandler {
    EventType getType();
    void handle(Object sender);
}