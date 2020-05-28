package org.springframework.samples.yogogym.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
public class EquipmentService {

	private EquipmentRepository equipmentRepository;

	@Autowired
	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}
	
	@Transactional(readOnly=true)
	public Equipment findEquipmentById(int equipmentId)throws DataAccessException {
		return equipmentRepository.findEquipmentById(equipmentId);
	}
	
	@Transactional(readOnly=true)
	public Collection<Equipment> findAllEquipment() throws DataAccessException {
		return Lists.newArrayList(this.equipmentRepository.findAll());
	}
}
