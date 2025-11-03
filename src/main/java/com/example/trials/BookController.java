package com.example.trials;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    // Create
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return repository.save(book);
    }

    // Read all
    @GetMapping
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // Read by ID
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = repository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            return repository.save(book);
        }
        return null;
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return "Book deleted with id: " + id;
    }
}
