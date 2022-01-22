package com.viks.learn.projectreactive.exercise01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

public class LearnCreationTest {

    private final LearnCreation exercise = new LearnCreation();

    @Test
    public void getEmptyMonoTest1() {
        // subscribe to empty data
        final Mono<String> emptyMono = exercise.getEmptyMono();

        emptyMono
                .subscribe(s -> {
                   // This will not run because there is no data
                    System.out.println("String: " + s);
                    Assertions.assertNotNull(s);
                });

        Assertions.assertNotNull(emptyMono);

        StepVerifier.create(emptyMono)
                .verifyComplete();
    }

    @Test
    public void getEmptyMonoTest2() {
        // subscribe to empty data
        final Mono<String> emptyMono = exercise.getEmptyMono()
                .switchIfEmpty(Mono.just("Vikash"));

        emptyMono
                .subscribe(s -> {
                    // This will not run because there is no data
                    System.out.println("String: " + s);
                    Assertions.assertNotNull(s);
                });

        Assertions.assertNotNull(emptyMono);

        StepVerifier.create(emptyMono)
                .expectNext("Vikash")
                .verifyComplete();
    }

    /**
     * verifyComplete will keep on waiting to complete because there is no signal to complete.
     *  TODO - Read https://www.woolha.com/tutorials/project-reactor-using-mono-never-and-flux-never-examples.
     */
    @Test
    public void monoWithNoSignalTest() {
        StepVerifier.create(exercise.getMonoWithNoSignal())
                .expectSubscription()
                .expectComplete()
                .verify();
    }

    @Test
    public void getJustMonoTest1() {
        final String expected = "Sample";

        exercise.getJustMono(Optional.empty()).subscribe(System.out::println);

        StepVerifier.create(exercise.getJustMono(Optional.empty()))
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    public void getJustMonoTest2() {
        final String expected = "Vikash";

        exercise.getJustMono(Optional.of("Vikash")).subscribe(System.out::println);

        StepVerifier.create(exercise.getJustMono(Optional.of("Vikash")))
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    public void getJustMonoTest3() {
        final String expected = null; // your name
        Assertions.assertNotNull(expected);
        // TODO create a Mono string of your name and test
    }

    @Test
    public void getSurprisingMonoTest1() {
        final String expected = null; // your name
        Assertions.assertNotNull(expected);
        // TODO create a Mono string of your name and test
    }

    @Test
    public void getSurprisingMonoTest2() {
        StepVerifier.create(exercise.getSurprisingMono().onErrorReturn("Vikash"))
                .expectNext("Vikash")
                .verifyComplete();
    }


    @Test
    public void  getEmptyFluxTest() {
        // TODO consume empty flux
    }

    @Test
    public void  getEmptyFluxTest1() {
        final Flux<String> stringFlux = exercise.getEmptyFlux().switchIfEmpty(exercise.getFooBarFlux());

        stringFlux.doOnNext(System.out::println).subscribe();

        StepVerifier.create(stringFlux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyComplete();
    }

    @Test
    public void  getFooBarFluxTest() {
        // TODO
    }

    @Test
    public void  getFooBarFluxFromListTest() {
        // TODO
    }

    @Test
    public void  errorFluxTest() {
        // TODO
    }

    @Test
    public void ounterTest() {
        // TODO
    }
}
