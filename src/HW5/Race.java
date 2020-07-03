package HW5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Race {
    private ArrayList<Stage> stages;
    private CountDownLatch latch;
    private CountDownLatch latchFinish;
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setLatch(int count) {
        latch = new CountDownLatch(count);
        latchFinish = new CountDownLatch(count);
    }

    public void setCarReady() {
        latch.countDown();
    }

    public void setFinish() {
        latchFinish.countDown();
    }

    public void waitStart() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitFinish() {
        try {
            latchFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
