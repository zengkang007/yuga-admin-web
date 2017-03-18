package com.yuga.modules.cst.utils;

import com.yuga.common.utils.CacheUtils;
import com.yuga.common.utils.SpringContextHolder;
import com.yuga.modules.cst.dao.ConsultantDao;
import com.yuga.modules.cst.entity.Consultant;
import com.yuga.modules.sys.dao.UserDao;
import com.yuga.modules.sys.entity.Role;
import com.yuga.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zengk on 2017/3/6.
 */
public class ConsultantUtil {

    private static ConsultantDao consultantDao = SpringContextHolder.getBean(ConsultantDao.class);

    public static final String CONSULTANT_CACHE = "userConsultantCache";
    public static final String CONSULTANT_CACHE_ID_ = "consultant_id_";
    public static final String CONSULTANT_CACHE_LOGIN_NAME_ = "ln";
    public static final String CONSULTANT_CACHE_LIST_BY_OFFICE_ID_ = "consultant_oid_";

    /**
     * 根据ID获取用户
     * @param id
     * @return 取不到返回null
     */
    public static Consultant getConsultantById(String id){
        Consultant consultant = (Consultant) CacheUtils.get(CONSULTANT_CACHE, CONSULTANT_CACHE_ID_ + id);
        if (consultant ==  null){
            consultant = consultantDao.get(id);
            if (consultant == null){
                return null;
            }
            CacheUtils.put(CONSULTANT_CACHE, CONSULTANT_CACHE_ID_ + consultant.getId(), consultant);
        }
        return consultant;
    }

}
