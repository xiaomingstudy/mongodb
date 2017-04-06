package com.demo.repository;

import com.demo.entity.JobTask;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/3/23.
 */
public interface JobTaskDao extends PagingAndSortingRepository<JobTask, Long>, JpaSpecificationExecutor<JobTask> {

}
