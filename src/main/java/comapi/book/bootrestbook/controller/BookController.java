package comapi.book.bootrestbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import comapi.book.bootrestbook.entities.Book;
import comapi.book.bootrestbook.services.BookService;




@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){                                       //ResponseEntity is helpfull for putting the HttpStatus code 
        // Book book = new Book();
        // book.setId(1234);
        // book.setTitle("Java Complete Reference");
        // book.setAuthor("xyz");
        //return book;
        List <Book> list = this.bookService.getAllBooks();                              //here coding done for the HTTP status code. If data wil not be there then it will return NOT_FOUND status code
        if(list.size() <= 0){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }


    //for single book
    @GetMapping("/books/{id}") 
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){                //here we can clearly see that return type is ResponseEntity<Object> 
        Book book = bookService.getBookById(id);
        if (book == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();      // THis will give Not Found
        }
        return ResponseEntity.of(Optional.of(book));
    }

    //TO create new book handler
    @PostMapping("/books")
    public ResponseEntity<Object> addBook(@RequestBody Book book){
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            System.out.println(book);
            return ResponseEntity.of(Optional.of(book));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build(); // THis will give Internal Server Error
        }
    }

    //delet book handler
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deletBook(@PathVariable ("bookId") int bookId){
        try {
            this.bookService.deletBook(bookId);
            return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();  //THis will give No Contenet
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
        
    }

    //update book handles
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Object> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId){
        try {
            this.bookService.updateBook(book,bookId);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }
}
