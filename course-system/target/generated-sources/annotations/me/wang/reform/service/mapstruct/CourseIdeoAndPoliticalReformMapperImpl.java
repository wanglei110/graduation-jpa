package me.wang.reform.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.wang.reform.domain.CourseIdeoAndPoliticalReform;
import me.wang.reform.service.dto.CourseIdeoAndPoliticalReformDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-23T15:54:02+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class CourseIdeoAndPoliticalReformMapperImpl implements CourseIdeoAndPoliticalReformMapper {

    @Override
    public CourseIdeoAndPoliticalReform toEntity(CourseIdeoAndPoliticalReformDto dto) {
        if ( dto == null ) {
            return null;
        }

        CourseIdeoAndPoliticalReform courseIdeoAndPoliticalReform = new CourseIdeoAndPoliticalReform();

        courseIdeoAndPoliticalReform.setId( dto.getId() );
        courseIdeoAndPoliticalReform.setProject( dto.getProject() );
        courseIdeoAndPoliticalReform.setProjectType( dto.getProjectType() );
        courseIdeoAndPoliticalReform.setType( dto.getType() );
        courseIdeoAndPoliticalReform.setRealName( dto.getRealName() );
        courseIdeoAndPoliticalReform.setName( dto.getName() );
        courseIdeoAndPoliticalReform.setPath( dto.getPath() );
        courseIdeoAndPoliticalReform.setCreateTime( dto.getCreateTime() );

        return courseIdeoAndPoliticalReform;
    }

    @Override
    public CourseIdeoAndPoliticalReformDto toDto(CourseIdeoAndPoliticalReform entity) {
        if ( entity == null ) {
            return null;
        }

        CourseIdeoAndPoliticalReformDto courseIdeoAndPoliticalReformDto = new CourseIdeoAndPoliticalReformDto();

        courseIdeoAndPoliticalReformDto.setId( entity.getId() );
        courseIdeoAndPoliticalReformDto.setProject( entity.getProject() );
        courseIdeoAndPoliticalReformDto.setProjectType( entity.getProjectType() );
        courseIdeoAndPoliticalReformDto.setType( entity.getType() );
        courseIdeoAndPoliticalReformDto.setRealName( entity.getRealName() );
        courseIdeoAndPoliticalReformDto.setPath( entity.getPath() );
        courseIdeoAndPoliticalReformDto.setCreateTime( entity.getCreateTime() );
        courseIdeoAndPoliticalReformDto.setName( entity.getName() );

        return courseIdeoAndPoliticalReformDto;
    }

    @Override
    public List<CourseIdeoAndPoliticalReform> toEntity(List<CourseIdeoAndPoliticalReformDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CourseIdeoAndPoliticalReform> list = new ArrayList<CourseIdeoAndPoliticalReform>( dtoList.size() );
        for ( CourseIdeoAndPoliticalReformDto courseIdeoAndPoliticalReformDto : dtoList ) {
            list.add( toEntity( courseIdeoAndPoliticalReformDto ) );
        }

        return list;
    }

    @Override
    public List<CourseIdeoAndPoliticalReformDto> toDto(List<CourseIdeoAndPoliticalReform> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourseIdeoAndPoliticalReformDto> list = new ArrayList<CourseIdeoAndPoliticalReformDto>( entityList.size() );
        for ( CourseIdeoAndPoliticalReform courseIdeoAndPoliticalReform : entityList ) {
            list.add( toDto( courseIdeoAndPoliticalReform ) );
        }

        return list;
    }
}
