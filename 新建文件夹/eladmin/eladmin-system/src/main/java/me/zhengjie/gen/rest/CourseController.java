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
package me.wang.gen.rest;

import me.wang.annotation.Log;
import me.wang.gen.domain.Course;
import me.wang.gen.service.CourseService;
import me.wang.gen.service.dto.CourseQueryCriteria;
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
* @website
* @author wang
* @date 2022-04-25
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "课程管理管理")
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('course:list')")
    public void exportCourse(HttpServletResponse response, CourseQueryCriteria criteria) throws IOException {
        courseService.download(courseService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询课程管理")
    @ApiOperation("查询课程管理")
    @PreAuthorize("@el.check('course:list')")
    public ResponseEntity<Object> queryCourse(CourseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(courseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程管理")
    @ApiOperation("新增课程管理")
    @PreAuthorize("@el.check('course:add')")
    public ResponseEntity<Object> createCourse(@Validated @RequestBody Course resources){
        return new ResponseEntity<>(courseService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程管理")
    @ApiOperation("修改课程管理")
    @PreAuthorize("@el.check('course:edit')")
    public ResponseEntity<Object> updateCourse(@Validated @RequestBody Course resources){
        courseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除课程管理")
    @ApiOperation("删除课程管理")
    @PreAuthorize("@el.check('course:del')")
    public ResponseEntity<Object> deleteCourse(@RequestBody Long[] ids) {
        courseService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}