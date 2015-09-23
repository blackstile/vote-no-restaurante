# Vote no restaurante - Escolha seus restaurantes preferidos

========
## Solução para ranking de votação.
  O objetivo do programa é classificar a ordem de prioridade entre 5 restaurantes fastfood. É apresentado sempre 2 restaurantes onde o usuário escolhe o 1 preferido, e assim é apresentado os demais, até que seja possível classificar a ordem de preferência de todos restaurantes, e atribuido os votos. Exemplo:<br>
  Usuário prefere <br><br>
    McDonalds > Bobs > Habibs > Subway <br><br>
  Na ultima opção de voto aparece para escolher entre McDonalds X Burguer King, caso o usuário escolhe Burguer King, a lista de preferência ficaria, Burguer King > McDonalds > Bobs > Habibs > Subway. Ou seja por escolher Burguer King ao McDonalds, ele ja colocou como preferência todos os outros sem precisar votar em 1 vez para cada opção, pois é entendido que se ele prefere o McDonalds em relação a todos, e prefere o Burguer King em relação ao McDonalds, então ele prefere Burguer King > Todos. Com isso o ranking ficaria:<br>
  Burguer King: 4 votos<br>
  Mc Donalds: 3 votos<br>
  Bobs: 2 votos<br>
  Habibs: 1 votos<br>
  Subway: 0 votos<br>
  Ou seja as opções são apresentadas até que seja possível formar o ranking de preferência, e não necessariamente comparando todos contra todos.
  

## Arquitetura
-----------

  * ***Maven***: Ferramenta para automação de projetos, controlando dependências, versões, execução de testes, compilação, etc.
  * ***Spring MVC 4***: Framework seguindo modelo MVC para criação de aplicações WEB, com injeção de dependência, persistência de dados, etc.
  * ***JQuery***: Framework javascript.
  * ***JUnit***: Framework para testes unitários.
  * ***Jetty***: Plugin maven para servidor web.
  * ***JPA 2.0 (Hibernate/HSQLDB/Spring Data JPA)***: Framework ORM, Banco de dados em memória e manipulação de dados.

## Configuração e execução do programa
---------

#### Configuração
Para executar o programa é preciso ter instado as seguintes ferramentas:

* ***Java 7 ou 8***.
* ***Maven***: https://maven.apache.org/download.cgi<br>
    Para instalação e configuração seguir o seguinte: https://maven.apache.org/install.html e https://maven.apache.org/configure.html

#### Execução

###### Execução do Programa

Primeiramente, prepare o projeto com o maven:
```
mvn install;
```
Existe 3 formas de execução do programa:

1. ***Execução por testes unitários***: Neste caso basta executar os testes que automaticamente com maven.
2. ***Execução Jetty***: Neste caso após preparar o projeto, deve se executar o comando:
```
mvn jetty:run;
```
3. ***Execução Remota***: Neste caso basta acessar o link http://52.26.113.163:8180/votenorestaurante/ e utilizar o aplicativo.

#### Melhorias
Para uma solução mais estruturada e robusta, as seguintes questões precisariam ser aplicadas:
  1. Validações dos tipos de parâmetros das requisições.
  2. Tratamento mais adequado para mensagens.


