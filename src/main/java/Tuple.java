import java.io.Serializable;
import java.security.SecureRandomParameters;

public class Tuple implements Serializable {
    private String p1;
    private float p2;

    public Tuple(){
        this.p1 = "";
        this.p2 = 0;
    }

    public Tuple(String p1, Float p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public Tuple(Tuple t){
        this(t.getP1(), t.getP2());
    }

    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Tuple t = (Tuple) o;

        return (
                this.p1.equals(t.getP1()) &&
                this.p2 == t.getP2()
                );
    }

    public Tuple clone() {
        return new Tuple(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("(").append(this.getP1()).append(",").append(this.getP2()).append(")\n");
        return sb.toString();
    }



    public float getP2() {
        return this.p2;
    }

    public String getP1() {
        return this.p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public void setP2(float p2) {
        this.p2 = p2;
    }
}
