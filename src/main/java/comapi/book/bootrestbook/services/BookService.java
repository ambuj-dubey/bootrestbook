package comapi.book.bootrestbook.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import comapi.book.bootrestbook.dao.BookRepository;
import comapi.book.bootrestbook.entities.Book;

@Component
@ComponentScan
public class BookService {
    @Autowired
    private BookRepository bookRepository;


   // private static List<Book> list = new ArrayList<>();

    // static{
    //     list.add(new Book(12,"Java","abc"));
    //     list.add(new Book(13,"Python","def"));
    //     list.add(new Book(14,"Angular","ghi"));
    // }
    
    //get all Books
    public List<Book> getAllBooks(){
        //return list;
        List<Book> list = (List<Book>)this.bookRepository.findAll();
        return list;
    }
    //get single book by id
    public Book getBookById(int id){
        Book book = null;
        try {                                                                           //here try and catch block added to handle the exception error
            //book  = list.stream().filter(e->e.getId()==id).findFirst().get();
            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    //adding the book
    public Book addBook(Book b){
        Book result = this.bookRepository.save(b);
        return result;
    }

    //deleting the book by id
    public void deletBook(int bid){
        //list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());
        // list =list.stream().filter(book -> {
        //     if(book.getId() != bid){
        //         return true;
        //     }else{
        //         return false;
        //     }
        // }).collect(Collectors.toList());
        this.bookRepository.deleteById(bid);
    }

    //update the book by id
    public void updateBook(Book book, int bookId){
        // list = list.stream().map(b->{
        //     if(b.getId() == bookId){
        //         b.setTitle(book.getTitle());
        //         b.setAuthor(book.getAuthor());
        //     }
        //     return b;
        // }).collect(Collectors.toList());
        book.setId(bookId);
        this.bookRepository.save(book);
    }
}
