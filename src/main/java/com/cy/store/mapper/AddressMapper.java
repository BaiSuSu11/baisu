package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//收获地址持久层接口
public interface AddressMapper {



    /**插入用户收获地址数据
     *
     * @param address 收货地址数据
     * @return 受影响行数
     */
    Integer insert(Address address);

    /**据用户id统计收获地址条数
     *
     * @param uid 用户id
     * @return
     */
    Integer countByUid(Integer uid);

    /**,
     * 根据用户的id查询用户的收货地址数据
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer uid);


    /**
     * 更具aid查询收获地址数据
     * @param aid 收货地址aid
     * @return 收货地址数据，没有返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根据uid来修改用户收获地址，全设置未非默认
     * @param uid 用户id
     * @return 返回行数
     */
    Integer updateNonDefault(Integer uid);

    Integer updateDefaultByAid(@Param("aid") Integer aid ,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);
}
