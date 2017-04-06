package com.demo.repository;

import com.demo.entity.CaseResult;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/3/24.
 */
public interface CaseResultDao extends PagingAndSortingRepository<CaseResult,Long>,JpaSpecificationExecutor<CaseResult>{
}
