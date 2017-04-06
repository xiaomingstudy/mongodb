package com.demo.repository;

import com.demo.entity.CaseScript;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/3/20.
 */
public interface CaseScriptDao extends PagingAndSortingRepository<CaseScript, Long>, JpaSpecificationExecutor<CaseScript> {

}
