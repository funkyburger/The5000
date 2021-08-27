package funkyburger.the5000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.widget.Toolbar;

import java.util.stream.Stream;

import funkyburger.the5000.event.*;
import funkyburger.the5000.object.CircularList;
import funkyburger.the5000.object.Player;
import funkyburger.the5000.ui.fragment.*;
import funkyburger.the5000.widget.MainToolBar;

public class MainActivity extends AppCompatActivity {

    private boolean gameOngoing = false;
    private PlayerSelectFragment playerSelectFragment;
    private PlayFragment playFragment;
    private MainToolBar mainToolBar;

    private CircularList<Player> players = new CircularList<>();
    private int activePlayerIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mainToolBar = findViewById(R.id.mainToolBar);
        mainToolBar.addEventHandler(new PlayPressedHandler(this));
        mainToolBar.addEventHandler(new PausePressedHandler(this));
        mainToolBar.addEventHandler(new GameEndedHandler(this));

        playerSelectFragment = new PlayerSelectFragment();
        playFragment = new PlayFragment();
        playFragment.shareMainToolBar(mainToolBar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, playerSelectFragment)
                    .commitNow();
        }
    }

    public void startOrResume() {
        if(gameOngoing) {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, playFragment)
                .commitNow();

        playFragment.setPlayers(getPlayers());
        playFragment.setActivePlayer(activePlayerIndex);
        gameOngoing = true;
    }

    public void pause() {
        if(!gameOngoing) {
            return;
        }

        activePlayerIndex = playFragment.getActivePlayerIndex();
        setPlayers(playFragment.getPlayers());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, playerSelectFragment)
                .commitNow();

        playerSelectFragment.setPlayers(getPlayers());

        gameOngoing = false;
    }

    public void stop() {
        players = new CircularList<>();
        activePlayerIndex = 0;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, playerSelectFragment)
                .commitNow();

        gameOngoing = false;
        mainToolBar.setGameWon(false);
    }

    public PlayerSelectFragment getPlayerSelectFragment() {
        return playerSelectFragment;
    }

    public PlayFragment getPlayFragment() {
        return playFragment;
    }

    public Stream<Player> getPlayers() {
        return players.stream();
    }

    public void setPlayers(Stream<Player> players) {
        this.players.clear();

        players.forEach(p -> this.players.add(p));
    }
}