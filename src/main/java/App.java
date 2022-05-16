
import java.io.IOException;
import java.time.temporal.Temporal;
import java.util.*;

import static java.lang.System.out;

public class App {
    static Scanner scan = new Scanner(System.in);
    static Comunidade comunidade = new Comunidade("Jackson");
    static Controller controller = new Controller(comunidade);
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        View view = new View(controller, scan);
        view.run();

    }


}