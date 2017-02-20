/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.checker;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.common.utils.DateUtils;
import com.yuga.common.utils.StringUtils;
import com.yuga.modules.school.dao.checker.YgTimeCheckDao;
import com.yuga.modules.school.dao.checker.YgTimeFieldDao;
import com.yuga.modules.school.entity.checker.YgTimeCheck;
import com.yuga.modules.school.entity.checker.YgTimeField;

/**
 * 宸℃鏃堕棿涓昏〃Service
 * @author 鏇惧悍
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class YgTimeCheckService extends CrudService<YgTimeCheckDao, YgTimeCheck> {

	private static Logger log = LoggerFactory.getLogger(YgTimeCheckService.class);

	
	@Autowired
	private YgTimeFieldDao timeFieldDao;
	
	public YgTimeCheck get(String id) {
		YgTimeCheck timeCheck = super.get(id);
		timeCheck.setYgTimeFieldList(timeFieldDao.findList(new YgTimeField(timeCheck)));
		return timeCheck;
	}
	
	public List<YgTimeCheck> findList(YgTimeCheck ygTimeCheck) {
		return super.findList(ygTimeCheck);
	}
	
	public Page<YgTimeCheck> findPage(Page<YgTimeCheck> page, YgTimeCheck ygTimeCheck) {
		return super.findPage(page, ygTimeCheck);
	}
	
	/**
	 * 创建详情
	 * @param ids
	 * @return
	 */
	public YgTimeCheck createTimeFields(String ids){
		YgTimeCheck outItem = null;
		if(ids != null) {
			outItem = new YgTimeCheck();
			String[] lstItem = ids.split(",");
			List<YgTimeField> ygTimeFieldList = Lists.newArrayList();
			for(String fiedId : lstItem) {
				ygTimeFieldList.add( timeFieldDao.get(fiedId) );
			}
			outItem.setYgTimeFieldList(ygTimeFieldList);
		}
		return outItem;
	}
	
	public String getInitTimeFields(){
		YgTimeCheck timeCheck = getExtraSelectFields();
		List<String> lstFields = Lists.newArrayList();
		if(timeCheck != null) {
			for(YgTimeField item : timeCheck.getYgTimeFieldList()){
				lstFields.add(item.getId());
			}
		}
		return "," + StringUtils.join(lstFields, ",") + ",";
	}
	
	/**
	 * 获取当前选项
	 * 根据当前时间判断分4种情况：
		a.正常的上课时间 显示(出了b、c、d以外的情况)：
		落实课程、课堂组织纪律好、使用多媒体教学、无座教、无使用手机、无体罚学生（以上勾选框默认全选），缺勤人数（下拉框，1个、2个、3个及以上）。
		
		b.11:50-12:10，午餐时间：
		教师到岗、学生纪律好 （以上勾选框默认全选）
		c.13:00-13:50，午管：
		教师到岗、学生纪律好 （以上勾选框默认全选）
		
		d.16:10-16:50下午托管
		学生纪律好、路队情况好、教室卫生好 （以上勾选框默认全选）
	 * @return
	 */
	public YgTimeCheck getExtraSelectFields(){
		YgTimeCheck entity = new YgTimeCheck();
		List<YgTimeCheck> lstOut = findList(entity);
		for(YgTimeCheck timeCheck : lstOut) {
			//组合时间
			String nowDate = DateUtils.getDate();
			String nowDatetime = DateUtils.getDateTime2();
			//String nowDatetime = BaseCourseService.TEST_DATE;
			String nowStartDate = nowDate + " " + timeCheck.getStartTime();
			String nowEndDate = nowDate + " " + timeCheck.getEndTime();
			log.info("当前时间：" + nowDatetime);
			log.info("nowStartDate:" + nowStartDate);
			log.info("nowEndDate:" + nowEndDate);
			boolean ret = DateUtils.isExist(nowDatetime, nowStartDate, nowEndDate);
			if(ret) {
				log.info("查询巡查详情信息.");
				timeCheck.setYgTimeFieldList(timeFieldDao.findList(new YgTimeField(timeCheck)));
				return timeCheck;
			}
		}
		return null;
	}
	
	@Transactional(readOnly = false)
	public void save(YgTimeCheck ygTimeCheck) {
		super.save(ygTimeCheck);
		//娣诲姞宸℃煡鏃堕棿瀛愰」
		for (YgTimeField timeField : ygTimeCheck.getYgTimeFieldList()){
			if (timeField.getId() == null){
				continue;
			}
			if (YgTimeField.DEL_FLAG_NORMAL.equals(timeField.getDelFlag())){
				timeField.setYgTimeCheck(ygTimeCheck);
				if (StringUtils.isBlank(timeField.getId())){
					timeFieldDao.insert(timeField);
				}else{
					timeField.preUpdate();
					timeFieldDao.update(timeField);
				}
			}else{
				timeFieldDao.delete(timeField);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(YgTimeCheck ygTimeCheck) {
		super.delete(ygTimeCheck);
	}
}