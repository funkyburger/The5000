package funkyburger.the5000.event;

import funkyburger.the5000.widget.DiceControl;
import funkyburger.the5000.widget.ScoreBoard;

public class PlayerKeptHandler implements EventHandler {

    private ScoreBoard scoreBoard;

    public  PlayerKeptHandler(ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
    }

    @Override
    public EventType getType() {
        return EventType.PlayerKept;
    }

    @Override
    public void handle(Object sender) {
        DiceControl control = (DiceControl)sender;
        if(control.getCurrent() < 1){
            throw new RuntimeException("Cannot keep 0 or negative score.");
        }

        control.setCanRoll(true);
        control.setKept(control.getKept() + control.getCurrent());
        control.setCurrent(0);
    }
}
