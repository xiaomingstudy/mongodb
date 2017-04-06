package com.demo.service.device;

import com.demo.entity.DeviceEvery;
import com.demo.entity.JobTask;
import com.demo.repository.JobTaskDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by 27259 on 2017/3/23.
 */
@Component
@Transactional
public class JobTaskService {

    @Autowired
    private JobTaskDao jobTaskDao;

    public void saveTaskJob(JobTask jobTask) {
        jobTaskDao.save(jobTask);
    }

    public JobTask findByCaseResultId(Long caseResultId){
        return jobTaskDao.findOne(caseResultId);
    }

}
