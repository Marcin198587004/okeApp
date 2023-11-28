package pl.kaminski.okeapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormRepo extends CrudRepository <Contact, Long>{

}
