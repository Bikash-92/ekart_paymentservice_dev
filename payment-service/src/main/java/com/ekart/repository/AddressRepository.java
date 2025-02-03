/**
 * Author: BIKASH
 */
package com.ekart.repository;

import com.ekart.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<ShippingAddress, Integer> {
}
