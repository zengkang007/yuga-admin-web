/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.CourseSectionTime;
import com.yuga.modules.school.dao.base.CourseSectionTimeDao;

/**
 * 节次时间Service
 * @author 曾康
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class CourseSectionTimeService extends CrudService<CourseSectionTimeDao, CourseSectionTime> {

	public CourseSectionTime get(String id) {
		return super.get(id);
	}
	
	public List<CourseSectionTime> findList(CourseSectionTime courseSectionTime) {
		return super.findList(courseSectionTime);
	}
	
	public Page<CourseSectionTime> findPage(Page<CourseSectionTime> page, CourseSectionTime courseSectionTime) {
		return super.findPage(page, courseSectionTime);
	}
	
	@Transactional(readOnly = false)
	public void save(CourseSectionTime courseSectionTime) {
		super.save(courseSectionTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(CourseSectionTime courseSectionTime) {
		super.delete(courseSectionTime);
	}
	
}