package funkyburger.the5000.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.R;
import funkyburger.the5000.object.Player;
import funkyburger.the5000.widget.ScoreBoard;

public class PlayFragment extends EventWireableFragment {

    private MainActivity mainActivity;
    private List<Player> players;

    private ScoreBoard scoreBoard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_fragment, container, false);

        scoreBoard = view.findViewById(R.id.scoreBoard);

        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Stream<Player> players) {
        List<Player> ps = players.collect(Collectors.toList());
        this.players = ps;
        scoreBoard.setPlayers(this.players);
    }
}
