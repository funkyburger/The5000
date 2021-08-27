package funkyburger.the5000.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import funkyburger.the5000.R;
import funkyburger.the5000.event.EventType;
import funkyburger.the5000.object.Player;

public class PlayerSelectItem extends EventWireableLinearLayout {
    private ImageView addIcon;
    private ImageView removeIcon;

    private boolean canAdd;
    private boolean canRemove;

    private EnsuringValueEditText playerNameField;

    public PlayerSelectItem(Context context, Player player) {
        super(context);
        initialize(player);
    }

    public PlayerSelectItem(Context context, @Nullable AttributeSet attrs, Player player) {
        super(context, attrs);
        initialize(player);
    }

    public PlayerSelectItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Player player) {
        super(context, attrs, defStyleAttr);
        initialize(player);
    }

    public Player getPlayer() {
        return new Player(playerNameField.getText().toString());
    }

    public void setPlayer(Player player) {
        playerNameField.setDefaultValue(player.getName(), true);
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

        addView(playerNameField);

        if(canAdd) {
            addView(addIcon);
        }

        if(canRemove) {
            addView(removeIcon);
        }
    }

    private void initialize(Player player) {
        setOrientation(LinearLayout.HORIZONTAL);
        setMinimumHeight(200);
        setGravity(Gravity.CENTER_VERTICAL);

        playerNameField = new EnsuringValueEditText(getContext(), player.getName());

        addIcon = new ImageView(getContext());
        addIcon.setImageResource(android.R.drawable.ic_input_add);
        addIcon.setOnClickListener(i -> { trigger(EventType.PlayerAdded); });

        removeIcon = new ImageView(getContext());
        removeIcon.setImageResource(android.R.drawable.ic_delete);
        removeIcon.setOnClickListener(i -> { trigger(EventType.PlayerRemoved); });

        addView(playerNameField);
    }
}
