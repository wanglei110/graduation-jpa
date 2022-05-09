package me.zhengjie.ideo.service.mapstruct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import me.zhengjie.ideo.domain.Ideo;
import me.zhengjie.ideo.service.dto.IdeoDto;
import me.zhengjie.ideo.service.dto.IdeoUser;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.service.dto.RoleName;
import me.zhengjie.modules.system.service.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-06T17:58:58+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class IdeoMapperImpl implements IdeoMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Ideo toEntity(IdeoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Ideo ideo = new Ideo();

        ideo.setId( dto.getId() );
        ideo.setCreateTime( dto.getCreateTime() );
        ideo.setKnowledgePoint( dto.getKnowledgePoint() );
        ideo.setContent( dto.getContent() );
        ideo.setUser( ideoUserToUser( dto.getUser() ) );

        return ideo;
    }

    @Override
    public IdeoDto toDto(Ideo entity) {
        if ( entity == null ) {
            return null;
        }

        IdeoDto ideoDto = new IdeoDto();

        ideoDto.setId( entity.getId() );
        ideoDto.setCreateTime( entity.getCreateTime() );
        ideoDto.setKnowledgePoint( entity.getKnowledgePoint() );
        ideoDto.setContent( entity.getContent() );
        ideoDto.setUser( userToIdeoUser( entity.getUser() ) );

        return ideoDto;
    }

    @Override
    public List<Ideo> toEntity(List<IdeoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Ideo> list = new ArrayList<Ideo>( dtoList.size() );
        for ( IdeoDto ideoDto : dtoList ) {
            list.add( toEntity( ideoDto ) );
        }

        return list;
    }

    @Override
    public List<IdeoDto> toDto(List<Ideo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IdeoDto> list = new ArrayList<IdeoDto>( entityList.size() );
        for ( Ideo ideo : entityList ) {
            list.add( toDto( ideo ) );
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

    protected User ideoUserToUser(IdeoUser ideoUser) {
        if ( ideoUser == null ) {
            return null;
        }

        User user = new User();

        user.setId( ideoUser.getId() );
        user.setRoles( roleNameSetToRoleSet( ideoUser.getRoles() ) );
        user.setUsername( ideoUser.getUsername() );
        user.setNickName( ideoUser.getNickName() );
        user.setGender( ideoUser.getGender() );
        user.setIsAdmin( ideoUser.getIsAdmin() );

        return user;
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

    protected IdeoUser userToIdeoUser(User user) {
        if ( user == null ) {
            return null;
        }

        IdeoUser ideoUser = new IdeoUser();

        ideoUser.setId( user.getId() );
        ideoUser.setRoles( roleSetToRoleNameSet( user.getRoles() ) );
        ideoUser.setUsername( user.getUsername() );
        ideoUser.setNickName( user.getNickName() );
        ideoUser.setGender( user.getGender() );
        ideoUser.setIsAdmin( user.getIsAdmin() );

        return ideoUser;
    }
}
