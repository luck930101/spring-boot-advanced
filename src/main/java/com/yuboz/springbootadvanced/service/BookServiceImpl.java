package com.yuboz.springbootadvanced.service;

import com.yuboz.springbootadvanced.domain.Book;
import com.yuboz.springbootadvanced.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id){
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }

}
