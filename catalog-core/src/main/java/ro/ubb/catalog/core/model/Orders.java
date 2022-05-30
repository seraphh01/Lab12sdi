package ro.ubb.catalog.core.model;


import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Data
public class Orders extends BaseEntity<Long> {
    public String details;
}