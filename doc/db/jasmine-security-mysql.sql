SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sec_function
-- ----------------------------
DROP TABLE IF EXISTS `sec_function`;
CREATE TABLE `sec_function`  (
  `id` bigint(20) NOT NULL,
  `function_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能代码',
  `function_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `lang_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言代码',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_function_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `sec_function_permission_rel`;
CREATE TABLE `sec_function_permission_rel`  (
  `id` bigint(20) NOT NULL,
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关联功能和权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_function_resource_rel
-- ----------------------------
DROP TABLE IF EXISTS `sec_function_resource_rel`;
CREATE TABLE `sec_function_resource_rel`  (
  `id` bigint(20) NOT NULL,
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关联功能和资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_menu`;
CREATE TABLE `sec_menu`  (
  `id` bigint(20) NOT NULL,
  `template_id` bigint(20) NOT NULL COMMENT '模板ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID',
  `menu_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单代码',
  `menu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_order` int(11) NOT NULL COMMENT '菜单序号',
  `link_resource_id` bigint(20) NULL DEFAULT NULL COMMENT '链接资源ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `lang_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言代码',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_menu_function_rel
-- ----------------------------
DROP TABLE IF EXISTS `sec_menu_function_rel`;
CREATE TABLE `sec_menu_function_rel`  (
  `id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关联菜单和功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_menu_template
-- ----------------------------
DROP TABLE IF EXISTS `sec_menu_template`;
CREATE TABLE `sec_menu_template`  (
  `id` bigint(20) NOT NULL,
  `template_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板代码',
  `template_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板名称',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `lang_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言代码',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_permission
-- ----------------------------
DROP TABLE IF EXISTS `sec_permission`;
CREATE TABLE `sec_permission`  (
  `id` bigint(20) NOT NULL,
  `permission_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限代码',
  `permission_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `lang_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言代码',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_permission_resource_rel
-- ----------------------------
DROP TABLE IF EXISTS `sec_permission_resource_rel`;
CREATE TABLE `sec_permission_resource_rel`  (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关联权限和资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_resource
-- ----------------------------
DROP TABLE IF EXISTS `sec_resource`;
CREATE TABLE `sec_resource`  (
  `id` bigint(20) NOT NULL,
  `resource_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型',
  `access_policy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问策略',
  `access_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问方式',
  `resource_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源路径',
  `frozen_flag` tinyint(4) NOT NULL COMMENT '冻结标志',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `lang_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言代码',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role`  (
  `id` bigint(20) NOT NULL,
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色代码',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `enable_flag` tinyint(4) NOT NULL COMMENT '启用标志',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `lang_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言代码',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_role_function_rel
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_function_rel`;
CREATE TABLE `sec_role_function_rel`  (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关联角色和功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sec_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sec_user_role_rel`;
CREATE TABLE `sec_user_role_rel`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp(0) NOT NULL COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关联用户和角色' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
