drop table if exists news;
drop table if exists category;


create table news (id bigint not null auto_increment,
                   publication_date datetime(6),
                   text varchar(5000),
                   title varchar(255),
                   category_id bigint,
                   primary key (id));

create table categories (id bigint not null auto_increment,
                       name enum('WORLD','SPORT','CINEMA','CARS','GAMES') not null,
                       primary key (id));

