package com.github.p2g3.dataVisualization.io;

import java.util.List;

public interface Dao<E> {

    void insertAll(StorageVar e);

    List<E> readAll(StorageVar e);
}