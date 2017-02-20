/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgClass;
import com.yuga.modules.school.dao.base.YgClassDao;

/**
 * 班级信息Service
 * @author 曾康
 * @version 2016-10-17
 */
@Service
@Transactional(readOnly = true)
public class YgClassService extends CrudService<YgClassDao, YgClass> {

	public YgClass get(String id) {
		return super.get(id);
	}
	
	public List<YgClass> findList(YgClass ygClass) {
		return super.findList(ygClass);
	}
	
	public Page<YgClass> findPage(Page<YgClass> page, YgClass ygClass) {
		return super.findPage(page, ygClass);
	}
	
	@Transactional(readOnly = false)
	public void save(YgClass ygClass) {
		super.save(ygClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgClass ygClass) {
		super.delete(ygClass);
	}
	
}