package com.demo.repository;

import com.demo.entity.AppInfo;
import com.demo.entity.TblTestAttachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 27259 on 2017/2/28.
 */
public interface TblTestAttachmentDao extends PagingAndSortingRepository<TblTestAttachment, Long>, JpaSpecificationExecutor<TblTestAttachment> {

}
