package com.demo.service.account;

import com.demo.entity.RoleMaint;
import com.demo.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.List;
import java.util.Map;

/**
 * Created by 27259 on 2017/3/13.
 */
@Component
@Transactional
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Page<RoleMaint> getRole( Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        return roleDao.findAll(pageRequest);
    }
    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;

        sort = new Sort(Sort.Direction.DESC, "roleDate");
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<RoleMaint> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Integer deleteFlag =0;
        filters.put("RoleMaint.deleteFlag", new SearchFilter("RoleMaint.deleteFlag", SearchFilter.Operator.EQ, deleteFlag));
        Specification<RoleMaint> spec = DynamicSpecifications.bySearchFilter(filters.values(), RoleMaint.class);
        return spec;
    }

    public Iterable<RoleMaint> getAllRole() {
        return roleDao.findAll();
    }

    public RoleMaint getRoleOne(Long id) {
        return roleDao.findOne(id);
    }

    public void deleteRoleM(Long id) {
        roleDao.delete(id);
    }

    public void saveRoleOne(RoleMaint newRole) {
        roleDao.save(newRole);
    }
}
