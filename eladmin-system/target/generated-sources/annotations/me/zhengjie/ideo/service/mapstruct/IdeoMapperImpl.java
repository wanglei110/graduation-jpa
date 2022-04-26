package me.zhengjie.ideo.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.ideo.domain.Ideo;
import me.zhengjie.ideo.service.dto.IdeoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-26T13:44:17+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class IdeoMapperImpl implements IdeoMapper {

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
        ideo.setUserId( dto.getUserId() );

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
        ideoDto.setUserId( entity.getUserId() );

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
}
