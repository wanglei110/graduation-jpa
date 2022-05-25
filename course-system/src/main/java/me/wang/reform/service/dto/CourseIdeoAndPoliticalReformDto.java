
package me.wang.reform.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import javax.persistence.Column;

/**
* @website https://el-admin.vip
* @description /
* @author wang
* @date 2022-05-18
**/
@Data
public class CourseIdeoAndPoliticalReformDto implements Serializable {

    /** id */
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /** 项目 */
    private String project;

    /** 类别 */
    private String projectType;

    /** 文件类别 */
    private String type;

    /** 文件真实名称 */
    private String realName;

    /** 文件路径 */
    private String path;

    /** 创建时间 */
    private Timestamp createTime;

    private String name;
}