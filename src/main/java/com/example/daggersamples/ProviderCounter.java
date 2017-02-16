package com.example.daggersamples;

import javax.inject.Inject;
import javax.inject.Provider;

final class ProviderCounter extends BaseCounter {
    @Inject
    Provider<Integer> provider;

    public ProviderCounter() {}

    public ProviderCounter(Provider<Integer> provider) {
        this.provider = provider;
    }

    @Override
    public void print() {
        super.print();
        log(provider.get());
        log(provider.get());
        log(provider.get());
    }
}