package com.gropoint.services.principals;

import com.gropoint.dto.ProgramDTO;
import com.gropoint.models.entities.principals.Product;
import com.gropoint.models.entities.principals.Program;
import com.gropoint.models.repositories.principals.ProductRepos;
import com.gropoint.models.repositories.principals.ProgramRepos;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.CustomField;
import com.gropoint.responses.ResponseGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProgramService {
    @Autowired
    private ProgramRepos programRepository;

    @Autowired
    private ProductRepos productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    public ResponseEntity<CommonResponse> saveProgram(ProgramDTO payload){
        Program program = modelMapper.map(payload, Program.class);
        payload.setName(payload.getName().trim());
        Long tempProd = 0L;

        Program currentProgram = new Program();
        if (program.getId() != null){
            currentProgram = programRepository.findById(program.getId()).get();
            tempProd = currentProgram.getProductId();
            currentProgram.setName(program.getName());
            currentProgram.setPoint(program.getPoint());
            currentProgram.setAmountTransaction(program.getAmountTransaction());
            currentProgram.setAmountUsage(program.getAmountUsage());
            currentProgram.setDuration(program.getDuration());
            currentProgram.setStatus(program.getStatus());
            currentProgram.setStartDate(program.getStartDate());
            currentProgram.setEndDate(program.getEndDate());
            currentProgram.setProductId(program.getProductId());
            currentProgram.setDescription(program.getDescription());
            currentProgram.setRepeats(program.isRepeats());
            currentProgram.setMultiple(program.isMultiple());
            program = currentProgram;
        }
        program = programRepository.save(program);

        if (program.getProductId() != 0){
            Product prod = productRepository.GetProductByID(program.getProductId());
            prod.setProgram(program);
            productRepository.save(prod);
        }

        if(tempProd != 0 && program.getProductId() != tempProd){
            Product prod = productRepository.GetProductByID(tempProd);
            prod.setProgram(null);
            productRepository.save(prod);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(program,
                        "Success save program."));
    }

    public Iterable<Program> findAllPrograms(){
        return programRepository.findAll();
    }

    public ResponseEntity<CommonResponse> findOneProgram(Long id){
        Optional<CustomField.getProgram> program = Optional.ofNullable(programRepository.getOneProgramById(id));

        if (!program.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("Not found program with id."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(program.get(),
                        "Success get one program with id."));
    }

    public void removeOneProgram(Long id){
        programRepository.deleteById(id);
    }

    public ResponseEntity<CommonResponse> findProgramByPrincipalId(Long id){
        Iterable<CustomField.getProgram> listProgram = programRepository.getProgramByPrincipalId(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                responseGenerator.success(listProgram,"Success get all program with principal ID.")
        );
    }
}
