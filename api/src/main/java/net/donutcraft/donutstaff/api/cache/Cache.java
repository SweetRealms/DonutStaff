package net.donutcraft.donutstaff.api.cache;

import java.util.Set;

public interface Cache <T>{
    Set<T> get();

    default void add(T t) {
        get().add(t);
    }

    default void remove(T t) {
        get().remove(t);

    }

    default boolean exist(T t) {
        return get().contains(t);
    }
}
