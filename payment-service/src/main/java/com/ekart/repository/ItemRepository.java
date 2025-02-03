/**
 * Author: BIKASH
 */
package com.ekart.repository;

import com.ekart.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
