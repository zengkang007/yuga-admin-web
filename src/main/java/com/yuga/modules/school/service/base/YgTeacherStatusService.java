/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgTeacherStatus;
import com.yuga.modules.school.dao.base.YgTeacherStatusDao;

/**
 * 教师教学情况Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgTeacherStatusService extends CrudService<YgTeacherStatusDao, YgTeacherStatus> {

	public YgTeacherStatus get(String id) {
		return super.get(id);
	}
	
	public List<YgTeacherStatus> findList(YgTeacherStatus ygTeacherStatus) {
		return super.findList(ygTeacherStatus);
	}
	
	public Page<YgTeacherStatus> findPage(Page<YgTeacherStatus> page, YgTeacherStatus ygTeacherStatus) {
		return super.findPage(page, ygTeacherStatus);
	}
	
	@Transactional(readOnly = false)
	public void save(YgTeacherStatus ygTeacherStatus) {
		super.save(ygTeacherStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgTeacherStatus ygTeacherStatus) {
		super.delete(ygTeacherStatus);
	}
	
}