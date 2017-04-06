package com.demo.repository;

import com.demo.entity.CaseResult;
import com.demo.entity.CaseStepResult;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/3/24.
 */
public interface CaseStepResultDao extends PagingAndSortingRepository<CaseStepResult, Long>, JpaSpecificationExecutor<CaseStepResult> {



}
