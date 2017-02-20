/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.checker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.dao.checker.YgTimeFieldDao;
import com.yuga.modules.school.entity.checker.YgTimeField;

/**
 * 巡检子表Service
 * @author 曾康
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class YgTimeFieldService extends CrudService<YgTimeFieldDao, YgTimeField> {

	public YgTimeField get(String id) {
		return super.get(id);
	}
	
	public List<YgTimeField> findList(YgTimeField ygTimeField) {
		return super.findList(ygTimeField);
	}
	
	public Page<YgTimeField> findPage(Page<YgTimeField> page, YgTimeField ygTimeField) {
		return super.findPage(page, ygTimeField);
	}
	
	@Transactional(readOnly = false)
	public void save(YgTimeField ygTimeField) {
		super.save(ygTimeField);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgTimeField ygTimeField) {
		super.delete(ygTimeField);
	}
}