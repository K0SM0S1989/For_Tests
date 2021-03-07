package app.multi;

import app.data.Order;
import app.data.OrderDto;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;

/**
 * Класс (поток) для конвертации json
 * для парралельности используется ForkJoinPool
 */
public class JsonThread implements Runnable {
    private String fileName;
    private Vector<Order> result;
    private List<OrderDto> orderDto;

    public JsonThread() {
    }

    public JsonThread(String fileName, Vector<Order> result, List<OrderDto> orderDto) {
        this.fileName = fileName;
        this.result = result;
        this.orderDto = orderDto;
    }

    @Override
    public void run() {
        if (orderDto != null){
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.invoke(new JsonParserAndConvert(0, orderDto.size(), fileName, result, orderDto));
            forkJoinPool.shutdown();
        }
    }
}
