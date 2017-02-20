/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgStudentStatus;
import com.yuga.modules.school.dao.base.YgStudentStatusDao;

/**
 * 学生学习情况Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgStudentStatusService extends CrudService<YgStudentStatusDao, YgStudentStatus> {

	public YgStudentStatus get(String id) {
		return super.get(id);
	}
	
	public List<YgStudentStatus> findList(YgStudentStatus ygStudentStatus) {
		return super.findList(ygStudentStatus);
	}
	
	public Page<YgStudentStatus> findPage(Page<YgStudentStatus> page, YgStudentStatus ygStudentStatus) {
		return super.findPage(page, ygStudentStatus);
	}
	
	@Transactional(readOnly = false)
	public void save(YgStudentStatus ygStudentStatus) {
		super.save(ygStudentStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgStudentStatus ygStudentStatus) {
		super.delete(ygStudentStatus);
	}
	
}