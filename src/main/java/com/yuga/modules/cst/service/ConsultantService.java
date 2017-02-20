/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.cst.entity.Consultant;
import com.yuga.modules.cst.dao.ConsultantDao;

/**
 * ConsultantService
 * @author zengk
 * @version 2017-01-06
 */
@Service
@Transactional(readOnly = true)
public class ConsultantService extends CrudService<ConsultantDao, Consultant> {

	@Autowired
	private ConsultantDao consultantDao;

	public Consultant get(String id) {
		return super.get(id);
	}
	
	public List<Consultant> findList(Consultant consultant) {
		return super.findList(consultant);
	}

	public Page<Consultant> findMyPage(Page<Consultant> page, Consultant entity) {
		entity.setPage(page);
		page.setList(consultantDao.findMyList(entity));
		return page;
	}

	public Page<Consultant> findPage(Page<Consultant> page, Consultant consultant) {
		return super.findPage(page, consultant);
	}
	
	@Transactional(readOnly = false)
	public void save(Consultant consultant) {
		super.save(consultant);
	}
	
	@Transactional(readOnly = false)
	public void delete(Consultant consultant) {
		super.delete(consultant);
	}
	
}