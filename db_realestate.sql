/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100109
Source Host           : localhost:3306
Source Database       : db_realestate

Target Server Type    : MYSQL
Target Server Version : 100109
File Encoding         : 65001

Date: 2016-01-06 09:25:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_categories
-- ----------------------------
DROP TABLE IF EXISTS `tbl_categories`;
CREATE TABLE `tbl_categories` (
  `fld_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_category_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fld_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_categories
-- ----------------------------
INSERT INTO `tbl_categories` VALUES ('1', 'Nhà ngoài mặt phố');
INSERT INTO `tbl_categories` VALUES ('2', 'Nhà trong hẻm');
INSERT INTO `tbl_categories` VALUES ('3', 'Nhà riêng');
INSERT INTO `tbl_categories` VALUES ('4', 'Biệt thự, nhà liền kề');
INSERT INTO `tbl_categories` VALUES ('5', 'Căn hộ chung cư');
INSERT INTO `tbl_categories` VALUES ('6', 'Văn phòng');
INSERT INTO `tbl_categories` VALUES ('7', 'Đất thổ cư, đất ở');
INSERT INTO `tbl_categories` VALUES ('8', 'Đất nền, đất liền kề, dự án');
INSERT INTO `tbl_categories` VALUES ('9', 'Đất nông, lâm nghiệp, trang trại');
INSERT INTO `tbl_categories` VALUES ('10', 'Mặt bằng, đất');
INSERT INTO `tbl_categories` VALUES ('11', 'Nhà trọ, phòng trọ');
INSERT INTO `tbl_categories` VALUES ('12', 'Nhà hàng, khách sạn');
INSERT INTO `tbl_categories` VALUES ('13', 'Shop, kiot, quán');
INSERT INTO `tbl_categories` VALUES ('14', 'Kho, xưởng');
INSERT INTO `tbl_categories` VALUES ('15', 'Các loại bất động sản khác');
INSERT INTO `tbl_categories` VALUES ('16', 'Nhà đất chính chủ');
INSERT INTO `tbl_categories` VALUES ('17', 'Nhà đất dịch vụ');
INSERT INTO `tbl_categories` VALUES ('18', 'Nhà trọ');

-- ----------------------------
-- Table structure for tbl_groups
-- ----------------------------
DROP TABLE IF EXISTS `tbl_groups`;
CREATE TABLE `tbl_groups` (
  `fld_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_method_id` int(11) DEFAULT NULL,
  `fld_category_id` int(11) DEFAULT NULL,
  `fld_page_id` int(11) DEFAULT NULL,
  `fld_group_url` text COLLATE utf8_unicode_ci,
  `fld_group_title` text COLLATE utf8_unicode_ci,
  `fld_group_param` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`fld_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_groups
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_methods
-- ----------------------------
DROP TABLE IF EXISTS `tbl_methods`;
CREATE TABLE `tbl_methods` (
  `fld_method_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_method_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fld_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_methods
-- ----------------------------
INSERT INTO `tbl_methods` VALUES ('1', 'Cần Bán');
INSERT INTO `tbl_methods` VALUES ('2', 'Cần Mua');
INSERT INTO `tbl_methods` VALUES ('3', 'Cho Thuê');
INSERT INTO `tbl_methods` VALUES ('4', 'Cần Thuê');

-- ----------------------------
-- Table structure for tbl_pages
-- ----------------------------
DROP TABLE IF EXISTS `tbl_pages`;
CREATE TABLE `tbl_pages` (
  `fld_page_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_page_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_page_url` text COLLATE utf8_unicode_ci,
  `fld_page_description` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`fld_page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_pages
-- ----------------------------
INSERT INTO `tbl_pages` VALUES ('1', 'Bất Động Sản', 'http://batdongsan.com.vn', 'Là kênh thông tin số 1 Việt Nam trong lĩnh vực nhà đất, bất động sản. Với lượng thông tin lớn, liên tục được cập nhật cùng cách trình bày chuyên nghiệp, website mang đến những thông tin mới nhất, đầy đủ nhất về thị trường nhà đất.\r\nKhông chỉ cung cấp tin tức về buôn bán bất động sản, website cũng tư vấn cho các bạn những bước cơ bản trong xây dựng, thiết kế nhà cửa, cách sắp xếp nội thất trong gia đình để phù hợp với phong thủy.\r\nCung cấp danh bạ các nhà môi giới nhà đất, doanh nghiệp, nhà cung cấp sản phẩm, dịch vụ cũng như tư vấn, trả lời các câu hỏi thắc mắc của bạn về bất động sản và các lĩnh vực liên quan.');
INSERT INTO `tbl_pages` VALUES ('2', 'Mua Bán Nhà Đất', 'http://www.muabannhadat.vn', 'Mua Bán Nhà Đất là một đối tác cung cấp những giải pháp tiếp thị trực tuyến dành riêng cho tiếp thị và môi giới bất động sản.\r\nDành cho các chủ đầu tư, sàn giao dịch, môi giới cá nhân và khu vực, những người đang hoạt động trong lĩnh vực bất động sản. Cập nhật nhanh, thông tin phong phú, chi tiết về nhà đất, dự án, doanh nghiệp, kiến thức phong thủy, bản đồ nhà đất…\r\nChuyên mục trang web được phân chia cụ thể, rõ ràng, hệ thống tìm kiếm chi tiết với đầy đủ các tiêu chí (giá cả, hướng nhà, pháp lý,….) thuận tiện cho người dùng.');
INSERT INTO `tbl_pages` VALUES ('3', 'Alo Nhà Đất', 'http://alonhadat.com.vn', 'Một trong những trang rao vặt bất động sản nổi tiếng hiện nay. Có đầy đủ các tin từ mua bán, san nhượng và các thông tin về nhà dất, kinh nghiệm, dự án ở khắp Việt Nam.');
INSERT INTO `tbl_pages` VALUES ('4', 'Đô Thị', 'http://dothi.net', 'Là kênh tin nhanh về bất động sản, Dothi.net tôi cam kết mang đến cho khách hành những thông tin nhanh nhất và chính xác nhất về thị trường bất động sản trong nước và quốc tế. Thông qua Dothi.net, cùng sự trợ giúp của các công nghệ tìm kiếm nâng cao, người dùng có thể tìm kiếm tất cả thông tin về nhà đất mua – bán, thuê – cho thuê tại hầu khắp các tỉnh thành trên cả nước; các thông tin cập nhật hàng giờ về thị trường bất động sản; danh sách chi tiết dự án hay những tư vấn về phong thủy và luật từ các chuyên gia trong lĩnh vực này…');
INSERT INTO `tbl_pages` VALUES ('5', 'Nhà Đất 24h', 'http://nhadat24h.net', 'Sàn giao dịch bất động sản trực tuyến, chuyên cập nhật và quảng cáo những tin tức mới nhất vè bất động sản.\r\nWebsite hỗ trợ khách hàng muốn tìm hiểu thông tin trực tuyến về bất động sản hoặc có nhu cầu mua bán, thuê, cho thuê hay có các hoạt động khác liên quan đến bất động sản.');
INSERT INTO `tbl_pages` VALUES ('6', '123 Nhà Đất', 'http://123nhadat.vn', 'Kênh thông tin bất động sản, hướng đến những khách hàng có nhu cầu thực sự về thông tin, giao dịch bất động sản.\r\nCung cấp thông tin thị trường bất động sản, đăng tin mua bán, cho thuê nhà đất miễn phí. Người dùng có thể tìm kiếm nhà, đất, căn hộ theo từng con đường, từng chung cư cao ốc.');
INSERT INTO `tbl_pages` VALUES ('7', 'Cafe Land', 'http://cafeland.vn', 'Trang website trực tuyến được thiết kế cho phép đăng tải các thông tin liên quan đến lĩnh vực bất động sản.\r\nKênh thông tin giao dịch bất động sản dành cho nhà đầu tư và bạn đọc. Các danh mục được phân chia hợp lý, chi tiết, tất cả các tài sản được đăng lên đều thông qua hệ thống kiểm duyệt với thông tin cụ thể, rõ ràng.');
INSERT INTO `tbl_pages` VALUES ('9', 'Định Giá Nhà Đất', 'http://dinhgianhadat.vn', 'Phần mềm định giá bất động sản trực tuyến giúp các bên có thể so sánh và quyết định giá trị giao dịch một cách tốt hơn, công bằng hơn.\r\nCung cấp tin tức thị trường bất động sản, mua bán nhà đất, xem quy hoạch. Cung cấp phần mềm định giá trực tuyến miễn phí. Người dùng chỉ cần nhập các thông tin cần thiết, phần mềm sẽ tự động phân tích từ những BĐS đang giao dịch trên thị trường để cho kết quả định giá nhanh chóng.');
INSERT INTO `tbl_pages` VALUES ('10', 'Kênh Bất Động Sản', 'http://kenhbds.vn', 'Website Kenhbds.vn – Kênh mua bán, cho thuê, cần thuê bất động sản uy tín hàng đầu Việt Nam!\r\nĐáp ứng nhu cầu chia sẻ và tìm kiếm thông tin về thị trường nhà đất, Kenhbds.vn tự hào với vai trò cầu nối đảm bảo tương tác hiệu quả nhất giữa người bán với người mua, người cho thuê và cần thuê, người có nhu cầu tìm kiếm và nhà cung cấp sản phẩm, dịch vụ.');
INSERT INTO `tbl_pages` VALUES ('11', 'Nhà Số', 'http://nhaso.vn/ha-noi', 'Một trong những kênh thông tin mua bán Bất động sản, cung cấp thông tin bất động sản minh bạch và chính xác về bất động sản.\r\nKênh thông tin mua bán Bất động Sản tại các tỉnh và thành phố lớn của Việt Nam. Giao diện thân thiện, dễ sử dụng. Nội dung thông tin có thể gồm văn bản, hình ảnh và video với chất lượng âm thanh, hình ảnh tốt.');

-- ----------------------------
-- Table structure for tbl_properties
-- ----------------------------
DROP TABLE IF EXISTS `tbl_properties`;
CREATE TABLE `tbl_properties` (
  `fld_property_id` int(11) NOT NULL AUTO_INCREMENT,
  `fld_group_id` int(11) DEFAULT NULL,
  `fld_property_url` text COLLATE utf8_unicode_ci,
  `fld_property_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_price` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_square` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_project` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_description` text COLLATE utf8_unicode_ci,
  `fld_property_contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fld_property_crawled` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fld_property_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_properties
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_users
-- ----------------------------
DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `tbl_users` (
  `fld_username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fld_password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fld_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fld_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_users
-- ----------------------------
INSERT INTO `tbl_users` VALUES ('duongnt', '07121994', null);
INSERT INTO `tbl_users` VALUES ('hienbtt', '01031993', null);

-- ----------------------------
-- Procedure structure for func_delete_categories
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_delete_categories`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_delete_categories`(IN id INT)
BEGIN
    DELETE 
    FROM tbl_categories
    WHERE tbl_categories.fld_category_id = id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_delete_groups
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_delete_groups`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_delete_groups`(IN id INT)
BEGIN
	DELETE FROM tbl_groups
	WHERE tbl_groups.fld_group_id = id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_delete_methods
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_delete_methods`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_delete_methods`(IN id INT)
BEGIN
	DELETE FROM tbl_methods
	WHERE tbl_methods.fld_method_id = id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_delete_pages
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_delete_pages`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_delete_pages`(IN id INT)
BEGIN
	DELETE FROM tbl_pages
	WHERE tbl_pages.fld_page_id = id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_delete_properties
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_delete_properties`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_delete_properties`(IN id INT)
BEGIN
	DELETE FROM tbl_properties 		
	WHERE tbl_properties.fld_property_id = id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_delete_users
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_delete_users`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_delete_users`(IN param_username VARCHAR(255), IN param_password VARCHAR(255))
BEGIN
	DELETE FROM tbl_users
	WHERE tbl_users.fld_username = param_username
	AND tbl_users.fld_password = param_password;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_insert_categories
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_insert_categories`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_insert_categories`(IN param_name VARCHAR(255))
BEGIN
	INSERT INTO tbl_categories(fld_category_name) 
	VALUES(param_name);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_insert_groups
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_insert_groups`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_insert_groups`(
 IN pr_method_id INT,
 IN	pr_category_id INT,
 IN	pr_page_id INT,
 IN	pr_group_url TEXT,
 IN pr_group_title TEXT,
 IN pr_group_param TEXT)
BEGIN
 INSERT INTO tbl_groups(
  fld_method_id,
  fld_category_id,
  fld_page_id,
  fld_group_url,
  fld_group_title,
  fld_group_param)
 VALUES(
  pr_method_id,
  pr_category_id, 
  pr_page_id,
  pr_group_url, 					
  pr_group_title,
  pr_group_param);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_insert_methods
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_insert_methods`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_insert_methods`(
	IN new_method_name VARCHAR(255))
BEGIN
	INSERT INTO tbl_methods(fld_method_name)
	VALUES(new_method_name);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_insert_pages
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_insert_pages`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_insert_pages`(
	IN pr_page_name VARCHAR(255),
	IN pr_page_url TEXT,
	IN pr_page_description TEXT)
BEGIN
	INSERT INTO	tbl_pages(
		fld_page_name, 
		fld_page_url, 
		fld_page_description)
	VALUES(
		pr_page_name, 
		pr_page_url, 
		pr_page_description);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_insert_properties
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_insert_properties`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_insert_properties`(
	IN pr_group_id INT,
	IN pr_property_url TEXT,
	IN pr_property_title TEXT,	
	IN pr_property_price VARCHAR(255),
	IN pr_property_square VARCHAR(255),
	IN pr_property_project VARCHAR(255),
	IN pr_property_address TEXT,
	IN pr_property_description TEXT,
	IN pr_property_contact VARCHAR(255),
	IN pr_property_mobile VARCHAR(255),
	IN pr_property_email VARCHAR(255))
BEGIN
	INSERT INTO tbl_properties(
		fld_group_id,
		fld_property_url,
		fld_property_title,
		fld_property_price,
		fld_property_square,
		fld_property_project,
		fld_property_address,
		fld_property_description,
		fld_property_contact,
		fld_property_mobile,
		fld_property_email,
		fld_property_crawled) 
	VALUES(
		pr_group_id,
		pr_property_url,
		pr_property_title,
		pr_property_price,
		pr_property_square,
		pr_property_project,
		pr_property_address,
		pr_property_description,
		pr_property_contact,
		pr_property_mobile,
		pr_property_email,
		NOW());
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_insert_users
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_insert_users`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_insert_users`(
	IN 	pr_username VARCHAR(255),
	IN	pr_password VARCHAR(255),
	IN 	pr_email VARCHAR(255))
BEGIN
	INSERT INTO	tbl_users(
		fld_username, 
		fld_password, 
		fld_email)
	VALUES(
		pr_username, 
		pr_password, 
		pr_email);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_categories
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_categories`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_categories`(IN pr_key VARCHAR(255))
BEGIN
    SELECT * 
    FROM tbl_categories 
    WHERE fld_category_name LIKE pr_key;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_groups
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_groups`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_groups`(IN pr_key VARCHAR(255))
BEGIN
    SELECT * 
    FROM tbl_groups 
    WHERE
        fld_group_url LIKE pr_key OR 
        fld_group_title LIKE pr_key OR 
        fld_group_param LIKE pr_key;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_groups_by_category_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_groups_by_category_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_groups_by_category_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_groups
	WHERE fld_category_id = param_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_groups_by_group_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_groups_by_group_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_groups_by_group_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_groups
	WHERE fld_group_id = param_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_groups_by_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_groups_by_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_groups_by_id`(
	IN param_method_id INT,
	IN param_category_id INT,
	IN param_page_id INT)
BEGIN
	SELECT * 
	FROM tbl_groups
	WHERE fld_method_id = param_method_id
	AND fld_category_id = param_category_id
	AND fld_page_id = param_page_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_groups_by_method_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_groups_by_method_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_groups_by_method_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_groups
	WHERE fld_method_id = param_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_groups_by_page_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_groups_by_page_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_groups_by_page_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_groups
	WHERE fld_page_id = param_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_methods
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_methods`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_methods`(IN pr_key VARCHAR(255))
BEGIN
	SELECT *
	FROM tbl_methods 
	WHERE fld_method_name LIKE pr_key;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_pages
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_pages`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_pages`(IN pr_key VARCHAR(255))
BEGIN
	SELECT	* 
	FROM tbl_pages 
	WHERE fld_page_name LIKE pr_key 
	OR fld_page_url LIKE pr_key 
	OR fld_page_description LIKE pr_key;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_properties
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_properties`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_properties`(IN pr_key VARCHAR(255))
BEGIN
	SELECT * 
	FROM tbl_properties 
	WHERE fld_property_url LIKE pr_key 
		OR fld_property_title LIKE pr_key 
		OR fld_property_square LIKE pr_key 
		OR fld_property_price LIKE pr_key
		OR fld_property_project LIKE pr_key 
		OR fld_property_address LIKE pr_key 
		OR fld_property_description LIKE pr_key 
		OR fld_property_contact LIKE pr_key 
		OR fld_property_mobile LIKE pr_key 
		OR fld_property_email LIKE pr_key;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_properties_by_category_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_properties_by_category_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_properties_by_category_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_properties
	WHERE tbl_properties.fld_group_id = ANY (SELECT tbl_groups.fld_group_id
							FROM tbl_groups
							WHERE tbl_groups.fld_category_id = param_id);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_properties_by_group_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_properties_by_group_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_properties_by_group_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_properties
	WHERE fld_group_id = param_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_properties_by_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_properties_by_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_properties_by_id`(
	IN param_method_id INT,
	IN param_category_id INT,
	IN param_page_id INT
)
BEGIN
	SELECT * 
	FROM tbl_properties
	WHERE fld_group_id = ANY (SELECT tbl_groups.fld_group_id
							FROM tbl_groups
					WHERE tbl_groups.fld_method_id = param_method_id
							AND tbl_groups.fld_category_id = param_category_id
						AND tbl_groups.fld_page_id = param_page_id) ;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_properties_by_method_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_properties_by_method_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_properties_by_method_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_properties
	WHERE tbl_properties.fld_group_id = ANY (SELECT tbl_groups.fld_group_id
							FROM tbl_groups
							WHERE tbl_groups.fld_method_id = param_id);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_properties_by_page_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_properties_by_page_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_properties_by_page_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_properties
	WHERE tbl_properties.fld_group_id = ANY (SELECT tbl_groups.fld_group_id
							FROM tbl_groups
							WHERE tbl_groups.fld_page_id = param_id);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_properties_by_property_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_properties_by_property_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_properties_by_property_id`(IN param_id INT)
BEGIN
	SELECT * 
	FROM tbl_properties
	WHERE fld_property_id = param_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_select_users
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_select_users`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_select_users`(
	IN param_username VARCHAR(255),
	IN param_password VARCHAR(255))
BEGIN
	SELECT * 
	FROM tbl_users 
	WHERE fld_username = param_username
	AND fld_password = param_password;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_update_categories
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_update_categories`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_update_categories`(
	IN pr_category_id 		INT,
	IN pr_category_name		VARCHAR(255))
BEGIN
	UPDATE tbl_categories 
	SET fld_category_name = pr_category_name 
	WHERE fld_category_id = pr_category_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_update_groups
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_update_groups`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_update_groups`(
	IN pr_group_id INT, 
	IN pr_method_id INT,
	IN pr_category_id INT,
	IN pr_page_id INT,
	IN pr_group_url TEXT,
	IN pr_group_title TEXT,
	IN pr_group_param TEXT)
BEGIN
 UPDATE tbl_groups SET 
fld_method_id = pr_method_id, 
fld_category_id = pr_category_id, 
fld_page_id = pr_page_id, 
fld_group_url = pr_group_url, 
fld_group_title = pr_group_title, 
fld_group_param = pr_group_param
 WHERE fld_group_id = pr_group_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_update_methods
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_update_methods`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_update_methods`(
	IN pr_method_id INT,
	IN pr_method_name VARCHAR(255))
BEGIN
	UPDATE tbl_methods
	SET tbl_methods.fld_method_name = pr_method_name 
	WHERE tbl_methods.fld_method_id = pr_method_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_update_pages
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_update_pages`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_update_pages`(
 IN param_page_id INT,
 IN param_page_name VARCHAR(255),
 IN param_page_url TEXT,
 IN param_page_description TEXT)
BEGIN
	UPDATE tbl_pages SET    
	tbl_pages.fld_page_name = param_page_name, 
	tbl_pages.fld_page_url = param_page_url, 
	tbl_pages.fld_page_description = param_page_description
	WHERE  tbl_pages.fld_page_id = param_page_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_update_properties
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_update_properties`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_update_properties`(
	IN pr_property_id INT,
	IN pr_group_id INT,
	IN pr_property_url TEXT,
	IN pr_property_title TEXT,	
	IN pr_property_price VARCHAR(255),
	IN pr_property_square VARCHAR(255),
	IN pr_property_project VARCHAR(255),
	IN pr_property_address TEXT,
	IN pr_property_description TEXT,
	IN pr_property_contact VARCHAR(255),
	IN pr_property_mobile VARCHAR(255),
	IN pr_property_email VARCHAR(255))
BEGIN
    UPDATE tbl_properties SET
	tbl_properties.fld_group_id = pr_group_id,
	tbl_properties.fld_property_url = pr_property_url,
	tbl_properties.fld_property_title = pr_property_title,
	tbl_properties.fld_property_price = pr_property_price,
	tbl_properties.fld_property_square = pr_property_square,
	tbl_properties.fld_property_project = pr_property_project,
	tbl_properties.fld_property_address = pr_property_address,
	tbl_properties.fld_property_description = pr_property_description,
	tbl_properties.fld_property_contact = pr_property_contact,
	tbl_properties.fld_property_mobile = pr_property_mobile,
	tbl_properties.fld_property_email = pr_property_email,
	tbl_properties.fld_property_crawled = NOW()					
    WHERE tbl_properties.fld_property_id = pr_property_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for func_update_users
-- ----------------------------
DROP PROCEDURE IF EXISTS `func_update_users`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `func_update_users`(
    IN param_username VARCHAR(255),
    IN param_password VARCHAR(255),
    IN param_email    VARCHAR(255))
BEGIN
    UPDATE tbl_users
    SET    tbl_users.fld_password = param_password,
           tbl_users.fld_email = param_email
    WHERE  tbl_users.fld_username = param_username;
END
;;
DELIMITER ;
