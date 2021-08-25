package funkyburger.the5000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
//import android.widget.Toolbar;

import funkyburger.the5000.event.*;
import funkyburger.the5000.ui.fragment.*;
import funkyburger.the5000.widget.MainToolBar;

public class MainActivity extends AppCompatActivity {

    private boolean gameOngoing = false;
    private PlayerSelectFragment playerSelectFragment;
    private PlayFragment playFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MainToolBar mainToolBar = findViewById(R.id.mainToolBar);
        mainToolBar.addEventHandler(new PlayPressedHandler(this));
        mainToolBar.addEventHandler(new PausePressedHandler(this));

        playerSelectFragment = new PlayerSelectFragment();
        playFragment = new PlayFragment();

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

        gameOngoing = true;
    }

    public void pause() {
        if(!gameOngoing) {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, playerSelectFragment)
                .commitNow();

        gameOngoing = false;
    }

    public PlayerSelectFragment getPlayerSelectFragment() {
        return playerSelectFragment;
    }

    public PlayFragment getPlayFragment() {
        return playFragment;
    }
}