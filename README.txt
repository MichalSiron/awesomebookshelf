1) Create database called 'bookshelf'
2) Create tables like is written in 'create_scripts.sql'
3) Run scripts:
 psql -c "\copy tb1_author from 'conf/bookshelf_public_tb1_author.csv' delimiter ';' csv;" -d bookshelf
 psql -c "\copy tb2_book from 'conf/bookshelf_public_tb2_book.csv' delimiter ';' csv;" -d bookshelf
 psql -c "\copy tb3_reader from 'conf/bookshelf_public_tb3_reader.csv' delimiter ';' csv;" -d bookshelf