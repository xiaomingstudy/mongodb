package com.demo.service.device;

import com.demo.entity.DeviceInfo;
import com.demo.repository.DeviceDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.Map;

/**
 * Created by 27259 on 2017/3/7.
 */
@Component
@Transactional
public class DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    public Page<DeviceInfo> getUserDeviceInfo(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<DeviceInfo> spec = buildSpecification(userId, searchParams);

        return deviceDao.findAll(spec,pageRequest);
    }


    public DeviceInfo getDeviceInfoBySerial_number (String SerialNumber) {
        Map<String, Object> searchParams = new HashedMap();
        Specification<DeviceInfo> spec = buildSpecification(SerialNumber, searchParams);

        return deviceDao.findOne(spec);
    }
    private Specification<DeviceInfo> buildSpecification(String SerialNumber, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("serialNumber", new SearchFilter("serialNumber", SearchFilter.Operator.EQ, SerialNumber));
        Specification<DeviceInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), DeviceInfo.class);
        System.out.println(spec);
        return spec;
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;

        sort = new Sort(Sort.Direction.DESC, "insertTime");
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<DeviceInfo> buildSpecification(Long userId, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("user.id", new SearchFilter("user.id", SearchFilter.Operator.EQ, userId));
        Specification<DeviceInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), DeviceInfo.class);
        System.out.println(spec);
        return spec;
    }


    public void deleteDevice(Long id) {
       deviceDao.delete(id);
    }

    public DeviceInfo getDevice(Long id) {
       return deviceDao.findOne(id);
    }

    public void saveDevice(DeviceInfo deviceI) {
        deviceDao.save(deviceI);
    }
}
