package funkyburger.the5000.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import funkyburger.the5000.object.CircularList;
import funkyburger.the5000.object.Player;

public class ScoreBoard extends TableLayout {
    private TextView currentAsText = null;
    private TextView scoreAsText = null;

    private CircularList<PlayerDashboard> dashboards = new CircularList<>();

    public ScoreBoard(Context context) {
        super(context);
    }

    public ScoreBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void nextPlayer(){
        dashboards.getCurrent().setActive(false);
        dashboards.next().setActive(true);
    }

    public void addScoreToActivePlayer(int score) {
        dashboards.getCurrent().addScore(score);
    }

    public void addPlayer(Player player){
        PlayerDashboard dashboard = new PlayerDashboard(getContext(), null);
        dashboard.setPlayer(player);

        addView(dashboard);

        if(dashboards.isEmpty()){
            dashboard.setActive(true);
        }

        dashboards.add(dashboard);
    }

    public Stream<Player> getPlayers() {
        return dashboards.stream().map(d -> d.getPlayer());
    }

    public void setPlayers(Stream<Player> players) {
        removeAllViews();
        dashboards.clear();

        players.forEach(p -> addPlayer(p));
    }

    public void setActivePlayer(int activePlayerIndex) {
        dashboards.forEach(d -> d.setActive(false));
        dashboards.setCursor(activePlayerIndex);
        dashboards.getCurrent().setActive(true);
    }

    public int getActivePlayer() {
        return dashboards.getCursor();
    }
}
