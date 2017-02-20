/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.checker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.checker.ClassCheck;
import com.yuga.modules.school.dao.checker.ClassCheckDao;

/**
 * 巡查信息Service
 * @author 曾康
 * @version 2016-10-17
 */
@Service
@Transactional(readOnly = true)
public class ClassCheckService extends CrudService<ClassCheckDao, ClassCheck> {

	public ClassCheck get(String id) {
		return super.get(id);
	}
	
	public List<ClassCheck> findList(ClassCheck classCheck) {
		return super.findList(classCheck);
	}
	
	public Page<ClassCheck> findPage(Page<ClassCheck> page, ClassCheck classCheck) {
		return super.findPage(page, classCheck);
	}
	
	@Transactional(readOnly = false)
	public void save(ClassCheck classCheck) {
		super.save(classCheck);
	}
	
	@Transactional(readOnly = false)
	public void delete(ClassCheck classCheck) {
		super.delete(classCheck);
	}
	
}