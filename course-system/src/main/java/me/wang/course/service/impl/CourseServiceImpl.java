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
package me.wang.course.service.impl;

import cn.hutool.core.util.ObjectUtil;
import me.wang.config.FileProperties;
import me.wang.course.repository.CourseRepository;
import me.wang.course.service.dto.CourseDto;
import me.wang.course.service.dto.CourseQueryCriteria;
import me.wang.ideo.domain.Ideo;
import me.wang.modules.system.domain.User;
import me.wang.storage.repository.LocalStorageRepository;
import me.wang.utils.*;
import me.wang.course.domain.Course;
import me.wang.exception.BadRequestException;
import me.wang.ideo.repository.IdeoRepository;
import me.wang.modules.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import me.wang.course.service.CourseService;
import me.wang.course.service.mapstruct.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;


/**
* @website
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
    //引入返回视频流的组件
    @Autowired
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
    private final LocalStorageRepository localStorageRepository;
    private final FileProperties properties;
    private final UserRepository userRepository;

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
        User user=userRepository.findUserById(SecurityUtils.getCurrentUserId());
        resources.setUser(user);
        return courseMapper.toDto(courseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Course resources) {
        if(!resources.getUser().getId().equals(SecurityUtils.getCurrentUserId())){
            throw new BadRequestException("只有创建该课程的用户可以编辑！");
        }
        Course course = courseRepository.findById(resources.getId()).orElseGet(Course::new);
        ValidationUtil.isNull( course.getId(),"Course","id",resources.getId());
        courseRepository.deleteCourseIdeoByCourseId(resources.getId());
        Set<Ideo> ideoSet=new HashSet<>();
        String [] ideoIds=resources.getIdeoIds().split(",");
        for(String id:ideoIds){
            Ideo ideo=ideoRepository.getOne(Long.parseLong(id));
            ideoSet.add(ideo);
        }
        resources.setIdeos(ideoSet);
        course.copy(resources);
        courseRepository.save(course);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            Course course = courseRepository.findById(id).orElseGet(Course::new);
            ValidationUtil.isNull(course.getId(),"Course","id",id);
            course.setIdeos(null);
            courseRepository.deleteById(id);
        }
    }

//    /**
//     * 根据课程id将对应视频传给前端
//     * */
//    @Override
//    public void getCourseVideo(HttpServletRequest request, HttpServletResponse response, Long courseId) {
//        try {
//            LocalStorage localStorage = localStorageRepository.findByCourseIdAndType(courseId,"视频");
//            if(ObjectUtils.isEmpty(localStorage)){
//                throw new BadRequestException("该课程没有视频");
//            }
//            //从视频信息中单独把视频路径信息拿出来保存
//            String videoPathUrl=localStorage.getPath();
//            //保存视频磁盘路径
//            Path filePath = Paths.get(videoPathUrl );
//            //Files.exists：用来测试路径文件是否存在
//            if (Files.exists(filePath)) {
//                //获取视频的类型，比如是MP4这样
//                String mimeType = Files.probeContentType(filePath);
//                if (StringUtils.hasText(mimeType)) {
//                    //判断类型，根据不同的类型文件来处理对应的数据
//                    response.setContentType(mimeType);
//                }
//                //转换视频流部分
//                request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
//                nonStaticResourceHttpRequestHandler.handleRequest(request, response);
//            } else {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//            }
//        }catch (Exception e){
//                throw new BadRequestException("视频获取错误!");
//            }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void upload(String name, MultipartFile multipartFile, Long courseId) {
//        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
//        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
//        String type = FileUtil.getFileType(suffix);
//        File file = FileUtil.upload(multipartFile, properties.getPath().getPath() + type +  File.separator);
//        if(ObjectUtil.isNull(file)){
//            throw new BadRequestException("上传失败");
//        }
//        try {
//            name = me.wang.utils.StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
//            LocalStorage localStorage = localStorageRepository.findByCourseIdAndType(courseId,"视频");
//            if(ObjectUtils.isEmpty(localStorage)){
//                LocalStorage newLocalStorage = new LocalStorage(
//                        file.getName(),
//                        name,
//                        suffix,
//                        file.getPath(),
//                        type,
//                        FileUtil.getSize(multipartFile.getSize()),
//                        courseId
//                );
//                newLocalStorage.setCourseId(courseId);
//                localStorageRepository.save(newLocalStorage);
//            }else{
//                localStorage.setRealName(file.getName());
//                localStorage.setName(name);
//                localStorage.setSuffix(suffix);
//                localStorage.setPath(file.getPath());
//                localStorage.setType(type);
//                localStorage.setSize(FileUtil.getSize(multipartFile.getSize()));
//                localStorageRepository.save(localStorage);
//            }
//        }catch (Exception e){
//            FileUtil.del(file);
//            throw e;
//        }
//    }

}