# Unidade 03 - Relacionamentos entre Classes e Coleções

> Material de apoio da disciplina de Programação Orientada a Objetos (POO).
> Cada aula é registrada em uma seção própria abaixo, com uma âncora referenciada no sumário.

## Sumário

- [Aula 11 - Associações, ArrayList e Multiplicidade](#aula-11)

<!--
Padrão para as próximas aulas:
1. Adicione o link no sumário acima, seguindo o mesmo formato:
   - [Aula N - Título](#aula-n)
2. Crie a seção da aula com a âncora correspondente:
   <a id="aula-n"></a>
   ## Aula N - Título
-->

---

<a id="aula-11"></a>

## Aula 11 - Associações, ArrayList e Multiplicidade

### 1. Associações

#### O que é uma associação

- Dois objetos podem estar ligados um ao outro; a ligação permite navegar de um objeto ao outro.
- Para que seja possível ligar objetos, as classes desses objetos devem estar relacionadas por meio de uma associação.
- A associação é um tipo de relacionamento que conecta duas classes.

No diagrama de classes, a associação é desenhada como uma linha ligando as duas classes. No diagrama de objetos, a linha liga os dois objetos concretos:

```text
: ContaBancaria                 : Cliente
numero = 4810-5      ---------  nome = Gustavo Pereira
saldo = 2150                    email = pereira@furb.br
                                celular = 47 99991199
```

```text
ContaBancaria  ───────────  Cliente
- numero : String           - nome : String
- saldo : double            - email : String
                            - celular : String
```

---

#### Aprimoramentos (adornos) da associação

As associações podem ser mais detalhadas por meio de aprimoramentos (também chamados de adornos). Existem quatro tipos: papel, nome, multiplicidade e navegabilidade.

**Papel**

- Cada classe que participa de uma associação tem um papel específico.
- É possível nomear explicitamente o papel de uma classe no relacionamento.
- Exemplo: numa associação entre `Pessoa` e `Livro`, a `Pessoa` tem o papel `- autor` e o `Livro` tem o papel `- obra`.

**Nome**

- Uma associação pode ter um nome, usado para descrever a natureza do relacionamento.
- Pode-se indicar a direção de leitura do nome com uma seta.
- Exemplo: `Pessoa escreve ▶ Livro`, ou, na direção inversa, `Livro ◀ é escrito por Pessoa`.

**Multiplicidade**

- Determina a quantidade de objetos que podem ser interconectados.
- Essa quantidade é chamada de multiplicidade.
- É escrita como uma expressão indicando o valor mínimo e o máximo.

Para preencher a multiplicidade de cada ponta, pergunte, fixando um objeto do lado oposto, qual a quantidade mínima e máxima do outro lado:

- Dado um autor, qual a quantidade mínima e máxima de obras que ele pode escrever? No mínimo nenhuma (0) e no máximo indeterminado (`*`), logo `0..*`.
- Dada uma obra, qual a quantidade mínima e máxima de autores que podem escrevê-la? No mínimo um (1) e no máximo indeterminado (`*`), logo `1..*`.

Notações mais comuns de multiplicidade:

| Multiplicidade | Significado |
|---|---|
| `0..1` | Os objetos não precisam estar relacionados; havendo relacionamento, no máximo uma instância se relaciona com a outra classe |
| `1` (ou `1..1`) | Exatamente um objeto se relaciona com os objetos da outra classe |
| `0..*` | Pode ou não haver instâncias participando do relacionamento (zero ou muitas) |
| `1..*` | Há pelo menos um objeto envolvido, podendo haver muitos |
| `3..5` | Pelo menos 3 e no máximo 5 instâncias envolvidas |

**Navegabilidade**

- Por padrão, a navegação entre objetos é bidirecional.
- É possível limitar a navegação a uma única direção, desenhando uma seta na associação. Trata-se de uma navegação unidirecional.
- Exemplo: com a seta apontando de `Livro` para `Pessoa`, a partir de um livro é possível navegar até seus autores, mas a partir de uma pessoa não é possível navegar até suas obras.

---

#### Associação reflexiva

- É uma associação que estabelece uma conexão entre objetos de uma mesma classe.
- Exemplo: a classe `Funcionario` associada a si mesma pela associação `chefia ▶`, com os papéis `- gerente` e `- empregado`. Um funcionário no papel de gerente chefia outros funcionários no papel de empregado.

```text
                chefia ▶
        - gerente          - empregado
              +---------------------+
              |    Funcionario      |
              +---------------------+
              | - nome : String     |
              +---------------------+
```

---

### 2. ArrayList

#### O que é um ArrayList

- Uma instância da classe `ArrayList` pode armazenar diversos objetos.
- É uma forma alternativa ao vetor (array) para guardar dados.
- Não possui tamanho limitado, como o vetor.
- Não armazena dados primitivos (apenas objetos).
- Os objetos armazenados são recuperados pela posição.

> Para usar, é necessário importar a classe: `import java.util.ArrayList;`

---

#### Criando um ArrayList (operador diamante)

O construtor padrão cria um `ArrayList` vazio. Usa-se o operador diamante (`<>`) para indicar o tipo dos objetos que se quer armazenar:

```java
ArrayList<Aluno> turma;
turma = new ArrayList<>();
```

É possível combinar a declaração e a criação em uma única linha:

```java
ArrayList<Aluno> turma = new ArrayList<>();
```

---

#### Principais métodos

| Método | Descrição |
|---|---|
| `add(E)` | Guarda um objeto |
| `get(int)` | Obtém um objeto na posição indicada |
| `remove(Object)` | Remove o objeto |
| `size()` | Retorna a quantidade de objetos armazenados |

---

#### Exemplo de uso

```java
ArrayList<Aluno> turma = new ArrayList<>();

Aluno a1 = new Aluno("Leonir Santos");
turma.add(a1);

Aluno a2 = new Aluno("Anderson Gonçalves");
turma.add(a2);

turma.add(new Aluno("Jean Cardoso"));
```

---

#### Percorrendo um ArrayList

Com o `for` tradicional, acessando pela posição:

```java
Aluno aluno;
for (int i = 0; i < turma.size(); i++) {
    aluno = turma.get(i);
    System.out.println(aluno.getNome());
}
```

Com o `for-each`, mais enxuto quando não é preciso o índice:

```java
for (Aluno aluno : turma) {
    System.out.println(aluno.getNome());
}
```

---

### 3. Associações com multiplicidade acima de 1

Quando a multiplicidade de uma associação é maior que 1 (por exemplo `1..*` ou `1..50`), a classe de origem precisa guardar vários objetos da classe de destino. Para isso, usamos um `ArrayList`.

#### Passos para traduzir a associação

1. **Criar uma variável na classe de origem.** O identificador da variável é igual ao papel. Na ausência de papel, o nome é derivado do nome da classe de destino, geralmente no plural.
2. **O tipo da variável deve ser `ArrayList`,** usando o operador diamante com o nome da classe de destino.
3. **Criar a instância do `ArrayList`,** na declaração da variável ou no construtor.

Exemplo: a classe `Disciplina` (origem) mantém muitos objetos `Aluno` (destino):

```java
import java.util.ArrayList;

public class Disciplina {

    private String nome;
    private ArrayList<Aluno> alunos = new ArrayList<>();

}
```

4. **A classe de origem deve implementar métodos para incluir, remover e obter** os objetos contidos na associação.

---

#### Método para incluir

Recebe como parâmetro o objeto a ser adicionado:

```java
public void incluirAluno(Aluno aluno) {
    alunos.add(aluno);
}
```

Se a associação for limitada (por exemplo `1..50`), deve-se impedir a inclusão de mais objetos do que o permitido:

```java
public void incluirAluno(Aluno aluno) {
    if (alunos.size() == 50) {
        throw new RuntimeException("Não é possível incluir mais alunos na disciplina");
    }
    alunos.add(aluno);
}
```

> Observação: aqui o limite é sinalizado com `throw`, exatamente a ideia vista na aula de exceções (Unidade 01). O slide usa `RuntimeException`; uma exceção mais específica como `IllegalStateException` (situação inválida do objeto) comunica melhor a intenção de "coleção cheia".

---

#### Método para remover

Recebe como parâmetro o objeto a ser removido:

```java
public void removerAluno(Aluno aluno) {
    alunos.remove(aluno);
}
```

---

#### Getter da associação

```java
public ArrayList<Aluno> getAlunos() {
    return alunos;
}
```

Não existe setter para a variável que mantém uma associação com multiplicidade maior que 1. A inclusão e a remoção são feitas pelos métodos `incluirAluno` e `removerAluno`, que é onde ficam as regras (como o limite de capacidade).

> Observação: retornar diretamente o `ArrayList` interno expõe a coleção, permitindo que outra classe faça `add`/`remove` por fora, contornando as regras dos métodos de inclusão. É um ponto de atenção de encapsulamento; por ora seguimos o padrão do slide, mas vale ter em mente ao evoluir o projeto.
