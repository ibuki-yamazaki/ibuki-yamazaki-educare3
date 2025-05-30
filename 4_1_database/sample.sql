#1問題
INSERT INTO smtable01 (id, name, department)
VALUES (1615, '山田', '総務部');
#2
SELECT * FROM smtable01;
#3
UPDATE smtable01
SET department = '開発部';

#4
DELETE FROM smtable01;
#5
INSERT INTO smtable01 (id, name, department) VALUES
(1612, '小波', '総務部'),
(1610, '伊井田', '開発部'),
(1609, '田中', '営業部'),
(1622, '園田', '開発部');
#6
UPDATE smtable01
SET department = '営業部'
WHERE id = 1607;

#7
SELECT * FROM smtable01
WHERE name LIKE '%木%';
#8
UPDATE smtable01
SET department = (
    SELECT department FROM smtable01 WHERE name = '大原'
);

#9
INSERT INTO smtable01 (id, name, department)
SELECT id, name, department FROM smtable02;

#10

DELETE FROM smtable01
WHERE id IN (
    SELECT id FROM smtable01
    ORDER BY name ASC
    LIMIT 4
);


