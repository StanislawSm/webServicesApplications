package ssmyk.example.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.service.CharacterService;

/**
 * Component for interaction with user using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    /**
     * Service for managing characters. Should be injected automatically.
     */
    private CharacterService characterService;

    /**
     * @param characterService service for managing characters
     */
    @Autowired
    public CommandLine(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Override
    public void run(String... args) throws Exception {
        characterService.findAll().forEach(System.out::println);
    }

}

