# fintan-react-todo

本家のサイトはこちらです。<br/>
https://fintan-contents.github.io/spa-restapi-handson/

ここでは、本家のサイトに加えて以下の追加をしています。

* backendのSpringBoot版を追加
* ハンズオンの演習である削除機能を実装

## SpringBoot版の起動方法

### postgreの起動（docker, docker-compose利用）
backend-bootディレクトリで、以下のコマンドを入力する
```
$ docker-compose -f docker/docker-compose.dev.yml up -d
```

### SpringBootの起動
IDEからDemoApplication.javaを実行する。<br/>
または、backend-bootディレクトリで、以下のコマンドを入力する
```
$ mvn spring-boot:run
```
