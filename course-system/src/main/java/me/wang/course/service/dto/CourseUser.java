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

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import me.wang.modules.system.service.dto.RoleName;

import java.util.Set;

/**
 * @author 
 * @date 2022-04-15-11-23
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
