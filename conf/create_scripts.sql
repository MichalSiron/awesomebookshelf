create table tb1_author
(
	tb_id integer not null
		constraint tb_author_tb_id_pk
		primary key,
	tb_name varchar(50) not null
)
;

create table tb2_book
(
	tb2_id integer not null
		constraint tb_book_tb_id_pk
		primary key,
	tb2_name varchar(40) not null,
	tb2_author integer not null
		constraint tb_book_tb_author_tb_id_fk
		references tb1_author,
	tb2_reader integer
)
;

create table tb3_reader
(
	tb3_id integer not null
		constraint tb3_reader_pkey
		primary key,
	tb3_name varchar(40) not null,
	tb3_birth date not null
)
;

alter table tb2_book
	add constraint tb2_book_tb3_reader_tb3_id_fk
foreign key (tb2_reader) references tb3_reader
;

