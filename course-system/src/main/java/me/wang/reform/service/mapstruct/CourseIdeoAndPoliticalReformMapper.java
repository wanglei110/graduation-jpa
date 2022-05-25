
package me.wang.reform.service.mapstruct;


import me.wang.base.BaseMapper;
import me.wang.reform.domain.CourseIdeoAndPoliticalReform;
import me.wang.reform.service.dto.CourseIdeoAndPoliticalReformDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @website https://el-admin.vip
* @author wang
* @date 2022-05-18
**/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseIdeoAndPoliticalReformMapper extends BaseMapper<CourseIdeoAndPoliticalReformDto, CourseIdeoAndPoliticalReform> {

}