package funkyburger.the5000.event;

import funkyburger.the5000.widget.PlayerSelectItemStack;

public class PlayerAddedHandler implements EventHandler {
    private PlayerSelectItemStack stack;

    public PlayerAddedHandler(PlayerSelectItemStack stack) {
        this.stack = stack;
    }

    @Override
    public EventType getType() {
        return EventType.PlayerAdded;
    }

    @Override
    public void handle(Object sender) {
        stack.addPlayer();
    }
}
