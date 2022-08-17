package software.sigma.internship.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "locale")
@Table(name = "locale")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Locale {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "iso_code", nullable = false, length = 2)
    private String isoCode;

    @OneToMany(mappedBy = "locale")
    private List<User> users;
}
