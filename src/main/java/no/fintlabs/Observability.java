package no.fintlabs;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class Observability {

    private final Counter federatedUsersCounter;
    private final Counter federatedUsersDeletedCounter;

    public Observability(MeterRegistry registry) {

        federatedUsersCounter = Counter.builder("flais.nam.federated-users")
                .description("The total number of federated users at the time we query for users to clean up.")
                .register(registry);

        federatedUsersDeletedCounter = Counter.builder("flais.nam.federated-users.deleted")
                .description("The total number of federated users deleted at the time of clean up.")
                .register(registry);
    }

    public void updatedFederatedUsers(double count) {
        federatedUsersCounter.increment(count);
    }

    public void updateFederatedDeletedUsers(double count) {
        federatedUsersDeletedCounter.increment(count);
    }
}
