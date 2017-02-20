/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgCourse;
import com.yuga.modules.school.dao.base.YgCourseDao;

/**
 * 课程Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgCourseService extends CrudService<YgCourseDao, YgCourse> {

	public YgCourse get(String id) {
		return super.get(id);
	}
	
	public List<YgCourse> findList(YgCourse ygCourse) {
		return super.findList(ygCourse);
	}
	
	public Page<YgCourse> findPage(Page<YgCourse> page, YgCourse ygCourse) {
		return super.findPage(page, ygCourse);
	}
	
	@Transactional(readOnly = false)
	public void save(YgCourse ygCourse) {
		super.save(ygCourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgCourse ygCourse) {
		super.delete(ygCourse);
	}
	
}