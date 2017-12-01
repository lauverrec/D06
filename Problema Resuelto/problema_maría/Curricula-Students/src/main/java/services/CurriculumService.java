/*
 * CurriculumService.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.Customer;

@Service
@Transactional
public class CurriculumService {

	// Managed repository -------------------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;

	// Supporting services ------------------------------------------------

	@Autowired
	private CustomerService			customerService;


	// Constructors -------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------------

	public Curriculum create() {
		Curriculum result;
		Customer customer;

		customer = this.customerService.findByPrincipal();
		result = new Curriculum();
		result.setCustomer(customer);

		return result;
	}

	public Curriculum findOneToEdit(final int curriculumId) {
		Curriculum result;

		result = this.curriculumRepository.findOne(curriculumId);
		this.checkPrincipal(result);

		return result;
	}

	public Collection<Curriculum> findByPrincipal() {
		Collection<Curriculum> result;
		Customer customer;

		customer = this.customerService.findByPrincipal();
		result = this.curriculumRepository.findByCustomerId(customer.getId());

		return result;
	}

	public void save(final Curriculum curriculum) {
		this.checkPrincipal(curriculum);
		this.curriculumRepository.save(curriculum);
	}

	public void delete(final Curriculum curriculum) {
		this.checkPrincipal(curriculum);
		this.curriculumRepository.delete(curriculum);
	}

	// lo he creado yo
	public Collection<Curriculum> findAll() {
		Collection<Curriculum> result;

		Assert.notNull(this.curriculumRepository);
		result = this.curriculumRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Other business methods ---------------------------------------------

	private void checkPrincipal(final Curriculum curriculum) {
		Customer customer;

		customer = this.customerService.findByPrincipal();
		Assert.isTrue(curriculum.getCustomer().equals(customer));
	}

}
