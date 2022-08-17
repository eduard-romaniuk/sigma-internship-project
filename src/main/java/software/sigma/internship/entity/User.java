package software.sigma.internship.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id",
            nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",
            nullable = false)
    private String name;

    @Column(name = "email",
            nullable = false)
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "locale_id",
            nullable = false,
            referencedColumnName = "id")
    private Locale locale;

    @Column(name = "role",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription",
            nullable = false)
    private Subscription subscription;

    @Column(name = "update_date",
            nullable = false)
    private Date updateDate;

    @Column(name = "create_date",
            nullable = false)
    private Date createDate;
}
