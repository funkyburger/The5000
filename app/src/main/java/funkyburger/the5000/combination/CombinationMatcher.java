package funkyburger.the5000.combination;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CombinationMatcher {
    public static boolean hasMatch(@NonNull List<Integer> sequence, @NonNull List<Integer> pattern) {
        if(sequence.size() < pattern.size()) {
            return false;
        }

        ArrayList<Integer> sequenceCopy = new ArrayList<>(sequence);

        for (int i = 0; i < pattern.size(); i++) {
            if(!contains(sequenceCopy, pattern.get(i))){
                return false;
            }
        }

        return true;
    }

    public static int fetchScoreFromMatch(@NonNull List<Integer> sequence, @NonNull List<Integer> pattern, @NonNull List<Combination> combinations){
        Optional<Combination> optional = combinations.stream().filter(c -> c.getSequence().equals(pattern)).findFirst();
        if(!optional.isPresent()){
            return 0;
        }

        if(hasMatch(sequence, pattern)){
            return optional.get().getScore();
        }

        return 0;
    }

    private static boolean contains(@NonNull List<Integer> sequence, int number) {
        for(int i = 0; i < sequence.size(); i++) {
            if(sequence.get(i) == number){
                sequence.remove(i);
                return true;
            }
        }

        return false;
    }
}
