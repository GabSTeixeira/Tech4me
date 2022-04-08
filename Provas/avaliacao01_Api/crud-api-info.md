Api para inserir, consultar, alterar e excluir musicas do banco de dados tech4musics na collection musicas.

    |MusicController|
    ->  |métodos|
        ->  |adicionar 1 musica        |  POST  |Url = http://localhost:8080/api/musicas/adicionar
            |adicionar varias musicas  |  POST  |Url = http://localhost:8080/api/musicas/adicionar/varios
            |consultar 1 unica musica  |   GET  |Url = http://localhost:8080/api/musicas/buscar/id
            |consultar varias musicas  |   GET  |Url = http://localhost:8080/api/musicas/buscar/todas
            |deletar 1 unica musica    | DELETE |Url = http://localhost:8080/api/musicas/deletar/id
            |modificar 1 unica musicas |   PUT  |Url = http://localhost:8080/api/musicas/alterar/id
        |converte de dto para MusicResponse quando é para retornar um obejeto pro client
        |converte de MusicRequest para MusicDto para todo input do client

    |MusicRequest|
    ->  |Não contem o atributo id
        |Ano de lançamento deve ser maior ou igual a 1600 e menor ou igual que 2040
        |Todo atributo não deve ser nulo e não pode ser vazio
    
    |MusicResponse|
    ->  |Não contem o atributo id
        |Não contem o atributo genero
        |Não contem o atributo ano de lançamento

    |ValidateException|
    ->  |Devolve strings com o atributo errado e o que esta errado com esse atributo
        |Devolve Strings com o atributo errado e o que esta errado com esse atributo em cada input do         adicionar varios

    |MongoConfig|
    ->  |só pra tirar o atributo class path do mongodb