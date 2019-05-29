package com.ljc.xdvideo.mapper;

import com.ljc.xdvideo.domain.Video;
import com.ljc.xdvideo.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能描述:video数据访问层
 *
 * @author linjiacheng2001
 * @date 2019-02-24 13:17
 */
public interface VideoMapper {

    @Select("select * from video")
    List<Video> findAll();


    @Select("select * from video where id=#{id}")
    Video findById(int id);

    //不是动态的sql语句@Update("update video set title=#{title} where id=#{id}")
    @UpdateProvider(type = VideoProvider.class, method = "updateVideo")
    int update(Video video);

    @Delete("delete from video where id=#{id}")
    int delete(int id);

    //返回自增id
    @Insert("INSERT INTO `xdclass`.`video`(`title`, `summary`, `cover_img`, `view_num`, `price`, `create_time`, `online`, `point`) " +
            "VALUES " +
            "(#{title}, #{summary}, #{coverImg}, #{viewNum}, #{price}," +
            " #{createTime}, " +
            "#{online}, #{point});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Video video);
}
