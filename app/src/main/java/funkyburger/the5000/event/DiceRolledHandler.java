package funkyburger.the5000.event;

import funkyburger.the5000.widget.DiceControl;

public class DiceRolledHandler implements EventHandler {
    @Override
    public EventType getType() {
        return EventType.DiceRolled;
    }

    @Override
    public void handle(Object sender) {
        DiceControl control = (DiceControl)sender;
        control.setCanRoll(false);
    }
}
