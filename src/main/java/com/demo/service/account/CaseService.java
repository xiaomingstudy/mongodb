package com.demo.service.account;

import antlr.ASTNULLType;
import com.demo.entity.Case;
import com.demo.repository.CaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by 27259 on 2017/2/27.
 */
@Component
@Transactional
public class CaseService {
    @Autowired
    private CaseDao caseDao;

    public List<Case> getAllCases() {

        return (List<Case>) caseDao.findAll();
    }

    public Case getCase(Long id) {
        return caseDao.findOne(id);
    }

    public  void deleteCase(Long id) {
        caseDao.delete(id);
    }

    public Page<Case> getUserCase(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<Case> spec = buildSpecification(userId, searchParams);

        return caseDao.findAll(spec, pageRequest);
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;

        sort = new Sort(Sort.Direction.DESC, "createDate");
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<Case> buildSpecification(Long userId, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("user.id", new SearchFilter("user.id", SearchFilter.Operator.EQ, userId));
        Specification<Case> spec = DynamicSpecifications.bySearchFilter(filters.values(), Case.class);
        return spec;
    }


    public void saveCase(Case entity) {
        caseDao.save(entity);
    }
}
