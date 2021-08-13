package funkyburger.the5000.event;

import java.util.stream.Collectors;

import funkyburger.the5000.combination.Combination;
import funkyburger.the5000.combination.CombinationGenerator;
import funkyburger.the5000.combination.CombinationMatcher;
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

        control.setLost(!CombinationMatcher.canMatch(control.getEnabledDiceValues().collect(Collectors.toList()), CombinationGenerator.generate()));
    }
}
