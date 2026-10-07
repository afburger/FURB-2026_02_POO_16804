# Unidade 05 - Polimorfismo e Interfaces

> Material de apoio da disciplina de Programação Orientada a Objetos (POO).
> Cada aula é registrada em uma seção própria abaixo, com uma âncora referenciada no sumário.

## Sumário

- [Aula 18 - Polimorfismo](#aula-18)
- [Aula 19 - Interfaces](#aula-19)

<!--
Padrão para as próximas aulas:
1. Adicione o link no sumário acima, seguindo o mesmo formato:
   - [Aula N - Título](#aula-n)
2. Crie a seção da aula com a âncora correspondente:
   <a id="aula-n"></a>
   ## Aula N - Título
-->

---

<a id="aula-18"></a>

## Aula 18 - Polimorfismo

### 1. Motivação

Uma empresa quer saber quanto deve pagar de salário para todos os seus funcionários. Com um único tipo de funcionário, basta percorrer a lista somando o salário de cada um:

```java
public double calcularCustoSalarios() {
    double total = 0;

    for (Funcionario f : funcionarios) {
        total += f.calcularSalario();
    }

    return total;
}
```

O problema aparece quando surgem outros tipos de funcionário, cada um com um cálculo de salário diferente:

- Vendedor: comissionado.
- Gerente: além do valor fixo, recebe um extra quando atinge metas.
- Consultor: além do valor fixo, recebe um extra a cada viagem a negócios.

Mantendo uma lista para cada tipo, o método cresce com um laço por tipo:

```java
public double calcularCustoSalarios() {
    double total = 0;

    for (Funcionario f : funcionarios) {
        total += f.calcularSalario();
    }
    for (Vendedor v : vendedores) {
        total += v.calcularSalario();
    }
    for (Consultor c : consultores) {
        total += c.calcularSalario();
    }
    for (Gerente g : gerentes) {
        total += g.calcularSalario();
    }

    return total;
}
```

A cada novo tipo de funcionário é preciso criar uma nova lista e um novo laço. Isso não escala. O polimorfismo resolve esse problema.

---

### 2. O que é polimorfismo

Polimorfismo é um conceito da Programação Orientada a Objetos que define a habilidade que diferentes objetos têm de responder, cada um à sua maneira, a chamadas idênticas de mensagens (métodos).

> O polimorfismo se apoia em dois conceitos da Unidade 04: a herança (os tipos específicos são subclasses de um tipo geral) e a sobrescrita de método (cada subclasse redefine o método à sua maneira). Aqui colhemos o benefício disso.

---

### 3. Variável polimórfica

Para atingir o polimorfismo, usamos uma variável polimórfica: uma variável que pode referenciar objetos de tipos distintos. Por enquanto, é uma variável cujo tipo de dado é uma superclasse.

Dada uma variável polimórfica, ela pode referenciar:

- objetos de sua própria classe;
- objetos cuja classe seja subclasse (direta ou indireta) da classe da variável.

---

### 4. Tipo estático e tipo dinâmico

```java
Funcionario f;
f = new Consultor();
```

Nesse código existem dois tipos envolvidos:

- Tipo estático: o tipo declarado na variável (`Funcionario`).
- Tipo dinâmico: o tipo usado para instanciar o objeto (`Consultor`).

---

### 5. Qual método é executado (despacho dinâmico)

Com uma variável polimórfica, o código que é acionado para atender a uma chamada depende do tipo dinâmico. Isso permite tratar todos os funcionários por uma única lista do tipo da superclasse:

```java
Funcionario[] funcionarios = new Funcionario[10];

funcionarios[0] = new Funcionario("José Silva");
funcionarios[1] = new Vendedor("Pedro Alcantara");
funcionarios[2] = new Funcionario("Cristina Lima");
funcionarios[3] = new Vendedor("Jorge Luna");
funcionarios[4] = new Consultor("Marcia Cristina de Souza");
// ...
funcionarios[9] = new Gerente("Lucas Gentil");

for (Funcionario f : funcionarios) {
    System.out.println(f.calcularSalario());
}
```

- Quando `f` referenciar o tipo dinâmico `Vendedor`, será acionado o `calcularSalario()` da classe `Vendedor`, e não o de `Funcionario`.
- Quando o tipo dinâmico não sobrescreve o método, é acionado o código da superclasse.

Repare que isso resolve a motivação: um único laço sobre `Funcionario[]` calcula o salário de todos, e cada objeto responde com o seu próprio cálculo. Não é mais preciso um laço por tipo.

---

### 6. Acesso limitado ao tipo estático

Por padrão, ao usar uma variável polimórfica, somente os membros da classe do tipo da variável (tipo estático) podem ser utilizados:

```java
Funcionario f = new Programador();

f.calcularSalario();     // compila: calcularSalario() existe em Funcionario
f.incluirLinguagem(l);   // NÃO compila: incluirLinguagem() só existe em Programador
```

Mesmo que o objeto seja um `Programador`, pela variável do tipo `Funcionario` só enxergamos os membros de `Funcionario`.

---

### 7. Downcasting e instanceof

Para acessar um método específico da subclasse a partir de uma variável polimórfica, é necessário efetuar uma conversão (cast). Essa operação é conhecida como downcasting:

```java
Funcionario f = new Programador("Pedro");
LinguagemProgramacao l = new LinguagemProgramacao("C");

((Programador) f).incluirLinguagem(l);
```

Antes de converter, é possível conferir se o objeto pertence a uma determinada classe da sua hierarquia, usando `instanceof`:

```java
for (Funcionario f : funcionarios) {
    System.out.print("\nNome: " + f.getNome() +
                     ". Salário Base: " + f.getSalarioBase());
    if (f instanceof Vendedor) {
        System.out.print(". Total vendas: " +
                         ((Vendedor) f).calcularTotalVendas());
    }
}
```

> Atenção à diferença entre erro de compilação e erro de execução. Atribuir diretamente um objeto da superclasse a uma variável da subclasse (`Vendedor v = new Funcionario(...)`) não compila. Já o downcasting compila, mas, se o tipo dinâmico real do objeto não for aquele para o qual se está convertendo, ocorre um erro em tempo de execução (`ClassCastException`). É por isso que se usa `instanceof` para checar antes de converter.

---

### 8. Benefícios do polimorfismo

- Permite programar de forma mais abstrata, tratando objetos diferentes pela interface comum da superclasse.
- Permite evitar comandos condicionais para o tratamento de casos especiais (como o laço por tipo visto na motivação).

---

### 9. Exemplo: método transferir

O método `transferir` da classe `ContaBancaria` ilustra o ganho. Ele saca da conta atual e deposita na conta de destino:

```java
public void transferir(ContaBancaria contaDestino, double valor) {
    this.sacar(valor);
    contaDestino.depositar(valor);
}
```

Como `contaDestino` é do tipo `ContaBancaria`, o `transferir` funciona para qualquer subclasse de conta (por exemplo `ContaEspecial`). O `sacar` e o `depositar` acionados serão os do tipo dinâmico real de cada conta, sem que `transferir` precise saber de qual tipo específico se trata.

---

<a id="aula-19"></a>

## Aula 19 - Interfaces

### 1. Motivação

Imagine um programa que soma pagamentos de tipos bem diferentes (contas, investimentos), representados por classes que não têm relação de herança entre si. Sem um tipo comum, é preciso guardar tudo como `Object` e testar o tipo de cada item para saber qual método chamar:

```java
Object[] pagamentos = new Object[4];

pagamentos[0] = new ContaAgua();
pagamentos[1] = new ContaLuz();
pagamentos[2] = new PrevidenciaPrivada();
pagamentos[3] = new MensalidadeEnsino();

double totalPagamentos = 0;

for (Object o : pagamentos) {
    if (o instanceof Despesa) {
        totalPagamentos += ((Despesa) o).calcularValorPagar();
    } else if (o instanceof Investimento) {
        totalPagamentos += ((Investimento) o).calcularValorPagar();
    }
}

System.out.println("Total de pagamentos = " + totalPagamentos);
```

O código volta a ficar cheio de `instanceof` e casts, um caso para cada tipo. A herança não resolve aqui, pois as classes não compartilham uma superclasse comum. A interface resolve.

---

### 2. O que é uma interface

Interfaces permitem especificar um conjunto de comportamentos desejáveis em objetos. Esse conjunto pode ser aplicado a objetos de classes distintas e não relacionadas entre si.

- A interface contém um conjunto de definições de métodos e de constantes.
- A interface não contém implementação de métodos.
- A interface também não contém variáveis.

Uma classe pode implementar uma ou mais interfaces. A classe que implementa uma interface concorda em implementar todos os métodos definidos por ela, ou seja, concorda em desempenhar determinados comportamentos.

> A interface se parece com a ideia de método abstrato (Unidade 04): são assinaturas sem corpo que a classe é obrigada a implementar. A diferença é que a interface não exige herança: ela conecta classes que não têm nenhum parentesco, enquanto o método abstrato depende de uma superclasse comum.

---

### 3. Representação em UML

- Uma interface pode ser representada como uma classe estereotipada (com o estereótipo `«interface»`).
- Para indicar que uma classe desempenha os comportamentos de uma interface, usa-se o relacionamento de realização (uma linha tracejada terminada por um triângulo vazado apontando para a interface).
- Exemplo: `Classe1` deve implementar todos os métodos de `Pagavel`. Dizemos que `Classe1` implementa a interface `Pagavel`, ou que `Classe1` realiza a interface `Pagavel`.
- Em Java, uma classe pode implementar múltiplas interfaces.

---

### 4. Tradução para a linguagem Java

A interface é declarada com a palavra reservada `interface`, contendo apenas as assinaturas dos métodos:

```java
public interface Pagavel {

    double calcularValorPagar();

}
```

A classe usa a palavra reservada `implements` para anunciar as interfaces que realiza, e implementa os métodos (vale usar `@Override`):

```java
public class Classe2 implements Pagavel {

    @Override
    public double calcularValorPagar() {
        // ...
    }

}
```

Para implementar múltiplas interfaces, lista-se todas separadas por vírgula:

```java
public class Classe2 implements Interface1, Interface2 {

    public void metodo1() { /* ... */ }
    public void metodo2() { /* ... */ }
    public int metodo3() { /* ... */ }

}
```

---

### 5. Polimorfismo com interface

O polimorfismo (Aula 18) também se aplica a objetos de classes que implementam interfaces. Podemos criar uma variável polimórfica cujo tipo é uma interface.

Retomando a motivação, se todas as classes de pagamento implementam `Pagavel`, o array passa a ser do tipo `Pagavel` e o laço dispensa os `instanceof` e os casts:

```java
Pagavel[] pagamentos = new Pagavel[4];

pagamentos[0] = new ContaAgua();
pagamentos[1] = new ContaLuz();
pagamentos[2] = new PrevidenciaPrivada();
pagamentos[3] = new MensalidadeEstudo();

double totalPagamentos = 0;

for (Pagavel p : pagamentos) {
    totalPagamentos += p.calcularValorPagar();
}

System.out.println("Total de pagamentos = " + totalPagamentos);
```

Cada objeto responde com a sua própria implementação de `calcularValorPagar()`, mesmo sendo de classes sem parentesco. É o polimorfismo via interface.

---

### 6. Observações importantes

- Os métodos declarados numa interface não precisam de modificador de acesso; o único admissível é `public`.
- Ao indicar que uma classe abstrata implementa uma interface, a classe abstrata não precisa implementar os métodos da interface. Porém, suas subclasses concretas devem implementá-los (subclasses diretas que também sejam abstratas igualmente não precisam).

---

### 7. Herança de interface

Uma interface pode herdar a assinatura de métodos de outra interface. Nesse caso, a classe que quiser implementar a interface mais específica deve fornecer algoritmos para todos os métodos herdados e próprios.

Por exemplo, se `Interface2` herda de `Interface1`, uma classe que implementa `Interface2` deve implementar `metodo1()`, `metodo2()` e `metodo3()` (os de ambas as interfaces).

```java
public class ClasseK implements InterfaceX, InterfaceY {

    public double calcular() {
        return 0;
    }

}
```

---

### 8. Interfaces no Java 8 (métodos default)

Incluir métodos novos em interfaces já existentes obrigaria todas as classes que as implementam a implementar os novos métodos. A partir do Java 8, é possível definir uma implementação padrão para um método da interface, usando a palavra reservada `default`:

```java
public interface Interface1 {

    void metodo1(String str);

    default void log(String str) {
        System.out.println("Log:" + str);
    }

}
```

Como as interfaces não definem variáveis, os métodos com implementação limitam-se a usar dados vindos dos parâmetros.
