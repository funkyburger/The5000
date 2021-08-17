package funkyburger.the5000.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import funkyburger.the5000.R;
import funkyburger.the5000.event.*;
import funkyburger.the5000.object.Player;
import funkyburger.the5000.widget.DiceControl;
import funkyburger.the5000.widget.ScoreBoard;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_fragment, container, false);

        DiceControl control = view.findViewById(R.id.diceControl);
        ScoreBoard scoreBoard = view.findViewById(R.id.scoreBoard);

        control.addEventHandler(new DiceRolledHandler());
        control.addEventHandler(new PlayerKeptHandler(scoreBoard));
        control.addEventHandler(new PlayerEndsHandler(scoreBoard));

        // TODO make name settable by users
        scoreBoard.addPlayer(new Player("Jean michel"));
        scoreBoard.addPlayer(new Player("Claude"));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}