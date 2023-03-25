package no.fintlabs.federateduser;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FederatedUserService {


    private final FederatedUserRepository federatedUserRepository;


    @Getter
    private final List<FederatedUser> federatedUsers = new ArrayList<>();

    @Getter
    private final List<FederatedUser> deletedFederatedUsers = new ArrayList<>();

    public FederatedUserService(FederatedUserRepository federatedUserRepository) {
        this.federatedUserRepository = federatedUserRepository;
    }

    @Scheduled(cron = "${flais.nam.federated-users-cleanup.cron}")
    public void deleteAllFederatedUsers() {
        federatedUsers.clear();
        deletedFederatedUsers.clear();
        federatedUsers.addAll(federatedUserRepository.findAll());

        federatedUsers.forEach(federatedUser -> {
                    federatedUserRepository.delete(federatedUser);
                    deletedFederatedUsers.add(federatedUser);
                }
        );
        log.info("Deleted {} of {} users", federatedUsers.size(), deletedFederatedUsers.size());
    }
}
