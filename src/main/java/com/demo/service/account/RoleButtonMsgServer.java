package com.demo.service.account;

import com.demo.entity.Case;
import com.demo.entity.RoleButtonMsg;
import com.demo.repository.RoleButtonMsgDao;
import org.apache.commons.collections.map.HashedMap;
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
 * Created by 27259 on 2017/4/3.
 */
@Component
@Transactional
public class RoleButtonMsgServer {

    @Autowired
    private RoleButtonMsgDao roleButtonMsgDao;

    public Page<RoleButtonMsg> getUserCase(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<RoleButtonMsg> spec = buildSpecification(userId, searchParams);

        return roleButtonMsgDao.findAll(spec, pageRequest);
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
    private Specification<RoleButtonMsg> buildSpecification(Long userId, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("user.id", new SearchFilter("user.id", SearchFilter.Operator.EQ, userId));
        Specification<RoleButtonMsg> spec = DynamicSpecifications.bySearchFilter(filters.values(), RoleButtonMsg.class);
        return spec;
    }

    private Specification<RoleButtonMsg> buildSpecification(Long roleId) {
        Map<String, Object> searchParams = new HashedMap();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Integer flag = 0;
        filters.put("roleId", new SearchFilter("roleId", SearchFilter.Operator.EQ, roleId));
        filters.put("flag", new SearchFilter("flag", SearchFilter.Operator.EQ, flag));
        Specification<RoleButtonMsg> spec = DynamicSpecifications.bySearchFilter(filters.values(), RoleButtonMsg.class);
        return spec;
    }

    public List<RoleButtonMsg> getRoleButtonMsg(Long roleId) {
        Specification<RoleButtonMsg> spec = buildSpecification(roleId);

        return roleButtonMsgDao.findAll(spec);
    }

    public Iterable<RoleButtonMsg> getAllRoleButton() {

        return roleButtonMsgDao.findAll();
    }


    public void saveroleButtonMsg(RoleButtonMsg newCaseStep) {
        roleButtonMsgDao.save(newCaseStep);
    }
    public RoleButtonMsg getRoleButtonMsgById(Long buttonId,Long roleId) {
        Specification<RoleButtonMsg> spec = buildSpecification1(roleId,buttonId);
        return  roleButtonMsgDao.findOne(spec);
    }

    private Specification<RoleButtonMsg> buildSpecification1(Long roleId,Long buttonId) {
        Map<String, Object> searchParams = new HashedMap();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("roleId", new SearchFilter("roleId", SearchFilter.Operator.EQ, roleId));
        filters.put("buttonId", new SearchFilter("buttonId", SearchFilter.Operator.EQ, buttonId));
        Specification<RoleButtonMsg> spec = DynamicSpecifications.bySearchFilter(filters.values(), RoleButtonMsg.class);
        return spec;
    }

}
