package funkyburger.the5000.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import funkyburger.the5000.R;
import funkyburger.the5000.event.EventHandler;
import funkyburger.the5000.event.EventType;
import funkyburger.the5000.utils.RandomUtil;

@SuppressLint("AppCompatCustomView")
public class Dice extends ImageView {
    private List<EventHandler> eventHandlers = new ArrayList<>();

    private int value = 0;

    public Dice(Context context) {
        super(context);
        initialize();
    }

    public Dice(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public Dice(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void Roll() {
        if(!isEnabled()){
            return;
        }

        setValue(RandomUtil.getRandomDiceValue());
    }

    public void reset() {
        super.setEnabled(true);
        super.setSelected(false);
        setValue(0);
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value) {
        this.value = value;

        if(value < 1){
            setSelected(false);
        }

        render();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(!enabled){
            super.setSelected(false);
        }

        render();
    }

    @Override
    public void setSelected(boolean selected){
        if(getValue() < 1){
            selected = false;
        }

        super.setSelected(selected);

        render();

        eventHandlers.stream().filter(h -> h.getType() == EventType.DiceSelected)
                .forEach(h -> h.handle(this));
    }

    public void addEventHandler(EventHandler handler){
        eventHandlers.add(handler);
    }

    private void initialize(){
        setValue(0);

        setOnClickListener(view -> {
            if(!isEnabled()){
                return;
            }

            setSelected(!isSelected());
        });
    }

    private void render(){
        switch (value) {
            case 0:
                setImageResource(R.drawable.dice_0);
                break;
            case 1:
                setImageResource(R.drawable.dice_1);
                break;
            case 2:
                setImageResource(R.drawable.dice_2);
                break;
            case 3:
                setImageResource(R.drawable.dice_3);
                break;
            case 4:
                setImageResource(R.drawable.dice_4);
                break;
            case 5:
                setImageResource(R.drawable.dice_5);
                break;
            case 6:
                setImageResource(R.drawable.dice_6);
                break;
            default:
                throw new RuntimeException("Invalid value of dice '" + value + "'");
        }

        if(!isEnabled()){
            setColorFilter(ContextCompat.getColor(getContext(), R.color.disabled));
        } else {
            if(isSelected()){
                setColorFilter(ContextCompat.getColor(getContext(), R.color.selected));
            }
            else{
                setColorFilter(ContextCompat.getColor(getContext(), R.color.enabled));
            }
        }
    }

}
