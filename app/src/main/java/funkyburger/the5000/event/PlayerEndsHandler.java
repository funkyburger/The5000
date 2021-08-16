package funkyburger.the5000.event;

import funkyburger.the5000.widget.DiceControl;
import funkyburger.the5000.widget.ScoreBoard;

// TODO rename to TurnEndsHandler
public class PlayerEndsHandler implements EventHandler {

    private ScoreBoard scoreBoard;

    public  PlayerEndsHandler(ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
    }

    @Override
    public EventType getType() {
        return EventType.PlayerEnds;
    }

    @Override
    public void handle(Object sender) {
        DiceControl control = (DiceControl)sender;

        if (control.isLost()) {
            control.startNewTurn();
        }
        else {
            scoreBoard.addScoreToActivePlayer(control.getKept());

            control.startNewTurn();
        }

        scoreBoard.nextPlayer();
    }
}
