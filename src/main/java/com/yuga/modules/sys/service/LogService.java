/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.common.utils.DateUtils;
import com.yuga.modules.sys.dao.LogDao;
import com.yuga.modules.sys.entity.Log;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	@Autowired LogDao logDao;
	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}

	public Page<Log> findFundPage(Page<Log> page, Log log) {
		log.setPage(page);
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		page.setList(logDao.findFundList(log));
		return page;
	}

	
}
