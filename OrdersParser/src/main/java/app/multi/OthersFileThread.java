package app.multi;

import app.data.Order;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;
/**
 * Класс (поток) для конвертации csv
 * для парралельности используется ForkJoinPool
 */
public class OthersFileThread implements Runnable {
    private final String fileName;
    private final Vector<Order> result;
    private final List<String> parsString;

    public OthersFileThread(String fileName, Vector<Order> result, List<String> parsString) {
        this.fileName = fileName;
        this.result = result;
        this.parsString = parsString;
    }

    @Override
    public void run() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new OthersParserAndConvert(0, parsString.size(), fileName, result, parsString));
        forkJoinPool.shutdown();
    }
}
