package com.demo.service.task;

import com.demo.entity.TblTestAttachment;
import com.demo.repository.TblTestAttachmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 27259 on 2017/4/6.
 */
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class TblTestAttachmentService {
    @Autowired
    private TblTestAttachmentDao tblTestAttachmentDao;

    public void save(TblTestAttachment tblTestAttachment){
        tblTestAttachmentDao.save(tblTestAttachment);
    }
}
