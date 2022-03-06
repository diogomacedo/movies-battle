# Movies Battle

API REST de um jogo no estilo #CardGame onde dois filmes serão apresentado e o jogador deve acertar qual deles possui a melhor avaliação no IMDB.

Para jogar, é necessário se autenticar.

A autenticação é realizada através de ***basic auth***.

Usuários de testes disponíveis:

| Nome | Nome de Usuário | Senha |
| ---- | --------------- | ----- |
| Ana Paula | paulana | P4ul4n4 |
| Diogo Macedo | diogomacedo01 | TitiTata99* |
| Rosemary Rosa | roserosa | umaSenhaQualquer |


O jogo contempla 5 endpoints. São eles:

* POST /partidas/iniciar
* POST /partidas/encerrar
* GET /rodadas/responder
* POST /rodadas/reponder
* GET /ranking

