/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.common.utils.DateUtils;
import com.yuga.modules.school.dao.base.BaseCourseDao;
import com.yuga.modules.school.entity.base.BaseCourse;
import com.yuga.modules.school.entity.base.CourseSectionTime;
import com.yuga.modules.sys.dao.UserDao;
import com.yuga.modules.sys.entity.User;

/**
 * 课表基础信息Service
 * @author 曾康
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class BaseCourseService extends CrudService<BaseCourseDao, BaseCourse> {

	private static Logger log = LoggerFactory.getLogger(BaseCourseService.class);

	public static String TEST_DATE = "2016-11-3 16:20"; //DateUtils.getDateTime2()
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CourseSectionTimeService sectionTimeService;
	
	private static Map<String, String> WEEK_MAP = new HashMap<>();
	static{
		WEEK_MAP.put("星期一", "0");
		WEEK_MAP.put("星期二", "1");
		WEEK_MAP.put("星期三", "2");
		WEEK_MAP.put("星期四", "3");
		WEEK_MAP.put("星期五", "4");
		WEEK_MAP.put("星期六", "5");
		WEEK_MAP.put("星期日", "6");
	}
	
	public BaseCourse get(String id) {
		BaseCourse baseCourse = super.get(id);
		if(baseCourse != null) {
			baseCourse.setUser( userDao.get(""+baseCourse.getTeacherId()) );
		}
		return baseCourse;
		
	}
	
	/**
	 * 获取当前节次
	 * @return
	 */
	public CourseSectionTime getCourseSection(){
		CourseSectionTime entity = new CourseSectionTime();
		List<CourseSectionTime> lstOut = sectionTimeService.findList(entity);
		for(CourseSectionTime sectionTime : lstOut) {
			//组合时间
			String nowDate = DateUtils.getDate();
			String nowDatetime = DateUtils.getDateTime2();
			//String nowDatetime = TEST_DATE;
			String nowStartDate = nowDate + " " + sectionTime.getStartTime();
			String nowEndDate = nowDate + " " + sectionTime.getEndTime();
			log.info("当前时间：" + nowDatetime);
			log.info("nowStartDate:" + nowStartDate);
			log.info("nowEndDate:" + nowEndDate);
			boolean ret = DateUtils.isExist(nowDatetime, nowStartDate, nowEndDate);
			if(ret) {
				log.info("查询到节次信息.");
				return sectionTime;
			}
		}
		return null;
	}
	
	public String getConvertWeekId(){
		String currentWeek = DateUtils.getWeek();
		return WEEK_MAP.get(currentWeek);
	}
	
	/**
	 * 通过班级、星期、节次查询基础课表对象
	 * 还有根据课表的时间范围
	 * @return
	 */
	public BaseCourse getCourseInfo(BaseCourse baseInfo){
		String classId = baseInfo.getClassId();
		Integer weekId = baseInfo.getWeekId();
		Integer sectionId = baseInfo.getSection();
		BaseCourse entity = new BaseCourse();
		entity.setClassId(classId);
		entity.setWeekId(weekId);
		entity.setSection(sectionId);
		entity.setqType(baseInfo.getqType());
		List<BaseCourse> lstBaseCourse = findList(entity);
		if(lstBaseCourse.size() > 0) {
			BaseCourse outItem = lstBaseCourse.get(0);
			//查出对应的教师信息
			if(outItem.getId()!= null){
				User teacherUser = userDao.get(outItem.getTeacherId());
				if(teacherUser != null) {
					User tempUser = new User();
					tempUser.setName(teacherUser.getName());
					outItem.setUser(tempUser);
				}
			} 
			return outItem;
		}
		return null;
	}
	
	public List<BaseCourse> findList(BaseCourse baseCourse) {
		return super.findList(baseCourse);
	}
	
	public Page<BaseCourse> findPage(Page<BaseCourse> page, BaseCourse baseCourse) {
		Page<BaseCourse> lstPage = super.findPage(page, baseCourse);
		List<BaseCourse> lstCourse = lstPage.getList();
		for(BaseCourse course : lstCourse) {
			if(course != null) {
				course.setUser( userDao.get(""+course.getTeacherId()) );
			}
		}
		return lstPage;
	}
	
	@Transactional(readOnly = false)
	public void save(BaseCourse baseCourse) {
		User user = new User();
		user.setId(""+baseCourse.getTeacherId());
		baseCourse.setUser(user);
		super.save(baseCourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(BaseCourse baseCourse) {
		super.delete(baseCourse);
	}
	
}