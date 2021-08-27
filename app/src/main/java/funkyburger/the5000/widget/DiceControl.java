package funkyburger.the5000.widget;

import android.content.Context;
import android.util.AttributeSet;

import java.util.stream.Stream;

import funkyburger.the5000.event.EventType;

public class DiceControl extends EventWireableTableLayout {
    private int diceCount = 0;
    private int current = 0;
    private int kept = 0;
    private boolean lost = false;

    private DiceArray diceArray;
    private DiceButtonRow buttonRow;

    public DiceControl(Context context) {
        super(context);
        initialize();
    }

    public DiceControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void Roll(){
        diceArray.Roll();

        buttonRow.setCanKeep(false);

        trigger(EventType.DiceRolled);
    }

    public void Keep() {
        diceArray.disableSelected();

        trigger(EventType.PlayerKept);
    }

    public void endTurn() {
        trigger(EventType.EndOfTurn);
    }

    public Stream<Integer> getSelectedDiceValues() {
        return diceArray.getSelectedDiceValues();
    }

    public Stream<Integer> getEnabledDiceValues() {
        return diceArray.getEnabledDiceValues();
    }

    public void startNewTurn() {
        diceArray.reset();
        setCurrent(0);
        setKept(0);
        setLost(false);
        buttonRow.setCanRoll(true);
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
        buttonRow.setCurrent(current);
    }

    public int getKept() {
        return kept;
    }

    public void setKept(int kept) {
        this.kept = kept;
        buttonRow.setKept(kept);
    }

    public void setCanRoll(boolean canRoll){
        buttonRow.setCanRoll(canRoll);
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        buttonRow.setLost(lost);
        this.lost = lost;
    }

    public void setWinning() {
        buttonRow.setWinning();
    }

    public void setWon() {
        buttonRow.setWon();
    }

    private void initialize(){
        diceArray = new DiceArray(getContext(), this, 6, 3);
        buttonRow = new DiceButtonRow(getContext(), this);
        addView(diceArray);
        addView(buttonRow);
    }
}
