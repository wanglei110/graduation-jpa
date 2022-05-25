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
package me.wang.paper.service.impl;

import me.wang.paper.domain.Paper;

import lombok.RequiredArgsConstructor;
import me.wang.paper.repository.PaperRepository;
import me.wang.paper.service.PaperService;
import me.wang.paper.service.dto.PaperDto;
import me.wang.paper.service.dto.PaperQueryCriteria;
import me.wang.paper.service.mapstruct.PaperMapper;

import me.wang.utils.PageUtil;
import me.wang.utils.QueryHelp;
import me.wang.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;


/**
* @website https://el-admin.vip
* @description 服务实现
* @author wang
* @date 2022-05-18
**/
@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {

    private final PaperRepository paperRepository;
    private final PaperMapper paperMapper;

    @Override
    public Map<String,Object> queryAll(PaperQueryCriteria criteria, Pageable pageable){
        Page<Paper> page = paperRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);

        return PageUtil.toPage(page.map(paperMapper::toDto));
    }

    @Override
    public List<PaperDto> queryAll(PaperQueryCriteria criteria){
        return paperMapper.toDto(paperRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PaperDto findById(Long id) {
        Paper paper = paperRepository.findById(id).orElseGet(Paper::new);
        ValidationUtil.isNull(paper.getId(),"Paper","id",id);
        return paperMapper.toDto(paper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaperDto create(Paper resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId()); 
        return paperMapper.toDto(paperRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Paper resources) {
        Paper paper = paperRepository.findById(resources.getId()).orElseGet(Paper::new);
        ValidationUtil.isNull( paper.getId(),"Paper","id",resources.getId());
        paper.copy(resources);
        paperRepository.save(paper);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            paperRepository.deleteById(id);
        }
    }

}