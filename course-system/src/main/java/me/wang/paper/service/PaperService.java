
package me.wang.paper.service;

import me.wang.paper.domain.Paper;
import me.wang.paper.service.dto.PaperDto;
import me.wang.paper.service.dto.PaperQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;

/**
* @website https://el-admin.vip
* @description 服务接口
* @author wang
* @date 2022-05-18
**/
public interface PaperService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(PaperQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<PaperDto>
    */
    List<PaperDto> queryAll(PaperQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return PaperDto
     */
    PaperDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return PaperDto
    */
    PaperDto create(Paper resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Paper resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

}