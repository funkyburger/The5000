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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //View blah = findViewById(R.id.container);

        MainToolBar mainToolBar = (MainToolBar) findViewById(R.id.mainToolBar);
        mainToolBar.addEventHandler(new PlayPressedHandler(this));
        mainToolBar.addEventHandler(new PausePressedHandler(this));

//        setActionBar(mainToolBar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolBar);
        //toolbar
        //setActionBar(new MainToolBar(getBaseContext()));

//        setContentView(R.layout.main_activity);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.mainToolBar);
//        setSupportActionBar(myToolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlayerSelectFragment())
                    .commitNow();
        }
    }

    public void startOrResume() {
        if(gameOngoing) {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PlayFragment())
                .commitNow();

        gameOngoing = true;
    }

    public void pause() {
        if(!gameOngoing) {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PlayerSelectFragment())
                .commitNow();

        gameOngoing = false;
    }
}