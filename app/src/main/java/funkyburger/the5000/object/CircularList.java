package funkyburger.the5000.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CircularList<T> extends ArrayList<T> {
    private int cursor;

    public CircularList(){
        super();
        cursor = 0;
    }

    public CircularList(Collection<? extends T> c){
        super(c);
        cursor = 0;
    }

    public T getCurrent() {
        return get(cursor);
    }

    public T next() {
        cursor = (cursor + 1) % size();

        return getCurrent();
    }
}
