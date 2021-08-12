package funkyburger.the5000.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import funkyburger.the5000.R;
import funkyburger.the5000.utils.RandomUtil;

@SuppressLint("AppCompatCustomView")
public class Dice extends ImageView {
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

    public Dice(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

        render();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        render();
    }

    @Override
    public void setSelected(boolean selected){
        super.setSelected(selected);

        render();
    }

    private void initialize(){
        setValue(0);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEnabled()){
                    return;
                }

                setSelected(!isSelected());
            }
        });
    }

    private void render(){
        switch (value) {
            case 0:
                if(!isEnabled()){
                    setImageResource(R.drawable.ic_disabled_dice0);
                } else {
                    if(isSelected()){
                        setImageResource(R.drawable.ic_selected_dice0);
                    }
                    else{
                        setImageResource(R.drawable.ic_dice0);
                    }
                }
                break;
            case 1:
                if(!isEnabled()){
                    setImageResource(R.drawable.ic_disabled_dice1);
                } else {
                    if(isSelected()){
                        setImageResource(R.drawable.ic_selected_dice1);
                    }
                    else{
                        setImageResource(R.drawable.ic_dice1);
                    }
                }
                break;
            case 2:
                if(!isEnabled()){
                    setImageResource(R.drawable.ic_disabled_dice2);
                } else {
                    if(isSelected()){
                        setImageResource(R.drawable.ic_selected_dice2);
                    }
                    else{
                        setImageResource(R.drawable.ic_dice2);
                    }
                }
                break;
            case 3:
                if(!isEnabled()){
                    setImageResource(R.drawable.ic_disabled_dice3);
                } else {
                    if(isSelected()){
                        setImageResource(R.drawable.ic_selected_dice3);
                    }
                    else{
                        setImageResource(R.drawable.ic_dice3);
                    }
                }
                break;
            case 4:
                if(!isEnabled()){
                    setImageResource(R.drawable.ic_disabled_dice4);
                } else {
                    if(isSelected()){
                        setImageResource(R.drawable.ic_selected_dice4);
                    }
                    else{
                        setImageResource(R.drawable.ic_dice4);
                    }
                }
                break;
            case 5:
                if(!isEnabled()){
                    setImageResource(R.drawable.ic_disabled_dice5);
                } else {
                    if(isSelected()){
                        setImageResource(R.drawable.ic_selected_dice5);
                    }
                    else{
                        setImageResource(R.drawable.ic_dice5);
                    }
                }
                break;
            case 6:
                if(!isEnabled()){
                    setImageResource(R.drawable.ic_disabled_dice6);
                } else {
                    if(isSelected()){
                        setImageResource(R.drawable.ic_selected_dice6);
                    }
                    else{
                        setImageResource(R.drawable.ic_dice6);
                    }
                }
                break;
            default:
                throw new RuntimeException("Invalid value of dice '" + value + "'");
        }
    }

}
