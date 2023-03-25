package no.fintlabs.federateduser;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FederatedUserRepository extends LdapRepository<FederatedUser> {
}
