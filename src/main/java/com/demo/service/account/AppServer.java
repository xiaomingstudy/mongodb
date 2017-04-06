package com.demo.service.account;

import com.demo.entity.AppInfo;
import com.demo.repository.AppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 27259 on 2017/4/6.
 */
@Component
@Transactional
public class AppServer {
    @Autowired
    private AppDao appDao;

    public AppInfo findOneById(Long id){
       return appDao.findOne(id);
    }
}
