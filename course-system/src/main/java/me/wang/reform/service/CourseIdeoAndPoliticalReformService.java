
package me.wang.reform.service;

import me.wang.reform.domain.CourseIdeoAndPoliticalReform;
import me.wang.reform.service.dto.CourseIdeoAndPoliticalReformDto;
import me.wang.reform.service.dto.CourseIdeoAndPoliticalReformQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;


/**
* @website https://el-admin.vip
* @description 服务接口
* @author wang
* @date 2022-05-18
**/
public interface CourseIdeoAndPoliticalReformService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(CourseIdeoAndPoliticalReformQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<CourseIdeoAndPoliticalReformDto>
    */
    List<CourseIdeoAndPoliticalReformDto> queryAll(CourseIdeoAndPoliticalReformQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return CourseIdeoAndPoliticalReformDto
     */
    CourseIdeoAndPoliticalReformDto findById(Long id);

    /**
    * 创建
    * @param
    * @return CourseIdeoAndPoliticalReformDto
    */
    CourseIdeoAndPoliticalReformDto create(String name, MultipartFile file, String project, String type);

    /**
    * 编辑
    * @param resources /
    */
    void update(CourseIdeoAndPoliticalReform resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);


}