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
import me.wang.annotation.Query;

/**
* @website
* @author wang
* @date 2022-04-26
**/
@Data
public class CourseQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String courseName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String courseCode;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String courseType;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String courseNature;

    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    private Float credit;

    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    private Integer totalHours;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String academy;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String teachingGroup;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String forProfessional;

    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    private Integer semester;
}