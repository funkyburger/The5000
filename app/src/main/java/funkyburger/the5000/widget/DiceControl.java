package funkyburger.the5000.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import funkyburger.the5000.event.DiceSelectedHandler;
import funkyburger.the5000.event.EventHandler;
import funkyburger.the5000.event.EventType;

public class DiceControl extends TableLayout {
    private List<EventHandler> eventHandlers = new ArrayList<>();

    private int diceCount = 0;
    private List<Dice> dices = null;
    private int current = 0;
    private int secured = 0;

    private TextView currentAsText = null;
    private TextView securedAsText = null;

    private Button rollButton;
    private Button keepButton;

    public DiceControl(Context context) {
        super(context);
    }

    public DiceControl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Roll(){
        for(int i = 0; i < dices.size(); i++){
            dices.get(i).Roll();
        }

        eventHandlers.stream().filter(h -> h.getType() == EventType.DiceRolled)
                .forEach(h -> h.handle(this));
    }

    public Stream<Integer> getSelectedDiceValues() {
        return dices.stream().filter(d -> d.isSelected()).map(d -> Integer.valueOf(d.getValue()));
    }

    public void startNewTurn() {
        for(int i = 0; i < dices.size(); i++){
            dices.get(i).reset();
        }
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

    public int getSecured() {
        return secured;
    }

    public void setSecured(int secured) {
        this.secured = secured;
    }

    public void setCanRoll(boolean canRoll){
        rollButton.setEnabled(canRoll);
    }

    public boolean getCanRoll(){
        return rollButton.isEnabled();
    }

    public void reportDiceWasSelected(){
        eventHandlers.stream().filter(h -> h.getType() == EventType.DiceSelected)
                .forEach(h -> h.handle(this));
    }

    public void addEventHandler(EventHandler handler){
        eventHandlers.add(handler);
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

                }
            });
        }


        Button passButton = new Button(getContext(), null);
        passButton.setText("Pass");

        passButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < dices.size(); i++){
                    //dices.get(i).setEnabled(false);
                    startNewTurn();
                }
            }
        });

        row = new TableRow(getContext(), null);
        row.addView(rollButton);
        row.addView(keepButton);
        row.addView(passButton);
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
