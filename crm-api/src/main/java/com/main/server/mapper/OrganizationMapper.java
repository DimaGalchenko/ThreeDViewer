package com.main.server.mapper;

import com.main.server.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {
    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    Organization dtoToOrganization(OrganizationDto organizationDto);
    OrganizationDto organizationToDto(Organization organization);
}
