import java.util.Comparator;

public class ConsumeComparator implements Comparator<Tuple> {

    public int compare(Tuple t1, Tuple t2) {
        float a1 = t1.getP2();
        float a2 = t2.getP2();

        return Float.compare(a2, a1);
    }
}
