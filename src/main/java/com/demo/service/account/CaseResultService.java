package com.demo.service.account;

import com.demo.entity.CaseResult;
import com.demo.repository.CaseResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 27259 on 2017/4/6.
 */
@Component
@Transactional
public class CaseResultService {
    @Autowired
    private CaseResultDao caseResultDao;

    public CaseResult findById(Long id){
        return  caseResultDao.findOne(id);
    }
    public void saveCaseResult(CaseResult caseResult){
        caseResultDao.save(caseResult);
    }
}
