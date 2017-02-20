/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.cst.entity.YgNotification;
import com.yuga.modules.cst.dao.YgNotificationDao;

/**
 * NotificationService
 * @author 曾康
 * @version 2017-02-16
 */
@Service
@Transactional(readOnly = true)
public class YgNotificationService extends CrudService<YgNotificationDao, YgNotification> {

	public YgNotification get(String id) {
		return super.get(id);
	}
	
	public List<YgNotification> findList(YgNotification ygNotification) {
		return super.findList(ygNotification);
	}
	
	public Page<YgNotification> findPage(Page<YgNotification> page, YgNotification ygNotification) {
		return super.findPage(page, ygNotification);
	}
	
	@Transactional(readOnly = false)
	public void save(YgNotification ygNotification) {
		super.save(ygNotification);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgNotification ygNotification) {
		super.delete(ygNotification);
	}
	
}