drop database if exists bibliotecaEEST;
create database if not exists bibliotecaEEST;
use bibliotecaEEST;

create table if not exists tb_livros (
    liv_isbn bigInt(13) primary key,
    liv_titulo varchar(50),
    liv_autor varchar(50), 
    liv_status varchar(15));

create table if not exists tb_users (
    use_matricula bigInt(14) primary key,
    use_nome varchar(50));

create table if not exists tb_emprestimos (
    emp_id int auto_increment primary key ,
    emp_liv_isbn bigInt(13) not null,
    emp_use_matricula bigInt(14) not null,
    emp_data timestamp not null  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key(emp_liv_isbn) references tb_livros(liv_isbn),
    foreign key(emp_use_matricula) references tb_users(use_matricula));

create table if not exists tb_devolucoes (
    dev_emp_id int ,
    dev_liv_isbn bigInt(13) not null,
    dev_use_matricula bigInt(14) not null,
    dev_data timestamp not null  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key(dev_liv_isbn) references tb_livros(liv_isbn),
    foreign key(dev_emp_id) references tb_emprestimos(emp_id),
    foreign key(dev_use_matricula) references tb_users(use_matricula));