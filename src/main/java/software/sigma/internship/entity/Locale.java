package software.sigma.internship.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "locale")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Locale {

    @Id
    @Column(name = "id",
            nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",
            nullable = false)
    private String name;

    @Column(name = "iso_code",
            nullable = false,
            length = 2)
    private String isoCode;
}
