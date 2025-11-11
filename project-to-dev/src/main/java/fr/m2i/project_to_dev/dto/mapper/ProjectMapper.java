package fr.m2i.project_to_dev.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fr.m2i.project_to_dev.controller.dto.ProjectDetailDTO;
import fr.m2i.project_to_dev.entity.Project;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    ProjectDetailDTO toDto( Project entity);
    Project toEntity(ProjectDetailDTO dto);

}
