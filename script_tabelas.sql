

create table Escola(Cod_Escola varchar2(100) primary key,Nome varchar2(100),Endereco varchar2(100),Cidade varchar2(100));
create table Competicao(Cod_Competicao varchar2(100)primary key,Nome varchar2(100),Escalao varchar2(100),Ano number);
create table Torneio(Cod_Torneio varchar2(100)references Competicao(Cod_Competicao) primary key,tipoCompeticao number);
create table Campeonato(Cod_Campeonato varchar2(100) references Competicao(Cod_Competicao) primary key,tipoCompeticao number);
create table Equipa(Cod_Equipa varchar2(100)primary key,Nome varchar2(100),Escalao varchar2(100),Cod_Escola varchar2(100));
create table Campo(Cod_Campo varchar2(100)primary key,Nome varchar2(100),Localidade varchar2(100),Quadrantes number,Cod_Escola varchar2(100)references Escola(Cod_Escola));
create table Jogador(Cod_Jogador varchar2(100)primary key,Nome varchar2(100),DatadeNascimento Date,Cod_Equipa varchar2(100)references Equipa(Cod_Equipa));
create table Jornada(Cod_Jornada varchar2(100)primary key,Cod_Competicao varchar2(100)references Competicao(Cod_Competicao),DataJornada Date);
create table Classificacao (Cod_Competicao varchar2(100)references Competicao(Cod_Competicao),Cod_Equipa varchar2(100),Pontos number,Golos number,Ano number, primary key(Cod_Competicao,Cod_Equipa));
create table Jogo(Cod_Jogo varchar2(100)primary key,Cod_Jornada varchar2(100) references Jornada(Cod_Jornada),Cod_Competicao varchar2(100)references Competicao(Cod_Competicao),Golo1 number,Golo2 number,Cod_Equipa1 varchar2(100) references Equipa(Cod_Equipa),Equipa2 varchar2(100) references Equipa(Cod_Equipa),Cod_Campo varchar2(100) references Campo(cod_Campo));
create table Marcadores(Cod_Competicao varchar2(100) references Competicao(Cod_Competicao) ,Cod_Jogador varchar2(100),primary key(Cod_Competicao,Cod_Jogador),Golo number);
create table Ocupacao(Cod_Campo varchar2(100) references Campo(Cod_Campo),Data_Jogo date,Quadrante1 number,Quadrante2 number,Quadrante3 number,Quandrante4 number, primary key(Cod_Campo,Data_Jogo));
create table Juiz(username varchar2(100)primary key,Pass varchar2(100),Nome varchar2(100));
create table EquipaTorneio(Cod_Equipa varchar2(100)references Equipa(cod_Equipa),Cod_Competicao varchar2(100)references Competicao(Cod_Competicao),Grupo number,Eliminado varchar2(10) references Jogo(Cod_jogo),primary key(Cod_Equipa,Cod_Competicao));
create table EquipaCampeonato(Cod_Equipa varchar2(100)references Equipa(cod_Equipa),Cod_Competicao varchar2(100)references Competicao(Cod_Competicao),primary key(Cod_Equipa,Cod_Competicao));
create table Gestor(username varchar2(100)primary key,Pass varchar2(100),Nome varchar2(100));
 alter table Torneio add(TIPOTORNEIO number);
 
 insert into equipa(cod_equipa)values(0);
 insert into competicao(cod_competicao)values(0);
 insert into equipatorneio(cod_equipa,cod_competicao)values(0,0);
 