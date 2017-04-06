package com.demo.service.account;

import com.demo.entity.CaseScript;
import com.demo.repository.CaseScriptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by 27259 on 2017/3/20.
 */
@Component
@Transactional
public class CaseScriptService {
    @Autowired
    private CaseScriptDao caseScriptDao;

    public Long saveCaseScript(CaseScript caseScript) {
        caseScriptDao.save(caseScript);
        return caseScript.getId();
    }

    public CaseScript findById(Long id){
        return  caseScriptDao.findOne(id);
    }
}
