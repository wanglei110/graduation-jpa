/*
*  Copyright 2019-2020 Zheng Jie
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
package me.zhengjie.course.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.zhengjie.ideo.domain.Ideo;
import me.zhengjie.modules.system.domain.User;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
* @website https://el-admin.vip
* @description /
* @author wang
* @date 2022-04-26
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

    @Column(name = "create_time")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
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


    @ManyToOne
    @ApiModelProperty(value = "userId")
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(
            name="course_ideo_elements",
            joinColumns = @JoinColumn(name="course_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="ideo_id",referencedColumnName = "id")
    )
    private Set<Ideo> ideos;

    public void copy(Course source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}