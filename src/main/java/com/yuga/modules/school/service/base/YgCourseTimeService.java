/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgCourseTime;
import com.yuga.modules.school.dao.base.YgCourseTimeDao;

/**
 * 课时维护Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgCourseTimeService extends CrudService<YgCourseTimeDao, YgCourseTime> {

	public YgCourseTime get(String id) {
		return super.get(id);
	}
	
	public List<YgCourseTime> findList(YgCourseTime ygCourseTime) {
		return super.findList(ygCourseTime);
	}
	
	public Page<YgCourseTime> findPage(Page<YgCourseTime> page, YgCourseTime ygCourseTime) {
		return super.findPage(page, ygCourseTime);
	}
	
	@Transactional(readOnly = false)
	public void save(YgCourseTime ygCourseTime) {
		super.save(ygCourseTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgCourseTime ygCourseTime) {
		super.delete(ygCourseTime);
	}
	
}