
package me.wang.reform.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author wang
* @date 2022-05-18
**/
@Entity
@Data
@Table(name="course_ideo_and_political_reform")
public class CourseIdeoAndPoliticalReform implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Long id;

    @Column(name = "project")
    @ApiModelProperty(value = "项目")
    private String project;

    @Column(name = "project_type")
    @ApiModelProperty(value = "类别")
    private String projectType;

    @Column(name = "type")
    @ApiModelProperty(value = "文件类别")
    private String type;

    @Column(name = "real_name")
    @ApiModelProperty(value = "文件真实名称")
    private String realName;

    @Column(name = "name")
    @ApiModelProperty(value = "文件名")
    private String name;

    @Column(name = "path")
    @ApiModelProperty(value = "文件路径")
    private String path;

    @Column(name = "create_time")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    public void copy(CourseIdeoAndPoliticalReform source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}