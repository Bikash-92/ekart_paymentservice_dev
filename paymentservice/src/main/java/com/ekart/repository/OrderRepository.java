/**
 * Author: BIKASH
 */
package com.ekart.repository;


import com.ekart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
