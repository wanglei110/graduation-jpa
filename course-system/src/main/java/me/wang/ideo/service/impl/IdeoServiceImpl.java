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
package me.wang.ideo.service.impl;

import me.wang.exception.BadRequestException;
import me.wang.ideo.domain.Ideo;
import me.wang.ideo.service.dto.IdeoQueryCriteria;
import me.wang.modules.system.domain.User;
import me.wang.utils.*;
import me.wang.modules.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import me.wang.ideo.repository.IdeoRepository;
import me.wang.ideo.service.IdeoService;
import me.wang.ideo.service.dto.IdeoDto;
import me.wang.ideo.service.mapstruct.IdeoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website
* @description 服务实现
* @author wang
* @date 2022-04-26
**/
@Service
@RequiredArgsConstructor
public class IdeoServiceImpl implements IdeoService {

    private final IdeoRepository ideoRepository;
    private final IdeoMapper ideoMapper;
    private final UserRepository userRepository;

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
        User user=userRepository.findUserById(SecurityUtils.getCurrentUserId());
        resources.setUser(user);
        return ideoMapper.toDto(ideoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Ideo resources) {
        if(!resources.getUser().getId().equals(SecurityUtils.getCurrentUserId())){
            throw new BadRequestException("只有创建该课程的用户可以编辑！");
        }
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

}