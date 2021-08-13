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

// TODO clean up
public class DiceControl extends TableLayout {
    private EventHandlerCollection handlerCollection;

    private int diceCount = 0;
    private List<Dice> dices = null;
    private int current = 0;
    private int kept = 0;
    private boolean lost = false;

    // TODO remove
    private TextView currentAsText = null;
    private TextView securedAsText = null;

    private Button rollButton;
    private Button keepButton;
    private Button endButton;

    public DiceControl(Context context) {
        super(context);
        handlerCollection = new EventHandlerCollection(this);
    }

    public DiceControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        handlerCollection = new EventHandlerCollection(this);
    }

    public void Roll(){
        if(dices.stream().allMatch(d -> !d.isEnabled())){
            dices.stream().forEach(d -> d.setEnabled(true));
        }

        dices.stream().forEach(d -> d.Roll());

        keepButton.setEnabled(false);

        handlerCollection.trig(EventType.DiceRolled);
    }

    public void Keep() {
        dices.stream().filter(d -> d.isSelected()).forEach(d ->  d.setEnabled(false));

        handlerCollection.trig(EventType.PlayerKept);
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
        setCanRoll(true);
    }

    public void Store(int amount){

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
        if(current > 0) {
            keepButton.setText("Keep (" + current + ")");
            keepButton.setEnabled(true);
        }
        else {
            keepButton.setText("Keep");
            keepButton.setEnabled(false);
        }
    }

    public int getKept() {
        return kept;
    }

    public void setKept(int kept) {
        this.kept = kept;
    }

    public void setCanRoll(boolean canRoll){
        rollButton.setEnabled(canRoll);
    }

    public boolean getCanRoll(){
        return rollButton.isEnabled();
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        if(lost){
            endButton.setText("Lost");
            endButton.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.SRC_ATOP);
            setCanRoll(false);
            setCurrent(0);
            setKept(0);
        }
        else {
            endButton.setText("End turn");
            endButton.getBackground().setColorFilter(null);
        }

        this.lost = lost;
    }

    public void reportDiceWasSelected(){
        handlerCollection.trig(EventType.DiceSelected);
    }

    public void addEventHandler(EventHandler handler){
        handlerCollection.add(handler);
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

        if(rollButton == null){
            rollButton = new Button(getContext(), null);
            rollButton.setText("Roll");

            rollButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Roll();
                }
            });
        }

        if(keepButton == null) {
            keepButton = new Button(getContext(), null);
            keepButton.setText("Keep");
            keepButton.setEnabled(false);

            keepButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Keep();
                }
            });
        }

        if(endButton == null){
            endButton = new Button(getContext(), null);
            endButton.setText("End turn");

            endButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    handlerCollection.trig(EventType.PlayerEnds);
                }
            });
        }


        row = new TableRow(getContext(), null);
        row.addView(rollButton);
        row.addView(keepButton);
        row.addView(endButton);
        this.addView(row);

        if(currentAsText == null){
            currentAsText = new TextView(getContext(), null);
        }
        if(securedAsText == null) {
            securedAsText = new TextView(getContext(), null);
        }

        row = new TableRow(getContext(), null);
        row.addView(currentAsText);
        row.addView(securedAsText);
        this.addView(row);
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
