package com.smartpro.controller;

import com.smartpro.dto.ProjectDto;
import com.smartpro.service.impl.ProjectServiceImpl;
import com.smartpro.util.ApiPaths;
import com.smartpro.util.TPage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@CrossOrigin
@Slf4j
public class ProjectController {
    //GET -sorgu işlemleri için
    //POST - kayıt ekleme
    //PUT - update yerine post kullanılacak
    //DELETE -delete yerine post kullanılacak

    private ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping(value = "getAllProjectPaigination")
    public ResponseEntity<TPage<ProjectDto>> getAllByPaigination(Pageable pageable){
        TPage<ProjectDto> data = projectService.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/getAllProject")
    public ResponseEntity<List<ProjectDto>> getAllProject(){
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id",required = true) Long id){
        return ResponseEntity.ok(projectService.getById(id));
    }

    @PostMapping(value = "/addProject")
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto project){
      return ResponseEntity.ok(projectService.save(project));
    }

    @PostMapping("deleteProject/")
    public ResponseEntity<Boolean> deleteProject(@RequestParam("id") long id ){
        log.info("Projectcontroller-> Delete by ıd");
        log.debug("Projectcontroller-> Delete by ıd PARAM="+id);
        return ResponseEntity.ok(projectService.delete(id));
    }
}
