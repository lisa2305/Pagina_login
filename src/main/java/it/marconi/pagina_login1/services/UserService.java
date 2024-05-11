package it.marconi.pagina_login1.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.marconi.pagina_login1.domains.Person;

@Service
public class UserService {
    ArrayList<Person> users = new ArrayList<>();

    public ArrayList<Person> getUsers() {
        return users;
    }

    public void addUser(Person newUser) {
        users.add(newUser);
    }

    public Optional<Person> getUserByUsername(String username) {

        for(Person u : users) {
            if(u.getSurname().equals(username)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}
