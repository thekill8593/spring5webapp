package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Publisher p1 = new Publisher("Harper Collins", "Fake Street 123");
        Book book1 = new Book("Domain driven design", "1234", p1);
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);

        publisherRepository.save(p1);
        authorRepository.save(eric);
        bookRepository.save(book1);

        Author rod = new Author("Rod", "Flanders");
        Publisher p2 = new Publisher("Natilla", "Fake Street 21333");
        Book book2 = new Book("J2EE Development without EJB", "127234", p2);
        rod.getBooks().add(book2);

        publisherRepository.save(p2);
        authorRepository.save(rod);
        bookRepository.save(book2);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
