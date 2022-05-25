
package me.wang.reform.service.dto;

import lombok.Data;
import me.wang.annotation.Query;

import java.sql.Timestamp;
import java.util.List;


/**
* @website https://el-admin.vip
* @author wang
* @date 2022-05-18
**/
@Data
public class CourseIdeoAndPoliticalReformQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String project;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String projectType;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String realName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String path;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}