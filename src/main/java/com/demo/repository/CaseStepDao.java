package com.demo.repository;

import com.demo.entity.Case;
import com.demo.entity.CaseStep;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/3/16.
 */
public interface CaseStepDao extends  PagingAndSortingRepository<CaseStep, Long>, JpaSpecificationExecutor<CaseStep>{
}
