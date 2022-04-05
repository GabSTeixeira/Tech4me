
perguta 1: 
    R: use tech4music
    E: isso "cria" o banco de dados, porém ele só vai ser realmente criado quando for inserido um documento.

pergunta 2:
    R: db.musicas.insertMany([{
    "titulo": "Forever",
    "artista": "Kiss",
    "album": "Hot in the Shade",
    "genero": "Rock",
    "anoLancamento": 1989,
    "compositor": "Paul Stanley"},{
    "titulo": "Algo parecido",
    "artista": "Skank",
    "album": "Os três primeiros",
    "genero": "Pop",
    "anoLancamento": 2018,
    "compositor": "Samuel Rosa"},{
    "titulo": "O que me importa",
    "artista": "Marisa Monte",
    "album": "Memórias, crônicas e declarações de amor",
    "genero": "MPB",
    "anoLancamento": 2000,
    "compositor": "Jose de Ribamar Cury"
}])
    E: cria a collection musicas passando 3 musicas pra ela