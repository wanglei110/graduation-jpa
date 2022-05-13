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
package me.wang.course.service.dto;

import lombok.Data;
import me.wang.ideo.service.dto.IdeoDto;

import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Set;

/**
* @website
* @description /
* @author wang
* @date 2022-04-26
**/
@Data
public class CourseDto implements Serializable {

    /** id */
    private Long id;

    /** 创建时间 */
    private Timestamp createTime;

    /** 课程名 */
    private String courseName;

    /** 课程代码 */
    private String courseCode;

    /** 课程类别 */
    private String courseType;

    /** 课程性质(必修,选修等) */
    private String courseNature;

    /** 学分 */
    private Float credit;

    /** 总学时数 */
    private Integer totalHours;

    /** 开课学院 */
    private String academy;

    /** 开课基层教学组织 */
    private String teachingGroup;

    /** 面向专业 */
    private String forProfessional;

    /** 开课学期 */
    private Integer semester;

    /**视频路径*/
    private Long videoId;

    private CourseUser user;

    private Set<IdeoDto> ideos;
}