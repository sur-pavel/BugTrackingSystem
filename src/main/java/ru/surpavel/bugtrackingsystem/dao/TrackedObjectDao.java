package ru.surpavel.bugtrackingsystem.dao;

import java.util.List;

import ru.surpavel.bugtrackingsystem.domain.TrackedObject;

public interface TrackedObjectDao {

    TrackedObject create(TrackedObject trackedObject);

    List<TrackedObject> findAll();

    TrackedObject findById(int id);

    TrackedObject update(TrackedObject trackedObject);

    void delete(int id);
}
