# **Golden Raspberry Awards**

Este projeto é uma aplicação Spring Boot que carrega informações sobre os filmes que ganharam o prêmio Golden Raspberry Awards.

## Pré-requisitos

Java 11 ou superior

Maven 3.6.0 ou superior

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
>Se você alterar o nome ou o conteúdo do arquivo CSV, certifique-se de atualizar a configuração `csv.file-path` para refletir o nome correto do arquivo.

## Executando os Testes de Integração

Os testes de integração são configurados para usar um arquivo CSV de teste (`movielist-test.csv`). Para executar os testes, use o Maven:

```bash
mvn test
```

## Estrutura do Projeto

- `src/main/java`: Contém o código fonte da aplicação.
- `src/test/java`: Contém os testes unitários e de integração.
- `src/main/resources`: Contém os recursos estáticos como arquivos CSV.
