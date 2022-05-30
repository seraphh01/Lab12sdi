package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends BaseEntity<Long>{
    private String name;
    private int grade;
}
