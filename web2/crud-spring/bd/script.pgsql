create table tb_usuario (
id_usu integer primary key generated always as
identity,
nome_usu varchar(100) not null,
login_usu varchar(30) not null,
senha_usu varchar(100) not null,
perfil_usu varchar(10) not null
);

INSERT INTO tb_usuario (nome_usu, login_usu, senha_usu, perfil_usu) values ('Igor', 'igor', 'igor', 'ADMIN');

create table tb_pessoa (
id_pes integer primary key generated always as
identity,
nome_pes varchar(100) not null,
idade_pes integer not null,
data_pes date not null,
motorista_pes varchar(10) not null
);