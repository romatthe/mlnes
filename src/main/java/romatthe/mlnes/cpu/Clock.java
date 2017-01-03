package romatthe.mlnes.cpu;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Clock {
    private int rate;
    private TimeUnit rateTimeUnit;
    private AtomicLong ticks;

    public Clock() {
        this.rate = 1;
        this.rateTimeUnit = TimeUnit.SECONDS;
        this.ticks = new AtomicLong(0);
        this.start();
    }

    public void start() {
        Runnable counterRunnable = new Runnable() {
            public void run() {
                ticks.incrementAndGet();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(counterRunnable, 0, this.rate, this.rateTimeUnit);
    }
}
