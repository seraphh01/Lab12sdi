package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.model.Orders;


public interface OrderRepository extends org.springframework.data.jpa.repository.JpaRepository<Orders, Long> {

}
