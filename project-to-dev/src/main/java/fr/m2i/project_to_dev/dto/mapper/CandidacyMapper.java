package fr.m2i.project_to_dev.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fr.m2i.project_to_dev.controller.dto.CandidacyDTO;
import fr.m2i.project_to_dev.entity.Candidacy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidacyMapper {

    CandidacyDTO toDto(Candidacy entity);

    Candidacy toEntity(CandidacyDTO dto);

}
