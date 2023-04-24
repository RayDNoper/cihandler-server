package ee.rdn.CIHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ContactController
{
    private final static String API_VERSION = "/api/v1";

    @Autowired
    ContactService service;

    @GetMapping(value={API_VERSION+"/contact",API_VERSION+"/contact/{id}"}, produces = "application/json")
    public ResponseEntity<Contact[]> contact(@PathVariable(required = false) Long id,
                                             @RequestParam(required = false) String anyname) {
        if (anyname != null && !anyname.isEmpty())
            return ResponseEntity.ok(service.findContactByName(anyname).toArray(new Contact[0]));
        if (id != null)
            return ResponseEntity.ok(service.getContact(id).toArray(new Contact[0]));
        return ResponseEntity.ok(service.getAllContacts().toArray(new Contact[0]));
    }

    @PutMapping(value=API_VERSION+"/contact/{id}")
    public ResponseEntity<Long> putContact(@Validated @RequestBody Contact contact,
                                              @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.replaceOrCreateContact(contact, id));
        } catch (Exception ex) {
           throw ex;
        }
    }

    @PostMapping(value=API_VERSION+"/contact")
    public ResponseEntity<Long> postContact(@Validated @RequestBody Contact contact) {
        return ResponseEntity.ok(service.createContact(contact));
    }
}
