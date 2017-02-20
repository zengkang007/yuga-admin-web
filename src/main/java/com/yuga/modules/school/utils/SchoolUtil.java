package com.yuga.modules.school.utils;

import java.util.List;

import com.yuga.common.utils.SpringContextHolder;
import com.yuga.modules.school.dao.base.BaseCourseDao;
import com.yuga.modules.school.dao.base.YgClassDao;
import com.yuga.modules.school.dao.base.YgCourseDao;
import com.yuga.modules.school.dao.base.YgCourseTimeDao;
import com.yuga.modules.school.dao.base.YgGradeDao;
import com.yuga.modules.school.dao.base.YgMultimediaStatusDao;
import com.yuga.modules.school.dao.base.YgStudentStatusDao;
import com.yuga.modules.school.dao.base.YgTeacherStatusDao;
import com.yuga.modules.school.dao.base.YgTeacherUserDao;
import com.yuga.modules.school.entity.base.BaseCourse;
import com.yuga.modules.school.entity.base.YgClass;
import com.yuga.modules.school.entity.base.YgCourse;
import com.yuga.modules.school.entity.base.YgCourseTime;
import com.yuga.modules.school.entity.base.YgGrade;
import com.yuga.modules.school.entity.base.YgMultimediaStatus;
import com.yuga.modules.school.entity.base.YgStudentStatus;
import com.yuga.modules.school.entity.base.YgTeacherStatus;
import com.yuga.modules.school.entity.base.YgTeacherUser;

public class SchoolUtil {
	private static YgGradeDao gradeDao = SpringContextHolder.getBean(YgGradeDao.class);
	private static YgCourseDao courseDao = SpringContextHolder.getBean(YgCourseDao.class);
	private static YgCourseTimeDao courseTimeDao = SpringContextHolder.getBean(YgCourseTimeDao.class);
	private static YgClassDao classDao = SpringContextHolder.getBean(YgClassDao.class);
	private static YgMultimediaStatusDao multiMediaDao = SpringContextHolder.getBean(YgMultimediaStatusDao.class);
	private static YgTeacherStatusDao teacherStatusDao = SpringContextHolder.getBean(YgTeacherStatusDao.class);
	private static YgTeacherUserDao teacherUserDao = SpringContextHolder.getBean(YgTeacherUserDao.class);
	private static YgStudentStatusDao studentStatusDao = SpringContextHolder.getBean(YgStudentStatusDao.class);
	private static BaseCourseDao baseCourseDao = SpringContextHolder.getBean(BaseCourseDao.class);

	/**
	 * 获取基础课表列表
	 * @return
	 */
	public static List<BaseCourse> getBaseCourseList(){
		BaseCourse entity = new BaseCourse();
		return baseCourseDao.findAllList(entity);
	}
	
	/**
	 * 获取基础课表Label
	 * @return
	 */
	public static String getBaseCourseLabel(String id){
		if(id != null) {
			BaseCourse entity = baseCourseDao.get(id);
			return (entity == null) ? "" : entity.getClassName();
		}
		return "";
	}
	
	/**
	 * 获取年级级别
	 * @return
	 */
	public static List<YgGrade> getGradeList(){
		YgGrade entity = new YgGrade();
		return gradeDao.findAllList(entity);
	}
	
	/**
	 * 获取年级级别Label
	 * @return
	 */
	public static String getGradeLabel(String id){
		if(id != null) {
			YgGrade entity = gradeDao.get(id);
			return (entity == null) ? "" : entity.getName();
		}
		return "";
	}
	
	/**
	 * 获取课程
	 * @return
	 */
	public static List<YgCourse> getYgCourseList(){
		YgCourse entity = new YgCourse();
		return courseDao.findAllList(entity);
	}
	
	/**
	 * 获取课程Label
	 * @return
	 */
	public static String getYgCourseLabel(String id){
		if(id != null) {
			YgCourse entity = courseDao.get(id);
			return (entity == null) ? "" : entity.getName();
		}
		return "";
	}
	
	/**
	 * 获取课时安排
	 * @return
	 */
	public static List<YgCourseTime> getYgCourseTimeList(){
		YgCourseTime entity = new YgCourseTime();
		return courseTimeDao.findAllList(entity);
	}

	/**
	 * 获取课时安排Label
	 * @return
	 */
	public static String getYgCourseTimeLabel(String id){
		if(id != null) {
			YgCourseTime entity = courseTimeDao.get(id);
			return (entity == null) ? "" : entity.getCourseName();
		}
		return "";
	}
	
	/**
	 * 获取班级信息
	 * @return
	 */
	public static List<YgClass> getYgClassList(){
		YgClass entity = new YgClass();
		return classDao.findAllList(entity);
	}
	
	/**
	 * 获取班级信息Label
	 * @return
	 */
	public static String getYgClassLabel(String id){
		if(id != null) {
			YgClass entity = classDao.get(id);
			return (entity == null) ? "" : entity.getClassName();
		}
		return "";
	}
	
	/**
	 * 获取多媒体使用情况
	 * @return
	 */
	public static List<YgMultimediaStatus> getMultiStatusList(){
		YgMultimediaStatus entity = new YgMultimediaStatus();
		return multiMediaDao.findAllList(entity);
	}
	
	/**
	 * 获取多媒体使用情况Label
	 * @return
	 */
	public static String getMultiStatusLabel(String id){
		if(id != null) {
			YgMultimediaStatus entity = multiMediaDao.get(id);
			return (entity == null) ? "" : entity.getName();
		}
		return "";
	}
	
	/**
	 * 获取教师教学情况
	 * @return
	 */
	public static List<YgTeacherStatus> getTeacherStatusList(){
		teacherStatusDao = SpringContextHolder.getBean(YgTeacherStatusDao.class);
		YgTeacherStatus entity = new YgTeacherStatus();
		return teacherStatusDao.findAllList(entity);
	}
	
	/**
	 * 获取教师信息
	 * @return
	 */
	public static List<YgTeacherUser> getTeacherList(){
		teacherUserDao = SpringContextHolder.getBean(YgTeacherUserDao.class);
		YgTeacherUser entity = new YgTeacherUser();
		return teacherUserDao.findAllList(entity);
	}
	
	/**
	 * 获取多教师教学情况Label
	 * @return
	 */
	public static String getTeacherStatusLabel(String id){
		if(id != null) {
			YgTeacherStatus entity = teacherStatusDao.get(id);
			return (entity == null) ? "" : entity.getLevel();
		}
		return "";
	}
	
	/**
	 * 获取学生学习情况
	 * @return
	 */
	public static List<YgStudentStatus> getStudentStatusList(){
		YgStudentStatus entity = new YgStudentStatus();
		return studentStatusDao.findAllList(entity);
	}
	
	/**
	 * 获取学生学习情况Label
	 * @return
	 */
	public static String getStudentStatusLabel(String id){
		if(id != null) {
			YgStudentStatus entity = studentStatusDao.get(id);
			return (entity == null) ? "" : entity.getLevel();
		}
		return "";
	}
}
