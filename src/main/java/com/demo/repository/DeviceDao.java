package com.demo.repository;

import com.demo.entity.DeviceInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/3/7.
 */
public interface DeviceDao extends PagingAndSortingRepository<DeviceInfo, Long>, JpaSpecificationExecutor<DeviceInfo>{
    Page<DeviceInfo> findByUserId(Long id, Pageable pageRequest);
}
