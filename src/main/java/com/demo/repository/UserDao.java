/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.demo.repository;

import org.hibernate.secure.internal.JaccSecurityListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User>{
	User findByLoginName(String loginName);
	Page<User> findById(Long id, Pageable pageRequest);
}
