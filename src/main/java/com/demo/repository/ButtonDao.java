package com.demo.repository;

import com.demo.entity.Button;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/2/28.
 */
public interface ButtonDao extends PagingAndSortingRepository<Button, Long>, JpaSpecificationExecutor<Button> {

}
