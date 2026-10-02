# Unidade 05 - Polimorfismo

> Material de apoio da disciplina de Programação Orientada a Objetos (POO).
> Cada aula é registrada em uma seção própria abaixo, com uma âncora referenciada no sumário.

## Sumário

- [Aula 17 - Polimorfismo](#aula-17)

<!--
Padrão para as próximas aulas:
1. Adicione o link no sumário acima, seguindo o mesmo formato:
   - [Aula N - Título](#aula-n)
2. Crie a seção da aula com a âncora correspondente:
   <a id="aula-n"></a>
   ## Aula N - Título
-->

---

<a id="aula-17"></a>

## Aula 17 - Polimorfismo

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
