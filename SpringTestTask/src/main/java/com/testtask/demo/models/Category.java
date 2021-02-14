package com.testtask.demo.models;

import com.testtask.demo.enums.CategoryNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,
            columnDefinition = "enum('WORLD','SPORT','CINEMA','CARS','GAMES')")
    private CategoryNames name;

    @OneToMany(targetEntity = News.class, mappedBy = "category")
    private List<News> newsList;
    
    

}
