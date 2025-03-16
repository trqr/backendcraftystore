package com.beboustore.demo.repositories;

import com.beboustore.demo.models.OrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {

}
