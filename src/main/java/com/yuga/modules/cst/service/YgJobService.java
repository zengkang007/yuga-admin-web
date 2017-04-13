/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.cst.entity.YgJob;
import com.yuga.modules.cst.dao.YgJobDao;

/**
 * JobService
 * @author zengk
 * @version 2017-03-04
 */
@Service
@Transactional(readOnly = true)
public class YgJobService extends CrudService<YgJobDao, YgJob> {

	public YgJob get(String id) {
		return super.get(id);
	}
	
	public List<YgJob> findList(YgJob ygJob) {
		return super.findList(ygJob);
	}
	
	public Page<YgJob> findPage(Page<YgJob> page, YgJob ygJob) {
		return super.findPage(page, ygJob);
	}
	
	@Transactional(readOnly = false)
	public void save(YgJob ygJob) {
		super.save(ygJob);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgJob ygJob) {
		super.delete(ygJob);
	}
	
}