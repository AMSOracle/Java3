package Homework4;

public class NotifyThread {
    private final Object lock1 = new Object();
    private char whoIsNext;

    public static void main(String[] args) {
        NotifyThread nf = new NotifyThread();
        nf.setWhoIsNext('A');
        Thread ta = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                nf.print('A', 'B');
            }
        });
        Thread tb = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                nf.print('B', 'C');
            }
        });
        Thread tc = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                nf.print('C', 'A');
            }
        });
        ta.start();
        tb.start();
        tc.start();
    }

    private void print(char myself, char next) {
        synchronized (lock1) {
            try {
                while (!(this.getWhoIsNext() == myself)) {
                    lock1.wait();
                }
                System.out.println(myself);
                this.setWhoIsNext(next);
                lock1.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public char getWhoIsNext() {
        return whoIsNext;
    }

    public void setWhoIsNext(char whoIsNext) {
        this.whoIsNext = whoIsNext;
    }
}

