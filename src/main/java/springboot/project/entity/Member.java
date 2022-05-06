package springboot.project.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

public class Member extends BaseEntity{

    @Id
    private String email;

    private String password;

    private String name;
}
