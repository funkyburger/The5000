package funkyburger.the5000.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Stream;

import funkyburger.the5000.event.PlayerAddedHandler;
import funkyburger.the5000.event.PlayerRemovedHandler;
import funkyburger.the5000.object.Player;

public class PlayerSelectItemStack extends EventWireableLinearLayout {
    private ArrayList<PlayerSelectItem> items = new ArrayList<>();
    private boolean changedSinceLastFetch;

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

    public void addPlayer() {
        PlayerSelectItem item = new PlayerSelectItem(getContext());
        item.setPlayer(new Player("Player" + (items.size() + 1)));
        item.addEventHandler(new PlayerAddedHandler(this));
        item.addEventHandler(new PlayerRemovedHandler(this));

        items.add(item);
        changedSinceLastFetch = true;
        refresh();
    }

    public void removePlayer() {
        if(items.isEmpty()) {
           throw new RuntimeException("No player to remove.");
        }

        items.remove(items.size() - 1);
        changedSinceLastFetch = true;
        refresh();
    }

    public Stream<Player> getPlayers() {
        changedSinceLastFetch = false;
        return items.stream().map(i -> i.getPlayer());
    }

    public boolean hasChangedSinceLastFetch() {
        return changedSinceLastFetch;
    }

    private void refresh() {
        items.forEach(i -> {
            i.setAddActivated(false);
            i.setRemoveActivated(false);
        });

        if(items.size() > 0) {
            PlayerSelectItem lastItem = items.get(items.size() - 1);
            lastItem.setAddActivated(true);
            if(items.size() > 1) {
                lastItem.setRemoveActivated(true);
            }
        }

        removeAllViews();
        items.forEach(i -> addView(i));
    }

    private void initialize() {
        setOrientation(LinearLayout.VERTICAL);
        changedSinceLastFetch = false;
    }
}
