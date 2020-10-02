create table customer (
id bigint not null auto_increment,
name varchar(100) not null,
code varchar(100) not null,
primary key (id),
UNIQUE(code)
)engine=InnoDB default charset=utf8;