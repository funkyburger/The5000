package funkyburger.the5000.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import funkyburger.the5000.R;
import funkyburger.the5000.event.EventType;
import funkyburger.the5000.object.Player;

public class PlayerSelectItem extends EventWireableLinearLayout {
//    private Player player = null;
    private ImageView addIcon;
    private ImageView removeIcon;

    private boolean canAdd;
    private boolean canRemove;

    private android.widget.EditText playerName;

    public PlayerSelectItem(Context context) {
        super(context);
        initialize();
    }

    public PlayerSelectItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public PlayerSelectItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public Player getPlayer() {
        return new Player(playerName.getText().toString());
    }

    public void setPlayer(Player player) {
        //this.player = player;
        playerName.setText(player.getName());
        refresh();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        refresh();
    }

    public void setAddActivated(boolean activated) {
        this.canAdd = activated;
        refresh();
    }

    public void setRemoveActivated(boolean activated) {
        this.canRemove = activated;
        refresh();
    }

    private void refresh() {
        removeAllViews();

        addView(playerName);

        if(isEnabled()) {
            playerName.setTextColor(getResources().getColor(R.color.black, getContext().getTheme()));
        } else {
            playerName.setTextColor(getResources().getColor(R.color.light_grey, getContext().getTheme()));
        }

        if(canAdd) {
            addView(addIcon);
        }

        if(canRemove) {
            addView(removeIcon);
        }
    }

    private void initialize() {
        setOrientation(LinearLayout.HORIZONTAL);
        setMinimumHeight(200);
        setGravity(Gravity.CENTER_VERTICAL);

        playerName = new EditText(getContext());
        //playerName.setOnKeyListener();

        addIcon = new ImageView(getContext());
        addIcon.setImageResource(android.R.drawable.ic_input_add);
        addIcon.setOnClickListener(i -> { trigger(EventType.PlayerAdded); });

        removeIcon = new ImageView(getContext());
        removeIcon.setImageResource(android.R.drawable.ic_delete);
        removeIcon.setOnClickListener(i -> { trigger(EventType.PlayerRemoved); });

        addView(playerName);
    }
}
