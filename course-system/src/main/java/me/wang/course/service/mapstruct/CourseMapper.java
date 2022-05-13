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
package me.wang.course.service.mapstruct;

import me.wang.base.BaseMapper;
import me.wang.course.service.dto.CourseDto;
import me.wang.modules.system.service.mapstruct.UserMapper;
import me.wang.course.domain.Course;
import me.wang.ideo.service.mapstruct.IdeoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author wang
* @date 2022-04-26
**/
@Mapper(componentModel = "spring", uses={IdeoMapper.class, UserMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper extends BaseMapper<CourseDto, Course> {

}