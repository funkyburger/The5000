package funkyburger.the5000.utils;

import java.util.Iterator;
import java.util.stream.BaseStream;

public class StreamUtil {
    public static boolean areEqual(BaseStream s1, BaseStream s2){
        Iterator<?> i1 = s1.iterator(), i2 = s2.iterator();
        while(i1.hasNext() && i2.hasNext()){
            if(!i1.next().equals(i2.next())) {
                return false;
            }
        }

        return !i1.hasNext() && !i2.hasNext();
    }
}
