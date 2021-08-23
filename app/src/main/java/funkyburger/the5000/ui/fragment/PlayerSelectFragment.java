package funkyburger.the5000.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.stream.Stream;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.R;
import funkyburger.the5000.object.Player;
import funkyburger.the5000.widget.PlayerSelectItemStack;

public class PlayerSelectFragment extends EventWireableFragment {
    private MainActivity mainActivity;
    private PlayerSelectItemStack stack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_select_fragment, container, false);
        stack = view.findViewById(R.id.playerStack);
        stack.addPlayer();

        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public Stream<Player> getPlayers() {
        return stack.getPlayers();
    }

    public boolean hasChangedSinceLastFetch() {
        return stack.hasChangedSinceLastFetch();
    }
}
