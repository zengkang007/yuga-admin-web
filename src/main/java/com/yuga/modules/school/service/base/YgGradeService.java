/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.dao.base.YgGradeDao;
import com.yuga.modules.school.entity.base.YgGrade;

/**
 * 年级级别Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgGradeService extends CrudService<YgGradeDao, YgGrade> {

	public YgGrade get(String id) {
		return super.get(id);
	}
	
	public List<YgGrade> findList(YgGrade ygGrade) {
		return super.findList(ygGrade);
	}
	
	public Page<YgGrade> findPage(Page<YgGrade> page, YgGrade ygGrade) {
		return super.findPage(page, ygGrade);
	}
	
	@Transactional(readOnly = false)
	public void save(YgGrade ygGrade) {
		super.save(ygGrade);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgGrade ygGrade) {
		super.delete(ygGrade);
	}
	
}