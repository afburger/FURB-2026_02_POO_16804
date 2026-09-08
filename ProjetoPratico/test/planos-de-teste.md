# Sonora - Fase 03: Planos de teste (PL01 a PL08)

Cada linha de plano corresponde a um método de teste. As saídas esperadas seguem o contrato da Fase 02: exceção para uso indevido (índice fora da faixa, conteúdo inválido) e retorno por valor no fluxo normal (playlist cheia devolve `false`, busca sem resultado devolve `null`).

## PL01 - Validar Musica.getDuracaoFormatada()

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Duração com minutos e segundos | Musica de 125 segundos | Deve resultar em "02:05" |
| 2 | Duração redonda em minutos | Musica de 90 segundos | Deve resultar em "01:30" |
| 3 | Menos de um minuto, com zero à esquerda | Musica de 5 segundos | Deve resultar em "00:05" |
| 4 | Dois dígitos nos minutos | Musica de 600 segundos | Deve resultar em "10:00" |
| 5 | Valor logo abaixo de dez minutos | Musica de 599 segundos | Deve resultar em "09:59" |

## PL02 - Validar construtor de Musica com dados inválidos

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Título vazio deve ser rejeitado | titulo "", artista "Queen", duracao 355 | Deve lançar IllegalArgumentException |
| 2 | Título nulo deve ser rejeitado | titulo null, artista "Queen", duracao 355 | Deve lançar IllegalArgumentException |
| 3 | Artista vazio deve ser rejeitado | titulo "Bohemian Rhapsody", artista "", duracao 355 | Deve lançar IllegalArgumentException |
| 4 | Duração zero deve ser rejeitada | titulo válido, artista válido, duracao 0 | Deve lançar IllegalArgumentException |
| 5 | Duração negativa deve ser rejeitada | titulo válido, artista válido, duracao -10 | Deve lançar IllegalArgumentException |
| 6 | Dados válidos criam a música | titulo "Bohemian Rhapsody", artista "Queen", duracao 355 | Objeto criado, com id maior que zero |

## PL03 - Playlist.adicionar(musica)

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Adicionar em playlist com espaço | playlist vazia, adicionar 1 música | Retorna true; quantidade passa a 1 |
| 2 | Adicionar várias dentro da capacidade | adicionar 3 músicas | Retorna true nas 3; quantidade igual a 3 |
| 3 | Adicionar além da capacidade | playlist com 100 músicas, adicionar mais 1 | Retorna false; quantidade continua 100 |

## PL04 - Playlist.getNaPosicao(indice)

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Posição válida devolve a música certa | playlist [A, B, C], getNaPosicao(1) | Retorna a música B |
| 2 | Índice negativo | getNaPosicao(-1) | Lança IndexOutOfBoundsException |
| 3 | Índice além da quantidade | playlist com 3 músicas, getNaPosicao(3) | Lança IndexOutOfBoundsException |

## PL05 - Playlist.removerNaPosicao(indice)

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Remoção reorganiza sem buraco | playlist [A, B, C], removerNaPosicao(0) | quantidade igual a 2; posição 0 passa a ser B e posição 1 passa a ser C |
| 2 | Índice negativo | removerNaPosicao(-1) | Lança IndexOutOfBoundsException |
| 3 | Índice além da quantidade | playlist [A, B], removerNaPosicao(2) | Lança IndexOutOfBoundsException |

## PL06 - Plataforma.buscarMusica / buscarMusicaPorId

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Buscar título existente | plataforma com "Faixa 1", buscarMusica("Faixa 1") | Retorna a música (não null) |
| 2 | Buscar título inexistente | buscarMusica("Inexistente") | Retorna null |
| 3 | Buscar id existente | buscarMusicaPorId(id de música cadastrada) | Retorna a música |
| 4 | Buscar id inexistente | buscarMusicaPorId(999999) | Retorna null |

## PL07 - Musica.reproduzir()

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Estado inicial | música recém-criada | reproducoes igual a 0 |
| 2 | Uma reprodução incrementa | reproduzir() 1 vez | reproducoes igual a 1 |
| 3 | Várias reproduções | reproduzir() 3 vezes | reproducoes igual a 3 |

## PL08 - Contadores de id

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Ids de Musica sequenciais | criar m1 e m2 em sequência | m2.getId() igual a m1.getId() + 1 |
| 2 | Contadores independentes | criar Musica, depois Usuario, depois Musica | o id da 2a Musica segue o da 1a, sem ser afetado pela criação do Usuario |
