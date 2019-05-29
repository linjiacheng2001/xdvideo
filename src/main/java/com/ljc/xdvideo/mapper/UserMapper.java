package com.ljc.xdvideo.mapper;

import com.ljc.xdvideo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-02-28 15:14
 */
public interface UserMapper {


    /**
     * 根据id查找用户
     *
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id} ")
    User findById(@Param("id") Integer userId);

    /**
     * 根据openId查找用户
     *
     * @param openId
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User findByOpenId(@Param("openid") String openId);

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO `xdclass`.`user`(`openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`) " +
            "VALUES (#{openid}, #{name}, #{headImg}, #{phone}, #{sign}, #{sex}, #{city}, #{createTime});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int savaUser(User user);

}
