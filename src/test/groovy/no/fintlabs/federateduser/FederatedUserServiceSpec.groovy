package no.fintlabs.federateduser

import groovy.util.logging.Slf4j
import no.fintlabs.Observability
import spock.lang.Specification

@Slf4j
class FederatedUserServiceSpec extends Specification {

    private FederatedUserRepository federatedUserRepository = Mock(FederatedUserRepository)

    def "deleteAllFederatedUsers should remove all users"() {
        given:
        def federatedUserService = new FederatedUserService(federatedUserRepository, Mock(Observability.class))

        when:
        federatedUserService.deleteAllFederatedUsers()

        then:
        federatedUserService.getFederatedUsers().size() == 2
        federatedUserService.getDeletedFederatedUsers().size() == 2
        1 * federatedUserRepository.findAll() >> [new FederatedUser(), new FederatedUser()]
        2 * federatedUserRepository.delete(_ as FederatedUser)
    }
}
