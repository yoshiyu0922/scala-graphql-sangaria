## docker構築手順

###  `docker`フォルダで以下のコマンドを実行する
```
$ cd kakeibooo/docker
$ docker-compose up -d
```

### ローカルからDBに接続できるか確認する場合
```
mysql -u root -proot -h 127.0.0.1 --port=3306 -D kakeibooo
SHOW TABLES FROM kakeibooo;
-> テーブル一覧が出ればOK
```

### その他

- DBクライアントから接続するためにはユーザの認証プラグインを変更する必要がある
- my.cnfで設定済み

