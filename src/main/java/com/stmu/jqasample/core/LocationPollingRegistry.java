package com.stmu.jqasample.core;

import java.util.Collection;

public interface LocationPollingRegistry {

    Collection<Location> getAll();

    void register(Location locationPollingRequest);

}
