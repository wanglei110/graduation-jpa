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
package me.wang.modules.system.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import me.wang.modules.system.service.DeptService;
import me.wang.modules.system.service.dto.DictDetailQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
* @author
* @date 2022
*/
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：部门管理")
@RequestMapping("/api/dictDetail")
public class DictController {

    private final DeptService deptService;
    private static final String ENTITY_NAME = "dept";

    @ApiOperation("查询字典详情")
    @GetMapping
    public ResponseEntity<Object> queryDictDetail(DictDetailQueryCriteria criteria,
                                                  @PageableDefault(sort = {"dictSort"}, direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>("dictDetail",HttpStatus.OK);
    }

}