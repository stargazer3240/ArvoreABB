<!--
SPDX-FileCopyrightText: 2023 Fabrício Moura Jácome
SPDX-FileCopyrightText: 2023 Ramon Oliveira de Almeida

SPDX-License-Identifier: MIT-0
-->

# Árvore Binária de Busca
Implementação de uma Árvore Binária de Busca para a matéria EDBII da UFRN.

## Instalação

### Requisitos
- Eclipse IDE para Java com suporte a JDK 17+ (https://www.eclipse.org/downloads/packages/installer)

### Compilação
- Crie um novo projeto Java na IDE Eclipse e insira os arquivos dentro do projeto.
- O Eclipse ficará responsável por criar os arquivos executáveis e por rodar o programa.

## Uso
### Iniciando o programa
- Para executar o programa, é necessário que hajam dois arquivos dentro da pasta `./input`: um arquivo chamado `arvore.txt` com a disposição inicial da árvore e um arquivo `comandos.txt` com a sequência de comandos desejada.

### Exemplo de arquivos input
```
./input/arvore.txt

32 13 5 41 20 60
```

```
./input/comandos.txt

CHEIA
COMPLETA
ENESIMO 3
INSIRA 36
CHEIA
PREORDEM
IMPRIMA 1
IMPRIMA 2
REMOVA 50
INSIRA 15
INSIRA 39
REMOVA 32
POSICAO 15
INSIRA 39
ENESIMO 5
MEDIANA
MEDIA 20
BUSCAR 36
INSIRA 25
MEDIANA
```

## Limitações
- O projeto não consegue identificar se um valor já está presente na árvore ao inserir, ou que um valor não está presente ao remover. De qualquer forma, ao realizar essas operações "redundantes", o estado da árvore se mantém inalterado e o programa prossegue executando.

## Autores
- Fabrício Moura Jácome
- Ramon Oliveira de Almeida

## Licença
Esse projeto está licenciado sob a licença [MIT License](https://spdx.org/licenses/MIT.html) (arquivos fonte) e sob [MIT-0 License](https://spdx.org/licenses/MIT-0) (documentação e arquivos de configuração) - veja a pasta LICENSE para mais detalhes.
