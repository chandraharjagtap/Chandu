package com.acks.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acks.model.Shop;

@Repository
public interface Shoprepository extends CrudRepository<Shop, Integer> {

}
