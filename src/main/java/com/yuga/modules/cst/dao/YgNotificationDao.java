/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.dao;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.cst.entity.YgNotification;

/**
 * NotificationDAO接口
 * @author 曾康
 * @version 2017-02-16
 */
@MyBatisDao
public interface YgNotificationDao extends CrudDao<YgNotification> {
	
}