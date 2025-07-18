-- 1. sampledb_taskデータベースの作成

CREATE DATABASE sampledb_task;

-- 2. roleテーブルの作成

CREATE TABLE role (
    role_id INT PRIMARY KEY,
    role_name VARCHAR(50)
);

-- 3. role情報登録

INSERT INTO role (role_id, role_name) VALUES
(1, '管理者'),
(2, '一般');

-- 4. user_infoテーブルの作成

CREATE TABLE user_info (
    user_id SERIAL PRIMARY KEY,
    login_id VARCHAR(50) UNIQUE,
    user_name VARCHAR(50),
    telephone VARCHAR(50),
    password VARCHAR(50),
    role_id INT NOT NULL REFERENCES role(role_id)
);
