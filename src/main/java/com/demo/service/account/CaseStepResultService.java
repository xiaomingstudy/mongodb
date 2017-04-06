package com.demo.service.account;

import com.demo.entity.*;
import com.demo.repository.CaseResultDao;
import com.demo.repository.CaseStepResultDao;
import com.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 27259 on 2017/3/24.
 */
@Component
@Transactional
public class CaseStepResultService {
    @Autowired
    private CaseResultDao caseResultDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CaseStepResultDao CaseStepResultDao;

    public String caseResultForAuto(Integer caseId,Integer deviceId,Long userId) {
        // 1.0保存test_case的结果信息
        CaseResult caseResult = new CaseResult();
        User user = userDao.findOne(userId);
        caseResult.setCaseId(caseId);
        caseResult.setTestResult(DeviceEvery.TEST_RESULT_WAITING);
        Date thisDate = new Date(System.currentTimeMillis());
        caseResult.setStartTime(thisDate);
        caseResult.setDeviceId(deviceId);
        caseResult.setUser(user);
        caseResultDao.save(caseResult);
      /*  Specification<CaseResult> spec = buildSpecification(thisDate);
        caseResultDao.findAll(spec);*/


        // 1.1修改案例表状态为待执行
       /* TblProjectRequestCase requestCase = projectCaseService
                .queryTblProjectCaseById(caseId);
        requestCase.setTestResult(DeviceConstants.TEST_RESULT_WAITING);
        baseDao.saveOrUpdate(requestCase);

        Integer caseResultId = caseResult.getId();*/

      /*  // 2.查询test_step步骤信息
        List<TblProjectRequestCaseStep> stepList = deviceTestService
                .getCaseStep(caseId);

        // 3.生成到步骤结果表中
        if (!CollectionUtils.isEmpty(stepList)) {

            for (TblProjectRequestCaseStep step : stepList) {
                // 3.0修改案例步骤状态为待执行
                step.setTestResult(DeviceConstants.TEST_RESULT_WAITING);
                baseDao.saveOrUpdate(step);

                TblProjectRequestCaseStepResult caseStepResult = new TblProjectRequestCaseStepResult();
                caseStepResult.setProjectRequestCaseId(caseId);
                caseStepResult.setProjectRequestCaseStepId(step.getId()
                        .intValue());
                caseStepResult.setProjectRequestCaseResultId(caseResultId);
                caseStepResult.setStepNum(step.getStepNum());
                caseStepResult.setStepDesc(step.getStepDesc());
                caseStepResult.setExpectedResult(step.getExpectedResult());
                caseStepResult
                        .setTestResult(DeviceConstants.TEST_RESULT_WAITING);
                caseStepResult.setIsHorizontal(1);
                baseDao.saveOrUpdate(caseStepResult);
            }*/
      /*  }
        return String.valueOf(caseResultId);

    }*/
        return caseResult.getId().toString();
    }

    public void saveCaseStepResult(CaseStepResult result){
        CaseStepResultDao.save(result);
    }

    public Iterable<CaseStepResult> getListByCaseResultId(Integer caseResultId){
        Specification<CaseStepResult> spec = buildSpecification(Long.valueOf(caseResultId));
        return CaseStepResultDao.findAll();
    }
    /**
     * 创建动态查询条件组合.
     */
    private Specification<CaseStepResult> buildSpecification(Long caseResultId) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("caseResultId", new SearchFilter("caseResultId", SearchFilter.Operator.EQ, caseResultId));
        Specification<CaseStepResult> spec = DynamicSpecifications.bySearchFilter(filters.values(), CaseStepResult.class);
        return spec;
    }

}
