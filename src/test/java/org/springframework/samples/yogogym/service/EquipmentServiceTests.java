package org.springframework.samples.yogogym.service;

import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Equipment;
import org.springframework.samples.yogogym.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EquipmentServiceTests {
	
	@Autowired
	private EquipmentService equipmentService;
	
	private static final int EQUIPMENT_ID=1;
	
	@Test
	public void shouldFindAllEquipments() {
		Collection<Equipment> equipments = this.equipmentService.findAllEquipment();
		assertThat(equipments).isNotNull();
		assertThat(equipments.size()).isEqualTo(10);
		Equipment equipment = EntityUtils.getById(equipments, Equipment.class, EQUIPMENT_ID);
		assertThat(equipment.getName()).isEqualTo("Squat-Calf");
		assertThat(equipment.getLocation()).isEqualTo("Muscle and Tonification");
	}
	
	@Test
	public void shouldFindCorrectEquipment() {
		Equipment equipment = this.equipmentService.findEquipmentById(EQUIPMENT_ID);
		assertThat(equipment.getName()).isEqualTo("Squat-Calf");
		assertThat(equipment.getLocation()).isEqualTo("Muscle and Tonification");
	}
}