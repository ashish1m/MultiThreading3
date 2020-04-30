import java.util.concurrent.atomic.AtomicBoolean;

public class StoreRunner {

    public static void main(String[] args) {
        AtomicBoolean syncing = new AtomicBoolean(false);
        AtomicBoolean preparingInvoice = new AtomicBoolean(false);

        Store store = new Store(new Store.Callback() {
            @Override
            public void onInvoiceSync() {
                syncing.set(false);
            }

            @Override
            public void onInvoicePrepared() {
                preparingInvoice.set(false);
            }
        });

        store.syncLatestPrice();
        syncing.set(true);

        store.prepareInvoice();
        preparingInvoice.set(true);

        while (syncing.get() || preparingInvoice.get()){
            //keep running
        }

        store.stop();
        System.out.println("Program terminated.");
    }
}
