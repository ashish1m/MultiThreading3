import java.util.concurrent.atomic.AtomicBoolean;

public class Resource {

    AtomicBoolean disallow = new AtomicBoolean(false);

    public synchronized void setDisallow() {
        disallow.set(true);
        System.out.println("Process disallowed");
    }

    public synchronized void process() {
        if (!disallow.get()) {
            try {
                Thread.sleep(2000);
                System.out.println("I processed as it was allowed.");
                System.out.println("Disallow: " + disallow.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("I could not process as it was not allowed.");
        }
    }
}
