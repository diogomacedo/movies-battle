# Movies Battle

API REST de um jogo no estilo #CardGame onde dois filmes serão apresentado e o jogador deve acertar qual deles possui a melhor avaliação no IMDB.

Para jogar, é necessário informar o usuário e seha.

Os usuários de testes disponíveis são:

| Nome | Nome de Usuário | Senha |
| ---- | --------------- | ----- |
| Ana Paula | paulana | P4ul4n4 |
| Diogo Macedo | diogomacedo01 | TitiTata99* |
| Rosemary Rosa | roserosa | umaSenhaQualquer |

O jogo envolve os conceitos de **partida** e **rodada**.

É mais fácil explicar esses conceitos a partir das rodadas.

Chama-se de **rodada** o período em que o jogador recebe o nome de dois filmes e deve responder qual deles tem a melhor avaliação.

Chama-se de **partida** um conjunto de rodadas. É através dela que se descobre a quantidade de respostas certas e erradas do jogador.

## Regras do jogo

1. O jogo só começa quando uma partida é iniciada;
2. Uma partida pode ser encerrada a qualquer momento;
3. Uma rodada só pode ser respondida após a partida ter sido iniciada;
4. Enquanto a rodada corrente não for respondida, não é possível obter uma nova;
5. Se o usuário cometer 3 erros, a partida é automaticamente encerrada;
6. A pontuação da partida é calculada a partir da quantidade de acertos X a porcentagem de acertos;


## Endpoints disponíveis

Há 5 endpoints disponíveis. São eles:

* POST /partidas/iniciar
* POST /partidas/encerrar
* GET /rodadas/responder
* POST /rodadas/reponder
* GET /ranking

#### POST /partidas/iniciar

Como o nome sugere,este endpoints é responsável por iniciar uma partida.

O jogado sempre deve iniciar uma partida para poder participar das rodadas

#### POST /partidas/encerrar

#### GET /rodadas/responder

#### POST /rodadas/responder

#### GET /ranking

