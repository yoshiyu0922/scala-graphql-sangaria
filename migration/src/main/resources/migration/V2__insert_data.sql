-- ユーザ　password:test
INSERT INTO users(id, front_user_id, name, password, created_at, updated_at, is_deleted, deleted_at) VALUES (53341711000000, 'yoshiyu0922', '吉川 侑希', '$2a$10$0K2iHNHE3HzH8izUOGQcseBAO99ka8kAYLE2JQivkIkPEipms7Cim', now(), now(), false, null);

-- 口座
INSERT INTO accounts(id, user_id, name, balance, sort_index, created_at, updated_at, is_deleted, deleted_at) VALUES (44941947000001, 53341711000000, '財布', 0, 1, now(), now(), false, null);
INSERT INTO accounts(id, user_id, name, balance, sort_index, created_at, updated_at, is_deleted, deleted_at) VALUES (44941947000002, 53341711000000, '住信SBIネット銀行', 0, 2, now(), now(), false, null);
INSERT INTO accounts(id, user_id, name, balance, sort_index, created_at, updated_at, is_deleted, deleted_at) VALUES (44941947000006, 53341711000000, '小規模企業共済', 0, 3, now(), now(), false, null);

-- カテゴリ
INSERT INTO categories (id, user_id, name, created_at, updated_at, is_deleted, deleted_at) VALUES
(53890137000001, 53341711000000, 'インターネット', now(), now(), false, now()),
(53890137000002, 53341711000000, '家賃', now(), now(), false, now()),
(53890137000003, 53341711000000, '電気代', now(), now(), false, now()),
(53890137000004, 53341711000000, '水道代', now(), now(), false, now()),
(53890137000005, 53341711000000, 'ガス代', now(), now(), false, now()),
(53890137000006, 53341711000000, '美容代', now(), now(), false, now()),
(53890137000015, 53341711000000, '買い物', now(), now(), false, now()),
(53890137000016, 53341711000000, '外食', now(), now(), false, now()),
(53890137000017, 53341711000000, 'カード利用料', now(), now(), false, now()),
(53890137000018, 53341711000000, '電子マネー', now(), now(), false, now()),
(53890137000019, 53341711000000, '日用品', now(), now(), false, now()),
(53890137000020, 53341711000000, '交際費', now(), now(), false, now()),
(53890137000021, 53341711000000, '外食・飲み会', now(), now(), false, now()),
(53890137000022, 53341711000000, '書籍代', now(), now(), false, now()),
(53890137000023, 53341711000000, 'セミナー', now(), now(), false, now()),
(53890137000024, 53341711000000, 'スポーツ', now(), now(), false, now()),
(53890137000025, 53341711000000, '医療費', now(), now(), false, now()),
(53890137000026, 53341711000000, '税金', now(), now(), false, now()),
(53890137000028, 53341711000000, '保険', now(), now(), false, now()),
(53890137000029, 53341711000000, '雑費', now(), now(), false, now());
