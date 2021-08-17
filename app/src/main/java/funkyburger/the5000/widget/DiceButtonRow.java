package funkyburger.the5000.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import funkyburger.the5000.event.EventType;

public class DiceButtonRow extends TableRow {
    private DiceControl control;

    private Button rollButton;
    private Button keepButton;
    private Button endButton;

    private boolean lost = false;

    public DiceButtonRow(Context context, DiceControl control) {
        super(context);
        this.control = control;
        initialize();
    }

    public DiceButtonRow(Context context, AttributeSet attrs, DiceControl control) {
        super(context, attrs);
        this.control = control;
        initialize();
    }

    public void setCurrent(int current) {
        if(current > 0) {
            keepButton.setText("Keep (" + current + ")");
            keepButton.setEnabled(true);
        }
        else {
            keepButton.setText("Keep");
            keepButton.setEnabled(false);
        }
    }

    public void setKept(int kept) {
        if(kept > 0) {
            endButton.setText("End turn (" + kept + ")");
            endButton.setEnabled(true);
        }
        else {
            endButton.setText("End turn");
            endButton.setEnabled(false);
        }
    }

    public void setCanKeep(boolean canKeep) {
        keepButton.setEnabled(canKeep);
    }

    public void setCanRoll(boolean canRoll){
        rollButton.setEnabled(canRoll);
    }

    public void setLost(boolean lost){
        if(lost){
            this.lost = true;
            endButton.setText("Lost");
            endButton.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.SRC_ATOP);
            endButton.setEnabled(true);
            setCanRoll(false);
            setCanKeep(false);
            setCurrent(0);
        }
        else {
            if(this.lost) {
                endButton.setText("End turn");
                endButton.getBackground().setColorFilter(null);
                this.lost = false;
            }
        }
    }

    private void initialize() {
        rollButton = new Button(getContext(), null);
        rollButton.setText("Roll");

        rollButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                control.Roll();
            }
        });

        keepButton = new Button(getContext(), null);
        keepButton.setText("Keep");
        keepButton.setEnabled(false);

        keepButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                control.Keep();
            }
        });

        endButton = new Button(getContext(), null);
        endButton.setText("End turn");

        endButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                control.endTurn();
            }
        });

        addView(rollButton);
        addView(keepButton);
        addView(endButton);
    }
}
