SET MODE MYSQL;

create table orders (
id bigint not null auto_increment,
descricao varchar(100) not null,
status varchar(50) not null,
code varchar(100) not null,
total decimal(15,4) not null,
customer varchar(100) not null,
data_emissao date not null,
primary key (id),
UNIQUE(code)
)engine=InnoDB default charset=utf8;

create table item (
id bigint not null auto_increment,
code varchar(100) not null,
quantity integer not null,
price decimal(15,4) not null,
total decimal(15,4) not null,
order_id bigint not null,
constraint pk_order foreign key (order_id) references orders(id),
primary key (id)
)engine=InnoDB default charset=utf8;
