package com.viks.learn.projectreactive.exercise02;

import com.viks.learn.projectreactive.domain.User;
import com.viks.learn.projectreactive.repository.AccessRepository;
import com.viks.learn.projectreactive.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transformation {

    private UserRepository repository = new UserRepository();
    private AccessRepository accessRepository = new AccessRepository();

    public Mono<User> getTeamManager() {
        return repository.getAllUsers()
                .filter(user -> user.equals(User.HETAL))
                .single();
    }

    public Flux<String> getAllUsername() {
        // TODO
        return Flux.error(new InstantiationException("missing implementation"));
    }

    public Mono<List<String>> getAllUsernameAsMono() {
        // TODO
        return Mono.error(new InstantiationException("missing implementation"));
    }

    public Mono<List<User>> getTeamListWithNewGuy() {
        // TODO
        return Mono.error(new InstantiationException("missing implementation"));
    }

    /**
     * using merge for similar kind of data
     */
    public Flux<User> getAllDevsAndIncludeNewGuy() {
        return Flux.merge(repository.getAllUsers(), repository.getNewGuy())
                .filter(user -> !User.HETAL.equals(user));
    }

    public Mono<User> giveMattermostAccessToNewGuy() {
        return Mono.zip(repository.getNewGuy(), accessRepository.getMattermostAccess())
                .map(tuple2 -> {
                    final User newGuy = tuple2.getT1();
                    String mmAccess = tuple2.getT2();
                    ArrayList<String> fullAccess = new ArrayList<>();
                    fullAccess.addAll(Arrays.asList(newGuy.getAccess().getAllAccess()));
                    fullAccess.add(mmAccess); // adding mattermost access
                    newGuy.getAccess().setAllAccess(fullAccess.toArray(new String[0]));
                    System.out.println(Thread.currentThread().getName() + "- updated newguy: " + newGuy);
                    return newGuy;
                });
    }

    /**
     * Returns Manager with primary access as manager instead of developer
     */
    public Mono<User> changePrimaryAccessForManager() {
        // TODO
        return Mono.error(new InstantiationException("missing implementation"));
    }


    public Mono<User> updateAccessToDefaultForNewGuy() {
        Mono<User> newGuyWithMmAccess = giveMattermostAccessToNewGuy();
        Flux<String> defaultAccess = accessRepository.getDefaultAccess();
        // TODO
        return Mono.error(new InstantiationException("missing implementation"));
    }

    public Mono<Boolean> validateManagerAccess() {
        return accessRepository.validateManagerAccess(repository.getManager());
    }

    public Mono<Boolean> validateManagerAccess(Mono<User> user) {
        return accessRepository.validateManagerAccess(user);
    }

    /**
     * Steps:
     * 1. get manager this.getTeamManager()
     * 2. validate manager's primary access
     * 3. handle validation exception in case of invalid manager role
     * 4. throw IllegalStateException
     */
    public Mono<User> getUserWithPrimaryAccessAsManager_ErrorOut() {
        // TODO
        return Mono.error(new InstantiationException("missing implementation"));
    }

    /**
     * Steps:
     * 1. get manager this.getTeamManager()
     * 2. validate manager's primary access
     * 3. handle validation exception in case of invalid manager role
     * 4. fallback: update manager's primary access and return
     */
    public Mono<User> getUserWithPrimaryAccessAsManager_Fallback() {
        // TODO
        return Mono.error(new InstantiationException("missing implementation"));
    }
}
