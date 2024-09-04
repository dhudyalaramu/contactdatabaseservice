package com.cms.contactdatabaseservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.contactdatabaseservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
