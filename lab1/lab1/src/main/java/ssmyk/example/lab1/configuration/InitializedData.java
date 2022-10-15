package ssmyk.example.lab1.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ssmyk.example.lab1.author.entity.Author;
import ssmyk.example.lab1.author.service.AuthorService;
import ssmyk.example.lab1.book.service.BookService;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.time.LocalDate;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Component
public class InitializedData {

    /**
     * Service for characters operations.
     */
    private final BookService bookService;

    /**
     * Service for users operations.
     */
    private final AuthorService authorService;

    @Autowired
    public InitializedData(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    @PostConstruct
    private synchronized void init() {
        Author author1 = Author.builder()
                .login("admin")
                .name("Adam")
                .surname("Cormel")
                .birthDate(LocalDate.of(1990, 10, 21))
                .email("admin@simplerpg.example.com")
                .password(Sha256Utility.hash("adminadmin"))
                .build();

        User kevin = User.builder()
                .login("kevin")
                .name("Kevin")
                .surname("Pear")
                .birthDate(LocalDate.of(2001, 1, 16))
                .email("kevin@example.com")
                .password(Sha256Utility.hash("useruser"))
                .build();

        User alice = User.builder()
                .login("alice")
                .name("Alice")
                .surname("Grape")
                .birthDate(LocalDate.of(2002, 3, 19))
                .email("alice@example.com")
                .password(Sha256Utility.hash("useruser"))
                .build();

        userService.create(admin);
        userService.create(kevin);
        userService.create(alice);

        Profession bard = Profession.builder().name("Bard").build();
        Profession cleric = Profession.builder().name("Cleric").build();
        Profession warrior = Profession.builder().name("Warrior").build();
        Profession rogue = Profession.builder().name("Rogue").build();

        professionService.create(bard);
        professionService.create(cleric);
        professionService.create(warrior);
        professionService.create(rogue);

        Character calvian = Character.builder()
                .name("Calvian")
                .age(18)
                .background("A yong bard with some infernal roots.")
                .experience(0)
                .level(1)
                .profession(bard)
                .charisma(16)
                .constitution(12)
                .strength(8)
                .portrait(getResourceAsByteArray("avatar/calvian.png"))//package relative path
                .user(kevin)
                .build();

        Character uhlbrecht = Character.builder()
                .name("Uhlbrecht")
                .age(37)
                .background("Quite experienced half-orc warrior.")
                .experience(0)
                .level(1)
                .profession(warrior)
                .charisma(8)
                .constitution(10)
                .strength(18)
                .portrait(getResourceAsByteArray("avatar/uhlbrecht.png"))//package relative path
                .user(kevin)
                .build();

        Character eloise = Character.builder()
                .name("Eloise")
                .age(32)
                .background("Human cleric.")
                .experience(0)
                .level(1)
                .profession(cleric)
                .charisma(8)
                .constitution(12)
                .strength(14)
                .portrait(getResourceAsByteArray("avatar/eloise.png"))//package relative path
                .user(alice)
                .build();

        Character zereni = Character.builder()
                .name("Zereni")
                .age(20)
                .background("Half elf rogue.")
                .experience(0)
                .level(1)
                .profession(rogue)
                .charisma(14)
                .constitution(12)
                .strength(10)
                .portrait(getResourceAsByteArray("avatar/zereni.png"))//package relative path
                .user(alice)
                .build();

        characterService.create(calvian);
        characterService.create(uhlbrecht);
        characterService.create(eloise);
        characterService.create(zereni);

    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        } catch (NullPointerException exception){
            System.out.println(exception);
            return null;
        }
    }

}

