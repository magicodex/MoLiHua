INSERT INTO `sys_user` VALUES (100001, 'MoLiHua', '123456', 1, '2022-02-16 12:30:00', -1, '2022-02-16 12:30:00', -1, 1);
INSERT INTO `sec_resource` VALUES (1, 'API', 'ANONYMOUS', '*', '/login', 0, NULL, '2022-02-16 12:30:00', -1, '2022-02-16 12:30:00', -1, 1);
INSERT INTO `sec_resource` VALUES (2, 'PAGE', 'AUTHENTICATED', 'GET', '/home', 0, NULL, '2022-02-16 12:30:00', -1, '2022-02-16 12:30:00', -1, 1);
INSERT INTO `sec_resource` VALUES (3, 'PAGE', 'AUTHENTICATED', '*', '/journal/search', 0, NULL, '2022-02-19 12:30:00', -1, '2022-02-19 12:30:00', -1, 1);
INSERT INTO `sec_resource` VALUES (4, 'PAGE', 'AUTHENTICATED', '*', '/journal/edit', 0, NULL, '2022-02-19 12:30:00', -1, '2022-02-19 12:30:00', -1, 1);
INSERT INTO `sec_resource` VALUES (5, 'PAGE', 'AUTHENTICATED', '*', '/journal/detail', 0, NULL, '2022-02-19 12:30:00', -1, '2022-02-19 12:30:00', -1, 1);
INSERT INTO `sec_resource` VALUES (6, 'PAGE', 'AUTHENTICATED', '*', '/journal/save', 0, NULL, '2022-02-19 12:30:00', -1, '2022-02-19 12:30:00', -1, 1);
INSERT INTO `sec_resource` VALUES (7, 'PAGE', 'AUTHENTICATED', '*', '/journal/delete', 0, NULL, '2022-02-19 12:30:00', -1, '2022-02-19 12:30:00', -1, 1);