
package me.wang.reform.rest;


import me.wang.annotation.Log;
import me.wang.reform.domain.CourseIdeoAndPoliticalReform;
import me.wang.reform.service.CourseIdeoAndPoliticalReformService;
import me.wang.reform.service.dto.CourseIdeoAndPoliticalReformQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author wang
* @date 2022-05-18
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "课程思政改革管理")
@RequestMapping("/api/courseIdeoAndPoliticalReform")
public class CourseIdeoAndPoliticalReformController {

    private final CourseIdeoAndPoliticalReformService courseIdeoAndPoliticalReformService;

    @GetMapping
    @Log("查询课程思政改革")
    @ApiOperation("查询课程思政改革")
    @PreAuthorize("@el.check('courseIdeoAndPoliticalReform:list')")
    public ResponseEntity<Object> queryCourseIdeoAndPoliticalReform(CourseIdeoAndPoliticalReformQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(courseIdeoAndPoliticalReformService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程思政改革")
    @ApiOperation("新增课程思政改革")
    @PreAuthorize("@el.check('courseIdeoAndPoliticalReform:add')")
    public ResponseEntity<Object> createCourseIdeoAndPoliticalReform(@RequestParam String name,
                                                                     @RequestParam("file") MultipartFile file,
                                                                     @RequestParam String project,
                                                                     @RequestParam String type){
        return new ResponseEntity<>(courseIdeoAndPoliticalReformService.create(name,file,project,type),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程思政改革")
    @ApiOperation("修改课程思政改革")
    @PreAuthorize("@el.check('courseIdeoAndPoliticalReform:edit')")
    public ResponseEntity<Object> updateCourseIdeoAndPoliticalReform(@Validated @RequestBody CourseIdeoAndPoliticalReform resources){
        courseIdeoAndPoliticalReformService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除课程思政改革")
    @ApiOperation("删除课程思政改革")
    @PreAuthorize("@el.check('courseIdeoAndPoliticalReform:del')")
    public ResponseEntity<Object> deleteCourseIdeoAndPoliticalReform(@RequestBody Long[] ids) {
        courseIdeoAndPoliticalReformService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}