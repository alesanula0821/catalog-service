package com.polarbook.catalogservice.domain;

import org.mockito.InjectMocks;
import org.mockito.Mock;

class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;


}