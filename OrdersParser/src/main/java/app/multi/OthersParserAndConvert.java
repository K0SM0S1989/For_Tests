package app.multi;

import app.data.Order;
import app.data.OrderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;

public class OthersParserAndConvert extends RecursiveAction {

    private final int from;
    private final int to;
    private final String fileName;
    private final Vector<Order> result;
    private final List<String> parsString;

    public OthersParserAndConvert(int from, int to, String fileName, Vector<Order> result, List<String> parsString) {
        this.from = from;
        this.to = to;
        this.fileName = fileName;
        this.result = result;
        this.parsString = parsString;
    }

    /**
     * вилка для выполнения задачи
     * задачи будут запускаться, пока размер данных для обработки не станет менее 50
     */
    @Override
    protected void compute() {
        if ((to - from) < 50) {
            parseAndConvert();
        } else {
            int mid = (to + from) >>> 1;
            OthersParserAndConvert task1 = new OthersParserAndConvert(from, mid, fileName, result, parsString);
            OthersParserAndConvert task2 = new OthersParserAndConvert(mid, to, fileName, result, parsString);
            invokeAll(task1, task2);
        }
    }

    /**
     * Парсинг данных из файла .csv
     * и конвертация в формат вывода
     * добавление в список result конвертированных объектов
     */

    private void parseAndConvert() {
        List<String> deltaParsString = new ArrayList<>();
        for (int i = from; i < to; i++) {
            deltaParsString.add(parsString.get(i));
        }
        List<OrderDto> othersFileOrder = deltaParsString.stream()
                .map(s -> s.split(","))
                .map(OrderDto::new).collect(Collectors.toList());
        int k = 0; // Счетчик для списка othersFileOrder
        for (int i = from; i < to; i++) {
            result.add(new Order(othersFileOrder.get(k), i + 1, fileName));
            k++;
        }
    }
}
