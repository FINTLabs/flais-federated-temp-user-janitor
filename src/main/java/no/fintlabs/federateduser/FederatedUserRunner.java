package no.fintlabs.federateduser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FederatedUserRunner implements ApplicationRunner {
    private final FederatedUserService federatedUserService;

    public FederatedUserRunner(FederatedUserService federatedUserService) {
        this.federatedUserService = federatedUserService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Start cleaning up federated users...");
        federatedUserService.deleteAllFederatedUsers();
        log.info("Finished cleaning up federated users!");
    }
}
