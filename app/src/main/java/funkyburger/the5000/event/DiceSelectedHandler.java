package funkyburger.the5000.event;

import java.util.Optional;

import funkyburger.the5000.combination.Combination;
import funkyburger.the5000.combination.CombinationGenerator;
import funkyburger.the5000.utils.StreamUtil;
import funkyburger.the5000.widget.DiceControl;

public class DiceSelectedHandler implements EventHandler {

    private  DiceControl diceControl;

    public DiceSelectedHandler(DiceControl diceControl) {
        this.diceControl = diceControl;
    }

    @Override
    public EventType getType() {
        return EventType.DiceSelected;
    }

    @Override
    public void handle(Object sender) {
        Optional<Combination> combination = CombinationGenerator.generate().stream().filter(c -> StreamUtil.areEqual(c.getSequence().stream(), diceControl.getSelectedDiceValues())).findFirst();

        if(combination.isPresent()) {
            diceControl.setCurrent(combination.get().getScore());
        }
        else {
            diceControl.setCurrent(0);
        }
    }
}
