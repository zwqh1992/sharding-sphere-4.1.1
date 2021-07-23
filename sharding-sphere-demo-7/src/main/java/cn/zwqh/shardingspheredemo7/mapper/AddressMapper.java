package cn.zwqh.shardingspheredemo7.mapper;

import cn.zwqh.shardingspheredemo7.entity.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 地址mapper
 * @author: wangc
 * @create: 2021-07-23 10:09
 **/
@Mapper
public interface AddressMapper {

    /**
     * 新增地址
     *
     * @param address
     */
    @Insert("insert into t_address(address_id,address_name) values(#{addressId},#{addressName})")
    void insertAddress(Address address);

    /**
     * 删除所有地址（方便再次执行）
     */
    @Delete("delete from t_address")
    void deleteAllAddress();
}
