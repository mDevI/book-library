package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Borrow;
import com.mdevi.booklib.model.BorrowID;
import org.springframework.data.repository.CrudRepository;

public interface BorrowRepository extends CrudRepository<Borrow, BorrowID> {


}
