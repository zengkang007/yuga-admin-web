/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.checker;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.dao.checker.YgClassCheckDao;
import com.yuga.modules.school.entity.checker.ClassCheckDTO;
import com.yuga.modules.school.entity.checker.YgClassCheck;
import com.yuga.modules.school.entity.checker.YgTimeField;
import com.yuga.modules.sys.dao.UserDao;

/**
 * 巡查情况Service
 * @author 曾康
 * @version 2016-11-02
 */
@Service
@Transactional(readOnly = true)
public class YgClassCheckService extends CrudService<YgClassCheckDao, YgClassCheck> {
	private static Logger log = LoggerFactory.getLogger(YgClassCheckService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private YgTimeFieldService timeFieldService;
	
	@Autowired
	private YgClassCheckDao classCheckDao;
	
	public YgClassCheck get(String id) {
		YgClassCheck entity = super.get(id);
		if(StringUtils.isNotEmpty(entity.getTeacherId())){
			entity.setUser(userDao.get(entity.getTeacherId()));
		}
		return entity;
	}
	
	public List<ClassCheckDTO> findTop10List() {
		return classCheckDao.findTop10List();
	}
	
	public List<YgClassCheck> findList(YgClassCheck ygClassCheck) {
		return super.findList(ygClassCheck);
	}
	
	public Page<YgClassCheck> findPage(Page<YgClassCheck> page, YgClassCheck ygClassCheck) {
		return super.findPage(page, ygClassCheck);
	}
	
	/**
	 * 开始统计分数
	 * 
	 */
	@Transactional(readOnly = false)
	public void save(YgClassCheck ygClassCheck) {
		log.info("统计默认分数、勾选分数");
		if(ygClassCheck.getTimeCheck() != null){
			int totalMark = 0;
			YgTimeField timeField = new YgTimeField();
			timeField.setYgTimeCheck(ygClassCheck.getTimeCheck());
			List<YgTimeField> lstTimeField = timeFieldService.findList(timeField);
			if(lstTimeField.size() > 0) {
				log.info("获取巡查方案个数." + lstTimeField.size() );
				List<String> lstCheckItems = ygClassCheck.getCheckItemsList();
				log.info("查询当前勾选巡查方案:" + lstCheckItems.toString());
				for(YgTimeField field : lstTimeField){
					if(lstCheckItems.contains( field.getId()) ) {
						log.info("当前勾选巡查方案:" + field.getFieldName() + " 分数：" + field.getMark());
						totalMark += Integer.parseInt(field.getMark());
					} else {
						log.info("当前尚未勾选巡查方案:" + field.getFieldName() + " 分数：" + field.getDefaultMark());
						if(field.getDefaultMark() != null) {
							totalMark += Integer.parseInt(field.getDefaultMark());
						}
					}
				}
			}
			
			log.info("开始统计缺勤人数,当前缺勤分数为:" + ygClassCheck.getTotalAbsence());
			totalMark += ygClassCheck.getTotalAbsence();
			log.info("总分为:" + totalMark);
			ygClassCheck.setTotalMark(totalMark);
		}
		super.save(ygClassCheck);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgClassCheck ygClassCheck) {
		super.delete(ygClassCheck);
	}
	
}