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

public class DiceControl extends TableLayout {
    private int diceCount = 0;
    private List<Dice> dices = null;
    private int current = 0;
    private int secured = 0;

    private TextView currentAsText = null;
    private TextView securedAsText = null;

    public DiceControl(Context context) {
        super(context);
    }

    public DiceControl(Context context, AttributeSet attrs) {
        super(context, attrs);
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

    public void Roll(){
        for(int i = 0; i < dices.size(); i++){
            dices.get(i).Roll();
        }
    }

    public void startNewTurn() {
        for(int i = 0; i < dices.size(); i++){
            dices.get(i).reset();
        }
    }

    public void Store(int amount){

    }

    public  void render() {
        currentAsText.setText("Hand : " + current);
        securedAsText.setText("Score : " + secured);
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

        Button rollButton = new Button(getContext(), null);
        rollButton.setText("Roll");

        rollButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Roll();
            }
        });

        Button storeButton = new Button(getContext(), null);
        storeButton.setText("Store");

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
        row.addView(storeButton);
        row.addView(passButton);
        this.addView(row);

        if(currentAsText == null){
            currentAsText = new TextView(getContext(), null);
        }
        if(securedAsText == null) {
            securedAsText = new TextView(getContext(), null);
        }
    }

    private List<Dice> GenerateDices(int count) {
        List<Dice> diceList = new ArrayList<Dice>();
        for (int i = 0; i < diceCount; i++) {
            diceList.add(new Dice(getContext(), null));
        }
        return diceList;
    }
}
