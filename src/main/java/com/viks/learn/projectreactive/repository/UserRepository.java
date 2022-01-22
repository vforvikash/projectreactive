package com.viks.learn.projectreactive.repository;

import com.viks.learn.projectreactive.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class UserRepository {

    private List<User> all = Arrays.asList(User.VIKASH, User.HETAL, User.KAKU, User.MITESH, User.VIDHI);

    public Flux<User> getAllUsers() { return Flux.fromIterable(all); }

    public Mono<User> getNewGuy() {
        return Mono.just(User.JASH);
    }

    public Mono<User> getManager() {
        return Mono.just(User.HETAL);
    }
}
