# bookAPI

## Pré-requisitos

[Postgres 9.6](https://www.postgresql.org/) (Caso não seja possível instalar essa versão, acredito que irá funcionar no 10 também)

[Java 8](https://www.java.com)

[Maven](https://maven.apache.org/)

## Instalação

### Configurando o banco
Execute a migration [__00__.sql](https://github.com/enriconavarro/bookAPI/blob/master/backend/src/main/resources/db/__00__.sql) no postgres.

### Gerando versão da aplicação
Na pasta raiz do projeto execute o comando: `mvn -f backend/ clean install`

<b>ATENÇÃO: Esse comando irá gerar uma versão da aplicação dentro de /bookAPI/backend/target com o nome de bookAPI-0.0.1-SNAPSHOT.jar</b>

### Executando a aplicação
Dentro da pasta <b>/bookAPI/backend/target</b> execute o seguinte comando: `java -jar bookAPI-0.0.1-SNAPSHOT.jar`
