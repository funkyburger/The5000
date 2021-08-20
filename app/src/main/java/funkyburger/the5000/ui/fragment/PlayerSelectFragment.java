package funkyburger.the5000.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.R;
import funkyburger.the5000.event.DiceRolledHandler;
import funkyburger.the5000.event.PlayerEndsHandler;
import funkyburger.the5000.event.PlayerKeptHandler;
import funkyburger.the5000.object.Player;
import funkyburger.the5000.ui.main.MainFragment;
import funkyburger.the5000.ui.main.MainViewModel;
import funkyburger.the5000.widget.DiceControl;
import funkyburger.the5000.widget.PlayerSelectItemStack;
import funkyburger.the5000.widget.ScoreBoard;

public class PlayerSelectFragment extends EventWireableFragment {
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_select_fragment, container, false);
        PlayerSelectItemStack playerStack = view.findViewById(R.id.playerStack);
        playerStack.addPlayer(new Player("Player1"));
        playerStack.addPlayer(new Player("Player2"));

//        Button testButton = view.findViewById(R.id.testButton);
//        testButton.setOnClickListener(b -> { mainActivity.switchFragment(); });

        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
