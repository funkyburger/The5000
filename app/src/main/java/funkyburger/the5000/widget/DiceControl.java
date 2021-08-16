package funkyburger.the5000.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import funkyburger.the5000.event.DiceSelectedHandler;
import funkyburger.the5000.event.EventHandler;
import funkyburger.the5000.event.EventHandlerCollection;
import funkyburger.the5000.event.EventType;

public class DiceControl extends EventWireableTableLayout {
    private int diceCount = 0;
    private List<Dice> dices = null;
    private int current = 0;
    private int kept = 0;
    private boolean lost = false;

    private DiceButtonRow buttonRow;

    public DiceControl(Context context) {
        super(context);
    }

    public DiceControl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Roll(){
        if(dices.stream().allMatch(d -> !d.isEnabled())){
            dices.stream().forEach(d -> d.setEnabled(true));
        }

        dices.stream().forEach(d -> d.Roll());

        buttonRow.setCanKeep(false);

        trigger(EventType.DiceRolled);
    }

    public void Keep() {
        dices.stream().filter(d -> d.isSelected()).forEach(d ->  d.setEnabled(false));

        trigger(EventType.PlayerKept);
    }

    public void endTurn() {
        trigger(EventType.PlayerEnds);
    }

    public Stream<Integer> getSelectedDiceValues() {
        return dices.stream().filter(d -> d.isSelected()).map(d -> Integer.valueOf(d.getValue()));
    }

    public Stream<Integer> getEnabledDiceValues() {
        return dices.stream().filter(d -> d.isEnabled()).map(d -> Integer.valueOf(d.getValue()));
    }

    public void startNewTurn() {
        for(int i = 0; i < dices.size(); i++){
            dices.get(i).reset();
        }
        setCurrent(0);
        setKept(0);
        setLost(false);
        buttonRow.setCanRoll(true);
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;

        if(diceCount < 1){
            throw new RuntimeException("Dice count should be a positive integer.");
        }

        dices = GenerateDices(diceCount);

        initialize();
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

    // TODO could be optimised
    public void reportDiceWasSelected(){
        trigger(EventType.DiceSelected);
    }

    private void initialize(){
        TableRow row = new TableRow(getContext(), null);

        for(int i = 0; i < dices.size(); i++){
            row.addView(dices.get(i));

            if(row.getChildCount() == 3 || i == dices.size() - 1){
                this.addView(row);
                row = new TableRow(getContext(), null);
            }
        }

        buttonRow = new DiceButtonRow(getContext(), this);
        addView(buttonRow);
    }

    private List<Dice> GenerateDices(int count) {
        List<Dice> diceList = new ArrayList<Dice>();
        for (int i = 0; i < diceCount; i++) {
            Dice dice = new Dice(getContext(), null);
            dice.addEventHandler(new DiceSelectedHandler(this));
            diceList.add(dice);
        }
        return diceList;
    }
}
