# Unidade 01 - Fundamentos de Programação Orientada a Objetos

> Material de apoio da disciplina de Programação Orientada a Objetos (POO).
> Cada aula é registrada em uma seção própria abaixo, com uma âncora referenciada no sumário.

## Sumário

- [Aula 1 - Introdução à Programação Orientada a Objetos](#aula-1)

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
