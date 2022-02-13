INSERT INTO `sys_user` VALUES (100001, 'MoLiHua', '123456', 1);
INSERT INTO `sec_resource` VALUES (1, 'API', 'ANONYMOUS', '*', '/login', 0, NULL);
INSERT INTO `sec_resource` VALUES (2, 'PAGE', 'AUTHENTICATED', 'GET', '/dashboard', 0, NULL);