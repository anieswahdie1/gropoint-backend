package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Program;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepos extends JpaRepository<Program, Long> {
    @Query(value = "SELECT id, id_program, name_program, amount_transaction, amount_usage,\n" +
            "duration, status, repeats, multiple, point, start_date, end_date, description " +
            " \n" +
            "FROM program " +
            "WHERE deleted=false AND principal_id = ?1",nativeQuery = true)
    Iterable<CustomField.getProgram> getProgramByPrincipalId(Long id);

    @Query(value = "SELECT id, id_program, name_program, amount_transaction, amount_usage,\n" +
            "duration, status, repeats, multiple, point, start_date, end_date, description, " +
            " product_id \n" +
            "FROM program " +
            "WHERE deleted=false AND id = ?1",nativeQuery = true)
    CustomField.getProgram getOneProgramById(Long id);
}
