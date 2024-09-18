package com.stmu.jqasample.infra.mock;

import com.stmu.jqasample.core.Location;
import com.stmu.jqasample.core.LocationPollingRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@ConditionalOnProperty(
        value = "env.runtime",
        havingValue = "mock",
        matchIfMissing = true
)
// Just a mock
public class MockLocationPollingRegistry implements LocationPollingRegistry {

    private static final Set<Location> REGISTRY = new HashSet<>();

    @Override
    public Collection<Location> getAll() {
        return new HashSet<>(REGISTRY);
    }

    @Override
    public void register(Location locationPollingRequest) {
        REGISTRY.add(locationPollingRequest);
    }
}
