package witold.knightequipbackend.сonfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.repositories.KnightRepository;

@Component
public class KnightInitializer implements CommandLineRunner {

    @Autowired
    private KnightRepository knightRepository;

    @Override
    public void run(String... args) {
        if (knightRepository.count() == 0) {
            knightRepository.save(new Knight());
        }
    }
}
