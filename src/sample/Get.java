package sample;

public class Get implements Runnable {
    private Data data;

    Get(Data d) {
        data = d;
    }

    @Override
    public void run() {
        while (true) {
            boolean dataSuccesful = data.get();
            while (!dataSuccesful) {
                Thread.currentThread().yield();
                dataSuccesful = data.get();
            }
        }
    }
}
