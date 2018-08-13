package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Borrow;
import com.mdevi.booklib.model.BorrowID;
import com.mdevi.booklib.model.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowRepository extends CrudRepository<Borrow, BorrowID> {

    List<Borrow> findById_Reader(Reader reader);

    List<Borrow> findBorrowsById_ReaderId(Integer readerId);
}
