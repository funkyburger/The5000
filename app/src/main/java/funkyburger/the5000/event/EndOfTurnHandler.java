package funkyburger.the5000.event;

import funkyburger.the5000.ui.fragment.PlayFragment;
import funkyburger.the5000.utils.GameUtil;
import funkyburger.the5000.widget.DiceControl;
import funkyburger.the5000.widget.ScoreBoard;

public class EndOfTurnHandler implements EventHandler {

    private ScoreBoard scoreBoard;
    private PlayFragment playFragment;

    public EndOfTurnHandler(ScoreBoard scoreBoard, PlayFragment playFragment)
    {
        this.scoreBoard = scoreBoard;
        this.playFragment = playFragment;
    }

    @Override
    public EventType getType() {
        return EventType.EndOfTurn;
    }

    @Override
    public void handle(Object sender) {
        DiceControl control = (DiceControl)sender;

        if (!control.isLost()) {
            scoreBoard.addScoreToActivePlayer(control.getKept());
        }

        if(GameUtil.isWinningScore(scoreBoard.getActivePlayerScore())) {
            control.setWon();
            scoreBoard.setWon();
            playFragment.displayWinMessage();
        } else {
            control.startNewTurn();
            scoreBoard.nextPlayer();
        }
    }
}