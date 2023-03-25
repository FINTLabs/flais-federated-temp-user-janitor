package no.fintlabs;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import no.fintlabs.federateduser.FederatedUserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Observability {
    private final MeterRegistry registry;
    private final FederatedUserService federatedUserService;

    public Observability(MeterRegistry registry, FederatedUserService federatedUserService) {
        this.registry = registry;
        this.federatedUserService = federatedUserService;
    }

    @PostConstruct
    public void init() {

        Gauge.builder("flais.nam.federated-users.count",
                        federatedUserService,
                        federatedUserService -> federatedUserService.getFederatedUsers().size()
                )
                .description("The total number of federated users at the time we query for users to clean up.")
                .register(registry);


        Gauge.builder("flais.nam.federated-users.deleted",
                        federatedUserService,
                        federatedUserService -> federatedUserService.getDeletedFederatedUsers().size()
                )
                .description("The total number of federated users deleted at the time of clean up.")
                .register(registry);

    }
}
