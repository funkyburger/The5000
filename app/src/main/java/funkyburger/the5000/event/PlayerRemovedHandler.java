package funkyburger.the5000.event;

import funkyburger.the5000.widget.PlayerSelectItemStack;

public class PlayerRemovedHandler implements EventHandler {
    private PlayerSelectItemStack stack;

    public PlayerRemovedHandler(PlayerSelectItemStack stack) {
        this.stack = stack;
    }

    @Override
    public EventType getType() {
        return EventType.PlayerRemoved;
    }

    @Override
    public void handle(Object sender) {
        stack.removePlayer();
    }
}