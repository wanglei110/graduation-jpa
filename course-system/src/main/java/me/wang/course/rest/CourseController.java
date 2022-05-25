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
package me.wang.course.rest;

import me.wang.annotation.AnonymousAccess;
import me.wang.annotation.Log;
import me.wang.course.service.CourseService;
import me.wang.course.service.dto.CourseQueryCriteria;
import me.wang.course.domain.Course;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @website
* @author wang
* @date 2022-04-26
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "课程管理管理")
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;


    @GetMapping
    @Log("查询课程管理")
    @ApiOperation("查询课程管理")
//    @PreAuthorize("@el.check('course:list')")
    @AnonymousAccess
    public ResponseEntity<Object> queryCourse(CourseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(courseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @AnonymousAccess
    @GetMapping(value = "/query_by_id")
    @Log("根据id查询课程管理")
    @ApiOperation("查询课程管理")
//    @PreAuthorize("@el.check('course:list')")
    public ResponseEntity<Object> queryCourseById(@RequestParam Long id){
        return new ResponseEntity<>(courseService.findById(id),HttpStatus.OK);
    }


    @PostMapping
    @Log("新增课程管理")
    @ApiOperation("新增课程管理")
//    @PreAuthorize("@el.check('course:add')")
    @AnonymousAccess
    public ResponseEntity<Object> createCourse(@Validated @RequestBody Course resources){
        return new ResponseEntity<>(courseService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程管理")
    @ApiOperation("修改课程管理")
//    @PreAuthorize("@el.check('course:edit')")
    @AnonymousAccess
    public ResponseEntity<Object> updateCourse(@Validated @RequestBody Course resources){
        courseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @DeleteMapping
    @Log("删除课程管理")
    @ApiOperation("删除课程管理")
//    @PreAuthorize("@el.check('course:del')")
    @AnonymousAccess
    public ResponseEntity<Object> deleteCourse(@RequestBody Long[] ids) {
        courseService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }



//    @AnonymousAccess
//    @GetMapping("/get_course_video")
//    public ResponseEntity<Object> getCourseVideo(HttpServletRequest request, HttpServletResponse response,@RequestParam Long courseId){
//        courseService.getCourseVideo(request,response,courseId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("upload_file")
//    @AnonymousAccess
//    public ResponseEntity<Object> createFile(@RequestParam String name, @RequestParam MultipartFile file,@RequestParam Long courseId){
//        courseService.upload(name, file,courseId);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

}