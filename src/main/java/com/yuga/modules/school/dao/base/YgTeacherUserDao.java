/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.dao.base;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.school.entity.base.YgTeacherUser;

/**
 * 教师信息DAO接口
 * @author 曾康
 * @version 2016-10-23
 */
@MyBatisDao
public interface YgTeacherUserDao extends CrudDao<YgTeacherUser> {
	
}