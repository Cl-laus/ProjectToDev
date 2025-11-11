package fr.m2i.project_to_dev.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fr.m2i.project_to_dev.controller.dto.ProjectOwnerBasicDTO;
import fr.m2i.project_to_dev.controller.dto.ProjectOwnerDetailDTO;
import fr.m2i.project_to_dev.entity.ProjectOwner;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectOwnerMapper {

    ProjectOwnerDetailDTO toDto(ProjectOwner entity);
    

    ProjectOwnerBasicDTO toListDto(ProjectOwner entity);

    ProjectOwner toEntity(ProjectOwnerBasicDTO dto);

    ProjectOwner toEntity(ProjectOwnerDetailDTO dto);
}
