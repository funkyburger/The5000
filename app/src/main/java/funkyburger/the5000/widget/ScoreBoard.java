package funkyburger.the5000.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreBoard extends TableLayout {

    private int current = 0;
    private int score = 0;

    private TextView currentAsText = null;
    private TextView scoreAsText = null;

    public ScoreBoard(Context context) {
        super(context);
        initialize();
    }

    public ScoreBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;

        currentAsText.setText("Current : " + current);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;

        scoreAsText.setText("Score : " + score);
    }

    private void initialize(){
        TableRow row = new TableRow(getContext(), null);

        currentAsText = new TextView(getContext(), null);
        scoreAsText = new TextView(getContext(), null);

        row.addView(currentAsText);
        row.addView(scoreAsText);
        this.addView(row);

        setCurrent(0);
        setScore(0);
    }
}
