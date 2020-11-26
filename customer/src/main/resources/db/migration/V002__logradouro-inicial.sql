create table logradouro (
id bigint not null auto_increment,
description varchar(100) not null,
customer_id bigint not null,
primary key (id),
constraint fk_customer foreign key (customer_id) references customer (id)
)engine=InnoDB default charset=utf8;