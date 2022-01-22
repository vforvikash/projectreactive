package com.viks.learn.projectreactive.exercise01;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

/**
 * Create Mono or Flux from raw data/object
 * Usually you'll not subscribe to publishers(mono/flux) when coding reactive in Spring Webflux
 */
public class LearnCreation {
    /**
     * ***************************************************************
     * ***************************************************************
     * Mono creation
     * ***************************************************************
     * ***************************************************************
     */
    public Mono<String> getEmptyMono() { return Mono.empty(); }
    public Mono<String> getMonoWithNoSignal() { return Mono.never(); }
    public Mono<String> getJustMono(Optional<String> input) { return Mono.just(input.orElse("Sample")); }
    public Mono<String> getSurprisingMono() { return Mono.error(new IllegalStateException("This session is not for blocking calls!!!")); }

    /**
     * ***************************************************************
     * ***************************************************************
     * Flux creation
     * ***************************************************************
     * ***************************************************************
     */
    public Flux<String> getEmptyFlux() { return Flux.empty(); }
    public Flux<String> getFooBarFlux() { return Flux.just("foo", "bar"); }
    public Flux<String> getFooBarFluxFromList() { return Flux.fromIterable(Arrays.asList("foo,bar".split(","))); }
    public Flux<String> errorFlux() { return Flux.error(new IllegalStateException("Testing flux exception")); }
    public Flux<Long> counter() { return Flux.interval(Duration.ofMillis(100)).take(10); }
}
