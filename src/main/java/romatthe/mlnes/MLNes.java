package romatthe.mlnes;

import romatthe.mlnes.rom.ROM;
import romatthe.mlnes.rom.ROMLoader;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MLNes {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to MLNES");

        ROM rom = ROMLoader.load("/Users/robinm/Source/mlnes/src/main/resources/test.bin");

        System.out.println(rom);

        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        AtomicInteger counter = new AtomicInteger(0);

        Runnable incrementRunnable = new Runnable() {
            public void run() {
                counter.incrementAndGet();
                System.out.println(counter.get());
            }
        };

        Runnable decrementRunnable = new Runnable() {
            public void run() {
                counter.addAndGet(counter.get() * -1);
                System.out.println(counter.get());
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(incrementRunnable, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(decrementRunnable, 0, 4, TimeUnit.SECONDS);
    }
}
