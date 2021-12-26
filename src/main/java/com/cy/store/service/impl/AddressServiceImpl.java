package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 新增收货地址的实现
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private  Integer maxcount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //判断是否达到限制，调用统计方法
        Integer count  = addressMapper.countByUid(uid);
        if(count >= maxcount)
            throw new AddressCountLimitException("用户收获地址已达上限");

        //补全日志,1默认，0不是默认
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);

        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //调用insert
        Integer rows = addressMapper.insert(address);
        if(rows!=1)
            throw new InsertException("插入时发生错误");

    }
}
