package me.wang.course.service.mapstruct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import me.wang.course.domain.Course;
import me.wang.course.service.dto.CourseDto;
import me.wang.course.service.dto.CourseUser;
import me.wang.ideo.domain.Ideo;
import me.wang.ideo.service.dto.IdeoDto;
import me.wang.ideo.service.mapstruct.IdeoMapper;
import me.wang.modules.system.domain.Role;
import me.wang.modules.system.domain.User;
import me.wang.modules.system.service.dto.RoleName;
import me.wang.modules.system.service.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-23T15:54:02+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Autowired
    private IdeoMapper ideoMapper;
    @Autowired
    private UserMapper userMapper;

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
        course.setVideoPath( dto.getVideoPath() );
        course.setUser( courseUserToUser( dto.getUser() ) );
        course.setIdeos( ideoDtoSetToIdeoSet( dto.getIdeos() ) );
        course.setVideoId( dto.getVideoId() );

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
        courseDto.setVideoId( entity.getVideoId() );
        courseDto.setVideoPath( entity.getVideoPath() );
        courseDto.setUser( userToCourseUser( entity.getUser() ) );
        courseDto.setIdeos( ideoSetToIdeoDtoSet( entity.getIdeos() ) );

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

    protected Role roleNameToRole(RoleName roleName) {
        if ( roleName == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleName.getId() );
        role.setName( roleName.getName() );

        return role;
    }

    protected Set<Role> roleNameSetToRoleSet(Set<RoleName> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleName roleName : set ) {
            set1.add( roleNameToRole( roleName ) );
        }

        return set1;
    }

    protected User courseUserToUser(CourseUser courseUser) {
        if ( courseUser == null ) {
            return null;
        }

        User user = new User();

        user.setId( courseUser.getId() );
        user.setRoles( roleNameSetToRoleSet( courseUser.getRoles() ) );
        user.setUsername( courseUser.getUsername() );
        user.setNickName( courseUser.getNickName() );
        user.setGender( courseUser.getGender() );
        user.setIsAdmin( courseUser.getIsAdmin() );

        return user;
    }

    protected Set<Ideo> ideoDtoSetToIdeoSet(Set<IdeoDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Ideo> set1 = new HashSet<Ideo>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IdeoDto ideoDto : set ) {
            set1.add( ideoMapper.toEntity( ideoDto ) );
        }

        return set1;
    }

    protected RoleName roleToRoleName(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleName roleName = new RoleName();

        roleName.setId( role.getId() );
        roleName.setName( role.getName() );

        return roleName;
    }

    protected Set<RoleName> roleSetToRoleNameSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleName> set1 = new HashSet<RoleName>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleName( role ) );
        }

        return set1;
    }

    protected CourseUser userToCourseUser(User user) {
        if ( user == null ) {
            return null;
        }

        CourseUser courseUser = new CourseUser();

        courseUser.setId( user.getId() );
        courseUser.setRoles( roleSetToRoleNameSet( user.getRoles() ) );
        courseUser.setUsername( user.getUsername() );
        courseUser.setNickName( user.getNickName() );
        courseUser.setGender( user.getGender() );
        courseUser.setIsAdmin( user.getIsAdmin() );

        return courseUser;
    }

    protected Set<IdeoDto> ideoSetToIdeoDtoSet(Set<Ideo> set) {
        if ( set == null ) {
            return null;
        }

        Set<IdeoDto> set1 = new HashSet<IdeoDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Ideo ideo : set ) {
            set1.add( ideoMapper.toDto( ideo ) );
        }

        return set1;
    }
}
