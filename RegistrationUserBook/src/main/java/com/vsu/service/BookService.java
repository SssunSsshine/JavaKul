package com.vsu.service;

import com.vsu.entity.Book;
import com.vsu.repository.BookRepository;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void insertBook(Book book) {
        bookRepository.insert(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book selectById(String id) {
        return bookRepository.selectById(Long.parseLong(id));
    }

    public List<Book> selectAllBooksByUser(Long id) {
        return bookRepository.selectAllByUserId(id);
    }

    public void updateByID(Book book) {
        bookRepository.updateByID(book);
    }
}
