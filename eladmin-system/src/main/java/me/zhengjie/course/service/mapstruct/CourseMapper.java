/*
*  Copyright 2019-2020
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
package me.zhengjie.course.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.course.domain.Course;
import me.zhengjie.course.service.dto.CourseDto;
import me.zhengjie.ideo.service.mapstruct.IdeoMapper;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.service.mapstruct.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author wang
* @date 2022-04-26
**/
@Mapper(componentModel = "spring", uses={IdeoMapper.class, UserMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper extends BaseMapper<CourseDto, Course> {

}