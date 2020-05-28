package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Trainer;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TrainerServiceTests {
	
	private static final String TRAINER1_USERNAME = "trainer1";
	
	@Autowired
	private TrainerService trainingService;
	
	@Test
	public void shouldFindCorrectTrainer() {
		Trainer trainer = this.trainingService.findTrainer(TRAINER1_USERNAME);
		assertThat(trainer.getId()).isEqualTo(1);
		assertThat(trainer.getFirstName()).isEqualTo("José Manuel");
		assertThat(trainer.getLastName()).isEqualTo("David Cifuentes");
		assertThat(trainer.getNif()).isEqualTo("12345678L");
		assertThat(trainer.getEmail()).isEqualTo("josemadaci@yogogym.com");
		assertThat(trainer.getClients().size()).isEqualTo(4);
		assertThat(trainer.getUser().getUsername()).isEqualTo("trainer1");
		assertThat(trainer.getUser().getPassword()).isEqualTo("trainer1999");
	}
}