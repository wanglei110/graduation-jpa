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
package me.zhengjie.ideo.service.impl;

import me.zhengjie.ideo.domain.Ideo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.ideo.repository.IdeoRepository;
import me.zhengjie.ideo.service.IdeoService;
import me.zhengjie.ideo.service.dto.IdeoDto;
import me.zhengjie.ideo.service.dto.IdeoQueryCriteria;
import me.zhengjie.ideo.service.mapstruct.IdeoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author wang
* @date 2022-04-26
**/
@Service
@RequiredArgsConstructor
public class IdeoServiceImpl implements IdeoService {

    private final IdeoRepository ideoRepository;
    private final IdeoMapper ideoMapper;

    @Override
    public Map<String,Object> queryAll(IdeoQueryCriteria criteria, Pageable pageable){
        Page<Ideo> page = ideoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(ideoMapper::toDto));
    }

    @Override
    public List<IdeoDto> queryAll(IdeoQueryCriteria criteria){
        return ideoMapper.toDto(ideoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public IdeoDto findById(Long id) {
        Ideo ideo = ideoRepository.findById(id).orElseGet(Ideo::new);
        ValidationUtil.isNull(ideo.getId(),"Ideo","id",id);
        return ideoMapper.toDto(ideo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IdeoDto create(Ideo resources) {
        return ideoMapper.toDto(ideoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Ideo resources) {
        Ideo ideo = ideoRepository.findById(resources.getId()).orElseGet(Ideo::new);
        ValidationUtil.isNull( ideo.getId(),"Ideo","id",resources.getId());
        ideo.copy(resources);
        ideoRepository.save(ideo);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            ideoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<IdeoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (IdeoDto ideo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建时间", ideo.getCreateTime());
            map.put("知识点", ideo.getKnowledgePoint());
            map.put("内容", ideo.getContent());
            map.put(" userId",  ideo.getUserId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}