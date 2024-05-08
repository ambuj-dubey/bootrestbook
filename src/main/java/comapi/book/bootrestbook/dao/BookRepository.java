package comapi.book.bootrestbook.dao;

import org.springframework.data.repository.CrudRepository;

import comapi.book.bootrestbook.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
    public Book findById(int id);
}
