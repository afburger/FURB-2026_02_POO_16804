# Unidade 01 - Fundamentos de Programação Orientada a Objetos

> Material de apoio da disciplina de Programação Orientada a Objetos (POO).
> Cada aula é registrada em uma seção própria abaixo, com uma âncora referenciada no sumário.

## Sumário

- [Aula 1 - Introdução à Programação Orientada a Objetos](#aula-1)
- [Aula 2 - Escopo de Variáveis](#aula-2)
- [Aula 3 - Diagrama de Objetos, Encapsulamento e Membros de Classe](#aula-3)
- [Aula 6 - Lançamento de Exceções](#aula-6)
- [Aula 7 - Tratamento de Exceções](#aula-7)

<!--
Padrão para as próximas aulas:
1. Adicione o link no sumário acima, seguindo o mesmo formato:
   - [Aula N - Título](#aula-n)
2. Crie a seção da aula com a âncora correspondente:
   <a id="aula-n"></a>
   ## Aula N - Título
-->

---

<a id="aula-1"></a>

## Aula 1 - Introdução à Programação Orientada a Objetos

### 1. Paradigmas de programação

Um paradigma de programação fornece e determina a visão que o programador possui sobre a estruturação e a execução do programa. Existem diferentes paradigmas, e algumas linguagens são multiparadigmas (podem ser usadas segundo mais de um estilo).

A seguir, o mesmo problema (calcular Fibonacci) resolvido em quatro paradigmas.

#### Imperativo

Baseia-se em comandos sequenciais para modificar o estado do programa.
Exemplo de linguagens: C, Pascal, Python (quando usadas de forma imperativa).

```pascal
program Fibonacci;

function fib(n: Integer): Integer;
var a: Integer = 1;
    b: Integer = 1;
    f: Integer;
    i: Integer;
begin
  if (n = 1) or (n = 2) then
    fib := 1
  else
  begin
    for i := 3 to n do
    begin
      f := a + b;
      b := a;
      a := f;
    end;
    fib := f;
  end;
end;

begin
  WriteLn(fib(10));
end.
```

#### Funcional

Trata a computação como a avaliação de funções matemáticas e evita estados e dados mutáveis.
Exemplo de linguagens: Haskell, Lisp.

```haskell
import Text.Printf

fib :: Int -> Int
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib (n-2)

main = printf "%d\n" (fib 10)
```

#### Lógico

Estilo de programação baseado em lógica formal, onde os programas consistem em fatos e regras que descrevem relações entre dados.
Exemplo de linguagens: Prolog, Datalog, Mercury.

```prolog
fib(1, 1).
fib(2, 1).

fib(X, Y) :-
    X > 1,
    X1 is X - 1,
    X2 is X - 2,
    fib(X1, Z),
    fib(X2, W),
    Y is W + Z.

main :-
    fib(10, X), write(X), nl.
```

#### Orientado a Objetos

Organiza o código em "objetos", que são instâncias de classes, combinando dados e comportamentos.
Exemplo de linguagens: Java, C++, Python (quando usada de forma orientada a objetos).

```java
public class Fibonacci {

    public long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        System.out.println(f.fibonacci(10));
    }
}
```

---

### 2. O paradigma orientado a objetos

- Surgiu na década de 60.
- As primeiras linguagens comerciais surgiram na década de 90.
- É uma visão contemporânea que utiliza a perspectiva de objetos.
- Capaz de ser usado em qualquer tipo de sistema (qualquer tamanho, complexidade ou área de negócio).

**Principais objetivos:**

- Melhorar a compreensão do sistema.
- Alto grau de reutilização (componentes acopláveis).
- Facilidade de manutenção.
- Facilidade de evolução.
- Maior qualidade.
- Maior produtividade e menor custo.

**Em contrapartida:**

- Maior curva de aprendizagem.
- Programas maiores.
- Não recomendável para qualquer tipo de problema.

> **Nota:** recursos mais sofisticados da POO, como herança e polimorfismo, podem ser desafiadores de compreender no início.

---

### 3. Conceitos básicos de POO

#### Analogia

A ideia central pode ser entendida por analogia: uma **classe** funciona como um molde (modelo) a partir do qual vários **objetos** são produzidos. O molde define a forma; cada item produzido a partir dele é um objeto distinto, com seus próprios valores.

#### Classes e objetos

Em programação orientada a objetos:

- Um **objeto** geralmente representa um elemento do mundo real. Todo objeto pertence a uma classe.
- Uma **classe** descreve as características comuns dos seus objetos.

#### Problema a ser resolvido

Calcular o IMC (índice de massa corpórea) de Marta:

| Característica | Valor |
|---|---|
| Nome | Marta da Silva |
| Idade | 21 anos |
| Altura | 1,71 m |
| Peso | 56 kg |
| Cor preferida | verde |
| Signo | aquário |
| Naturalidade | Blumenau |

Existem muitas formas de caracterizar Marta, mas para **resolver este problema** as características úteis são apenas:

- Altura: 1,71 m
- Peso: 56 kg

O paradigma orientado a objetos leva a modelar somente o que é relevante ao problema (abstração).

#### Objetos: atributos e estado

- Objetos são caracterizados por um conjunto de **atributos**. No exemplo, o objeto que representa Marta é caracterizado por altura e peso.
- Dizemos que os objetos possuem um **estado**. O estado corresponde ao valor de seus atributos.
- O valor de um atributo é um dado.
- O estado do objeto **pode mudar**.

```
Estado do objeto            Novo estado do objeto
Altura: 1,71 m       -->    Altura: 1,71 m
Peso:   56 kg               Peso:   56,5 kg
```

#### Objetos: operações e comportamento

> "No desenvolvimento de software orientado a objetos, primeiro damos foco às estruturas de dados" (BAKER, 2005).

- Os dados estão contidos dentro do objeto e pertencem apenas àquele objeto.
- Além de dados, os objetos são capazes de executar **operações**.
- As operações podem executar alguma ação com os dados do próprio objeto.
- Exemplo: o objeto que representa Marta é capaz de calcular o IMC da Marta.

#### Classe

Todo objeto que se quer criar pertence a uma classe de objetos. Através da classe definimos:

- Quais **atributos** os objetos podem possuir.
- Quais **operações** os objetos podem realizar.

Toda classe possui um **nome**.

#### Exemplo: classe Cachorro

**Classe `Cachorro`**

- Conjunto de atributos: Nome, Raça, Cor, Peso.
- Conjunto de operações: Latir, Abanar o rabo, Pegar coisas.

A partir dessa classe podemos criar vários objetos (instâncias):

| | Objeto 1 | Objeto 2 |
|---|---|---|
| Nome | pingo | brutus |
| Raça | labrador | pastor alemão |
| Cor | branco | marrom |
| Peso | 12 kg | 21 kg |

Ambos compartilham a mesma estrutura (definida pela classe), mas possuem estados diferentes (valores de atributos próprios).

---

### 4. Resumindo

Conforme Barker (2005):

- Um **objeto** é uma construção de software que empacota estado (dados) e comportamento (funções) e representa uma abstração do mundo real.
- Uma **classe** é uma abstração que descreve as características comuns de todos os objetos de um grupo de objetos comuns.
- Uma classe pode ser vista como um **modelo (molde) para criar objetos**.

---

<a id="aula-2"></a>

## Aula 2 - Escopo de Variáveis

### 1. O que é escopo

O escopo de uma variável denota sua visibilidade no programa, isto é, onde a variável é acessível. Fora desse contexto, o identificador não pode ser utilizado (está fora do escopo da variável).

Em Java, o escopo de uma variável depende de onde ela é declarada. A classe abaixo serve de referência para os três tipos de escopo:

```java
public class MinhaClasse {

    int var1;                          // variável de instância

    void metodoA() {
        var1 = 20;
    }

    void metodoB() {
        String var2;                   // variável local
        var2 = "TESTE";

        do {
            int var3 = 10;             // variável de bloco
        } while (false);

        System.out.println(var2);
    }
}
```

| Variável | Onde é declarada | Tipo de escopo |
|---|---|---|
| `var1` | no corpo da classe, fora dos métodos | de instância |
| `var2` | dentro do corpo de um método | local |
| `var3` | dentro de um bloco (`do { ... }`) | de bloco |

---

### 2. Variável de instância

- É declarada no corpo da classe, fora de qualquer método.
- O escopo da variável de instância são **todos os métodos da classe**.
- No exemplo, `var1` pode ser lida e alterada tanto por `metodoA` quanto por `metodoB`.

> Cada objeto possui sua própria cópia das variáveis de instância; elas existem enquanto o objeto existir.

---

### 3. Variável local

- É uma variável criada dentro do corpo de um método.
- O escopo da variável local é o **próprio método** em que a variável foi declarada.
- No exemplo, `var2` só existe dentro de `metodoB`.

> Os parâmetros de um método também têm escopo local: valem apenas dentro do método que os declara.

---

### 4. Variável de bloco

- É uma variável criada dentro de um bloco (delimitado por `{ }`) dentro de um método, por exemplo em um `do/while`, `for` ou `if`.
- O escopo da variável de bloco é o **bloco onde a variável foi declarada**.
- No exemplo, `var3` só existe dentro do bloco do `do/while`; após o `}` do bloco ela deixa de existir.

---

### 5. Inicialização de variáveis

As variáveis declaradas num método ou num bloco (locais e de bloco) **não possuem valor inicial**. Só é possível ler o valor depois de atribuir explicitamente um valor. Tentar ler antes gera erro de compilação.

Já as variáveis de instância têm **valor padrão**: o Java as inicializa automaticamente.

| Tipo da variável de instância | Valor padrão |
|---|---|
| Numérica inteira (`byte`, `short`, `int`, `long`) | `0` |
| Numérica de ponto flutuante (`float`, `double`) | `0.0` |
| `char` | `'\u0000'` (caractere nulo) |
| `boolean` | `false` |
| Referência (objetos, `String`, arrays) | `null` |

> Resumo prático: variáveis numéricas iniciam em `0`, booleanas em `false` e variáveis de referência em `null`.

---

### 6. Mesmo nome em escopos diferentes

É possível declarar duas variáveis com o mesmo nome, desde que estejam em escopos diferentes.

```java
public static void main(String[] args) {

    {
        int x = 0;
        System.out.println(x);
    }

    {
        String x = "10";
        System.out.println(x);
    }

}
```

Cada `x` só é válido dentro do seu próprio bloco; são variáveis independentes.

---

### 7. Variável local com o mesmo nome de uma de instância (sombreamento)

Também é possível usar, num mesmo contexto, o mesmo identificador para uma variável local e uma de instância. Quando isso acontece, a linguagem dá preferência à variável **local** (mais interna). Esse efeito é chamado de sombreamento (variable shadowing): a variável local "esconde" a de instância.

Para acessar explicitamente a variável de instância, usa-se `this`:

```java
public class Classe1 {

    int var1 = 20;                     // variável de instância

    void exibir() {
        int var1 = 5;                  // variável local (sombreia a de instância)

        this.var1 = 10;                // altera a variável de instância

        System.out.println(var1);       // imprime 5  (local)
        System.out.println(this.var1);  // imprime 10 (instância)
    }
}
```

- `var1` (sem qualificador) refere-se à variável **local**.
- `this.var1` refere-se à variável **de instância**.

> Boa prática: evite sombreamento desnecessário, pois ele dificulta a leitura. A exceção comum e aceita é o padrão `this.campo = campo;` em construtores e métodos `set`, onde o parâmetro tem o mesmo nome do atributo.

---

### 8. Boas práticas de escopo

- Declare cada variável no **menor escopo possível** e o mais próximo de onde ela é usada.
- Prefira variáveis locais a variáveis de instância quando o dado não precisa persistir no objeto.
- Não confie no valor padrão de variáveis de instância como se fosse um valor "de negócio": inicialize explicitamente quando o valor importar.

---

<a id="aula-3"></a>

## Aula 3 - Diagrama de Objetos, Encapsulamento e Membros de Classe

### 1. Diagrama de Objetos

#### 1. O que é um diagrama de objetos

- É um diagrama que mostra uma fotografia do estado detalhado de um sistema, num determinado instante do tempo.
- É um diagrama da UML que contém somente objetos (não contém classes).
- Fornece uma perspectiva concreta de objetos e seus relacionamentos.
- Tem uso limitado, pois apresenta somente estruturas de dados (o estado), sem representar o comportamento.

---

#### 2. Notação (elementos do diagrama)

Cada objeto é representado por um retângulo com dois compartimentos: no topo, a identificação `nomeObjeto : Classe` (com o nome sublinhado, indicando que é uma instância); abaixo, os valores dos atributos.

```text
+-----------------------+
| nomeObjeto : Classe   |   (nome sublinhado)
+-----------------------+
| atributo1 = valor     |
| atributo2 = valor     |
| ...                   |
+-----------------------+
```

> O nome do objeto é sublinhado (é o que distingue um objeto de uma classe no diagrama). É possível representar um objeto anônimo, omitindo o nome: `: Classe`.

---

#### 3. Diagrama de classes x diagrama de objetos

Os dois diagramas são complementares. O de classes descreve a estrutura (os tipos); o de objetos mostra instâncias concretas num instante.

Tomando a classe `Pessoa` como exemplo:

Diagrama de classes:

```text
+---------------------------+
|          Pessoa           |
+---------------------------+
| peso : double             |
| altura : double           |
+---------------------------+
| calcularImc() : double    |
+---------------------------+
```

Diagrama de objetos (um objeto):

```text
+---------------------+
| marta : Pessoa      |
+---------------------+
| peso = 78           |
| altura = 1.71       |
+---------------------+
```

A partir de uma mesma classe podem existir vários objetos, cada um com seu próprio estado:

```text
+-----------------+   +-----------------+   +-----------------+
| p1 : Pessoa     |   | p2 : Pessoa     |   | : Pessoa        |
+-----------------+   +-----------------+   +-----------------+
| peso = 78       |   | peso = 68       |   | peso = 84       |
| altura = 1.71   |   | altura = 1.70   |   | altura = 1.79   |
+-----------------+   +-----------------+   +-----------------+
```

O terceiro objeto está anônimo (sem nome antes de `: Pessoa`).

| Aspecto | Diagrama de classes | Diagrama de objetos |
|---|---|---|
| O que mostra | a estrutura (tipos) | instâncias concretas num instante |
| Conteúdo | classes, atributos (com tipo) e operações | objetos e valores de atributos |
| Atributos | `peso : double` | `peso = 78` |
| Quantidade | uma classe | vários objetos da mesma classe |

> Cada objeto do diagrama corresponde, em código, a um `new` já executado: `p1` seria o resultado de `new Pessoa()` com `peso = 78` e `altura = 1.71`.

---

### 2. Encapsulamento

#### 1. Motivação

Considere a classe abaixo, usada para representar contas bancárias, com os atributos sem controle de acesso:

```java
public class ContaBancaria {

    String titular;
    double saldo;

    void depositar(double valor) {
        saldo = saldo + valor;
    }

    void sacar(double valor) {
        saldo = saldo - valor;
    }
}
```

Uma classe cliente consegue usar os métodos normalmente:

```java
public class CaixaEletronico {

    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaBancaria();
        conta1.titular = "Sandro da Silva";
        conta1.depositar(500);
        conta1.sacar(100);
        System.out.println(conta1.saldo);   // mostra 400
    }
}
```

O problema aparece quando a classe cliente acessa o atributo diretamente:

```java
conta1.saldo = 10000;   // define o saldo sem um depósito correspondente
```

Esse comando permite definir um valor de saldo sem que exista um depósito correspondente, violando a integridade dos dados.

---

#### 2. O que é encapsulamento

- O acesso ao atributo deve ser controlado, para garantir a integridade dos dados (o estado do objeto precisa ser controlado).
- Somente o próprio objeto deveria manipular o valor de seus atributos.
- Essa técnica se chama encapsulamento de dados.
- Em Java, para aplicar o encapsulamento é preciso tornar o atributo privado (`private`).

---

#### 3. Modificadores de acesso (visibilidade)

Em UML, um atributo encapsulado é indicado por um sinal de `-` na frente do nome. Outros símbolos indicam o grau de visibilidade dos membros (atributos e operações):

| Símbolo UML | Nome | Palavra reservada | Significado |
|---|---|---|---|
| `-` | Privado | `private` | Somente visível pela própria classe |
| `+` | Público | `public` | Visível para qualquer classe |
| `#` | Protegido | `protected` | Estudaremos mais tarde (herança) |
| `~` | De pacote | (ausência de símbolo) | Estudaremos mais tarde |

A palavra reservada também é conhecida como modificador de acesso. Sintaxe para declarar um atributo em Java:

```text
modificador_de_acesso tipo_de_dado identificador;
```

Exemplo:

```java
private double saldo;
```

---

#### 4. Métodos de acesso (getters e setters)

- Todos os atributos de um objeto deveriam ser encapsulados.
- Os atributos que precisam ser acessados por outras classes podem ser expostos por meio de métodos de acesso, geralmente públicos.
- **Getters**: recuperam o valor de um atributo. O nome usa o prefixo `get` seguido do nome do atributo com a inicial maiúscula. Exceção: se o atributo for lógico (booleano), usa-se o prefixo `is`. O getter nunca tem parâmetro, é do tipo função e retorna um dado do mesmo tipo do atributo.
- **Setters**: atribuem valor a um atributo. O nome usa o prefixo `set` seguido do nome do atributo com a inicial maiúscula. O setter sempre tem um parâmetro, é do tipo procedimento (`void`) e o parâmetro é do mesmo tipo do atributo.

Exemplo completo, já encapsulado e com métodos de acesso para `titular`:

```java
public class ContaBancaria {

    private String titular;
    private double saldo;

    void depositar(double valor) {
        saldo = saldo + valor;
    }

    void sacar(double valor) {
        saldo = saldo - valor;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTitular() {
        return titular;
    }
}
```

> Observação: o setter é o lugar natural para validar o valor antes de alterar o estado do objeto (por exemplo, recusar valores negativos, ou impedir que `sacar` deixe o saldo negativo). É justamente esse controle que a Motivação da Aula pedia. Um setter que só faz a atribuição direta não agrega proteção sobre o dado.

---

#### 5. A palavra-chave this

- `this` refere-se ao objeto corrente, isto é, o objeto no qual o método foi chamado.
- O principal motivo para usar `this` é quando um parâmetro de método possui o mesmo nome de uma variável de instância.
- Quando há dois identificadores com o mesmo nome, por padrão o Java usa o de menor escopo (o parâmetro/variável local). Para acessar explicitamente a variável de instância, usa-se `this.atributo` (ver [Aula 2 - Escopo de Variáveis](#aula-2), sombreamento).

No método `setTitular` acima, o parâmetro e o atributo se chamam `titular`; `this.titular` refere-se ao atributo, e `titular` (sem qualificador) ao parâmetro.

---

#### 6. Boas práticas de encapsulamento

- Sempre dar preferência a encapsular todos os atributos de uma classe.
- Somente é admissível utilizar `public` para constantes.
- Se for necessário expor o valor de um atributo para outros objetos/classes, implementar um método getter.
- Se for necessário permitir que outros objetos/classes definam o valor de um atributo, implementar um método setter (aproveitando para validar).

---

#### 7. Encapsulamento de métodos

- Ao utilizar POO, é possível ocultar a complexidade do trabalho interno executado pelo objeto.
- Isso cria uma forma simplificada e compreensível de utilizar o objeto, o que favorece a reutilização.
- Analogia: o motorista não precisa compreender como o mecanismo interno de combustão funciona para ligar o carro.

---

### 3. Membros de Classe, Sobrecarga e Construtores

#### 1. Membros de classe (estáticos)

- São membros (variáveis ou métodos) que pertencem à classe, e não a nenhuma instância em particular.
- Podem ser utilizados sem que haja uma instância da classe.
- No diagrama de classes, os membros de classe são sublinhados.

##### Variáveis de classe

- Uma variável comum e compartilhada entre todas as instâncias.
- Pode ser manipulada sem que haja uma instância; o acesso usa a sintaxe `Classe.identificador`.
- Também chamadas de variáveis estáticas ou campos de classe.

Sintaxe:

```text
modificador static tipo_de_dado identificador;
```

Exemplo:

```java
private static int atributo1;
```

##### Métodos de classe

- Podem manipular variáveis de classe.
- Não podem manipular variáveis de instância sem uma instância explícita.
- Não podem reusar métodos de instância.
- Não podem utilizar a palavra `this`.

Sintaxe:

```text
modificador static tipo_de_dado identificador(parametros);
```

##### Exemplos de membros estáticos da biblioteca Java

`Integer.MAX_VALUE`, `Math.sqrt()`, `Math.abs()`, `Math.max()`, `JOptionPane.showInputDialog()`, `JOptionPane.showMessageDialog()`.

> Todos são acessados pela classe (`Classe.membro`), sem criar um objeto.

---

#### 2. Sobrecarga de métodos

- A linguagem Java suporta a sobrecarga de métodos, isto é, a implementação de vários métodos com o mesmo nome.
- Os métodos devem ter assinaturas diferentes: podem ter o mesmo nome desde que a lista de parâmetros seja diferente.
- O compilador não considera o tipo de retorno para diferenciar o método. Por isso, dois métodos com a mesma assinatura mas retornos distintos não podem ser implementados na mesma classe.
- Deve ser utilizada com moderação, pois pode tornar o código menos legível.

Exemplo de sobrecarga (mesmo nome, listas de parâmetros diferentes):

```java
int somar(int a, int b) {
    return a + b;
}

double somar(double a, double b) {
    return a + b;
}

int somar(int a, int b, int c) {
    return a + b + c;
}
```

---

#### 3. Construtores

- São similares a métodos, com a exceção de que são invocados exclusivamente durante a criação de objetos.
- São utilizados para inicializar um objeto.
- A declaração é semelhante à de um método, porém não possui tipo de dado de retorno e seu identificador é igual ao nome da classe.
- Não é obrigatório criar um construtor. Quando nenhum é implementado, o compilador fornece automaticamente um construtor padrão (um construtor sem argumentos).

Exemplo:

```java
public class ContaBancaria {

    private String titular;
    private double saldo;

    public ContaBancaria(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }
}
```

> Observação importante: a partir do momento em que se declara qualquer construtor (como o `ContaBancaria(String)` acima), o compilador deixa de fornecer o construtor padrão sem argumentos. Se ainda for necessário criar objetos sem argumentos, é preciso declarar também um construtor sem parâmetros.

##### O operador new

O operador `new` realiza quatro operações:

1. Cria o objeto na memória, alocando espaço para armazenar os valores de suas variáveis de instância.
2. Inicializa as variáveis de instância (com os valores padrão).
3. Executa o construtor que foi utilizado no operador `new`.
4. Retorna o endereço de memória do objeto criado.

---

<a id="aula-6"></a>

## Aula 6 - Lançamento de Exceções

### 1. O que é uma exceção

- Uma exceção é um evento que ocorre durante a execução do programa e que interrompe o fluxo normal de execução.
- Quando uma operação incorreta é identificada dentro de um método, o método pode criar um objeto de uma classe que caracteriza o erro e notificá-lo ao sistema.
- Esse objeto é denominado objeto de exceção e contém informação sobre o erro.
- Essa operação (criar o objeto e notificar o sistema) é conhecida como lançamento de exceção.
- O efeito de uma exceção lançada é, por enquanto, abortar a execução do programa.

> Ainda nesta unidade veremos como capturar e tratar exceções (com `try`/`catch`) para que o programa não seja abortado. Nesta aula tratamos apenas do lançamento.

---

### 2. Lançamento de exceções em Java

A instrução para lançar uma exceção é `throw`, seguida da criação de um objeto de exceção. Sintaxe:

```text
throw new TipoDaExcecao("mensagem");
```

Onde a mensagem indica um texto que pode ser apresentado quando a exceção for gerada. Exemplo:

```java
throw new IllegalArgumentException("mensagem");
```

`IllegalArgumentException` é uma exceção pronta da biblioteca Java, usada para indicar que um argumento recebido é inválido.

---

### 3. Exemplo: validando um valor antes de alterar o estado

O uso mais comum nesta fase é validar um valor dentro de um setter (ou construtor) antes de gravar o dado no objeto. Se o valor for inválido, o método lança a exceção e não altera o estado:

```java
public void setSalario(double novoSalario) {
    if (novoSalario < 0) {
        throw new IllegalArgumentException("Salário incorreto");
    }
    salario = novoSalario;
}
```

Esse é exatamente o ponto que ficou pendente na [Aula 3 - Encapsulamento](#aula-3): o setter é o lugar de validar o dado, e o `throw` é o mecanismo que impede a atribuição de um valor inválido. Sem a validação, o atributo aceitaria qualquer valor; com ela, o objeto protege o próprio estado.

---

### 4. Código após o `throw`

Quando uma instrução `throw` é sempre executada, não pode existir nenhum comando depois dela no mesmo bloco, pois esse comando seria inalcançável (o compilador acusa erro de código inalcançável).

Atenção para não confundir com o exemplo acima: lá o `throw` está dentro de um `if` (execução condicional), então ele só ocorre quando o valor é inválido. Por isso a linha `salario = novoSalario;` depois do `if` é válida: ela é executada no caso normal, em que a exceção não foi lançada.

```java
// throw incondicional: a linha seguinte é inalcançável (erro de compilação)
public void metodo() {
    throw new IllegalArgumentException("sempre lança");
    // salario = 10;   // NÃO compila: código inalcançável
}
```

---

<a id="aula-7"></a>

## Aula 7 - Tratamento de Exceções

### 1. Tipos de erros

Durante a execução de um programa, podemos nos deparar com dois tipos de erros:

| Tipo de erro | O que é | Pode ser evitado? |
|---|---|---|
| Erro de lógica | Erro de concepção do algoritmo | Sim, deve ser evitado |
| Erro de execução | Operação sem suporte pelo ambiente durante a execução | Muitas vezes não |

#### Erros de lógica

São erros na concepção do algoritmo. O programa compila e executa, mas produz um resultado errado ou acessa algo que não deveria.

```java
double[] nota = new double[3];
nota[0] = 10;
nota[1] = 7;
nota[2] = 8;

double somaNotas = 0;
for (int i = 0; i <= nota.length; i++) {   // erro de lógica: i <= length
    somaNotas += nota[i];
}

System.out.println("A média é: " + (somaNotas / nota.length));
```

O erro está na condição `i <= nota.length`. Como os índices válidos vão de `0` a `nota.length - 1`, o laço tenta acessar `nota[3]`, que não existe. Os erros de lógica devem ser evitados, pois nascem de um raciocínio incorreto do programador.

#### Erros de execução

São erros causados por operações que não possuem suporte pelo ambiente no momento da execução. Exemplos:

- Entrada de dados inadequada.
- Manipulação indevida de arquivos.
- Operação aritmética ilegal.
- Comando não atendido pelo periférico.
- Falta de memória.

Os erros de execução correspondem a condições excepcionais ou anormais, frequentemente chamadas de **exceções**. Muitas vezes não podem ser evitados (dependem do usuário, do ambiente, de recursos externos). Diante deles, a pergunta é: como o programa deve se comportar?

> Analogia: se o usuário digita uma URL incorreta no navegador, o esperado não é o navegador travar, e sim tratar a situação (avisar o usuário, pedir outra URL). O mesmo vale para os nossos programas.

---

### 2. Erros em Java e a hierarquia de classes

Quando um erro de execução ocorre, a JVM o detecta, cria um objeto que caracteriza o erro e notifica o programa que o causou. A partir daí, o programa pode tratar o erro, inclusive acessando o objeto criado pela JVM.

Java representa erros com classes. As duas principais, ambas subclasses de `Throwable`, são `Error` e `Exception`:

```text
                       Throwable
                      /         \
                  Error          Exception
                 /     \         /        \
    VirtualMachineError  \   RuntimeException   IOException
      |  |  |          LinkageError    |  |  |
      |  |  |                          |  |  |
 InternalError                 ArithmeticException
 OutOfMemoryError              IndexOutOfBoundsException
 StackOverflowError            IllegalArgumentException
                                    |
                               NumberFormatException
                               NullPointerException
```

Diferença fundamental entre os dois ramos:

| Ramo | Significado | Espera-se tratamento? |
|---|---|---|
| `Error` e subclasses | Erros graves (falha da JVM, falta de memória, estouro de pilha) | Não. Não se espera que o programa os trate |
| `Exception` e subclasses | Condições que o programa poderia contornar | Sim. Podem e devem ser tratadas quando fizer sentido |

Além dos objetos criados pela JVM, o próprio programador pode criar explicitamente objetos para sinalizar situações de erro (como vimos no `throw`). Também é possível criar novas classes de erro, desde que sejam subclasses de `Throwable` ou de alguma de suas subclasses.

---

### 3. Como tratar exceções: o comando try..catch

O tratamento de exceções é feito com o comando `try`. A sintaxe básica é:

```java
try {
    comando;
    comando;
    comando;
    // ...
} catch (ClasseExcecao objeto) {
    comando caso ocorra erro;
    comando caso ocorra erro;
    // ...
}
```

Como funciona:

- No início do `try`, o ambiente passa a monitorar a execução de todos os comandos declarados no seu corpo.
- Se nenhum erro ocorrer, o `try` termina normalmente e o bloco `catch` não é executado.
- Se ocorrer um erro em algum comando do bloco `try`, o fluxo é desviado para o bloco `catch`.
- O `catch` exige um parâmetro: o objeto da exceção. Pode-se declarar qualquer classe que estenda `Throwable`.

Exemplo: converter um texto digitado em número inteiro, capturando entrada inválida.

```java
Scanner teclado = new Scanner(System.in);
int idade;
try {
    idade = Integer.parseInt(teclado.nextLine());
} catch (NumberFormatException objErro) {
    System.out.println("Valor incorreto.");
}
System.out.println("Fim");
```

Dizemos que esse fragmento é capaz de capturar o erro `NumberFormatException`. Se o usuário digitar algo que não é um número, em vez de abortar o programa, cai no `catch` e a execução segue no `println("Fim")`.

Combinando com um laço, dá para insistir até o usuário digitar um valor válido:

```java
Scanner teclado = new Scanner(System.in);
int num;

System.out.println("Digite um número: ");
while (true) {
    try {
        num = Integer.parseInt(teclado.nextLine());
        System.out.println("O número informado é: " + num);
        break;                          // sai do laço quando a conversão dá certo
    } catch (NumberFormatException objErro) {
        System.out.println("Valor incorreto. Por favor, digite novamente");
    }
}
```

> Quando a conversão funciona, o `break` encerra o laço. Quando falha, o `catch` avisa o usuário e o `while` repete a leitura.

---

### 4. Múltiplos catch

Um mesmo comando `try` aceita várias cláusulas `catch`, cada uma tratando uma classe de erro diferente.

```java
Scanner teclado = new Scanner(System.in);

try {
    int a[] = new int[2];
    a[4] = 30 / Integer.parseInt(teclado.nextLine());
    System.out.println("Operação concluída com êxito");
} catch (NumberFormatException e) {
    System.out.println("Valor digitado é inválido");
} catch (ArithmeticException e) {
    System.out.println("Falha na divisão");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Não conseguiu atribuir ao vetor");
} catch (Exception e) {
    System.out.println("Qualquer outra exceção");
}
System.out.println("Fora do bloco");
```

Como o mecanismo escolhe qual `catch` executar:

1. Ao ocorrer um erro em um comando do bloco `try`, é criado um objeto da classe que caracteriza o erro e o programa é notificado.
2. O comando que causou o erro é interrompido.
3. As cláusulas `catch` são verificadas na ordem em que foram escritas, até encontrar uma que possa tratar aquele erro.
4. Quando uma cláusula compatível é encontrada, o fluxo é desviado para o primeiro comando dela. Ao terminar, o `try` finaliza normalmente e o fluxo prossegue após o comando `try`.

> Ordem importa. Cláusulas mais específicas devem vir antes das mais genéricas. Como `Exception` é superclasse das demais, ela fica por último, funcionando como um "pega tudo".

#### Mesmo tratamento para várias classes (multi-catch, Java 7+)

A partir do Java 7, uma única cláusula `catch` pode tratar várias classes de erro, separadas por `|`, quando o tratamento for o mesmo.

```java
try {
    // ...
} catch (IOException | SQLException ex) {
    // ...
}
```

---

### 5. Propagação de erros

Quando um método é interrompido por um erro, o controle retorna ao método que o chamou, que poderá tratar o erro.

```java
public static int obterValor() {
    Scanner teclado = new Scanner(System.in);
    int valor = Integer.parseInt(teclado.nextLine());
    return valor;
}

public static void teste1() {
    while (true) {
        try {
            int num = obterValor();
            System.out.println("Numero informado = " + num);
            break;
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Informe novamente");
        }
    }

    System.out.println("Fim");
}
```

Repare que o erro pode acontecer dentro de `obterValor()`, mas quem o trata é `teste1()`, o método chamador. Regras da propagação:

- Se o método chamador também não puder tratar o erro, ele também é interrompido.
- A propagação continua subindo pela cadeia de chamadas até que algum método consiga tratar o erro.
- O último método que tem a oportunidade de tratar o erro é o `main()`.
- Se nem o `main()` tratar o erro, o programa é interrompido.

---

### 6. Exceções verificadas pelo compilador (checked)

Alguns métodos exigem que o compilador force o programador a tratar a exceção no local onde ela pode ocorrer, ou a postergar explicitamente esse tratamento. Essas são as exceções **verificáveis** (checked).

Regra de quais são verificáveis:

- `Error` e `RuntimeException`, bem como todas as suas subclasses, **não** são verificáveis.
- Todas as demais classes de exceção **são** verificáveis.

O código abaixo não compila, porque `FileReader` pode lançar `FileNotFoundException`, que é verificável e precisa ser tratada:

```java
public void abrirArquivo() {
    arquivo = new FileReader("arquivo.txt");   // erro de compilação
}
```

Uma forma de resolver é tratar a exceção ali mesmo, com `try..catch`:

```java
public void abrirArquivo() {
    try {
        arquivo = new FileReader("arquivo.txt");
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado");
    }
}
```

---

### 7. Cláusula throws

A cláusula `throws` é usada na assinatura de um método para informar que o método **chamador** deverá tratar a possibilidade daquelas exceções ocorrerem. É utilizada com classes de erros verificáveis: em vez de tratar a exceção, o método delega esse tratamento para quem o chamou.

```java
public void abrirArquivo() throws FileNotFoundException {
    arquivo = new FileReader("arquivo.txt");
}
```

É possível informar várias exceções na cláusula `throws`, bastando separá-las por vírgula.

> Resumo prático da escolha: ou você trata a exceção verificável aqui (`try..catch`), ou declara `throws` e repassa a responsabilidade para o chamador. Uma das duas é obrigatória para o código compilar.

---

### 8. Cláusula finally

A cláusula `finally` está associada ao comando `try` e serve para definir um conjunto de comandos que **sempre** serão executados, quer o `try` termine com erro, quer termine sem erro.

```java
try {
    comando;
    comando;
    // ...
} catch (ClasseExcecao objeto) {
    comando caso ocorra erro;
    comando caso ocorra erro;
    // ...
} finally {
    comando;
    comando;
}
```

O uso da cláusula `finally` geralmente está associado à liberação de recursos alocados durante o bloco `try` (fechar um arquivo, uma conexão, etc.), garantindo que essa liberação aconteça mesmo que ocorra uma exceção.

---

### 9. Observações finais

- Todo comando `try` define dois ou mais blocos, referentes ao seu corpo e às suas cláusulas `catch`. Esses blocos definem **escopo de variáveis**, então uma variável declarada dentro do `try` não é visível no `catch` nem fora dele. Ver [Aula 2 - Escopo de Variáveis](#aula-2).
- Se um método de uma superclasse declara exceções verificáveis, ao sobrescrever esse método é obrigatório redeclarar as mesmas exceções (ou um subconjunto compatível delas).

> Boas práticas: trate apenas as exceções que você realmente sabe como resolver; ordene os `catch` do mais específico para o mais genérico; use `finally` (ou try-with-resources) para não deixar recursos abertos; e evite capturar `Exception` genérica só para silenciar o problema.