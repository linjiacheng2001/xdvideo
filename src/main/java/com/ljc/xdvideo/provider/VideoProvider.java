package com.ljc.xdvideo.provider;

import com.ljc.xdvideo.domain.Video;
import org.apache.ibatis.jdbc.SQL;

/**
 * 功能描述:
 * video构建动态sql语句
 *
 * @author linjiacheng2001
 * @date 2019-02-24 15:31
 */
public class VideoProvider {

    /**
     * 动态更新video
     *
     * @param video
     * @return
     */
    public String updateVideo(final Video video) {
        return new SQL() {{
            UPDATE("video");

            //条件写法.
            if (video.getTitle() != null) {
                SET("title=#{title}");
            }
            if (video.getSummary() != null) {
                SET("summary=#{summary}");
            }
            if (video.getCoverImg() != null) {
                SET("cover_img=#{coverImg}");
            }
            if (video.getViewNum() != null) {
                SET("view_num=#{viewNum}");
            }
            if (video.getPrice() != null) {
                SET("price=#{price}");
            }
            if (video.getOnline() != null) {
                SET("online=#{online}");
            }
            if (video.getPoint() != null) {
                SET("point=#{point}");
            }


            WHERE("id=#{id}");
        }}.toString();
    }
}
