
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sec_function`
--

DROP TABLE IF EXISTS `sec_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_function` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `function_code` varchar(100) NOT NULL COMMENT '功能代码',
  `function_name` varchar(100) NOT NULL COMMENT '功能名称',
  `created_lang` varchar(50) NOT NULL COMMENT '语言代码',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_role`
--

DROP TABLE IF EXISTS `sec_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_role` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `role_code` varchar(100) NOT NULL COMMENT '角色代码',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `enable_flag` tinyint(4) NOT NULL COMMENT '启用标志',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_lang` varchar(50) NOT NULL COMMENT '语言代码',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_function_resource_rel`
--

DROP TABLE IF EXISTS `sec_function_resource_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_function_resource_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联功能和资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_resource`
--

DROP TABLE IF EXISTS `sec_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_resource` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `resource_type` varchar(50) NOT NULL COMMENT '资源类型',
  `access_policy` varchar(50) NOT NULL COMMENT '访问策略',
  `access_method` varchar(50) NOT NULL COMMENT '访问方式',
  `resource_path` varchar(1000) NOT NULL COMMENT '资源路径',
  `frozen_flag` tinyint(4) NOT NULL COMMENT '冻结标志',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `created_lang` varchar(50) NOT NULL COMMENT '语言代码',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_permission_resource_rel`
--

DROP TABLE IF EXISTS `sec_permission_resource_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_permission_resource_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联权限和资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_role_function_rel`
--

DROP TABLE IF EXISTS `sec_role_function_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_role_function_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联角色和功能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_permission`
--

DROP TABLE IF EXISTS `sec_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `permission_code` varchar(100) NOT NULL COMMENT '权限代码',
  `permission_name` varchar(100) NOT NULL COMMENT '权限名称',
  `created_lang` varchar(50) NOT NULL COMMENT '语言代码',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_function_permission_rel`
--

DROP TABLE IF EXISTS `sec_function_permission_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_function_permission_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联功能和权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_user_role_rel`
--

DROP TABLE IF EXISTS `sec_user_role_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_user_role_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联用户和角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_menu_template`
--

DROP TABLE IF EXISTS `sec_menu_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_menu_template` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `template_code` varchar(100) NOT NULL COMMENT '模板代码',
  `template_name` varchar(100) NOT NULL COMMENT '模板名称',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_lang` varchar(50) NOT NULL COMMENT '语言代码',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单模板';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_menu_function_rel`
--

DROP TABLE IF EXISTS `sec_menu_function_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_menu_function_rel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `function_id` bigint(20) NOT NULL COMMENT '功能ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联菜单和功能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sec_menu`
--

DROP TABLE IF EXISTS `sec_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `template_id` bigint(20) NOT NULL COMMENT '模板ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `menu_code` varchar(100) NOT NULL COMMENT '菜单代码',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `menu_path` varchar(1000) DEFAULT NULL COMMENT '菜单路径',
  `menu_order` int(11) NOT NULL COMMENT '菜单序号',
  `link_function_id` bigint(20) DEFAULT NULL COMMENT '链接功能ID',
  `link_resource_id` bigint(20) DEFAULT NULL COMMENT '链接资源ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `created_lang` varchar(50) NOT NULL COMMENT '语言代码',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `created_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_updated_by` bigint(20) NOT NULL COMMENT '最后更新人ID',
  `version_number` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-22 11:13:47
