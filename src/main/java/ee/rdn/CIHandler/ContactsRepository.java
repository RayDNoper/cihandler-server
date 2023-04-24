package ee.rdn.CIHandler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByNameContainingOrCodenameContaining(String firstname, String codename);
}
