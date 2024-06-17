# **Golden Raspberry Awards**

Este projeto é uma aplicação Spring Boot que carrega informações sobre os filmes que ganharam o prêmio Golden Raspberry Awards.

## Pré-requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Baixando e Instalando o JDK

### Baixando o JDK 11

Você pode baixar o JDK 11 de várias fontes. Aqui estão algumas opções:

- [AdoptOpenJDK (agora Adoptium)](https://adoptium.net/temurin/releases/?version=11)
- [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Amazon Corretto](https://aws.amazon.com/pt/corretto/)
- [OpenJDK](https://openjdk.java.net/install/)

### Passo a Passo para Instalar o JDK 11

1. **Baixar o JDK:**

   Vá para um dos links fornecidos acima e baixe o instalador apropriado para o seu sistema operacional.

2. **Instalar o JDK:**

   Siga as instruções de instalação fornecidas no site de download. Normalmente, isso envolve executar o instalador e seguir os prompts na tela.

3. **Configurar a Variável de Ambiente JAVA_HOME:**

   Após a instalação, configure a variável de ambiente JAVA_HOME para apontar para o diretório onde o JDK foi instalado.

   - **No Windows:**
     - Abra as configurações do sistema e vá até Configurações Avançadas do Sistema.
     - Clique no botão Variáveis de Ambiente.
     - Em Variáveis do sistema, clique em Novo e adicione JAVA_HOME com o caminho para o diretório do JDK (por exemplo, `C:\Program Files\AdoptOpenJDK\jdk-11.0.11.9-hotspot`).
     - Edite a variável Path e adicione `%JAVA_HOME%\bin` ao final.

4. **Verificar a Instalação:**

   Abra um novo terminal ou prompt de comando e digite:

   ```sh
   java -version
   ```

   Você deve ver a versão do JDK que você instalou, por exemplo:

   ```sh
   openjdk version "11.0.11" 2021-04-20 LTS
   OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.11+9)
   OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.11+9, mixed mode)
   ```

## Instalando o Maven

### Passo 1: Baixar o Maven

Vá para a [página de download do Maven](https://maven.apache.org/download.cgi).
Baixe o arquivo binário ZIP (por exemplo, `apache-maven-3.8.4-bin.zip`).

### Passo 2: Extrair o Arquivo

Extraia o conteúdo do arquivo ZIP em um diretório de sua escolha (por exemplo, `C:\apache-maven-3.8.4`).

### Passo 3: Configurar as Variáveis de Ambiente

1. **Adicionar MAVEN_HOME:**

   - Clique com o botão direito em "Este Computador" ou "Meu Computador" e selecione "Propriedades".
   - Clique em "Configurações avançadas do sistema".
   - Clique em "Variáveis de ambiente".
   - Em "Variáveis do sistema", clique em "Novo".
   - Nome da variável: `MAVEN_HOME`
   - Valor da variável: `C:\apache-maven-3.8.4`

2. **Adicionar o Diretório bin do Maven ao PATH:**

   - Ainda em "Variáveis de ambiente", encontre a variável `Path` na lista de "Variáveis do sistema" e clique em "Editar".
   - Clique em "Novo" e adicione o caminho `C:\apache-maven-3.8.4\bin`.
   - Clique em "OK" em todas as janelas para salvar as configurações.

### Passo 4: Verificar a Instalação

Abra um novo terminal (Prompt de Comando ou PowerShell).

Execute o comando:

```sh
mvn -version
```

Você deve ver algo como:

```sh
Apache Maven 3.8.4 (ce064eaa5b5c38a790dd3a2e8d3c07b085a5cd56)
Maven home: C:\apache-maven-3.8.4
Java version: 11.0.11, vendor: AdoptOpenJDK, runtime: C:\Program Files\AdoptOpenJDK\jdk-11.0.11.9-hotspot
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

## Configuração do Projeto

Clone o repositório:

```bash
git clone https://github.com/DiogoEller/golden-raspberry-awards.git
cd golden-raspberry-awards
```

Construir o projeto:

```bash
mvn clean install
```

## Executando a Aplicação

Para rodar a aplicação, você pode usar o Maven ou seu IDE preferido.

### Usando Maven

Executar a aplicação:

```bash
mvn spring-boot:run
```

### Usando um IDE (Eclipse, IntelliJ, etc.)

Importar o projeto como um projeto Maven existente e executar a classe `GoldenRaspberryAwardsApplication` como uma aplicação Java.

## Configuração de Arquivo CSV

A aplicação carrega os dados dos filmes a partir de um arquivo CSV localizado em `src/main/resources`. Existem dois arquivos CSV:

- `movielist.csv`: Usado na execução normal da aplicação.
- `movielist-test.csv`: Usado nos testes de integração.

> [!IMPORTANT]
> Para rodar a aplicação principal ou os testes de integração, é necessário alterar manualmente a configuração do arquivo CSV no arquivo `application.properties` para corresponder ao nome do arquivo CSV que você deseja utilizar.

### Executando a Aplicação Principal

Para executar a aplicação principal, certifique-se de que o arquivo `application.properties` está configurado para usar o `movielist.csv`:

```properties
csv.file-path=movielist.csv
```

### Executando os Testes de Integração

Para executar os testes de integração, altere o arquivo `application.properties` para usar o `movielist-test.csv`:

```properties
csv.file-path=movielist-test.csv
```

> [!NOTE]
> Se você alterar o nome ou o conteúdo do arquivo CSV, certifique-se de atualizar a configuração `csv.file-path` para refletir o nome correto do arquivo.

## Executando os Testes de Integração

Os testes de integração são configurados para usar um arquivo CSV de teste (`movielist-test.csv`). Para executar os testes, use o Maven:

```bash
mvn test
```

## Estrutura do Projeto

- `src/main/java`: Contém o código fonte da aplicação.
- `src/test/java`: Contém os testes unitários e de integração.
- `src/main/resources`: Contém os recursos estáticos como arquivos CSV.

## Endpoints Disponíveis

Quando o sistema está sendo executado, você tem à disposição os seguintes endpoints:

### `GET /api/movies`

Retorna uma lista de todos os filmes na base de dados.

**Exemplo de resposta:**

```json
[
    {
        "id": 1,
        "title": "Movie A",
        "year": 2000,
        "studios": "Studio X",
        "producers": "Producer A",
        "winner": true
    },
    {
        "id": 2,
        "title": "Movie B",
        "year": 2005,
        "studios": "Studio Y",
        "producers": "Producer B",
        "winner": false
    },
    {
        "id": 3,
        "title": "Movie C",
        "year": 2010,
        "studios": "Studio Z",
        "producers": "Producer C",
        "winner": false
    }
]
```

### `GET /api/winners`

Retorna uma lista de todos os filmes que ganharam o prêmio.

**Exemplo de resposta:**

```json
[
    {
        "id": 1,
        "title": "Movie A",
        "year": 2000,
        "studios": "Studio X",
        "producers": "Producer A",
        "winner": true
    },
    {
        "id": 2,
        "title": "Movie B",
        "year": 2005,
        "studios": "Studio Y",
        "producers": "Producer B",
        "winner": true
    },
    {
        "id": 3,
        "title": "Movie C",
        "year": 2010,
        "studios": "Studio Z",
        "producers": "Producer C",
        "winner": true
    }
]
```

### `GET /api/producers`

Retorna uma lista de produtores com os intervalos entre os prêmios ganhos.

**Exemplo de resposta:**

```json
{
    "min": [
        {
            "producer": "Producer A",
            "interval": 1,
            "previousWin": 2000,
            "followingWin": 2001
        }
    ],
    "max": [
        {
            "producer": "Producer B",
            "interval": 5,
            "previousWin": 2010,
            "followingWin": 2015
        }
    ]
}
```
