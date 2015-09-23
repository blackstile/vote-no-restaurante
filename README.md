# Vote no restaurante - Escolha seus restaurantes preferidos

========
## Solução para ranking de votação.
  O objetivo do programa é classificar a ordem de prioridade entre 5 restaurantes fastfood. É apresentado sempre 2 restaurantes onde o usuário escolhe o 1 preferido, e assim é apresentado os demais, até que seja possível classificar a ordem de preferência de todos restaurantes, e atribuido os votos. Exemplo:
  Usuário prefere 
    McDonalds > Bobs > Habibs > Subway
  Na ultima opção de voto aparece para escolher entre McDonalds X Burguer King, caso o usuário escolhe Burguer King, a lista de preferência ficaria, Burguer King > McDonalds > Bobs > Habibs > Subway. Ou seja por escolher Burguer King ao McDonalds, ele ja colocou como preferência todos os outros em precisar votar em 1 para cada opção, pois é entendido que se ele prefere o McDonalds em relação a todos, e prefere o Burguer King em relação ao McDonalds, então ele prefere Burguer King > Todos. Com isso o ranking ficaria:
  Burguer King: 4 votos
  Burguer King: 4 votos
  

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
* ***Redis***: http://redis.io/download (Os passos para instalação e execução seguem no mesmo link, utilizei o endereço e porta padrão: localhost:6379)

#### Execução

###### Execução do Redis
Para executar o Redis basta acessar a pasta "src" e executar o script ./redis-server

###### Execução do Programa

Primeiramente, prepare o projeto com o maven (Lembrando que para executar os testes, o Redis deve ser executado):
```
mvn install;
```
Existe 2 formas de execução do programa:

1. ***Execução por testes unitários***: Neste caso basta executar os testes que automaticamente é iniciado e derrubado o grizzly para utilização das webservices.
2. ***Execução grizzly manualmente***: Neste caso basta executar o metódo "main" da classe "ConfigApp", com isso o grizzly é iniciado e é possível executar os webservices externamente, seja pela browser, curl, etc.

#### Melhorias
Para uma solução mais estruturada, performática e escalável, as seguintes questões precisariam ser aplicadas:
  1. Banco de dados mais apropriado para o tipo de dado, como por exemplo banco de dados de grafos.
  2. Validações dos tipos de parâmetros das requisições.
  3. Tratamento mais adequado para mensagens.
  4. Controle de injeção de dependências.

## Utilização

O WebService RESTFull é formado por 3 serviços:

###### Inserção de Mapa
  ***URL***: http://localhost:8080/logistica/deliveryRoute/addRouteMap <br>
  ***Tipo***: PUT <br>
  ***Formato Requisição***: JSON <br>

  ```
  {
    "name":"SP",
    "routes":[
      {"origin":"A","destination":"B","distance":10},
      {"origin":"B","destination":"D","distance":15},
      {"origin":"A","destination":"C","distance":20},
      {"origin":"C","destination":"D","distance":30},
      {"origin":"B","destination":"E","distance":50},
      {"origin":"D","destination":"E","distance":30}
    ]
  }
  ```

  ***Parâmetros Requisição***:<br>
    * ***name***: Nome do mapa - String;<br>
    * ***routes***: Lista de rodas contendo:<br>
    * ***origin***: Origem da rota - String;<br>
    * ***destination***: Destino da rota - String;<br>
    * ***distance***: Distância em Km - Inteiro;<br>

  ***Formato Resposta***: JSON

  ```
  {
    "status":"ERROR/SUCCESS",
    "message":"Mensagem de resposta"
  }
  ```

  ***Parâmetros Resposta***:<br>
  * ***status***: Status da requisição - ERROR/SUCCESS;<br>
  * ***message***: Mensagem - String:<br>

  ***Exemplo de utilização***:
  ```
  curl -v -H "Content-Type: application/json" -X POST -d '{"name":"SP","routes":[{"origin":"A","destination":"B","distance":10},{"origin":"B","destination":"D","distance":15},{"origin":"A","destination":"C","distance":20},{"origin":"C","destination":"D","distance":30},{"origin":"B","destination":"E","distance":50},{"origin":"D","destination":"E","distance":30}]}' http://localhost:8080/logistica/deliveryRoute/addRouteMap 
  ```

###### Busca de rotas<br>
  ***URL***: http://localhost:8080/logistica/deliveryRoute/routes/{mapName}<br>
  ***Tipo***: GET<br>
  ***Parametros Requisição***:<br>
    * ***mapName***: Nome do mapa - String

  ***Formato Resposta***: JSON
  ```
  [
    {"origin":"A","destination":"B","distance":10},
    {"origin":"B","destination":"D","distance":15},
    {"origin":"A","destination":"C","distance":20},
    {"origin":"C","destination":"D","distance":30},
    {"origin":"B","destination":"E","distance":50},
    {"origin":"D","destination":"E","distance":30}
  ]
  ```
  ***Parâmetros da reposta***:<br>
    * ***origin***: Origem da rota - String;<br>
    * ***destination***: Destino da rota - String;<br>
    * ***distance***: Distância em Km - Inteiro;<br>

 ***Exemplo de utilização***:
  ```
  curl -v -H "Content-type: application/xml" -X GET http://localhost:8080/logistica/deliveryRoute/routes/SP
  ```

###### Busca de menor custo<br>
  ***URL***: http://localhost:8080/logistica/deliveryRoute/lowerDeliveryCost<br>
  ***Tipo***: POST<br>
  ***Formato Requisição***: JSON<br>
  ```
  {
    "mapName":"SP",
    "origin":"A",
    "destination":"D",
    "autonomy":10,
    "fuelPrice":2.50
  }
  ```
  ***Parâmetros Requisição***:<br>
    * ***mapName***: Nome do mapa - String;<br>
    * ***origin***: Local de origem - String:<br>
    * ***destination***: Local de destino - String;<br>
    * ***autonomy***: Autonomia do veiculo Km por litro - Float 2 casas decimais;<br>
    * ***fuelPrice***: Preço do litro de combustível - Float 2 casas decimais;<br>

  ***Formato Resposta***: JSON
  ```
  {
    "routes":["A","B","D"],
    "totalCost":6.25
  }
  ```
  ***Parâmetros Resposta***:<br>
    * ***routes***: Lista encadeada do caminho de menor custo;<br>
    * ***totalCost***: Custo Total - Float 2 casas decimais:<br>

  ***Exemplo de utilização***:
  ```
  curl -v -H "Content-Type: application/json" -X POST -d '{"mapName":"SP","origin":"A","destination":"D","autonomy":10,"fuelPrice":2.50}' http://localhost:8080/logistica/deliveryRoute/lowerDeliveryCost 
  ```

