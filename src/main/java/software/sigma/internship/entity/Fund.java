package software.sigma.internship.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "fund")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Fund {

    @Id
    @Column(name = "id",
            nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",
            nullable = false)
    String name;

    @Column(name = "description",
            columnDefinition = "TEXT",
            nullable = false
    )
    String description;

    @Column(name = "link",
            nullable = false)
    String link;

    @Column(name = "update_date",
            nullable = false)
    private Date updateDate;

    @Column(name = "create_date",
            nullable = false)
    private Date createDate;
}
