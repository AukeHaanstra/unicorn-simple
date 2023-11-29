package nl.pancompany.unicorn.application.service;

import nl.pancompany.unicorn.application.domain.Unicorn;

public interface Dao<T, ID> {
    T findUnicorn(ID id);

    T save(T t);

    long count();
}
