package funkyburger.the5000.event;

import funkyburger.the5000.ui.fragment.PlayFragment;
import funkyburger.the5000.utils.GameUtil;
import funkyburger.the5000.widget.DiceControl;
import funkyburger.the5000.widget.MainToolBar;
import funkyburger.the5000.widget.ScoreBoard;

public class EndOfTurnHandler implements EventHandler {

    private ScoreBoard scoreBoard;
    private PlayFragment playFragment;
    private MainToolBar mainToolBar;

    public EndOfTurnHandler(ScoreBoard scoreBoard, PlayFragment playFragment, MainToolBar mainToolBar)
    {
        this.scoreBoard = scoreBoard;
        this.playFragment = playFragment;
        this.mainToolBar = mainToolBar;
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
            mainToolBar.setGameWon(true);
        } else {
            control.startNewTurn();
            scoreBoard.nextPlayer();
        }
    }
}
