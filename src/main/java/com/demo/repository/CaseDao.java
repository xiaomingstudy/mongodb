package com.demo.repository;

import com.demo.entity.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/2/28.
 */
public interface CaseDao extends PagingAndSortingRepository<Case, Long>, JpaSpecificationExecutor<Case> {
    Case findByCaseNumber(String caseNumber);
    Page<Case> findByUserId(Long id, Pageable pageRequest);
}
