package pl.kaminski.okeapp.service;

import org.springframework.stereotype.Service;
import pl.kaminski.okeapp.models.Contact;
import pl.kaminski.okeapp.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    //Todo rzuc wyjatkiem dla null
    public List<Contact> findAllContacts(String filterText) {
        if (filterText == null){
            throw new IllegalArgumentException("filterText is null");
        }

        if (filterText.isEmpty()) {
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
            throw new IllegalArgumentException("Contact is null");
        }

        contactRepository.save(contact);
    }

}
