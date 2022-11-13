package com.smartpro.controller;

import com.smartpro.dto.IssueDto;
import com.smartpro.service.impl.IssueServiceImpl;
import com.smartpro.util.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@CrossOrigin
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
}
