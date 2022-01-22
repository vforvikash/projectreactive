package com.viks.learn.projectreactive.exercise02;

import com.viks.learn.projectreactive.domain.Access;
import com.viks.learn.projectreactive.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class TransformationTest {
    private final Transformation trans = new Transformation();

    @Test
    public void getTeamManagerTest() {
        trans.getTeamManager().subscribe(System.out::println);

        StepVerifier.create(trans.getTeamManager())
                .assertNext(user -> {
                    Assertions.assertEquals(User.HETAL, user);
                })
                .verifyComplete();
    }

    @Test
    public void getAllUsernameTest() {
        // TODO
    }

    @Test
    public void getAllUsernameAsMonoTest() {
        // TODO
    }

    @Test
    public void getTeamListWithNewGuyTest() {
        // TODO
    }

    @Test
    public void getAllDevsAndIncludeNewGuyTest() {
        trans.getAllDevsAndIncludeNewGuy().subscribe(System.out::println);

        StepVerifier.create(trans.getAllDevsAndIncludeNewGuy())
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void giveMattermostAccessToNewGuyTest() {
        System.out.println(Thread.currentThread().getName()); // Thread

        trans.giveMattermostAccessToNewGuy()
                .subscribeOn(Schedulers.boundedElastic()) //use a different thread pool
                .subscribe(user -> {
                    System.out.println(Thread.currentThread().getName() + " - " + user);
                });

        //validate if user has access to mattermost
        StepVerifier.create(trans.giveMattermostAccessToNewGuy())
                .assertNext(user -> {
                    Assertions.assertEquals(User.JASH.getUsername(), user.getUsername());
                    final List<String> allAccess = Arrays.asList(user.getAccess().getAllAccess());
                    Assertions.assertTrue(allAccess.contains(Access.ACCESS_MM));
                })
                .verifyComplete();
    }

    @Test
    public void changePrimaryAccessForManagerTest() {
        // TODO
    }

    @Test
    public void updateAccessToDefaultForNewGuyTest() {
        // TODO
    }

    @Test
    public void validateManagerAccessTest() {
        trans.validateManagerAccess().subscribe(System.out::println);

        StepVerifier.create(trans.validateManagerAccess())
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void validateManagerAccessTest2() {
        final Mono<User> manager = trans.getTeamManager().flatMap(user -> {
            user.getAccess().setPrimaryAccess(Access.PRIMARY_ACCESS_MANAGER);
            return Mono.just(user);
        });

        StepVerifier.create(trans.validateManagerAccess(manager))
                .expectNext(true)
                .verifyComplete();
    }

    // TODO add actual implementation and run this test case
    @Test
    public void getUserWithPrimaryAccessAsManagerTest1() {
        trans.getUserWithPrimaryAccessAsManager_ErrorOut().subscribe(System.out::println);

        StepVerifier.create(trans.getUserWithPrimaryAccessAsManager_ErrorOut())
                .expectError(IllegalStateException.class)
                .verify();
    }

    // TODO add actual implementation and run this test case
    @Test
    public void getUserWithPrimaryAccessAsManagerTest2() {
        trans.getUserWithPrimaryAccessAsManager_Fallback().subscribe(System.out::println);

        StepVerifier.create(trans.getUserWithPrimaryAccessAsManager_Fallback())
                .assertNext(user -> {
                    Assertions.assertEquals(User.HETAL.getUsername(), user.getUsername());
                    Assertions.assertEquals(Access.PRIMARY_ACCESS_MANAGER, user.getAccess().getPrimaryAccess());
                })
                .verifyComplete();
    }
}
