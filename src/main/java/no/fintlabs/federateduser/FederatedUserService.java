package no.fintlabs.federateduser;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.ldap.LdapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class FederatedUserService {

    @Value("${fint.ldap.federated-user-base}")
    private String federatedUserBase;

    private final LdapService ldapService;

    public FederatedUserService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    public void deleteAllFederatedUsers() {
        AtomicInteger deletedUserCount = new AtomicInteger();
        List<FederatedUser> federatedUsers = ldapService.getAll(federatedUserBase, FederatedUser.class);
        log.info("Found {} users.", federatedUsers.size());
        federatedUsers.forEach(federatedUser -> {
                    log.info("Deleting {}", federatedUser.getDn());
                    ldapService.deleteEntry(federatedUser);
                    deletedUserCount.getAndIncrement();
                }
        );
        log.info("Deleted {} of {} users", federatedUsers.size(), deletedUserCount);
    }
}
