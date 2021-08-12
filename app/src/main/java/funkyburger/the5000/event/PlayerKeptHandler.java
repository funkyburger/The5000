package funkyburger.the5000.event;

public class PlayerKeptHandler implements EventHandler {
    @Override
    public EventType getType() {
        return EventType.PlayerKept;
    }

    @Override
    public void handle(Object sender) {

    }
}
