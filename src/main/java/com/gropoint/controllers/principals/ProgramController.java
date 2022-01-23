package com.gropoint.controllers.principals;

import com.gropoint.dto.ProgramDTO;
import com.gropoint.models.entities.principals.Program;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @PostMapping("/program/add")
    public ResponseEntity<CommonResponse> addNewProgram(@RequestBody ProgramDTO payload){
        return programService.saveProgram(payload);
    }

    @GetMapping("/programs")
    public Iterable<Program> getAllProgram(){
        return programService.findAllPrograms();
    }

    @GetMapping("/program/{id}")
    public ResponseEntity<CommonResponse> getOneProgram(@PathVariable("id") Long id){
        return programService.findOneProgram(id);
    }

    @DeleteMapping("/program/delete/{id}")
    public void deleteOneProgram( @PathVariable("id") Long id){
        programService.removeOneProgram(id);
    }

    @GetMapping("/program/principal/{id}")
    public ResponseEntity<CommonResponse> getProgramByPrincipalId(@PathVariable("id") Long id){
        return programService.findProgramByPrincipalId(id);
    }

    @PutMapping("/program/edit")
    public ResponseEntity<CommonResponse> editProgramById(@RequestBody ProgramDTO payload){
        return programService.saveProgram(payload);
    }
}
