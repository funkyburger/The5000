package funkyburger.the5000.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.R;
import funkyburger.the5000.event.*;
import funkyburger.the5000.object.*;
import funkyburger.the5000.widget.*;

public class PlayFragment extends EventWireableFragment {

    private MainActivity mainActivity;
    private List<Player> players;

    private ScoreBoard scoreBoard;
    private DiceControl control;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_fragment, container, false);

        scoreBoard = view.findViewById(R.id.scoreBoard);
        control = view.findViewById(R.id.diceControl);

        control.addEventHandler(new DiceRolledHandler());
        control.addEventHandler(new PlayerKeptHandler(scoreBoard));
        control.addEventHandler(new PlayerEndsHandler(scoreBoard));

        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Stream<Player> players) {
        //List<Player> ps = players.collect(Collectors.toList());
        this.players = players.collect(Collectors.toList());
        scoreBoard.setPlayers(this.players);
    }
}
