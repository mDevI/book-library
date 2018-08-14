package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.repository.ReaderRepository;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReaderRegistryOperations {

    private static final String ALL_LIBRARY_READERS = " ALL LIBRARY READERS ";
    private static final String READERS_BY_NAME = " READER BY NAME: ";
    private final ReaderRepository readerRepository;

    public ReaderRegistryOperations(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public void registerReader(String readerName, Byte discount) {

        if (readerRepository.findReaderByName(readerName).isPresent()) {
            System.out.println("A reader with such name is already exists.");
        } else {
            Reader reader = new Reader();
            reader.setName(readerName);
            reader.setDiscount_point(discount);
            readerRepository.save(reader);
        }
    }

    public void showAllReaders() {
        final List<Reader> readerList = readerRepository.findAll();
        if (readerList.size() > 0) {
            printReadersInTable(readerList, ALL_LIBRARY_READERS);
        } else {
            System.out.println("There are no readers yet.");
        }
    }

    private void printReadersInTable(@NotNull List<Reader> readerList, String paragraph) {
        System.out.println("___________________" + paragraph + "_____________________");
        System.out.printf("| %3s | %30s | %10s | %5s |", "ID", "READER NAME", "DISCOUNT", "POINT");
        System.out.println();
        for (Reader reader : readerList) {
            System.out.printf("| %3s | %30s | %10s | %5s |\n", reader.getId(), reader.getName(),
                    reader.getDiscount_point(), reader.getRank());
        }
        System.out.println("___________________" + paragraph + "_____________________");
    }

    public void findReadersByName(String namePattern, Boolean strictOption) {
        List<Reader> readersList = new ArrayList<>();
        if (strictOption) {
            Optional<Reader> reader = readerRepository.findReaderByName(namePattern);
            if (reader.isPresent()) {
                readersList.add(reader.get());
            }
        } else {
            readersList = readerRepository.findReadersByNameContaining(namePattern);
        }
        if (readersList.size() > 0) {
            readersList.sort(Comparator.comparingInt(Reader::getId));
            printReadersInTable(readersList, READERS_BY_NAME.concat(namePattern.toUpperCase()));
        } else {
            System.out.println("No readers found.");
        }

    }
}
