# scala-graphql-sangaria

## 概要

GraphQLアプリの雛形

## Environments

* Scala 2.12.8
* PlayFramework 2.7.2
* sbt 1.2.8
* scalikejdbc 3.2
* jwt-play-json 2.1.0
* sangria 1.4.2 (GraphQL library)
* Docker

## 環境構築

### 1. docker-compose up

```shell
$ cd docker

$ docker-compose up
```

### 2. migration

```shell
$ cd migration

$ sbt flywayMigrate
```

### 3. アプリを起動

```shell
$ sbt run
```

### call API

- [GraphiQL](https://github.com/graphql/graphiql/tree/main/packages/graphiql#readme)をインストールする
- 認証は以下を呼べばトークンを取得できる
```
mutation auth {
  auth(
    userId: "yoshiyu0922"
    password: "test"
  ) {
    token
  }
}
```


## Other Commands

### create jar

```sbtshell
sbt assembly
```

### create scala-doc

```sbtshell
sbt doc
``

### [scalafmt](https://scalameta.org/scalafmt/)

```sbtshell
sbt scalafmt
```
