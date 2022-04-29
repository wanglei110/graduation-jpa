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
package me.zhengjie.course.service.impl;

import me.zhengjie.course.domain.Course;
import me.zhengjie.ideo.domain.Ideo;
import me.zhengjie.ideo.repository.IdeoRepository;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.course.repository.CourseRepository;
import me.zhengjie.course.service.CourseService;
import me.zhengjie.course.service.dto.CourseDto;
import me.zhengjie.course.service.dto.CourseQueryCriteria;
import me.zhengjie.course.service.mapstruct.CourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author wang
* @date 2022-04-26
**/
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final IdeoRepository ideoRepository;

    @Override
    public Map<String,Object> queryAll(CourseQueryCriteria criteria, Pageable pageable){
        Page<Course> page = courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(courseMapper::toDto));
    }

    @Override
    public List<CourseDto> queryAll(CourseQueryCriteria criteria){
        return courseMapper.toDto(courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CourseDto findById(Long id) {
        Course course = courseRepository.findById(id).orElseGet(Course::new);
        ValidationUtil.isNull(course.getId(),"Course","id",id);
        return courseMapper.toDto(course);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseDto create(Course resources) {

        return courseMapper.toDto(courseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Course resources) {
        Course course = courseRepository.findById(resources.getId()).orElseGet(Course::new);
        ValidationUtil.isNull( course.getId(),"Course","id",resources.getId());
        courseRepository.deleteCourseIdeoByCourseId(resources.getId());
        Set<Ideo> ideoSet=new HashSet<>();
        for(Ideo ideo:resources.getIdeos()){
            ideo=ideoRepository.getOne(ideo.getId());
            ideoSet.add(ideo);
        }
        resources.setIdeos(ideoSet);
        course.copy(resources);
        courseRepository.save(course);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            courseRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CourseDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CourseDto course : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建时间", course.getCreateTime());
            map.put("课程名", course.getCourseName());
            map.put("课程代码", course.getCourseCode());
            map.put("课程类别", course.getCourseType());
            map.put("课程性质(必修,选修等)", course.getCourseNature());
            map.put("学分", course.getCredit());
            map.put("总学时数", course.getTotalHours());
            map.put("开课学院", course.getAcademy());
            map.put("开课基层教学组织", course.getTeachingGroup());
            map.put("面向专业", course.getForProfessional());
            map.put("开课学期", course.getSemester());
            map.put(" userId",  course.getUser().getId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}