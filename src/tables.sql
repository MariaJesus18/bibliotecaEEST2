drop database if exists bibliotecaEEST;
create database if not exists bibliotecaEEST;
use bibliotecaEEST;

create table if not exists tb_categorias(
    cat_id int primary key auto_increment,
    cat_categoria varchar(30) not null
    );

create table if not exists tb_livros (
    liv_isbn bigInt(13) primary key,
    liv_titulo varchar(50),
    liv_autor varchar(50),
    liv_status varchar(15),
    liv_cat_id int,
    foreign key(liv_cat_id) references tb_categorias(cat_id));

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
    dev_id int primary key auto_increment,
    dev_emp_id int,
    dev_liv_isbn bigInt(13) not null,
    dev_use_matricula bigInt(14) not null,
    dev_data timestamp not null  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key(dev_liv_isbn) references tb_livros(liv_isbn),
    foreign key(dev_emp_id) references tb_emprestimos(emp_id),
    foreign key(dev_use_matricula) references tb_users(use_matricula));



    

drop trigger if exists `bibliotecaeest`.`tb_emprestimos_BEFORE_INSERT`;
delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER  `bibliotecaeest`.`tb_emprestimos_BEFORE_INSERT` BEFORE INSERT ON `tb_emprestimos` FOR EACH ROW
begin 
if ((select liv_status from tb_livros, tb_emprestimos where emp_liv_isbn = liv_isbn and emp_liv_isbn = new.emp_liv_isbn) = 'Indisponivel') then
  signal sqlstate '45000' set message_text = 'O livro já tá emprestado';
end if;
end //

delimiter ;

drop trigger if exists`bibliotecaeest`.`tb_devolucoes_BEFORE_INSERT`;
delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER `bibliotecaeest`.`tb_devolucoes_BEFORE_INSERT` BEFORE INSERT ON `tb_devolucoes` FOR EACH ROW
begin 
if ((select liv_status from tb_livros, tb_devolucoes where dev_liv_isbn = liv_isbn and dev_liv_isbn = new.dev_liv_isbn) = 'Disponivel') then
  signal sqlstate '45000' set message_text = 'O livro não tá emprestado';
end if;
end //

delimiter ;



