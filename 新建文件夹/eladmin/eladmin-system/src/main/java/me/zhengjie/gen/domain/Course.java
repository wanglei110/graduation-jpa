/*
*  Copyright 2022-2022
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.wang.gen.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website
* @description /
* @author wang
* @date 2022-04-25
**/
@Entity
@Data
@Table(name="course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Long id;

    @Column(name = "if_deleted")
    @ApiModelProperty(value = "ifDeleted")
    private Integer ifDeleted;

    @Column(name = "create_time")
    @ApiModelProperty(value = "createTime")
    private Timestamp createTime;

    @Column(name = "course_name")
    @ApiModelProperty(value = "课程名")
    private String courseName;

    @Column(name = "course_code")
    @ApiModelProperty(value = "课程代码")
    private String courseCode;

    @Column(name = "course_type")
    @ApiModelProperty(value = "课程类别")
    private String courseType;

    @Column(name = "course_nature")
    @ApiModelProperty(value = "课程性质(必修,选修等)")
    private String courseNature;

    @Column(name = "credit")
    @ApiModelProperty(value = "学分")
    private Float credit;

    @Column(name = "total_hours")
    @ApiModelProperty(value = "总学时数")
    private Integer totalHours;

    @Column(name = "academy")
    @ApiModelProperty(value = "开课学院")
    private String academy;

    @Column(name = "teaching_group")
    @ApiModelProperty(value = "开课基层教学组织")
    private String teachingGroup;

    @Column(name = "for_professional")
    @ApiModelProperty(value = "面向专业")
    private String forProfessional;

    @Column(name = "semester")
    @ApiModelProperty(value = "开课学期")
    private Integer semester;

    @Column(name = "user_id")
    @ApiModelProperty(value = "userId")
    private Long userId;

    public void copy(Course source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}