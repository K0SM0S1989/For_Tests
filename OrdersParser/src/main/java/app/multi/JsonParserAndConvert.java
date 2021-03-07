package app.multi;

import app.data.Order;
import app.data.OrderDto;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.RecursiveAction;

public class JsonParserAndConvert extends RecursiveAction {
    private final String fileName;
    private final int from;
    private final int to;
    private final Vector<Order> result;
    private final List<OrderDto> orderDto;

    public JsonParserAndConvert(int from, int to, String fileName, Vector<Order> result, List<OrderDto> orderDto) {
        this.from = from;
        this.to = to;
        this.fileName = fileName;
        this.result = result;
        this.orderDto = orderDto;
    }
    /**
     * вилка для выполнения задачи
     * задачи будут запускаться, пока размер данных для обработки не станет менее 50
     */
    @Override
    protected void compute() {
        if ((to - from) < 50) {
            convert();
        } else {
            int mid = (to + from) >>> 1;
            JsonParserAndConvert task1 = new JsonParserAndConvert(from, mid, fileName, result, orderDto);
            JsonParserAndConvert task2 = new JsonParserAndConvert(mid, to, fileName, result, orderDto);
            invokeAll(task1, task2);
        }
    }
    /**
     * метод для конвертирования данных и добавления в список result для вывода
     */
    private void convert() {
        for (int i = from; i < to; i++) {
            result.add(new Order(orderDto.get(i), i + 1, fileName));
        }
    }
}
