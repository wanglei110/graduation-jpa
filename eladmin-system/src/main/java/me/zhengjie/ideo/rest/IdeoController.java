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
package me.zhengjie.ideo.rest;

import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.annotation.Log;
import me.zhengjie.ideo.domain.Ideo;
import me.zhengjie.ideo.service.IdeoService;
import me.zhengjie.ideo.service.dto.IdeoQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author wang
* @date 2022-04-26
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "思政元素管理管理")
@RequestMapping("/api/ideo")
public class IdeoController {

    private final IdeoService ideoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('ideo:list')")
    public void exportIdeo(HttpServletResponse response, IdeoQueryCriteria criteria) throws IOException {
        ideoService.download(ideoService.queryAll(criteria), response);
    }


    @GetMapping
    @Log("查询思政元素管理")
    @ApiOperation("查询思政元素管理")
//    @PreAuthorize("@el.check('ideo:list')")
    @AnonymousAccess
    public ResponseEntity<Object> queryIdeo(IdeoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(ideoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增思政元素管理")
    @ApiOperation("新增思政元素管理")
//    @PreAuthorize("@el.check('ideo:add')")
    @AnonymousAccess
    public ResponseEntity<Object> createIdeo(@Validated @RequestBody Ideo resources){
        return new ResponseEntity<>(ideoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改思政元素管理")
    @ApiOperation("修改思政元素管理")
//    @PreAuthorize("@el.check('ideo:edit')")
    @AnonymousAccess
    public ResponseEntity<Object> updateIdeo(@Validated @RequestBody Ideo resources){
        ideoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除思政元素管理")
    @ApiOperation("删除思政元素管理")
//    @PreAuthorize("@el.check('ideo:del')")
    @AnonymousAccess
    public ResponseEntity<Object> deleteIdeo(@RequestBody Long[] ids) {
        ideoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}