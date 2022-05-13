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
package me.wang.ideo.service;

import me.wang.ideo.domain.Ideo;
import me.wang.ideo.service.dto.IdeoQueryCriteria;
import me.wang.ideo.service.dto.IdeoDto;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website
* @description 服务接口
* @author wang
* @date 2022-04-26
**/
public interface IdeoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(IdeoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<IdeoDto>
    */
    List<IdeoDto> queryAll(IdeoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return IdeoDto
     */
    IdeoDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return IdeoDto
    */
    IdeoDto create(Ideo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Ideo resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

}