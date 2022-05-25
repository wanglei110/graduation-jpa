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
package me.wang.storage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import me.wang.config.FileProperties;
import me.wang.course.domain.Course;
import me.wang.course.repository.CourseRepository;
import me.wang.storage.domain.LocalStorage;
import me.wang.storage.repository.LocalStorageRepository;
import me.wang.storage.service.LocalStorageService;
import me.wang.storage.service.dto.LocalStorageDto;
import me.wang.storage.service.dto.LocalStorageQueryCriteria;
import me.wang.storage.service.mapstruct.LocalStorageMapper;
import me.wang.utils.*;
import me.wang.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

/**
* @author
* @date 2022-03-30
*/
@Service
@RequiredArgsConstructor
public class LocalStorageServiceImpl implements LocalStorageService {

    private final LocalStorageRepository localStorageRepository;
    private final LocalStorageMapper localStorageMapper;
    private final FileProperties properties;
    private final CourseRepository courseRepository;

    @Override
    public Object queryAll(LocalStorageQueryCriteria criteria, Pageable pageable){
        Page<LocalStorage> page = localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(localStorageMapper::toDto));
    }

    @Override
    public List<LocalStorageDto> queryAll(LocalStorageQueryCriteria criteria){
        return localStorageMapper.toDto(localStorageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public LocalStorageDto findById(Long id){
        LocalStorage localStorage = localStorageRepository.findById(id).orElseGet(LocalStorage::new);
        ValidationUtil.isNull(localStorage.getId(),"LocalStorage","id",id);
        return localStorageMapper.toDto(localStorage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LocalStorage create(String name, MultipartFile multipartFile,Long courseId,String project,String projectType) {
        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        File file = FileUtil.upload(multipartFile, properties.getPath().getPath() + type +  File.separator);
        if(ObjectUtil.isNull(file)){
            throw new BadRequestException("上传失败");
        }
        if(courseId!=null&&type.equals("video")){
            Course course=courseRepository.getOne(courseId);
            if(ObjectUtil.isEmpty(course)){
                throw new BadRequestException("请输入正确的课程id");
            }
            course.setVideoPath("http://localhost:8000/file/video/"+file.getName());
        }
        if(!StringUtils.isBlank(project)&&!StringUtils.isBlank(projectType)){
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(
                    file.getName(),
                    name,
                    suffix,
                    file.getPath(),
                    type,
                    FileUtil.getSize(multipartFile.getSize()),
                    courseId,
                    project,
                    projectType
            );
            return localStorageRepository.save(localStorage);
        }else{
            try {
                name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
                LocalStorage localStorage = new LocalStorage(
                        file.getName(),
                        name,
                        suffix,
                        file.getPath(),
                        type,
                        FileUtil.getSize(multipartFile.getSize()),
                        courseId,
                        null,
                        null);
                return localStorageRepository.save(localStorage);
            }catch (Exception e){
                FileUtil.del(file);
                throw e;
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LocalStorage resources) {
        LocalStorage localStorage = localStorageRepository.findById(resources.getId()).orElseGet(LocalStorage::new);
        ValidationUtil.isNull( localStorage.getId(),"LocalStorage","id",resources.getId());
        localStorage.copy(resources);
        localStorageRepository.save(localStorage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            LocalStorage storage = localStorageRepository.findById(id).orElseGet(LocalStorage::new);
            FileUtil.del(storage.getPath());
            localStorageRepository.delete(storage);
        }
    }

}
