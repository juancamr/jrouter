package com.juancamr.route;

import java.util.concurrent.Future;

import java.util.Map;

@FunctionalInterface
public interface FunctionCustomFuture {
    Map<String, Object> apply(Map<String, Object> respuesta);
}
