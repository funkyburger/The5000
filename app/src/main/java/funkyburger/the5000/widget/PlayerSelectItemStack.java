package funkyburger.the5000.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import funkyburger.the5000.object.Player;

public class PlayerSelectItemStack extends LinearLayout {
    private ArrayList<PlayerSelectItem> items = new ArrayList<>();

    public PlayerSelectItemStack(Context context) {
        super(context);
        initialize();
    }

    public PlayerSelectItemStack(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public PlayerSelectItemStack(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void nextPlayer() {

    }

    @Deprecated
    public void addPlayer(Player player) {
        PlayerSelectItem item = new PlayerSelectItem(getContext());
        item.setPlayer(player);

        PlayerSelectItem next = new PlayerSelectItem(getContext());
        addView(item);
    }

    private void initialize() {
        setOrientation(LinearLayout.VERTICAL);
        //setPaddingRelative();

//        TextView playerName = new TextView(getContext());
//playerName.setText("andouille");
//
//        addView(playerName);
    }
}
