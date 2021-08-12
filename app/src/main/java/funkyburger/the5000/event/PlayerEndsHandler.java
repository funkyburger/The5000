package funkyburger.the5000.event;

import funkyburger.the5000.widget.DiceControl;

public class PlayerEndsHandler implements EventHandler {
    @Override
    public EventType getType() {
        return EventType.PlayerEnds;
    }

    @Override
    public void handle(Object sender) {
        DiceControl control = (DiceControl)sender;

        if(control.isLost()){
            control.startNewTurn();
        }
        else {
            // TODO ended turn
            control.startNewTurn();
        }
    }
}
