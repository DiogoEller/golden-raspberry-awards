# **Golden Raspberry Awards**

Este projeto é uma aplicação Spring Boot que carrega informações sobre os filmes que ganharam o prêmio Golden Raspberry Awards.

## Pré-requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Instalando o JDK

### Passo 1: Baixar o JDK 11

Você pode baixar o JDK 11 de várias fontes. Aqui estão algumas opções:

- [AdoptOpenJDK (agora Adoptium)](https://adoptium.net/temurin/releases/?version=11)
- [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Amazon Corretto](https://aws.amazon.com/pt/corretto/)
- [OpenJDK](https://openjdk.java.net/install/)

Vá para um dos links fornecidos acima e baixe o instalador apropriado para o seu sistema operacional.

### Passo 2: Instalar o JDK 11

Siga as instruções de instalação fornecidas no site de download. Normalmente, isso envolve executar o instalador e seguir os prompts na tela.

### Passo 3: Configurar as Variáveis de Ambiente

Após a instalação, configure a variável de ambiente JAVA_HOME para apontar para o diretório onde o JDK foi instalado.

1. **Adicionar JAVA_HOME:**

   - Clique com o botão direito em "Este Computador" ou "Meu Computador" e selecione "Propriedades".
   - Clique em "Configurações avançadas do sistema".
   - Clique em "Variáveis de ambiente".
   - Em "Variáveis do sistema", clique em "Novo".
   - Nome da variável: `JAVA_HOME`
   - Valor da variável (por exemplo): `C:\Program Files\AdoptOpenJDK\jdk-11.0.11.9-hotspot`

2. **Adicionar o Diretório bin do Java ao PATH:**

   - Ainda em "Variáveis de ambiente", encontre a variável `Path` na lista de "Variáveis do sistema" e clique em "Editar".
   - Clique em "Novo" e adicione o caminho `%JAVA_HOME%\bin`.
   - Clique em "OK" em todas as janelas para salvar as configurações.

### Passo 4: Verificar a Instalação

Abra um novo terminal (Prompt de Comando ou PowerShell).

Execute o comando:

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
   - Valor da variável (por exemplo): `C:\apache-maven-3.8.4`

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

Importar o projeto como um projeto Maven existente e executar a classe `GoldenRaspberryAwardsApplication.java` como uma aplicação Java.

## Configuração de Arquivo CSV

A aplicação carrega os dados dos filmes a partir de um arquivo CSV localizado em `src/main/resources`. Este arquivo é usado tanto na execução normal da aplicação quanto nos testes de integração.

> [!IMPORTANT]
> O arquivo CSV utilizado originalmente é `movielist.csv`.

### Executando a Aplicação Principal

Para executar a aplicação principal, certifique-se de que o arquivo `application.properties` (em `golden-raspberry-awards\src\main\resources\application.properties`) está configurado para usar o `movielist.csv`:

```properties
csv.file-path=/movielist.csv
```

Se desejar usar um arquivo CSV diferente, basta renomeá-lo e atualizar o caminho no application.properties para corresponder ao novo nome do arquivo.

## Executando os Testes de Integração

Os testes de integração são configurados para usar o arquivo CSV original (`movielist.csv`). Para executar os testes, use o comando Maven:

```bash
mvn test
```

## Estrutura do Projeto

- `src/main/java`: Contém o código fonte da aplicação.
- `src/test/java`: Contém os testes integração.
- `src/main/resources`: Contém o arquivo CSV.

## Endpoint Disponível

Quando o sistema está sendo executado, você tem à disposição o seguinte endpoint:

### `GET /api/producers`

Retorna o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido. Podendo vir mais de um para cada situação.

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

## Testando o Endpoint

Você pode testar o endpoint de várias maneiras, aqui estão algumas opções:

### Usando o Postman

1. Baixe e instale o [Postman](https://www.postman.com/downloads/).
2. Abra o Postman e crie uma nova requisição.
3. Defina o método HTTP como `GET` e insira a URL `http://localhost:8080/api/producers`.
4. Clique em "Send" para enviar a requisição e ver a resposta.

### Usando o Curl

Abra um terminal e execute o seguinte comando:

```sh
curl -X GET http://localhost:8080/api/producers
```

### Usando o Navegador

Abra o seu navegador preferido e insira a URL http://localhost:8080/api/producers. Pressione Enter para ver a resposta.
