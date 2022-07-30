package com.main.server.controllers;

import com.main.server.model.Organization;
import com.main.server.exception.ResourceAlreadyExistException;
import com.main.server.exception.ResourceNotFoundException;
import com.main.server.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping(path = {"/api/organizations"})
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<Organization> save(@RequestBody @Valid Organization organization)
            throws ResourceAlreadyExistException {

        return new ResponseEntity<>(organizationService.save(organization), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Organization> update(@PathVariable @NotNull Long id, @RequestBody @Valid Organization organization)
            throws ResourceNotFoundException, ResourceAlreadyExistException {

        return new ResponseEntity<>(organizationService.update(id, organization), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getAll() {

        return new ResponseEntity<>(organizationService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Organization> findById(@PathVariable @NotNull Long id) throws ResourceNotFoundException {

        return new ResponseEntity<>(organizationService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Organization> delete(@PathVariable @NotNull Long id) throws ResourceNotFoundException {

        return new ResponseEntity<>(organizationService.delete(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<List<Organization>> findByName(@PathVariable @NotNull String name) {

        return ResponseEntity.ok(organizationService.findByName(name));
    }
}
