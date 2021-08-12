package funkyburger.the5000.combination;

import java.util.List;

public class Combination {
    private List<Integer> sequence;
    private int score;

    public Combination(List<Integer> sequence, int score){
        this.sequence = sequence;
        this.score = score;
    }

    public List<Integer> GetSequence() {
        return sequence;
    }
    public int getScore() {
        return score;
    }
}
