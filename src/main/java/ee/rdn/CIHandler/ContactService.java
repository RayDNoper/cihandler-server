package ee.rdn.CIHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactsRepository repository;

    public List<Contact> getContact(Long id) {
        return Arrays.asList(repository.findById(id).get());
    }

    public List<Contact> getAllContacts(){
        return repository.findAll();
    }

    public Long replaceOrCreateContact(Contact contact, Long id) {
        Contact newContact = repository.findById(id).map(c -> {
            c.setName(contact.getName());
            c.setCodename(contact.getCodename());
            c.setContactPhone(contact.getContactPhone());
            return c;
        }).orElseGet( () -> contact );
        repository.save(newContact);
        return newContact.getId();

    }
    public Long replaceOrCreateContactB(Contact contact, Long id) {
        return repository.save(
            repository.findById(id).map(c -> {
                c.setName(contact.getName());
                c.setCodename(contact.getCodename());
                c.setContactPhone(contact.getContactPhone());
                return c;
            }).orElseGet( () -> contact )
        ).getId();
    }

    public Long createContact(Contact contact) {
        return repository.save(contact).getId();
    }

    public List<Contact> findContactByName(String anyname) {
        return repository.findByNameContainingOrCodenameContaining(anyname, anyname);
    }
}
