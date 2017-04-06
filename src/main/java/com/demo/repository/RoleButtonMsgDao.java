package com.demo.repository;

import com.demo.entity.RoleButtonMsg;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/2/28.
 */
public interface RoleButtonMsgDao extends PagingAndSortingRepository<RoleButtonMsg, Long>, JpaSpecificationExecutor<RoleButtonMsg> {

}
