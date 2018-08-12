package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends CrudRepository<Reader, Integer> {
    List<Reader> findAll();

    Optional<Reader> findReaderByName(String name);

    Optional<Reader> findReaderById(Integer id);

    List<Reader> findReadersByNameIsLike(String name);
}
