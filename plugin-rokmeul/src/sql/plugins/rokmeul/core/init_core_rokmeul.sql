
--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'ROKMEUL_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('ROKMEUL_MANAGEMENT','rokmeul.adminFeature.Manage.name',1,'jsp/admin/plugins/rokmeul/ManagePostss.jsp','rokmeul.adminFeature.Manage.description',0,'rokmeul',NULL,NULL,NULL,4);

DELETE FROM core_admin_right WHERE id_right = 'ROKMEUL_CATEGORY_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('ROKMEUL_CATEGORY_MANAGEMENT','rokmeul.adminFeature.Manage.category.name',1,'jsp/admin/plugins/rokmeul/ManageCategory.jsp','rokmeul.adminFeature.Manage.category.description',0,'rokmeul',NULL,NULL,NULL,4);

--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'ROKMEUL_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('ROKMEUL_MANAGEMENT',1);

DELETE FROM core_user_right WHERE id_right = 'ROKMEUL_CATEGORY_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('ROKMEUL_CATEGORY_MANAGEMENT',1);

