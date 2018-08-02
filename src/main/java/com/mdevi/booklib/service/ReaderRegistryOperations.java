package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.ReaderDAO;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReaderRegistryOperations {

    private final ReaderDAO readerDAO;

    public ReaderRegistryOperations(ReaderDAO readerDAO) {
        this.readerDAO = readerDAO;
    }

    public void registerReader(String readerName, Byte discount) {
        Reader reader = new Reader();
        reader.setName(readerName);
        reader.setDiscount_point(discount);
        readerDAO.insert(reader);
    }


    public void showAllReaders() {
        List<Reader> readerList = readerDAO.findAll();
        if (readerList != null) {
            printReadersInTable(readerList);
        } else {
            System.out.println("There are no readers yet.");
        }
    }

    private void printReadersInTable(List<Reader> readerList) {

    }
}
