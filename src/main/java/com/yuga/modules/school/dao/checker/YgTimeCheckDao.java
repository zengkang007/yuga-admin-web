/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.dao.checker;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.school.entity.checker.YgTimeCheck;

/**
 * 巡检时间主表DAO接口
 * @author 曾康
 * @version 2016-10-26
 */
@MyBatisDao
public interface YgTimeCheckDao extends CrudDao<YgTimeCheck> {
	
}