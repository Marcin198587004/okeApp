package pl.kaminski.okeapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface FormRepo extends CrudRepository <Form, Long>{

}
