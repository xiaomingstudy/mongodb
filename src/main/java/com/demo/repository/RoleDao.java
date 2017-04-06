package com.demo.repository;

import com.demo.entity.RoleMaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.management.relation.RoleInfo;

/**
 * Created by 27259 on 2017/3/13.
 */
public interface RoleDao extends PagingAndSortingRepository<RoleMaint,Long>,JpaSpecificationExecutor<RoleMaint>{
    Page<RoleMaint> findByUserId(Long id, Pageable pageRequest);
}
