package thirdQuestion;

import java.util.Random;
import java.util.concurrent.*;
public class WayneEnterprisesSimulation {

    public static void main(String[] args) {
        LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(12); // 7 customers + 5 ships
        // Create customer threads
        for (int i = 0; i < 7; i++) {
            executorService.submit(new Customer(orderQueue));
        }
        // Create ship threads
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Ship(orderQueue));
        }
        executorService.shutdown();
    }
}