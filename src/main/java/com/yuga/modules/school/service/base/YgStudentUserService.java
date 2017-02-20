/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgStudentUser;
import com.yuga.modules.school.dao.base.YgStudentUserDao;

/**
 * 学生信息Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgStudentUserService extends CrudService<YgStudentUserDao, YgStudentUser> {

	public YgStudentUser get(String id) {
		return super.get(id);
	}
	
	public List<YgStudentUser> findList(YgStudentUser ygStudentUser) {
		return super.findList(ygStudentUser);
	}
	
	public Page<YgStudentUser> findPage(Page<YgStudentUser> page, YgStudentUser ygStudentUser) {
		return super.findPage(page, ygStudentUser);
	}
	
	@Transactional(readOnly = false)
	public void save(YgStudentUser ygStudentUser) {
		super.save(ygStudentUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgStudentUser ygStudentUser) {
		super.delete(ygStudentUser);
	}
	
}