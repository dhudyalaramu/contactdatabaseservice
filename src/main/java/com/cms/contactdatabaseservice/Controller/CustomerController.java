package com.cms.contactdatabaseservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.contactdatabaseservice.Repository.CustomerRepository;
import com.cms.contactdatabaseservice.entity.Customer;

@RestController
//@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		return customerRepository.findById(id).map(customer -> ResponseEntity.ok().body(customer))
				.orElse(ResponseEntity.notFound().build());

	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		if (!customerRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		customer.setId(id);
		Customer updatedCustomer = customerRepository.save(customer);
		return ResponseEntity.ok().body(updatedCustomer);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		if (!customerRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		customerRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
