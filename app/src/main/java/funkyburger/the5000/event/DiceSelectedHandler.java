package funkyburger.the5000.event;

import java.util.Optional;

import funkyburger.the5000.combination.Combination;
import funkyburger.the5000.combination.CombinationGenerator;
import funkyburger.the5000.utils.StreamUtil;
import funkyburger.the5000.widget.DiceControl;

public class DiceSelectedHandler implements EventHandler {

    private  DiceControl diceControl;

    public DiceSelectedHandler() {
        this.diceControl = null;
    }

    public DiceSelectedHandler(DiceControl diceControl) {
        this.diceControl = diceControl;
    }

    @Override
    public EventType getType() {
        return EventType.DiceSelected;
    }

    @Override
    public void handle(Object sender) {
        if(diceControl != null) {
            handleForDice();
        }
        else{
            handleForControl((DiceControl)sender);
        }
    }

    private void handleForDice(){
        diceControl.reportDiceWasSelected();
    }

    private void handleForControl(DiceControl control){
        Optional<Combination> combination = CombinationGenerator.generate().stream().filter(c -> StreamUtil.areEqual(c.getSequence().stream(), control.getSelectedDiceValues())).findFirst();

        if(combination.isPresent()) {
            control.setCurrent(combination.get().getScore());
        }
        else {
            control.setCurrent(0);
        }
    }
}
