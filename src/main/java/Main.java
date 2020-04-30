public class Main {

    public static void main(String[] args) {
        System.out.println("Program started.");
        Resource resource = new Resource();

        Thread t1 = new Thread(resource::process);

        Thread t2 = new Thread(resource::setDisallow);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program terminated.");
    }
}
