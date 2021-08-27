package funkyburger.the5000.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import funkyburger.the5000.event.DiceSelectedHandler;

public class DiceArray extends EventWireableTableLayout {
    private List<Dice> dices = null;

    private DiceControl control;
    private int diceCount;
    private int countPerRow;

    public DiceArray(Context context, DiceControl control, int diceCount, int countPerRow) {
        super(context);

        dices = new ArrayList<>();

        this.control = control;
        this.diceCount = diceCount;
        this.countPerRow = countPerRow;

        initialize();
    }

    public DiceArray(Context context, DiceControl control, AttributeSet attrs, int diceCount, int countPerRow) {
        super(context, attrs);

        this.control = control;
        this.diceCount = diceCount;
        this.countPerRow = countPerRow;

        dices = new ArrayList<>();

        initialize();
    }

    public void Roll() {
        if(dices.stream().allMatch(d -> !d.isEnabled())){
            dices.stream().forEach(d -> d.setEnabled(true));
        }

        dices.stream().forEach(d -> d.Roll());
    }

    public Stream<Integer> getSelectedDiceValues() {
        return dices.stream().filter(d -> d.isSelected()).map(d -> Integer.valueOf(d.getValue()));
    }

    public Stream<Integer> getEnabledDiceValues() {
        return dices.stream().filter(d -> d.isEnabled()).map(d -> Integer.valueOf(d.getValue()));
    }

    public void disableSelected() {
        dices.stream().filter(d -> d.isSelected()).forEach(d ->  d.setEnabled(false));
    }

    public void reset() {
        dices.stream().forEach(d -> d.reset());
    }

    private void initialize() {
        TableRow row = new TableRow(getContext(), null);

        for(int i = 0; i < diceCount; i++){
            Dice dice = new Dice(getContext(), null);
            dice.addEventHandler(new DiceSelectedHandler(control));

            row.addView(dice);
            dices.add(dice);

            if(row.getChildCount() == countPerRow || i == diceCount - 1){
                addView(row);
                row = new TableRow(getContext(), null);
            }
        }
    }
}
