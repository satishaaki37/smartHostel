package com.smarthostel.service;

import java.util.List;

import com.smarthostel.entity.Owner;


public interface OwnerService {

	List<Owner> getAllOwners();

	Owner registerOwner(Owner owner);

}
