package ee.rdn.CIHandler;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@ToString
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String codename;
    private String contactPhone;

    public Contact(String name, String codename, String contactPhone) {
        this.name = name;
        this.codename = codename;
        this.contactPhone = contactPhone;
    }

    public Contact() {
    }
}
