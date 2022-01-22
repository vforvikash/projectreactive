package com.viks.learn.projectreactive.repository;

import com.viks.learn.projectreactive.domain.Access;
import com.viks.learn.projectreactive.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class AccessRepository {

    public Mono<String> getMattermostAccess() {
        return Mono.just(Access.ACCESS_MM);
    }

    public Flux<String> getDefaultAccess() { return Flux.fromIterable(Arrays.asList(Access.DEFAULT_ACCESS_LIST)); }

    public Mono<Boolean> validateManagerAccess(Mono<User> manager) {
        return manager
                .filter(user -> user.getAccess().getPrimaryAccess().equals(Access.PRIMARY_ACCESS_MANAGER))
                .map(user -> user.getAccess().getPrimaryAccess().equals(Access.PRIMARY_ACCESS_MANAGER))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid primary access for manager")));
    }

}
