package app;

import app.data.Order;
import app.data.OrderDto;
import app.multi.JsonThread;
import app.multi.OthersFileThread;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;


@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);    }

    @Override
    public void run(String... args) throws Exception {
        Vector<Order> result = new Vector<>();// список для сбора и вывода пропарсенных и конвертированных данных
        Thread[] threads = new Thread[args.length];
        for (int i = 0; i < args.length; i++) {
            int point = args[i].lastIndexOf('.') + 1;
            String fileFormat = args[i].substring(point);
            if (fileFormat.equals("json")) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    List<OrderDto> orderDto = mapper.readValue(new File(args[i]), new TypeReference<List<OrderDto>>() {
                    });
                    threads[i] = new Thread(new JsonThread(args[i], result, orderDto));
                } catch (JsonMappingException exception) {
                    threads[i] = new Thread(new JsonThread(args[i], result, null));
                    System.out.println(parsingError(exception.getMessage(), args[i]));
                }
            } else {
                threads[i] = new Thread(new OthersFileThread(args[i], result, Files.readAllLines(Paths.get(args[i]))));
            }
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        result.sort(Comparator.comparing(Order::getId));//сортировка элементов списка по полю id
        ObjectMapper mapper = new ObjectMapper();
        result.forEach(res -> {                        // вывод на печать элементов списка result
            try (OutputStream outputStream = new ByteArrayOutputStream()) {
                mapper.writeValue(outputStream, res);
                System.out.println(outputStream.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Метод для вывода сообщения ошибки
     * @param getMessage
     * @param fileName
     * @return
     */
    private String parsingError(String getMessage, String fileName) {
        String[] arrayFromMessage = getMessage
                .replaceAll("(?U)[\\pP]", "")
                .split(" ");
        return "Ошибка при парсинге файла " + fileName + "\nНераспознанное поле - " + arrayFromMessage[2] + " в строке " + arrayFromMessage[20];
    }
}
