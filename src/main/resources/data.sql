INSERT INTO user_permission (`id`,`user_email`,`permission_level`) VALUES(1,"admin1@test.com",7);
INSERT INTO user_permission (`id`,`user_email`,`permission_level`) VALUES(2,"admin2@test.com",7);
INSERT INTO user_permission (`id`,`user_email`,`permission_level`) VALUES(3,"user1@test.com",4);
INSERT INTO user_permission (`id`,`user_email`,`permission_level`) VALUES(4,"user2@test.com",4);
INSERT INTO group_permission(`id`,`group_name`) VALUES(1,"admin1");
INSERT INTO group_permission(`id`,`group_name`) VALUES(2,"admin2");
INSERT INTO group_permission(`id`,`group_name`) VALUES(3,"user2");
INSERT INTO group_permission(`id`,`group_name`) VALUES(4,"user2");
INSERT INTO group_permission(`id`,`group_name`) VALUES(5,"superAdmin");
INSERT INTO group_permission(`id`,`group_name`) VALUES(6,"normalUser");
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(1, 1);
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(1, 5);
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(2, 2);
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(2, 5);
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(3, 3);
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(3, 6);
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(4, 4);
INSERT INTO `user_groups_permissions`(`user_permission_id`,`group_permission_id`) VALUES(4, 6);


