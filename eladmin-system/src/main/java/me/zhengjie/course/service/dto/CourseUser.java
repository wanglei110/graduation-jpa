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
package me.zhengjie.course.service.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import me.zhengjie.modules.system.service.dto.RoleName;
import me.zhengjie.modules.system.service.dto.RoleSmallDto;

import java.util.Set;

/**
 * @author 
 * @date 2018-11-23
 */
@Data
public class CourseUser {

    private Long id;

    private Set<RoleName> roles;

    private Long deptId;

    private String username;

    private String nickName;

    private String gender;

    @JSONField(serialize = false)
    private Boolean isAdmin = false;
}
