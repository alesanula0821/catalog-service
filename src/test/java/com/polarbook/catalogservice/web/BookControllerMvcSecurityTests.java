package com.polarbook.catalogservice.web;

import com.polarbook.catalogservice.configuration.SecurityConfiguration;
import com.polarbook.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
public class BookControllerMvcSecurityTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtDecoder jwtDecoder;

    @MockBean
    BookService bookService;

    @Test
    void whenDeleteBookWithEmployeeRoleThenShouldReturn204() throws Exception {
        var isbn = "7373731394";
        Mockito.doNothing().when(bookService).removeBookFromCatalog(isbn);
        mockMvc
            .perform(MockMvcRequestBuilders.delete("/books/" + isbn)
                    .with(SecurityMockMvcRequestPostProcessors.jwt().authorities(new SimpleGrantedAuthority("ROLE_employee"))))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void whenDeleteBookWithCustomerRoleThenShouldReturn403() throws Exception {
        var isbn = "7373731394";
        Mockito.doNothing().when(bookService).removeBookFromCatalog(isbn);
        mockMvc
            .perform(MockMvcRequestBuilders.delete("/books/" + isbn)
                    .with(SecurityMockMvcRequestPostProcessors.jwt().authorities(new SimpleGrantedAuthority("ROLE_customer"))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }


    @Test
    void whenDeleteBookNotAuthenticatedThenShouldReturn401() throws Exception {
            var isbn = "7373731394";
            Mockito.doNothing().when(bookService).removeBookFromCatalog(isbn);
            mockMvc
                    .perform(MockMvcRequestBuilders.delete("/books/" + isbn))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
