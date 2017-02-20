/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.dao.base;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.school.entity.base.CourseSectionTime;

/**
 * 节次时间DAO接口
 * @author 曾康
 * @version 2016-11-01
 */
@MyBatisDao
public interface CourseSectionTimeDao extends CrudDao<CourseSectionTime> {
	
}