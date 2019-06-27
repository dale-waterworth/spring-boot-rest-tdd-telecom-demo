package com.dale.telecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dale.telecom.entity.PhoneNumber;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {

	public List<PhoneNumber> findAll();

	public List<PhoneNumber> findAllBycustomerId(Integer customerId);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE  PhoneNumber p set p.active = ?1 WHERE p.id = ?2")
	@Transactional
	int setActiveForPhoneNumber(boolean active, Long id);
}