# Unidade 02 - Qualidade e Testes de Software

> Material de apoio da disciplina de Programação Orientada a Objetos (POO).
> Cada aula é registrada em uma seção própria abaixo, com uma âncora referenciada no sumário.

## Sumário

- [Aula 9 - Aula 9 - Qualidade de Software e Testes de Unidade com JUnit](#aula-9)

<a id="aula-9"></a>

## Aula 9 - Qualidade de Software e Testes de Unidade com JUnit

### 1. Qualidade e o custo de corrigir erros

Falhas de software têm impacto real e podem paralisar setores inteiros, como no apagão cibernético global de julho de 2024, que afetou transportes, finanças, saúde e telecomunicações. Isso motiva a preocupação com qualidade e teste desde cedo no desenvolvimento.

Um conceito de qualidade bastante usado na indústria é o de que um produto com qualidade é aquele que cumpre com sua especificação (CROSBY, 1979). Trazendo para software, testar é executar o programa de maneira controlada para avaliar se ele se comporta ou não conforme o especificado.

Um argumento central a favor de testar cedo é o custo de correção. Quanto mais tarde no ciclo de desenvolvimento um erro é detectado, mais caro fica corrigi-lo. A tabela abaixo ilustra esse crescimento, usando uma unidade de custo genérica X, que pode ser expressa em horas, reais, etc.

| Fase em que o erro é detectado | Custo relativo (X) |
|---|---|
| Especificação e arquitetura | 1 |
| Implementação | 5 |
| Testes de sistema | 15 |
| Em produção | 30 |

> Fonte: (IBM, 2008). A leitura prática é direta: um erro que custaria 1 para corrigir ainda na especificação pode custar 30 se só for descoberto em produção. Detectar cedo reduz de forma significativa o custo total do software.

---

### 2. Plano de testes e casos de teste

Testes não devem ser improvisados, precisam ser planejados. Esse planejamento é registrado formalmente em um documento chamado plano de testes.

Um plano de testes pode conter:

- Propósito do teste
- Identificação
- Itens a serem testados
- Critérios de aceite
- Documentos produzidos (logs, relatórios, etc.)
- Ambiente a ser testado

Dentro de um plano de testes há um conjunto de casos de teste. Cada caso de teste descreve um teste particular, com um conjunto de dados de entrada diferente e uma saída esperada.

Como exemplo, considere a classe `Calculadora`:

```text
Calculadora
+ somar(num1: int, num2: int): int
+ subtrair(num1: int, num2: int): int
+ multiplicar(num1: int, num2: int): int
+ dividir(num1: int, num2: int): int
```

Um plano de testes para validar essa classe poderia ser organizado assim:

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| 1 | Validar soma de números inteiros | Somar 10 e 75 | 85 |
| 2 | Validar subtração de inteiros | Subtrair 35 de 100 | 65 |
| 3 | Validar multiplicação de inteiros | Multiplicar 5 por 8 | 40 |
| 4 | Validar divisão de inteiros | Dividir 10 por 2 | 5 |

> Repare que cada caso é independente e tem entrada e saída esperada bem definidas. É essa estrutura que vamos transformar em código com JUnit mais adiante.

---

### 3. Tipos de teste

Nesta disciplina distinguimos dois tipos principais de teste.

#### Testes de sistema

- Executam o sistema sob o ponto de vista do usuário, em busca de falhas em relação aos requisitos propostos.
- São executados em condições próximas da realidade de uso: ambiente de hardware real, restrições de recursos, volume de dados, etc.

#### Testes de unidade (ou testes unitários)

- Testam unidades, isto é, partes individuais do software (funcionalidades pequenas e específicas).
- Em programação orientada a objetos, a unidade é o método.
- São realizados pelos próprios desenvolvedores, para verificar se o software faz o que deve fazer.

> Nesta disciplina o foco é o teste de unidade, e vamos construir testes para validar classes da camada de negócio.

---

### 4. Preparando o JUnit no

Para escrever testes de unidade em Java usaremos o framework JUnit (versão JUnit 6 / Jupiter). O passo a passo abaixo prepara o projeto.

1. Criar uma pasta `lib` dentro do projeto e adicionar o `.jar` do JUnit dentro dela (por exemplo, `junit-platform-console-standalone-6.0.0.jar`).
2. Fora da pasta `src`, criar uma nova pasta chamada `test`.
3. Definir essa pasta como uma paste de `Sources`..

> Em quase todas IDEs o procedimento é o mesmo, respeitando o padrão de cada uma: colocar o jar em uma pasta do projeto, adicioná-lo como biblioteca, criar a pasta das classes de teste e, dependendo da IDE, marcar essa pasta como pasta de teste.

---

### 5. Escrevendo um teste com JUnit

Como implementamos testes para a camada de negócio, haverá uma classe de teste paralela para cada classe a ser testada. Em geral, o nome dessa classe é igual ao da classe testada com o sufixo `Test` (por exemplo, `Calculadora` gera `CalculadoraTest`).

Para cada caso de teste, criamos um método com a anotação `@Test`, seguindo estas regras:

- O método deve ser público e não pode ter parâmetros.
- O método não pode retornar dados (`void`).
- Dentro do método, usamos um comando `assert` para validar uma situação.

O exemplo abaixo implementa o caso de teste 1 do plano da classe `Calculadora`:

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CalculadoraTest {

    @Test
    public void test1_SomarNumeros() {
        Calculadora calc = new Calculadora();
        int resultado = calc.somar(10, 75);
        assertEquals(85, resultado);
    }

}
```

No `assertEquals(85, resultado)`, o primeiro argumento (`85`) é o valor esperado e o segundo (`resultado`) é o valor obtido na execução. Em outras palavras, o teste afirma: espero que `somar(10, 75)` resulte em 85. Se o valor obtido for diferente do esperado, o teste falha.

---

### 6. Métodos assert

Os métodos `assert` verificam se uma condição é verdadeira. Caso a verificação não seja bem sucedida, o teste falha (uma exceção é lançada internamente). Os principais são:

| Método | Descrição |
|---|---|
| `assertEquals(long esperado, long real)` | Verifica se dois números inteiros são iguais |
| `assertEquals(double esperado, double real, double limite)` | Verifica se dois números decimais são iguais, respeitando uma margem de diferença (`limite`) |
| `assertEquals(objetoEsperado, objetoReal)` | Verifica se dois objetos são iguais |
| `assertTrue(boolean condição)` | Verifica se a condição é verdadeira |
| `assertFalse(boolean condição)` | Verifica se a condição é falsa |
| `assertNotNull(objeto)` | Verifica se a variável referencia um objeto |
| `assertNull(objeto)` | Verifica se a variável não referencia um objeto |

> A versão de `assertEquals` para números decimais pede um terceiro parâmetro, o `limite` (também chamado de delta). Ele existe porque comparar `double` por igualdade exata é problemático devido a arredondamentos, então aceitamos como iguais valores cuja diferença seja menor que esse limite.

---

### 7. Anotações do JUnit

Além de `@Test`, o JUnit oferece anotações para controlar o ciclo de vida dos testes e organizar a execução.

| Anotação | Funcionalidade | Observações |
|---|---|---|
| `@Test` | Marca um método como um teste unitário | É o ponto de entrada principal de um caso de teste |
| `@BeforeEach` | Executa antes de cada método de teste | Ideal para inicializar variáveis ou objetos comuns a todos os testes |
| `@AfterEach` | Executa depois de cada método de teste | Usado para limpar recursos (fechar conexões, resetar dados) |
| `@BeforeAll` | Executa uma vez antes de todos os testes da classe | O método deve ser `static` (ou a classe deve ser anotada com `@TestInstance(Lifecycle.PER_CLASS)`) |
| `@AfterAll` | Executa uma vez depois de todos os testes da classe | Também deve ser `static` (mesma regra do `@BeforeAll`) |
| `@DisplayName` | Define um nome legível para o teste ou a classe | Facilita a leitura dos relatórios de execução |
| `@Disabled` | Desabilita temporariamente um teste ou classe | Útil para pular testes em desenvolvimento ou que dependem de algo externo |
| `@Tag` | Agrupa testes com uma tag | Permite executar subconjuntos de testes, como `@Tag("integration")` |
| `@Timeout` | Define um tempo máximo de execução para o teste | Se excedido, o teste falha automaticamente |
| `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` | Define que a ordem de execução dos testes será determinada pela anotação `@Order` | É aplicada na classe de teste |
| `@Order(n)` | Define a ordem de execução de um método de teste | Funciona apenas se a classe estiver anotada com `@TestMethodOrder` |

---

### 8. Casos de teste com o mesmo contexto

Quando dois ou mais casos de teste compartilham o mesmo contexto (por exemplo, todos precisam de uma `Calculadora` já criada), é possível definir esse contexto em um método anotado, evitando repetir o mesmo código em cada teste.

```java
@BeforeEach
public void inicializarContexto() {
    calc = new Calculadora();
}
```

Da mesma forma, é possível definir um método para ser executado após cada caso de teste, por exemplo para liberar recursos:

```java
@AfterEach
public void finalizarContexto() {
    arquivo.close();
}
```

> Atenção a um detalhe importante. Para inicializar um objeto novo antes de cada teste, a anotação correta é `@BeforeEach`, que roda antes de cada método. A `@BeforeAll` roda uma única vez para a classe inteira e, por isso, exige um método `static`. Use `@BeforeEach` quando cada teste precisa de um estado limpo e independente, e reserve `@BeforeAll` para preparações caras que valem para todos os testes (como abrir uma conexão única).

---

### 9. Testando exceções

Nem todo teste espera um valor de retorno. Às vezes o comportamento correto do método é justamente lançar uma exceção, e queremos validar que isso acontece. Por exemplo, dividir um número inteiro por zero deve lançar `ArithmeticException`.

Para isso usamos `assertThrows`, informando a classe da exceção esperada e o trecho de código que deve lançá-la:

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CalculadoraTest {

    @Test
    public void test5_DividirPorZero() {
        Calculadora calc = new Calculadora();
        assertThrows(ArithmeticException.class, () -> calc.dividir(5, 0));
    }

}
```

> Observe o segundo argumento do `assertThrows`. Ele precisa ser um bloco de código a ser executado (uma expressão lambda `() -> ...`), e não a chamada direta do método. Se escrevermos `calc.dividir(5, 0)` diretamente como argumento, o código nem compila, porque a chamada seria avaliada antes de chegar ao `assertThrows`. Com o lambda, é o próprio `assertThrows` que executa o trecho e verifica se a exceção esperada foi lançada.

---

### 10. Resumindo

- Testar cedo é mais barato: o custo de corrigir um erro cresce muito conforme o ciclo avança, de 1 na especificação a 30 em produção (IBM, 2008).
- O plano de testes registra formalmente o planejamento e reúne os casos de teste, cada um com entrada e saída esperada.
- Teste de unidade valida a menor parte do software; em orientação a objetos, a unidade é o método, e o teste é escrito pelo próprio desenvolvedor.
- No JUnit, cada classe testada tem uma classe de teste paralela com sufixo `Test`; cada caso vira um método `public void` sem parâmetros anotado com `@Test`.
- Os métodos `assert` comparam o valor esperado com o obtido; para decimais, o `assertEquals` exige um limite de tolerância.
- As anotações de ciclo de vida (`@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`) evitam repetição e preparam ou limpam o contexto dos testes.
- Para validar erros esperados, use `assertThrows` passando a exceção e um lambda com o código que deve lançá-la.