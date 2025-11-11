package fr.m2i.project_to_dev.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fr.m2i.project_to_dev.controller.dto.DevBasicDTO;
import fr.m2i.project_to_dev.controller.dto.DevDetailDTO;
import fr.m2i.project_to_dev.entity.Dev;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DevMapper {

    DevDetailDTO toDto(Dev entity);

    DevBasicDTO toListDto(Dev entity);

    Dev toEntity(DevBasicDTO dto);
    Dev toEntity(DevDetailDTO dto);

}
