package me.wang.paper.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.wang.paper.domain.Paper;
import me.wang.paper.service.dto.PaperDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-23T15:54:02+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class PaperMapperImpl implements PaperMapper {

    @Override
    public Paper toEntity(PaperDto dto) {
        if ( dto == null ) {
            return null;
        }

        Paper paper = new Paper();

        paper.setId( dto.getId() );
        paper.setOrderNo( dto.getOrderNo() );
        paper.setName( dto.getName() );

        return paper;
    }

    @Override
    public PaperDto toDto(Paper entity) {
        if ( entity == null ) {
            return null;
        }

        PaperDto paperDto = new PaperDto();

        paperDto.setId( entity.getId() );
        paperDto.setOrderNo( entity.getOrderNo() );
        paperDto.setName( entity.getName() );

        return paperDto;
    }

    @Override
    public List<Paper> toEntity(List<PaperDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Paper> list = new ArrayList<Paper>( dtoList.size() );
        for ( PaperDto paperDto : dtoList ) {
            list.add( toEntity( paperDto ) );
        }

        return list;
    }

    @Override
    public List<PaperDto> toDto(List<Paper> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PaperDto> list = new ArrayList<PaperDto>( entityList.size() );
        for ( Paper paper : entityList ) {
            list.add( toDto( paper ) );
        }

        return list;
    }
}
