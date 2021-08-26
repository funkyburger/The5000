package funkyburger.the5000.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import funkyburger.the5000.object.Player;

public class PlayerDashboard extends TableRow {
    private Player player;

    private TextView nameTag = null;
    private TextView scoreTag = null;

    public PlayerDashboard(Context context) {
        super(context);
        initialize();
    }

    public PlayerDashboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public void setActive(boolean active){
        if(active) {
            nameTag.setTypeface(null, Typeface.BOLD);
        }
        else{
            nameTag.setTypeface(null, Typeface.NORMAL);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;

        nameTag.setText(player.getName());
        scoreTag.setText(String.valueOf(player.getScore()));
    }

    public Player getPlayer() {
        return player;
    }

    public void addScore(int score){
        player.addScore(score);
        scoreTag.setText(String.valueOf(player.getScore()));
    }

    public int getScore() {
        return player.getScore();
    }

    private void initialize(){
        setMinimumHeight(100);

        nameTag = new TextView(getContext(), null);
        nameTag.setPadding(20, 10, 20, 10);
        scoreTag = new TextView(getContext(), null);

        addView(nameTag);
        addView(scoreTag);
    }
}
