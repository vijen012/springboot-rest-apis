package com.restful.address.repos;

import org.springframework.data.repository.CrudRepository;

import com.restful.address.data.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
