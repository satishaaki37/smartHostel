package com.smarthostel.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthostel.entity.Owner;
import com.smarthostel.repository.OwnerRepository;
import com.smarthostel.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService
{
	
	@Autowired
	OwnerRepository ownerRepository;
	@Override
	public List<Owner> getAllOwners() {
		
		return ownerRepository.findAll();
	}
	@Override
	public Owner registerOwner(Owner owner) {
		
		return ownerRepository.save(owner);
	}

}
