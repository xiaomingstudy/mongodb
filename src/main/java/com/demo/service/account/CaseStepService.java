package com.demo.service.account;

import com.demo.entity.CaseStep;
import com.demo.repository.CaseStepDao;
import org.apache.commons.collections.FastHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 27259 on 2017/3/16.
 */
@Component
@Transactional
public class CaseStepService {

    @Autowired
    private CaseStepDao caseStepDao;
    public List<CaseStep> getCaseStep() {
        return (List<CaseStep>) caseStepDao.findAll();
    }

    public List<CaseStep> getCaseStep(Long caseId) {
        Specification<CaseStep> spec = buildSpecification(caseId);
        return (List<CaseStep>) caseStepDao.findAll(spec);
    }

    public CaseStep getCaseStepById(Long caseStepId) {
        return  caseStepDao.findOne(caseStepId);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<CaseStep> buildSpecification(Long caseId) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("caseId", new SearchFilter("caseId", SearchFilter.Operator.EQ, caseId));
        Specification<CaseStep> spec = DynamicSpecifications.bySearchFilter(filters.values(), CaseStep.class);
        return spec;
    }

    public void saveCaseStep(CaseStep newCaseStep) {
        caseStepDao.save(newCaseStep);
    }

    public void deleteCaseStep(Long case_id) {
        caseStepDao.delete(case_id);
    }
    public void deleteCaseStep(CaseStep CaseStep) {
        caseStepDao.delete(CaseStep);
    }
}
