package software.sigma.internship.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "iso_code", nullable = false, unique = true, length = 2)
    private String isoCode;
}
