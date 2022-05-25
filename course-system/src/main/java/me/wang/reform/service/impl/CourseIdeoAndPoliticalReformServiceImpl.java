
package me.wang.reform.service.impl;

import cn.hutool.core.util.ObjectUtil;
import me.wang.config.FileProperties;
import me.wang.course.domain.Course;
import me.wang.exception.BadRequestException;
import me.wang.reform.domain.CourseIdeoAndPoliticalReform;

import lombok.RequiredArgsConstructor;
import me.wang.reform.repository.CourseIdeoAndPoliticalReformRepository;
import me.wang.reform.service.CourseIdeoAndPoliticalReformService;
import me.wang.reform.service.dto.CourseIdeoAndPoliticalReformDto;
import me.wang.reform.service.dto.CourseIdeoAndPoliticalReformQueryCriteria;
import me.wang.reform.service.mapstruct.CourseIdeoAndPoliticalReformMapper;
import me.wang.storage.domain.LocalStorage;
import me.wang.storage.repository.LocalStorageRepository;
import me.wang.storage.service.dto.LocalStorageDto;
import me.wang.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @description 服务实现
* @author wang
* @date 2022-05-18
**/
@Service
@RequiredArgsConstructor
public class CourseIdeoAndPoliticalReformServiceImpl implements CourseIdeoAndPoliticalReformService {

    private final CourseIdeoAndPoliticalReformRepository courseIdeoAndPoliticalReformRepository;
    private final CourseIdeoAndPoliticalReformMapper courseIdeoAndPoliticalReformMapper;
    private final FileProperties properties;
    private final LocalStorageRepository localStorageRepository;

    @Override
    public Map<String,Object> queryAll(CourseIdeoAndPoliticalReformQueryCriteria criteria, Pageable pageable){
        Page<LocalStorage> page = localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
//        return PageUtil.toPage(page.map(courseIdeoAndPoliticalReformMapper::toDto));
        List<LocalStorage> localStorageList=page.getContent();
        List<CourseIdeoAndPoliticalReform> list=new ArrayList<>();;
        for(LocalStorage localStorage:localStorageList){
            if(!StringUtils.isBlank(localStorage.getProjectType())||!StringUtils.isBlank(localStorage.getProject())){
                CourseIdeoAndPoliticalReform courseIdeoAndPoliticalReform=new CourseIdeoAndPoliticalReform();
                BeanUtils.copyProperties(localStorage,courseIdeoAndPoliticalReform);
                list.add(courseIdeoAndPoliticalReform);
            }
        }
        Page<CourseIdeoAndPoliticalReform> politicalReforms=listConvertToPage(list,pageable);
        return PageUtil.toPage(politicalReforms.map(courseIdeoAndPoliticalReformMapper::toDto));
    }

    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public List<CourseIdeoAndPoliticalReformDto> queryAll(CourseIdeoAndPoliticalReformQueryCriteria criteria){
        return courseIdeoAndPoliticalReformMapper.toDto(courseIdeoAndPoliticalReformRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CourseIdeoAndPoliticalReformDto findById(Long id) {
        LocalStorage localStorage = localStorageRepository.findById(id).orElseGet(LocalStorage::new);
        ValidationUtil.isNull(localStorage.getId(),"LocalStorage","id",id);
        CourseIdeoAndPoliticalReform courseIdeoAndPoliticalReform=new CourseIdeoAndPoliticalReform();
        BeanUtils.copyProperties(localStorage,courseIdeoAndPoliticalReform);
        return courseIdeoAndPoliticalReformMapper.toDto(courseIdeoAndPoliticalReform);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseIdeoAndPoliticalReformDto create(String name, MultipartFile multipartFile, String project, String type) {
        CourseIdeoAndPoliticalReform courseIdeoAndPoliticalReform=new CourseIdeoAndPoliticalReform();
        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        File file = FileUtil.upload(multipartFile, properties.getPath().getPath() + "file" +  File.separator);
        if(ObjectUtil.isNull(file)){
            throw new BadRequestException("上传失败");
        }
        try {
            courseIdeoAndPoliticalReform.setProject(project);
            courseIdeoAndPoliticalReform.setProjectType(type);
            courseIdeoAndPoliticalReform.setPath(file.getPath());
            courseIdeoAndPoliticalReform.setRealName(file.getName());
            return courseIdeoAndPoliticalReformMapper.toDto(courseIdeoAndPoliticalReformRepository.save(courseIdeoAndPoliticalReform));
        }catch (Exception e){
            FileUtil.del(file);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CourseIdeoAndPoliticalReform resources) {
        LocalStorage localStorage = localStorageRepository.findById(resources.getId()).orElseGet(LocalStorage::new);
        ValidationUtil.isNull( localStorage.getId(),"LocalStorage","id",resources.getId());
        BeanUtils.copyProperties(resources,localStorage);
        localStorageRepository.save(localStorage);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            localStorageRepository.deleteById(id);
        }
    }

}