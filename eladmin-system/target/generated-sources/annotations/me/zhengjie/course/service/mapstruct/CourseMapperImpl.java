package me.zhengjie.course.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.course.domain.Course;
import me.zhengjie.course.service.dto.CourseDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-26T13:44:18+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course toEntity(CourseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( dto.getId() );
        course.setCreateTime( dto.getCreateTime() );
        course.setCourseName( dto.getCourseName() );
        course.setCourseCode( dto.getCourseCode() );
        course.setCourseType( dto.getCourseType() );
        course.setCourseNature( dto.getCourseNature() );
        course.setCredit( dto.getCredit() );
        course.setTotalHours( dto.getTotalHours() );
        course.setAcademy( dto.getAcademy() );
        course.setTeachingGroup( dto.getTeachingGroup() );
        course.setForProfessional( dto.getForProfessional() );
        course.setSemester( dto.getSemester() );
        course.setUserId( dto.getUserId() );

        return course;
    }

    @Override
    public CourseDto toDto(Course entity) {
        if ( entity == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setId( entity.getId() );
        courseDto.setCreateTime( entity.getCreateTime() );
        courseDto.setCourseName( entity.getCourseName() );
        courseDto.setCourseCode( entity.getCourseCode() );
        courseDto.setCourseType( entity.getCourseType() );
        courseDto.setCourseNature( entity.getCourseNature() );
        courseDto.setCredit( entity.getCredit() );
        courseDto.setTotalHours( entity.getTotalHours() );
        courseDto.setAcademy( entity.getAcademy() );
        courseDto.setTeachingGroup( entity.getTeachingGroup() );
        courseDto.setForProfessional( entity.getForProfessional() );
        courseDto.setSemester( entity.getSemester() );
        courseDto.setUserId( entity.getUserId() );

        return courseDto;
    }

    @Override
    public List<Course> toEntity(List<CourseDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Course> list = new ArrayList<Course>( dtoList.size() );
        for ( CourseDto courseDto : dtoList ) {
            list.add( toEntity( courseDto ) );
        }

        return list;
    }

    @Override
    public List<CourseDto> toDto(List<Course> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourseDto> list = new ArrayList<CourseDto>( entityList.size() );
        for ( Course course : entityList ) {
            list.add( toDto( course ) );
        }

        return list;
    }
}
