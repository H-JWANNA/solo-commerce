-- member (point, created_at, last_modified_at, member_id, email, name, password, phoneNumber, username)
INSERT INTO member
VALUES (100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 'email1@asd.asd', 'user', 'password123', '010-1234-1231',
        'username1'),
       (200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 'email2@asd.asd', 'admin', 'asdasdasdasd', '010-1234-1232',
        'admin'),
       (300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 'email3@asd.asd', 'owner', 'password123', '010-1234-1233',
        'owner');

-- member_roles (member_member_id, roles)
INSERT INTO member_roles
VALUES (1, 'USER'),
       (2, 'ADMIN'),
       (2, 'OWNER'),
       (2, 'USER'),
       (3, 'OWNER'),
       (3, 'USER');

-- product (price, created_at, last_modified_at, product_id, information, product_name)
INSERT INTO product
VALUES (10000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 'INFO', '바삭 돈가스'),
       (12000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 'INFO', '얼큰 부대찌개'),
       (14000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 'INFO', '밀푀유나베'),
       (15000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 'INFO', '매콤 닭볶음탕'),
       (16000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 'INFO', '안동 찜닭'),
       (18000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 'INFO', '불고기 피자'),
       (20000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 'INFO', '미국산 초이스 등심 스테이크');

-- store (created_at, last_modified_at, manager_id, store_id, address, store_name)
INSERT INTO store
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 1, '주소1', '가게1'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2, '주소2', '가게2'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 3, '주소3', '가게3'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 4, '주소4', '가게4'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 5, '주소5', '가게5'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 6, '주소6', '가게6'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 7, '주소7', '가게7'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 8, '주소8', '가게8'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 9, '주소9', '가게9'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 10, '주소10', '가게10'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 11, '주소11', '가게11'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 12, '주소12', '가게12'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 13, '주소13', '가게13'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 14, '주소14', '가게14'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 15, '주소15', '가게15'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 16, '주소16', '가게16'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 17, '주소17', '가게17'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 18, '주소18', '가게18'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 19, '주소19', '가게19'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 20, '주소20', '가게20'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 21, '주소21', '가게21'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 22, '주소22', '가게22'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 23, '주소23', '가게23'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 24, '주소24', '가게24'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 25, '주소25', '가게25'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 26, '주소26', '가게26'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 27, '주소27', '가게27'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 28, '주소28', '가게28'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 29, '주소29', '가게29'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 30, '주소30', '가게30'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 31, '주소31', '가게31'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 32, '주소32', '가게32'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 33, '주소33', '가게33'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 34, '주소34', '가게34'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 35, '주소35', '가게35'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 36, '주소36', '가게36'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 37, '주소37', '가게37'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 38, '주소38', '가게38'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 39, '주소39', '가게39'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 40, '주소40', '가게40'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 41, '주소41', '가게41'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 42, '주소42', '가게42'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 43, '주소43', '가게43'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 44, '주소44', '가게44'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 45, '주소45', '가게45'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 46, '주소46', '가게46'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 47, '주소47', '가게47'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 48, '주소48', '가게48'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 49, '주소49', '가게49'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 50, '주소50', '가게50'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 51, '주소51', '가게51'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 52, '주소52', '가게52'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 53, '주소53', '가게53'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 54, '주소54', '가게54'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 55, '주소55', '가게55'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 56, '주소56', '가게56'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 57, '주소57', '가게57'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 58, '주소58', '가게58'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 59, '주소59', '가게59'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 60, '주소60', '가게60');
