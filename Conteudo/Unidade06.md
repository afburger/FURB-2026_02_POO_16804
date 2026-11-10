# Unidade 06 - Persistência em Arquivos

## Sumário

- [Aula 23 - Persistência em Arquivos](#aula-23)

<!--
Padrão para as próximas aulas:
1. Adicione o link no sumário acima, seguindo o mesmo formato:
   - [Aula N - Título](#aula-n)
2. Crie a seção da aula com a âncora correspondente:
   <a id="aula-n"></a>
   ## Aula N - Título
-->

---

<a id="aula-23"></a>

## Aula 23 - Persistência em Arquivos

### 1. Entrada e Saída (E/S)

Enquanto o programa está executando, os dados ficam na **memória principal** (RAM). Quando o programa termina, ou o computador é desligado, esses dados se perdem. **Persistir** um dado é gravá-lo em um meio de armazenamento secundário (disco rígido, SSD, pendrive), de onde ele pode ser recuperado depois.

**E/S (Entrada e Saída)**, ou I/O (Input/Output), é a troca de dados entre o programa e o mundo externo: disco, teclado, tela, rede. O arquivo é apenas um dos dispositivos com que o programa se comunica.

| Direção | O que acontece | Exemplos |
|---|---|---|
| **Entrada** | os dados vêm de fora **para a memória** do programa | ler um arquivo, ler o teclado |
| **Saída** | os dados vão da memória **para fora** do programa | gravar um arquivo, escrever na tela |

#### Categorias das operações de E/S em arquivos

As operações de E/S em arquivos são divididas em duas categorias, de acordo com **sobre o que** a operação atua:

- **Operações de acesso ao sistema de arquivos:** obter a relação de arquivos e subdiretórios de um diretório, ler propriedades de um arquivo ou diretório, criar e apagar diretórios, renomear arquivos.
- **Operações de leitura e edição do conteúdo de arquivos:** ler o conteúdo de arquivos e alterar (gravar) dados em arquivos.

As categorias e a direção (entrada ou saída) são dois eixos diferentes, que se cruzam:

| | Entrada | Saída |
|---|---|---|
| **Sistema de arquivos** | listar um diretório, ler tamanho e data de um arquivo | criar ou apagar diretório, renomear arquivo |
| **Conteúdo do arquivo** | ler o conteúdo | gravar ou alterar dados |

> Em Java, a primeira categoria é tratada pela classe `File`, que não lê nem grava o conteúdo. A segunda é tratada pelos **streams**, e é neles que aparece a divisão entre entrada (`InputStream`) e saída (`OutputStream`).

---

### 2. Sistema de arquivos e a classe `File`

O **sistema de arquivos** é o sistema utilizado pelo sistema operacional para armazenar, organizar e acessar os dados que estão armazenados no computador. Ele utiliza os discos (rígidos ou SSD), mídias óticas, pendrives etc.

#### Caminhos

O caminho indica o local (diretório) de um arquivo, usando um caractere separador: barra (`/`) no Linux e macOS, barra invertida (`\`) no Windows.

| Tipo de caminho | Descrição | Exemplo |
|---|---|---|
| Absoluto | contém todos os diretórios desde a raiz | `D:\Exemplo\POO\Java\Slide1.pdf` |
| Relativo | é relativo ao diretório atual do programa | `Java\Slide1.pdf` |

> Dentro de uma `String` Java, a barra invertida precisa ser escrita duas vezes: `"D:\\dados.dat"`. A barra comum (`"etc/texto.txt"`) funciona também no Windows.

#### A classe `File`

A classe `File` representa um arquivo ou um diretório. Criar um objeto `File` **não cria o arquivo no disco**: o objeto apenas representa um caminho, que pode ou não existir.

| Membro | Descrição |
|---|---|
| `File(String)` | Cria um objeto que representa um arquivo ou um diretório, que pode ou não existir |
| `getName()` | Retorna o nome do arquivo ou diretório |
| `exists()` | Retorna `true` se o arquivo/diretório existe |
| `isDirectory()` | Retorna `true` se o objeto representar um diretório |
| `isFile()` | Retorna `true` se o objeto representar um arquivo |
| `length()` | Retorna o tamanho, em bytes, do arquivo (válido apenas para arquivos) |
| `listFiles()` | Retorna um array com arquivos e diretórios contidos no diretório representado pelo objeto |
| `createTempFile()` | Cria um arquivo no diretório temporário |

```java
// Lista o conteúdo de um diretório, indicando o que é diretório e o que é arquivo
File diretorio = new File("C:\\Windows");

File[] conteudoDiretorio = diretorio.listFiles();
for (File item : conteudoDiretorio) {
    if (item.isDirectory()) {
        System.out.println("Diretório: " + item.getName());
    } else {
        System.out.println("Arquivo " + item.getName() +
                           " tem " + item.length() + " bytes");
    }
}
```

---

### 3. Arquivo texto x arquivo binário

Todo arquivo é, no fundo, uma sequência de bytes. O que muda é **como esses bytes são interpretados**.

| | Arquivo texto | Arquivo binário |
|---|---|---|
| O que os bits representam | caracteres | dados (números, valores lógicos, objetos) |
| Pode ser aberto em editor de texto | sim, é "legível" para humanos | não, aparece como caracteres sem sentido |
| Sequência de bytes | segue uma codificação de caracteres | qualquer sequência de bytes |
| Processamento | precisa converter texto em dado (ex.: `"89.9"` em `double`) | mais eficiente, o dado já está no formato da memória |

> Exemplo: o número `1234` em um arquivo texto ocupa 4 bytes (os caracteres `'1'`, `'2'`, `'3'`, `'4'`). Em um arquivo binário, gravado como `int`, ocupa sempre 4 bytes, mas com o valor numérico em si. Já o número `1234567890` ocupa 10 bytes em texto e continua ocupando 4 bytes como `int` binário.

---

### 4. Streams

Um **stream** (fluxo) é um objeto que obtém dados de uma fonte (teclado, arquivo, rede) ou grava dados em um destino (tela, arquivo etc.). Pense no stream como um "cano" por onde os dados passam, sempre em uma única direção.

```text
                 FileOutputStream
   Disco  <====================== Memória      (saída: gravação)

                 FileInputStream
   Disco  ======================> Memória      (entrada: leitura)
```

#### Hierarquia de classes

As classes de gravação descendem de `OutputStream` e as de leitura descendem de `InputStream`, ambas abstratas.

```text
                      OutputStream (abstrata)
          ┌──────────────────┼────────────────────┐
  FileOutputStream   FilterOutputStream    ObjectOutputStream
                 ┌───────────┼───────────────┐
         DataOutputStream  PrintStream  BufferedOutputStream


                      InputStream (abstrata)
          ┌──────────────────┼────────────────────┐
  FilterInputStream    FileInputStream     ObjectInputStream
          ┌──────────────────┐
  DataInputStream    BufferedInputStream
```

#### Encadeamento de streams

É possível encadear um stream em outro: um objeto que estende `InputStream` ou `OutputStream` "embrulha" outro, criando uma forma alternativa de ler ou gravar os dados. Cada objeto da cadeia acrescenta uma capacidade.

```text
Leitura:   Disco ==> FileInputStream ==> DataInputStream ==> Memória
                     (lê bytes)          (converte bytes em int, double, String...)

Gravação:  Disco <== FileOutputStream <== DataOutputStream <== Memória
```

Na prática, o encadeamento é feito passando um stream no construtor do outro:

```java
FileOutputStream fos = new FileOutputStream(arquivo);   // sabe gravar bytes no arquivo
DataOutputStream dos = new DataOutputStream(fos);       // sabe converter int, double... em bytes
```

#### Ponteiro do arquivo

O **ponteiro** é um número (`long`) que representa o número do byte do arquivo (começando de 0) em que o ponteiro está posicionado.

- Qualquer operação de leitura ou gravação sempre é feita na posição atual do ponteiro.
- O ponteiro avança automaticamente quando ocorre uma operação de leitura (ou gravação).

```text
1. Após abrir o arquivo:            [byte][byte][byte][byte] ... [byte][byte]
                                      ^
2. Após executar read():            [byte][byte][byte][byte] ... [byte][byte]
                                            ^
3. Depois de vários read():         [byte][byte][byte][byte] ... [byte][byte]
                                                                 ^
4. Após o último read():            [byte][byte][byte][byte] ... [byte][byte]
                                                                          ^ (fim: read() retorna -1)
```

---

### 5. Gravação e leitura de arquivos binários

#### 5.1 Gravação com `FileOutputStream`

A classe `FileOutputStream` é utilizada para gravar bytes em arquivos.

| Membro | Descrição |
|---|---|
| `FileOutputStream(File)` | Cria um objeto para gravar um arquivo. Se o arquivo já existir, será **recriado** (destruindo o conteúdo que havia) |
| `FileOutputStream(File, boolean)` | Cria um objeto para gravar um arquivo, possibilitando **acrescentar** novos dados no arquivo (quando o segundo parâmetro é `true`) |
| `write(int)` | Grava um byte no arquivo |
| `write(byte[])` | Grava os dados de um array no arquivo |
| `flush()` | Provoca a atualização do arquivo |
| `close()` | Fecha o arquivo |

```java
// Criação: se dados.dat existir, o conteúdo anterior é perdido
File arquivo = new File("D:\\dados.dat");
FileOutputStream fos = new FileOutputStream(arquivo);
fos.write(79);
fos.write(105);
fos.close();
```

O construtor `FileOutputStream(File, boolean)` permite indicar que se quer **acrescentar** dados ao final do arquivo, sem apagar o que já existe:

```java
// Alteração: acrescenta o byte 65 no final do arquivo
File arquivo = new File("D:\\dados.dat");
FileOutputStream fos = new FileOutputStream(arquivo, true);
fos.write(65);
fos.close();
```

#### 5.2 Exceções que podem ser lançadas na gravação

```text
           Exception
          ┌────┴──────────────┐
     IOException        RuntimeException
          |                   |
FileNotFoundException   SecurityException
```

| Exceção | Descrição |
|---|---|
| `SecurityException` | Usuário sem permissão para gravar dados no arquivo |
| `FileNotFoundException` | Diretório não existe |
| `IOException` | Falha de gravação |

> `IOException` e `FileNotFoundException` são exceções **verificáveis** (checked): o compilador obriga a tratá-las com `try/catch` ou a declará-las com `throws`. `SecurityException` descende de `RuntimeException` e não é verificável.

#### 5.3 Leitura com `FileInputStream`

A classe `FileInputStream` é utilizada para ler bytes de arquivos.

| Membro | Descrição |
|---|---|
| `FileInputStream(File)` | Cria um objeto para ler os dados de um arquivo |
| `read()` | Lê um byte do arquivo. Retorna `-1` quando atingir o final do arquivo |
| `read(byte[])` | Lê os dados do arquivo, suficiente para alimentar o vetor. Retorna a quantidade de bytes lidos |
| `readAllBytes()` | Lê e retorna um vetor com todos os dados do arquivo |
| `skip()` | Salta para uma posição do arquivo, em relação à posição atual |
| `close()` | Fecha o arquivo |

Duas formas equivalentes de ler o arquivo byte a byte até o fim:

```java
File f = new File("D:\\dados.dat");
FileInputStream fis = new FileInputStream(f);
int dado;

while (true) {
    dado = fis.read();
    if (dado != -1) {
        System.out.println(dado);
    } else {
        break;                         // -1: chegou ao fim do arquivo
    }
}
fis.close();
```

```java
// Forma resumida: lê e testa na própria condição do while
File f = new File("D:\\dados.dat");
FileInputStream fis = new FileInputStream(f);
int dado;

while ((dado = fis.read()) != -1) {
    System.out.println(dado);
}

fis.close();
```

---

### 6. Persistência de dados primitivos em arquivos binários

#### Motivação

`FileOutputStream` só sabe gravar **bytes**. Como gravar o valor de uma variável `double`?

```java
System.out.print("Informe o preço: ");

Scanner teclado = new Scanner(System.in);
double preco = Double.parseDouble(teclado.nextLine());

System.out.println("Valor digitado: " + preco);

File f = new File("c:\\dados.bin");
FileOutputStream fos = new FileOutputStream(f);
fos.write(preco);                      // ERRO de compilação: write espera int, não double
```

A solução é encadear um `DataOutputStream`, que converte os tipos primitivos e `String` em bytes.

#### 6.1 `DataOutputStream`

`DataOutputStream` oferece uma série de métodos para gravação de dados primitivos e `String`: `writeInt()`, `writeLong()`, `writeDouble()`, `writeFloat()`, `writeBoolean()`, `writeChar()`, `writeByte()`, `writeShort()`, `writeUTF()` (para `String`).

```java
File f = new File("c:\\dados.bin");
FileOutputStream fos = new FileOutputStream(f);
DataOutputStream dos = new DataOutputStream(fos);
dos.writeDouble(preco);                // 8 bytes
dos.writeBoolean(true);                // 1 byte
dos.writeUTF("FURB");                  // 2 bytes de tamanho + 4 bytes de texto
dos.close();
```

Conteúdo do arquivo gerado, em hexadecimal (com `preco = 100`):

```text
Posição   Bytes                                  Significado
00-07     40 59 00 00 00 00 00 00                double (8 bytes)
08        01                                     boolean true (1 byte)
09-0A     00 04                                  tamanho da String (4)
0B-0E     46 55 52 42                            "FURB"
```

#### 6.2 `DataInputStream`

`DataInputStream` oferece os métodos correspondentes para leitura: `readInt()`, `readLong()`, `readDouble()`, `readFloat()`, `readBoolean()`, `readChar()`, `readByte()`, `readShort()`, `readUTF()`. Após a leitura, o ponteiro é posicionado após a quantidade de bytes lidos.

```java
File f = new File("D:\\dados.bin");
FileInputStream fis = new FileInputStream(f);
DataInputStream dis = new DataInputStream(fis);
double preco = dis.readDouble();
boolean bool = dis.readBoolean();
String instituicao = dis.readUTF();
dis.close();
```

#### 6.3 Observações

- `DataInputStream` e `DataOutputStream` leem e gravam tipos primitivos e `String` de forma **independente de máquina**: o arquivo gravado em um sistema operacional é lido corretamente em outro.
- Os dados devem ser lidos **na mesma ordem** em que foram gravados. O arquivo não guarda o tipo de cada dado: se gravar `double, boolean, String` e ler `int, ...`, os bytes serão interpretados de forma errada.
- A exceção `EOFException` é lançada quando há a tentativa de ler dados **além do fim do arquivo**. Ela pode ser usada para saber que não há mais dados.
- Ao encadear streams, **somente o objeto mais externo precisa ser fechado**: `dis.close()` também fecha o `FileInputStream`.

```java
// Lê registros até o fim do arquivo, usando EOFException
while (true) {
    try {
        int codigo = dis.readInt();
        String descricao = dis.readUTF();
        double preco = dis.readDouble();
        System.out.println(codigo + " - " + descricao + " - " + preco);
    } catch (EOFException e) {
        break;                         // não há mais registros
    }
}
```

---

### 7. Buffer de dados

#### A vantagem do buffer

Cada acesso ao arquivo tem um **custo fixo**, independente de quantos bytes transfere: o Java pede ao sistema operacional, que aciona o dispositivo e devolve o controle ao programa. Gravar 4 bytes ou 8 KB custa quase o mesmo nesse trecho.

Sem buffer, cada `writeInt`, `writeUTF` ou `writeDouble` vira um acesso separado ao disco. O **buffer** é uma área na memória (8 KB por padrão) que fica no meio do caminho:

- **Na gravação** (`BufferedOutputStream`): os dados se acumulam no buffer e só vão para o disco quando ele enche, ou no `flush()`/`close()`, em um único acesso.
- **Na leitura** (`BufferedInputStream`): o primeiro `read` busca um bloco de 8 KB do disco, e as leituras seguintes saem da memória, muito mais rápida, até o bloco acabar.

> Analogia: levar as compras do carro para casa um item por viagem, ou usar uma sacola. A quantidade de compras é a mesma, mas o número de viagens despenca.

#### `BufferedInputStream` e `BufferedOutputStream`

| Membro | Descrição |
|---|---|
| `BufferedInputStream(InputStream)` | Cria um objeto para ler os dados de um `InputStream`, usando um buffer de 8 KB |
| `BufferedInputStream(InputStream, int size)` | Idem, definindo o tamanho do buffer |
| `BufferedOutputStream(OutputStream)` | Cria um objeto para gravar dados em um `OutputStream`, usando um buffer de 8 KB |
| `BufferedOutputStream(OutputStream, int size)` | Idem, definindo o tamanho do buffer |
| `flush()` | Grava os dados do buffer imediatamente |
| `close()` | Faz o `flush()` e fecha o stream |

O buffer entra **no meio** da cadeia, entre o stream do arquivo e o `DataInputStream`/`DataOutputStream`:

```text
Disco ==> FileInputStream ==> BufferedInputStream ==> DataInputStream ==> Memória
```

```java
File arquivo = new File("D:\\dados.dat");

FileInputStream fis = new FileInputStream(arquivo);
BufferedInputStream bis = new BufferedInputStream(fis);
DataInputStream dis = new DataInputStream(bis);

// ... leitura usando os métodos do DataInputStream (o objeto mais externo)

dis.close();
```

> Nesse exemplo, deve-se utilizar os métodos do `DataInputStream` para ler os dados do stream.

#### Cuidados

- Enquanto não houver `flush()` ou `close()`, os dados gravados estão **só na memória**. Se o programa terminar antes disso, eles se perdem. Por isso é essencial fechar o stream (ver tópico 8).
- O ganho é pequeno quando o programa já grava blocos grandes de uma vez (`write(byte[])` com vetores grandes) ou quando a classe já tem buffer interno, como `PrintWriter(File)` e `Scanner`.

> Nos exemplos da aula, gravar e ler 100.000 produtos levou cerca de 130 ms (gravação) e 140 ms (leitura) sem buffer, contra cerca de 60 ms e 50 ms com buffer. São cerca de 300 mil acessos ao disco sem buffer, contra cerca de 330 acessos de 8 KB com buffer. Veja as classes `ArquivoBinarioDireto` e `ArquivoBinarioBuffer` no tópico 12.

---

### 8. Fechamento de streams

Depois de manipulado, o arquivo **deve ser fechado**. O fechamento libera o arquivo para o sistema operacional e, no caso de streams com buffer, grava os dados pendentes.

O problema da forma simples é que, se ocorrer um erro durante a leitura, o `close()` não é executado:

```java
File arquivo = new File("D:\\Temp\\dados.bin");
FileInputStream fis = new FileInputStream(arquivo);

int dado;
while ((dado = fis.read()) != -1) {
    System.out.println(dado);
}

fis.close();                           // se ocorrer erro na leitura, esta linha não é executada
```

A solução tradicional é fechar no bloco `finally`, que sempre é executado:

```java
File arquivo = new File("D:\\Temp\\dados.bin");
FileInputStream fis = null;

try {
    fis = new FileInputStream(arquivo);

    int dado;
    while ((dado = fis.read()) != -1) {
        System.out.println(dado);
    }
} finally {
    if (fis != null) {
        fis.close();
    }
}
```

A sintaxe resumida é o **try-with-resources**: os objetos criados nos parênteses do `try` são fechados automaticamente ao final do bloco, mesmo que ocorra uma exceção.

```java
try (FileInputStream fis = new FileInputStream(arquivo)) {
    int dado;
    while ((dado = fis.read()) != -1) {
        System.out.println(dado);
    }
}                                      // fis.close() é chamado automaticamente aqui
```

> Para usar `try(...)`, os objetos instanciados devem ser de classes que implementam a interface `java.lang.AutoCloseable`. Todos os streams vistos nesta aula, além de `Scanner` e `PrintWriter`, implementam essa interface.

---

### 9. Alteração de dados em arquivos binários: `RandomAccessFile`

#### Por que outra classe?

A classe `DataOutputStream` somente funciona com duas modalidades:

- recriar um arquivo;
- acrescentar dados ao final do arquivo.

Não é possível **alterar dados já gravados** com `DataOutputStream`: os streams são sequenciais (começam no byte 0 e vão avançando) e de mão única (só leem ou só gravam).

A classe `RandomAccessFile` permite **acessar qualquer posição do arquivo diretamente**, lendo e gravando no mesmo objeto. É isso que permite alterar dados no meio do arquivo sem reescrevê-lo inteiro. Ela implementa as interfaces `DataInput` e `DataOutput`, por isso tem os mesmos métodos de leitura e gravação de `DataInputStream` e `DataOutputStream` (`readInt()`, `writeDouble()`, `readUTF()` etc.).

#### Modos de abertura

O construtor recebe o arquivo e o parâmetro `mode`:

| Modo | Descrição |
|---|---|
| `"r"` | Acesso somente leitura |
| `"rw"` | Leitura e gravação |
| `"rws"` | Leitura e gravação. Cada gravação é aplicada imediatamente no arquivo e causa a alteração da data de modificação do arquivo |
| `"rwd"` | Similar ao `rws`, porém somente causa a alteração da data de modificação do arquivo quando o arquivo é fechado |

> Os modos `rws` e `rwd` são mais lentos.

#### Principais métodos

| Membro | Descrição |
|---|---|
| `seek(long)` | Posiciona o ponteiro em relação ao primeiro byte do arquivo. Ex.: `seek(50)` posiciona no 51º byte, independente de onde o ponteiro estiver |
| `skipBytes(int)` | Salta n bytes à frente da posição atual do ponteiro |
| `getFilePointer()` | Retorna a posição atual do ponteiro |
| `length()` | Retorna o tamanho do arquivo, em bytes |
| `setLength(long)` | Permite truncar o arquivo. Sempre informar um valor menor do que o atual |
| `readInt()`, `writeInt()`, `readUTF()`, `writeUTF()`... | Mesmos métodos de `DataInputStream` e `DataOutputStream` |

#### Exemplo: alterar o preço de um produto

Considere um arquivo em que cada produto ocupa um `int` (código, 4 bytes) e um `double` (preço, 8 bytes). Como todos os registros têm o mesmo tamanho, a posição de qualquer produto pode ser **calculada**:

```java
try (RandomAccessFile raf = new RandomAccessFile("produtos.dat", "rw")) {
    int tamanhoRegistro = 4 + 8;            // int + double = 12 bytes
    int indice = 2;                         // terceiro produto (começa em 0)
    raf.seek(indice * tamanhoRegistro + 4); // pula até o preço dele
    raf.writeDouble(999.90);                // sobrescreve só esses 8 bytes
}
```

```text
Byte:     0           4                  12          16                 24          28                 36
          [  codigo  ][      preco      ][  codigo  ][      preco      ][  codigo  ][      preco      ]
          |------------ produto 0 -------|------------ produto 1 -------|------------ produto 2 -------|
                                                                                    ^ seek(2 * 12 + 4) = 28
```

O arquivo pode ter um milhão de produtos: a alteração toca apenas 8 bytes.

> Esse acesso direto só funciona bem com **registros de tamanho fixo**. Uma `String` gravada com `writeUTF()` tem tamanho variável, o que impede calcular a posição do registro. Para guardar texto em registros de tamanho fixo, grave sempre a mesma quantidade de caracteres (por exemplo, 30), completando com espaços.

---

### 10. Leitura e gravação de arquivos texto

#### 10.1 Gravação com `PrintWriter`

A classe `PrintWriter` é utilizada para gravar texto. Ela descende de `Writer` e oferece os mesmos métodos que já usamos no `System.out`: `print()`, `println()` e `printf()`.

| Método | Descrição |
|---|---|
| `PrintWriter(File)` / `PrintWriter(String)` | Cria um arquivo de texto (se existir, é **recriado**) |
| `PrintWriter(OutputStream)` | Edita o arquivo representado pelo stream |
| `close()` | Fecha o arquivo |

```java
File arquivo = new File("etc/texto.txt");
PrintWriter arquivoTexto = new PrintWriter(arquivo);

arquivoTexto.println("Primeira linha");
arquivoTexto.print(true);
arquivoTexto.printf("Preço: %20.2f", 1234.5);

arquivoTexto.close();
```

#### 10.2 Acrescentar texto ao final do arquivo

O `PrintWriter(File)` sempre recria o arquivo. Para abrir o arquivo para inclusão de novo texto, encadeia-se um `FileOutputStream` aberto com `append = true`:

```java
File arquivo = new File("etc/texto.txt");
FileOutputStream fos = new FileOutputStream(arquivo, true);
PrintWriter arquivoTexto = new PrintWriter(fos);
arquivoTexto.println("nova linha");
arquivoTexto.close();
```

> Para **alterar uma linha no meio** de um arquivo texto, não existe acesso direto como no `RandomAccessFile`, porque as linhas têm tamanhos diferentes. A forma usual é ler o arquivo inteiro, alterar os dados na memória e recriar o arquivo.

#### 10.3 Separador de linha

No arquivo texto, o caractere de separação de linha é diferente entre as plataformas:

| Sistema operacional | Caractere(s) |
|---|---|
| Windows | `\r\n` |
| UNIX/Linux | `\n` |
| macOS | `\n` |

O método `println()` gera o separador específico da plataforma em que o programa está rodando.

#### 10.4 Leitura com `Scanner`

A classe `Scanner`, já usada para ler o teclado, também pode ser utilizada para efetuar operações de leitura em arquivo texto.

| Método | Descrição |
|---|---|
| `Scanner(File)` / `Scanner(String)` | Abre o arquivo |
| `close()` | Fecha o arquivo |
| `String nextLine()` | Lê uma linha |
| `String next()` | Retorna uma palavra |
| `byte nextByte()` | Lê um `byte` |
| `int nextInt()` | Lê um `int` |
| `long nextLong()` | Lê um `long` |
| `float nextFloat()` | Lê um `float` |
| `double nextDouble()` | Lê um `double` |
| `boolean hasNext()` | Retorna `true` se há dado para ser lido no arquivo |

```java
// Exibe o conteúdo de um arquivo texto, linha a linha
Scanner arqTexto = new Scanner(new File("etc\\texto.txt"));
while (arqTexto.hasNext()) {
    System.out.println(arqTexto.nextLine());
}
arqTexto.close();
```

> Cuidado com números decimais: `printf("%.2f")` e `nextDouble()` usam o idioma do sistema. Em português, gravam e esperam vírgula (`89,90`), enquanto `Double.parseDouble()` espera ponto (`89.90`). Nos exemplos da aula, o preço é gravado concatenando o `double` (que sempre usa ponto) e lido com `nextLine()` + `Double.parseDouble()`.

#### 10.5 Codificação de caracteres

- **Mapa de caracteres** (*charset*): conjunto de caracteres, cada um com um código (*code point*). Exemplos: US-ASCII (alfabeto inglês), Latin-1 (ISO-8859-1, caracteres ocidentais, incluindo latinos) e Unicode (caracteres de todas as linguagens).
- **Codificação** (*encoding*): como o code point vira bytes no arquivo. O Unicode tem várias:

| Codificação | Bytes por caractere |
|---|---|
| UTF-8 | 1 byte para caracteres ocidentais; de 2 a 4 bytes para os demais |
| UTF-16 | 2 ou 4 bytes |
| UTF-32 | sempre 4 bytes |

Exemplo: o caractere `€` tem code point 8364 (U+20AC). Em UTF-8, é representado pelos bytes `0xE2 0x82 0xAC`; em UTF-16, por `0x20 0xAC`.

> Gravar com uma codificação e ler com outra gera caracteres trocados, como `Ã§` no lugar de `ç`. Desde o Java 18, a codificação padrão é UTF-8, mas é possível informar a codificação explicitamente.

```java
// Gravação informando a codificação
String linha = "áéíóúç!";
PrintWriter out = new PrintWriter("etc/arq4.txt", "UTF-8");
out.println(linha);
out.close();

// Leitura informando a mesma codificação
File arquivo = new File("etc/arq4.txt");
Scanner sc = new Scanner(arquivo, "UTF-8");
while (sc.hasNext()) {
    System.out.println(sc.nextLine());
}
sc.close();
```

---

### 11. Serialização

A **serialização** de objetos consiste em converter os dados de estado de um objeto numa representação binária ou textual, a fim de armazená-lo num meio de armazenamento secundário. A operação inversa (a partir de uma cadeia de bits reconstruir um objeto) é chamada de **desserialização** (ORACLE, 2019).

Diferente do `DataOutputStream`, em que gravamos campo a campo (`writeInt`, `writeUTF`...), na serialização o objeto inteiro é gravado com um único comando.

#### 11.1 `ObjectOutputStream` e `ObjectInputStream`

O principal método de `ObjectOutputStream` é `writeObject()`, e o de `ObjectInputStream` é `readObject()`. Ambos também implementam `DataOutput`/`DataInput`, portanto também gravam e leem tipos primitivos.

```java
// Serialização
File f = new File("Objetos.bin");
try (FileOutputStream fos = new FileOutputStream(f);
     ObjectOutputStream o = new ObjectOutputStream(fos)) {
    o.writeObject("Boa noite");
    o.writeObject(new ContaBancaria("1", "Lucas", 300));
    o.writeObject(new Veiculo("MIE-3932", "Anderson"));
}
```

```java
// Desserialização
String objeto1;
ContaBancaria objeto2;
Veiculo objeto3;

try (FileInputStream fis = new FileInputStream(f);
     ObjectInputStream ois = new ObjectInputStream(fis)) {
    objeto1 = (String) ois.readObject();           // readObject() retorna Object:
    objeto2 = (ContaBancaria) ois.readObject();    // é preciso fazer o cast
    objeto3 = (Veiculo) ois.readObject();
}
```

> Para ler os objetos do stream, deve-se executar as operações de leitura **na mesma ordem** em que foram executadas as operações de gravação.

#### 11.2 Regras da serialização

- Podem ser gravados vários objetos no stream.
- Para um objeto ser serializado, sua classe deve implementar a interface `Serializable`. `Serializable` é uma **interface de marcação**: não tem métodos, apenas "marca" a classe como serializável.
- Valores de variáveis **estáticas** não são serializados (pertencem à classe, não ao objeto).
- Para não serializar determinada variável de instância, utiliza-se o modificador `transient`:

```java
private transient String atributo;    // não é gravado; na leitura recebe o valor padrão (null)
```

> Se um atributo for de uma classe que não implementa `Serializable`, a gravação falha. Coleções como `ArrayList` são serializáveis, desde que os seus elementos também sejam.

#### 11.3 Exceções lançadas

| Exceção | Quando ocorre |
|---|---|
| `ClassNotFoundException` | Ao tentar desserializar objeto de uma classe desconhecida |
| `NotSerializableException` | Ao tentar serializar um objeto de uma classe que não implementa `Serializable` |
| `InvalidClassException` | Ao tentar desserializar um objeto que foi serializado com versão diferente da classe |
| `StreamCorruptedException` | Ao ler um arquivo cujo conteúdo não está no formato esperado (ver 11.5) |

#### 11.4 Versionamento: `serialVersionUID`

É possível "versionar" uma classe ao persistir um objeto. O versionamento garante que objetos somente possam ser reconstituídos quando recuperados a partir da mesma versão de classe que persistiu o objeto.

```java
private static final long serialVersionUID = 1L;
```

- Quando não há declaração de `serialVersionUID`, o Java gera um número de versão automaticamente, calculado a partir da estrutura da classe. Qualquer alteração na classe muda esse número.
- É recomendável que todas as classes serializáveis declarem explicitamente um valor para `serialVersionUID`.
- Se a versão gravada for diferente da versão da classe, a leitura lança `java.io.InvalidClassException`.

#### 11.5 Acrescentar objetos: `StreamCorruptedException`

Uma exceção da classe `StreamCorruptedException` pode ser lançada ao ler um arquivo com objetos persistidos que foi gravado com `FileOutputStream(arquivo, true)` com o objetivo de acrescentar dados.

A falha ocorre porque, sempre que um `ObjectOutputStream` é criado, ele grava um **novo cabeçalho** no arquivo, constituído de 4 bytes (`AC ED 00 05`). Na leitura, o segundo cabeçalho no meio do arquivo é interpretado como dado inválido.

```text
Arquivo após duas gravações com append:
[AC ED 00 05][objetos da 1ª gravação][AC ED 00 05][objetos da 2ª gravação]
                                      ^ a leitura não espera um cabeçalho aqui
```

> Solução: para acrescentar objetos, leia todos os objetos, acrescente o novo na memória e grave tudo novamente. Uma forma prática é gravar uma lista inteira (`ArrayList`) com um único `writeObject()`.

#### 11.6 Serialização em texto

A serialização nativa do Java (`ObjectOutputStream`) gera sempre um arquivo binário. Para serializar em texto, o próprio JDK oferece as classes `XMLEncoder` e `XMLDecoder`, que gravam e leem o objeto no formato XML.

O `XMLEncoder` trabalha com o padrão **JavaBean**: a classe precisa ser pública e ter construtor público sem parâmetros e getters/setters para os atributos.

```java
// Serialização em XML
try (XMLEncoder encoder = new XMLEncoder(
        new BufferedOutputStream(new FileOutputStream("produtos.xml")))) {
    encoder.writeObject(produtos);     // produtos é um List<Produto>
}

// Desserialização a partir do XML
try (XMLDecoder decoder = new XMLDecoder(
        new BufferedInputStream(new FileInputStream("produtos.xml")))) {
    List<Produto> lidos = (List<Produto>) decoder.readObject();
}
```

Trecho do arquivo gerado, legível em qualquer editor de texto:

```text
<object class="persistencia.Produto">
 <void property="codigo">
  <int>10</int>
 </void>
 <void property="descricao">
  <string>Mouse sem fio</string>
 </void>
 <void property="preco">
  <double>89.9</double>
 </void>
</object>
```

> Diferente da leitura linha a linha com `Scanner` (tópico 10), em que o programador define o formato e faz a conversão, aqui a conversão é automática. No mercado, o formato textual mais usado para isso é o JSON, com bibliotecas externas como Gson e Jackson.

> Segurança: tanto `readObject()` quanto `XMLDecoder` só devem ser usados com arquivos de **origem confiável**. Ler um arquivo malicioso pode criar objetos arbitrários e executar código.

---

### 12. Exemplos completos

Os exemplos da aula usam a classe `Produto` (código, descrição e preço, num cenário de estoque de uma loja de informática). Todas as classes têm a mesma estrutura, para facilitar a comparação:

- `gravar(List<Produto>)`: **criação**, recria o arquivo;
- `acrescentar(Produto)`: **alteração**, acrescenta um produto;
- `ler()`: **leitura** de todos os produtos;
- `main`: cria o arquivo com 2 produtos, acrescenta 1 e lê tudo.

| Classe | Tipo | Gravação | Leitura |
|---|---|---|---|
| `ArquivoTextoDireto` | texto | `PrintWriter` | `Scanner` |
| `ArquivoTextoBuffer` | texto | `FileOutputStream` → `BufferedOutputStream` → `PrintWriter` | `FileInputStream` → `BufferedInputStream` → `Scanner` |
| `ArquivoBinarioDireto` | binário | `FileOutputStream` → `DataOutputStream` | `FileInputStream` → `DataInputStream` |
| `ArquivoBinarioBuffer` | binário | `FileOutputStream` → `BufferedOutputStream` → `DataOutputStream` | `FileInputStream` → `BufferedInputStream` → `DataInputStream` |
| `ArquivoSerializacaoBinaria` | binário (objetos) | `FileOutputStream` → `BufferedOutputStream` → `ObjectOutputStream` | `FileInputStream` → `BufferedInputStream` → `ObjectInputStream` |
| `ArquivoSerializacaoTexto` | texto (XML) | `FileOutputStream` → `BufferedOutputStream` → `XMLEncoder` | `FileInputStream` → `BufferedInputStream` → `XMLDecoder` |

Como referência, a classe com buffer para arquivo binário:

```java
public class ArquivoBinarioBuffer {

    private final File arquivo;

    public ArquivoBinarioBuffer(String caminho) {
        this.arquivo = new File(caminho);
    }

    // CRIAÇÃO: recria o arquivo com a lista de produtos
    public void gravar(List<Produto> produtos) throws IOException {
        // O close() do objeto mais externo faz o flush do buffer e fecha toda a cadeia
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo)))) {
            for (Produto p : produtos) {
                escrever(dos, p);
            }
        }
    }

    // ALTERAÇÃO: acrescenta um produto no final (append = true)
    public void acrescentar(Produto p) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(arquivo, true)))) {
            escrever(dos, p);
        }
    }

    // LEITURA: lê os produtos até o fim do arquivo
    public List<Produto> ler() throws IOException {
        List<Produto> produtos = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(arquivo)))) {
            while (true) {
                try {
                    produtos.add(lerProduto(dis));
                } catch (EOFException e) {
                    break;             // fim do arquivo
                }
            }
        }
        return produtos;
    }

    // A ORDEM de gravação define a ordem de leitura: int, String, double
    private void escrever(DataOutputStream dos, Produto p) throws IOException {
        dos.writeInt(p.getCodigo());
        dos.writeUTF(p.getDescricao());
        dos.writeDouble(p.getPreco());
    }

    // Lê na MESMA ordem em que foi gravado
    private Produto lerProduto(DataInputStream dis) throws IOException {
        int codigo = dis.readInt();
        String descricao = dis.readUTF();
        double preco = dis.readDouble();
        return new Produto(codigo, descricao, preco);
    }
}
```

> Os pares direto/buffer geram arquivos **idênticos** byte a byte: o buffer muda o desempenho, não o resultado. O arquivo binário com 3 produtos tem 92 bytes; o mesmo conteúdo serializado com `ObjectOutputStream` tem 252 bytes, porque a serialização grava também o nome da classe e os nomes e tipos dos atributos.

---

### 13. Resumindo: qual classe usar?

| Operação | Arquivo texto | Arquivo binário |
|---|---|---|
| Criar (recria se existir) | `new PrintWriter(arquivo)` | `new FileOutputStream(arquivo)` + `DataOutputStream` |
| Acrescentar no final | `new PrintWriter(new FileOutputStream(arquivo, true))` | `new FileOutputStream(arquivo, true)` + `DataOutputStream` |
| Ler | `new Scanner(arquivo)` | `FileInputStream` + `BufferedInputStream` + `DataInputStream` |
| Alterar no meio do arquivo | ler tudo, alterar em memória e recriar o arquivo | `new RandomAccessFile(arquivo, "rw")` + `seek()` |
| Gravar / ler objetos | `XMLEncoder` / `XMLDecoder` | `ObjectOutputStream` / `ObjectInputStream` |
| Fechar | `close()` ou try-with-resources | `close()` ou try-with-resources |

#### Boas práticas

- Sempre feche os streams, de preferência com **try-with-resources**. Ao encadear, basta fechar o objeto mais externo.
- Use **buffer** ao ler ou gravar muitos dados pequenos em arquivos binários.
- Leia os dados **na mesma ordem e com os mesmos tipos** em que foram gravados.
- Escolha o tipo de arquivo pela necessidade: **texto** quando o conteúdo precisa ser lido por pessoas ou por outros programas; **binário** quando o desempenho e o tamanho importam.
- Em classes serializáveis, declare o `serialVersionUID` e marque como `transient` o que não deve ser gravado.
- Desserialize apenas arquivos de origem confiável.

