package ro.ubb.catalog.core.model;


import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderProduct extends BaseEntity<Long> {
    private Integer orderID;
    private Integer productID;
    private Integer quantity;
}
