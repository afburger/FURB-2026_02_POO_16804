# Unidade 04 - Herança

> Material de apoio da disciplina de Programação Orientada a Objetos (POO).
> Cada aula é registrada em uma seção própria abaixo, com uma âncora referenciada no sumário.

## Sumário

- [Aula 15 - Herança (parte 1)](#aula-15)
- [Aula 17 - Herança (parte 2)](#aula-17)

<!--
Padrão para as próximas aulas:
1. Adicione o link no sumário acima, seguindo o mesmo formato:
   - [Aula N - Título](#aula-n)
2. Crie a seção da aula com a âncora correspondente:
   <a id="aula-n"></a>
   ## Aula N - Título
-->

---

<a id="aula-15"></a>

## Aula 15 - Herança (parte 1)

### 1. O que é herança

A herança é uma forma de reuso de software em que uma nova classe é criada absorvendo os membros (atributos e métodos) de uma classe já existente.

- Ao criar uma classe, em vez de declarar membros completamente novos, é possível definir que a nova classe herde os membros de uma classe já existente.
- A classe já existente é chamada de superclasse, classe pai ou classe base.
- A nova classe é chamada de subclasse, classe filha ou classe derivada.
- A subclasse pode adicionar seus próprios membros.
- A subclasse mantém os comportamentos da superclasse, mas também pode adicionar comportamento específico.

---

### 2. Representação em UML e especialização

- A herança é uma forma de reuso.
- A subclasse é mais específica que a superclasse, representando um grupo de objetos mais especializado.
- Por isso, a herança também é conhecida como especialização.

Em UML, a herança é representada por uma linha da subclasse até a superclasse, terminada por uma seta com triângulo vazado apontando para a superclasse:

```text
        +----------------+
        |   Superclasse  |
        +----------------+
                 △
                 |
        +----------------+
        |   Subclasse    |
        +----------------+
```

---

### 3. Exemplo: a palavra-chave extends

Superclasse `Calculadora`:

```java
public class Calculadora {

    private double memoria;

    public double getMemoria() {
        return memoria;
    }

    public void setMemoria(double mem) {
        this.memoria = mem;
    }

    public double somar(double op1, double op2) {
        return op1 + op2;
    }

    public double subtrair(double op1, double op2) {
        return op1 - op2;
    }

    public double multiplicar(double op1, double op2) {
        return op1 * op2;
    }

    public double dividir(double op1, double op2) {
        return op1 / op2;
    }

}
```

A subclasse `CalculadoraCientifica` é uma versão especializada de `Calculadora`. Dizemos que `CalculadoraCientifica` estende (ou herda) a classe `Calculadora`, usando a palavra-chave `extends`:

```java
public class CalculadoraCientifica extends Calculadora {

    public double raizQuadrada(double op) {
        // ...
    }

    public double exponenciacao(int base, int expoente) {
        // ...
    }

    public long fatorial(int op) {
        // ...
    }

}
```

No programa principal, um objeto de `CalculadoraCientifica` usa tanto os métodos próprios quanto os herdados de `Calculadora`:

```java
public static void main(String[] args) {
    CalculadoraCientifica cs = new CalculadoraCientifica();
    System.out.println(cs.somar(12, 13));   // método herdado de Calculadora
    System.out.println(cs.fatorial(5));     // método próprio de CalculadoraCientifica
}
```

---

### 4. Herança é um relacionamento "é um(a)"

- A herança é um relacionamento entre classes.
- Lemos o relacionamento com a expressão "é um(a)".
- Exemplo: `CalculadoraCientifica` é uma `Calculadora`.

> Esse é o teste prático para decidir se deve haver herança: se "A é um(a) B" faz sentido, A pode ser subclasse de B. Se a relação for melhor descrita por "A tem um(a) B", trata-se de associação (Unidade 03), não de herança.

---

### 5. Hierarquia de herança

- É possível especializar tanto classes construídas pelo próprio programador quanto classes de terceiros, incluindo as classes da própria linguagem Java.
- Cada subclasse pode ser, por sua vez, superclasse de futuras subclasses.
- A superclasse direta é a herdada diretamente por uma subclasse; a superclasse indireta é a herdada indiretamente (mais acima na hierarquia).
- As relações de herança formam estruturas hierárquicas parecidas com uma árvore, chamadas de hierarquia de herança.
- Java é uma linguagem de herança simples: uma classe tem no máximo uma superclasse direta. Isso a diferencia de linguagens de herança múltipla, que permitem várias superclasses diretas para a mesma classe.

---

### 6. A classe Object

Em Java, todas as classes herdam da classe `Object` (direta ou indiretamente), que está no pacote `java.lang`. Por isso, todo objeto já nasce com alguns métodos herdados de `Object`:

| Método | Descrição |
|---|---|
| `equals(Object)` | Compara o objeto atual com o objeto recebido como parâmetro, para conferir se são iguais |
| `toString()` | Retorna uma representação textual do objeto |

---

### 7. Herança e membros privados

Uma subclasse não pode acessar membros privados de sua superclasse.

```java
public class Classe1 {

    private int valor;

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

}
```

```java
public class Classe2 extends Classe1 {

    private void metodo1() {
        valor = 10;   // erro de compilação: valor é privado em Classe1
    }

}
```

Porém, a subclasse pode alterar o valor de variáveis privadas da superclasse por meio de métodos não privados da superclasse:

```java
public class Classe2 extends Classe1 {

    private void metodo1() {
        setValor(10);   // ok: usa o setter público da superclasse
    }

}
```

Quando a variável é privada e o setter não é público (ou não existe setter), para acessá-la na subclasse recomenda-se:

- Manter privada a variável de instância.
- Tornar (ou criar) o setter com o modificador de acesso `protected`.

O modificador `protected` torna o membro acessível pela própria classe, por classes do mesmo pacote e pelas subclasses. Em UML, o membro protegido usa o símbolo `#`.

> Este é o `#` que ficou como "estudaremos mais tarde" na tabela de visibilidade da Unidade 01 (Encapsulamento). `protected` fica entre `private` e `public`: abre o acesso para as subclasses sem tornar o membro público para todo o sistema.

---

### 8. Sobrescrita de método

Na subclasse, além de incluir novos atributos e métodos, é possível redefinir o comportamento de um método herdado. Essa funcionalidade é chamada de sobrescrita de método (override).

Exemplo: uma `ContaEspecial` (subclasse de `ContaBancaria`) precisa sobrescrever `sacar` para considerar o limite de crédito. Na superclasse:

```java
public void sacar(double valor) {
    if (valor > saldo) {
        throw new IllegalArgumentException("Sem saldo");
    }
    saldo -= valor;
}
```

Na subclasse, a nova versão considera o limite. Como a subclasse precisa alterar o `saldo` (privado na superclasse), é preciso que a superclasse ofereça um `setSaldo` protegido (`protected`), acessível pela subclasse:

```java
@Override
public void sacar(double valor) {
    if (valor > getSaldo() + getLimiteCredito()) {
        throw new IllegalArgumentException("Sem saldo");
    }
    setSaldo(getSaldo() - valor);
}
```

**A anotação @Override.** Ao tentar sobrescrever um método, é fácil errar o nome ou os parâmetros e, em vez de sobrescrever, acabar criando um novo método sobrecarregado (a classe passaria a ter duas versões do método). Para evitar isso, recomenda-se usar a anotação `@Override` imediatamente antes do método: o compilador confere se realmente existe um método com aquela assinatura na superclasse e acusa erro caso não exista.

> Cuidado com a diferença: sobrescrita (override) redefine um método herdado mantendo a mesma assinatura; sobrecarga (overload, visto na Unidade 01) cria outro método com o mesmo nome e lista de parâmetros diferente. Um erro de digitação na assinatura transforma silenciosamente uma sobrescrita pretendida em uma sobrecarga; é isso que o `@Override` protege.

**A palavra-chave super.** É frequente o método sobrescrito querer reutilizar o método da superclasse para executar parte do trabalho. Para isso, usa-se `super` para referenciar o método da superclasse:

```java
@Override
public void calcularTotal() {
    setDesconto(10);
    setIcms(7);
    super.calcularTotal();
}
```

---

### 9. Herança e construtores

Em Java, os construtores da superclasse não são herdados pelas subclasses. Ainda assim, ao criar um objeto, todos os construtores devem chamar algum construtor da superclasse imediata.

Para chamar o construtor da superclasse usa-se `super(...)`, que deve ser o primeiro comando do construtor:

```java
public class Classe2 extends Classe1 {

    public Classe2() {
        super(30);
        System.out.println("Classe2");
    }

}
```

**Execução em cascata.** A chamada obrigatória de um construtor da superclasse leva à execução em cascata dos construtores de toda a hierarquia, da classe de mais alto nível até a classe que está sendo instanciada:

```java
public class Classe1 {
    public Classe1(int valor) {
        System.out.println(valor);
    }
}

public class Classe2 extends Classe1 {
    public Classe2() {
        super(30);
        System.out.println("Classe2");
    }
}

public class Classe3 extends Classe2 {
    public Classe3() {
        super();
        System.out.println("Classe3");
    }
}
```

Ao executar `new Classe3();`, a saída é:

```text
30
Classe2
Classe3
```

**Construtor padrão e herança.** Se nenhum construtor é declarado, o Java introduz o construtor padrão, que faz uma chamada ao construtor padrão da superclasse (`super()`). Porém, se a superclasse não tem construtor padrão (só tem construtor com parâmetros) e a subclasse não declara nenhum construtor, ocorre erro de compilação, pois o construtor padrão gerado automaticamente não conseguiria chamar `super()`. Nesse caso, o programador deve criar explicitamente um construtor na subclasse que chame o construtor existente da superclasse.

> Isso se conecta ao que vimos na Unidade 01 (Construtores): declarar um construtor com parâmetros faz o Java deixar de fornecer o construtor padrão. Na herança, esse detalhe deixa de ser só uma conveniência e passa a poder quebrar a compilação da subclasse.

---

<a id="aula-17"></a>

## Aula 17 - Herança (parte 2)

### 1. Generalização

A generalização é o processo inverso da especialização: em vez de partir de uma classe e criar subclasses mais específicas, partimos de várias classes parecidas e criamos uma superclasse. É um processo de encontrar e criar superclasses:

- Num conjunto de classes relacionadas, localizar os membros (atributos e métodos) comuns entre elas.
- Mover os membros comuns para uma classe, tornando-a superclasse das classes originais.
- Os métodos cuja implementação é diferente entre as classes permanecem em cada classe original.

O processo pode ser aplicado mais de uma vez, sobre um conjunto menor de classes. Se, após generalizar, uma classe não possuir mais nenhum membro próprio, ela pode ser promovida a superclasse das demais.

> Em muitas ocasiões, a superclasse que surge da generalização não tem um significado no mundo real: ela representa um conceito abstrato. Isso é um efeito natural do processo e motiva o próximo conceito, a classe abstrata.

---

### 2. Classe abstrata

Uma classe abstrata representa um conceito genérico. Em UML, é expressa com o nome da classe em itálico. Em Java, usa-se o modificador `abstract`:

```java
public abstract class ContaBancaria {

    // ...

}
```

Características:

- Por ser uma abstração, não é possível criar objetos de classes abstratas.
- Classes abstratas precisam ser estendidas para serem reusadas.
- Classes que não são abstratas são chamadas de classes concretas, e somente classes concretas podem ser instanciadas.

```java
public abstract class Classe1 {

    // ...

}

Classe1 c1 = new Classe1();   // erro de compilação: não pode instanciar Classe1
```

> Uma classe abstrata pode, inclusive, ser especializada a partir de uma classe concreta: a hierarquia pode combinar classes concretas e abstratas em diferentes níveis.

---

### 3. Métodos abstratos

**Motivação.** Imagine uma superclasse `Figura` com o método `desenhar()` de corpo vazio, e subclasses como `Pentagono` que herdam esse corpo vazio. Nada obriga a subclasse a de fato implementar `desenhar()`, e um `desenhar()` vazio não tem sentido para uma figura concreta. Como exigir que cada subclasse forneça sua própria implementação?

```java
public class Figura {

    private Color cor;

    public void desenhar() {

    }

}

public class Pentagono extends Figura {

}
```

**Solução.** Definindo o método como abstrato, instruímos o compilador a exigir que as subclasses implementem um método com aquela assinatura. Em UML, os métodos abstratos são escritos em itálico.

Um método abstrato é um método sem implementação (sem corpo): declara-se que o método deve existir na subclasse, embora não haja implementação na superclasse. É declarado com o modificador `abstract` antes do tipo de retorno:

```java
public abstract class Figura {

    private Color cor;

    public abstract void desenhar();

}
```

Regras importantes:

- Qualquer classe que contenha um método abstrato deve ser abstrata também.
- Se a subclasse não implementar o método abstrato, ocorre erro de compilação:

```java
public class Pentagono extends Figura {

}
// erro de compilação: Pentagono precisa implementar o método abstrato desenhar()
```

> Método abstrato e sobrescrita se completam: o método abstrato obriga a subclasse a fornecer a implementação, e a subclasse o faz sobrescrevendo o método (vale usar `@Override`, visto na parte 1).

---

### 4. Impedir extensão e sobrescrita: a palavra-chave final

Nem sempre queremos permitir herança ou sobrescrita. A palavra reservada `final` restringe isso.

**Impedir que um método seja sobrescrito.** Às vezes um método tem uma implementação que não deveria ser alterada por sobrescrita, pois mudar o algoritmo poderia deixar o estado do objeto inconsistente. Para impedir a sobrescrita, usa-se `final` antes do tipo de retorno:

```java
public final void metodo1() {
    // ...
}
```

**Impedir que uma classe seja estendida.** Usa-se `final` antes da palavra `class`:

```java
public final class Classe1 {

    // ...

}

public class Classe2 extends Classe1 {   // erro de compilação
}
```

Esse recurso é útil, por exemplo, para criar classes de objetos imutáveis, como a classe `String`.

> Repare no contraste entre os modificadores vistos: `abstract` obriga a subclasse a implementar; `final` proíbe a subclasse de alterar (ou até de existir). São as duas pontas do controle sobre a herança.
