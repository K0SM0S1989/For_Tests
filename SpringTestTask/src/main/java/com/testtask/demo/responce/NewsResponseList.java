package com.testtask.demo.responce;

import com.testtask.demo.models.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsResponseList {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Long id;
    String title;
    String text;
    String timeStamp;

    public NewsResponseList(News news){
        this.id = news.getId();
        this.title = news.getTitle();
        this.text = news.getText();
        this.timeStamp = dateFormat.format(news.getPublicationDate());
    }

}
