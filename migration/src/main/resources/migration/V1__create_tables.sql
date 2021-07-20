-- ユーザー
CREATE TABLE IF NOT EXISTS users(
    id            BIGINT NOT NULL comment 'ユーザーID',
    front_user_id VARCHAR(255) NOT NULL comment 'ユーザーID（画面用）',
    name          VARCHAR(255) NOT NULL comment 'ユーザー名',
    password      VARCHAR(255) NOT NULL comment 'パスワード（sha256）',
    created_at    DATETIME DEFAULT NULL comment '作成日時',
    updated_at    DATETIME DEFAULT NULL comment '更新日時',
    is_deleted    BOOLEAN DEFAULT false comment '削除フラグ',
    deleted_at    DATETIME comment '削除日時',
    PRIMARY KEY (id)
)
comment = 'ユーザー';

-- 口座（ex. 三井住友銀行、小規模企業共済、FXなど）
CREATE TABLE IF NOT EXISTS accounts(
    id         BIGINT NOT NULL comment '口座ID',
    user_id    BIGINT NOT NULL comment 'ユーザーID',
    name       VARCHAR(255) NOT NULL comment '口座名',
    balance    INT DEFAULT 0 comment '残高',
    sort_index INT NOT NULL comment '表示順',
    created_at DATETIME DEFAULT NULL comment '作成日時',
    updated_at DATETIME DEFAULT NULL comment '更新日時',
    is_deleted BOOLEAN DEFAULT false comment '削除フラグ',
    deleted_at DATETIME comment '削除日時',
    PRIMARY KEY (id)
)
comment = '口座（ex. 三井住友銀行、小規模企業共済、NISAなど）';

-- 収支 カテゴリ
CREATE TABLE IF NOT EXISTS categories(
    id          BIGINT NOT NULL comment 'カテゴリID',
    user_id     BIGINT NOT NULL comment 'ユーザーID',
    name        VARCHAR(255) NOT NULL comment 'カテゴリ名',
    created_at  DATETIME DEFAULT NULL comment '作成日時',
    updated_at  DATETIME DEFAULT NULL comment '更新日時',
    is_deleted  BOOLEAN DEFAULT false comment '削除フラグ',
    deleted_at  DATETIME comment '削除日時',
    PRIMARY KEY (id)
)
comment = 'カテゴリ';
