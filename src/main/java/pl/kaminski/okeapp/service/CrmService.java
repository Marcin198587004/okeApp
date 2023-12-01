package pl.kaminski.okeapp.service;

import org.springframework.stereotype.Service;
import pl.kaminski.okeapp.Contact;
import pl.kaminski.okeapp.repository.ContactRepository;

import java.util.List;

@Service
public class CrmService {
    private final ContactRepository contactRepository;
    public CrmService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

    }
    public List<Contact> findAllContacts(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(filterText);
        }
    }
    public long countContact(){
        return contactRepository.count();
    }
    public void deleteContact(Contact contact){
contactRepository.delete(contact);

    }
    public void saveContact(Contact contact){
        if(contact == null) {
            System.err.println("Contakt is null");
            return;
        }
        contactRepository.save(contact);
    }

}
