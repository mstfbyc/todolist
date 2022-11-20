package com.smartpro.controller;

import com.smartpro.dto.IssueDto;
import com.smartpro.service.impl.IssueServiceImpl;
import com.smartpro.util.ApiPaths;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Slf4j
@SecurityRequirement(name = "todolist")
public class IssueController {
    private final IssueServiceImpl issueServiceImpl;

    public IssueController(IssueServiceImpl issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }

    @GetMapping("getById")
    public ResponseEntity<IssueDto> getById(@RequestParam(required = true, value="id") Long id){
        IssueDto issueDto = issueServiceImpl.getById(id);
        return ResponseEntity.ok(issueDto);
    }
    //TODO: Geri kalan servis metodlarını bağlayınız.
}
