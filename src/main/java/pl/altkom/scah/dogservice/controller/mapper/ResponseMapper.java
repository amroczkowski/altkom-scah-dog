package pl.altkom.scah.dogservice.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.altkom.scah.dogservice.controller.model.Dog;

public class ResponseMapper {
    public static List<Dog> map(
            final List<pl.altkom.scah.dogservice.repository.model.Dog> dogs) {
        return dogs.stream()
                .map(ResponseMapper::map)
                .collect(Collectors.toList());
    }

    public static Dog map(final pl.altkom.scah.dogservice.repository.model.Dog dog) {
        final Dog result = new Dog();
        result.setId(dog.getId());
        result.setName(dog.getName());
        result.setBreed(dog.getBreed());
        result.setDateOfBirth(dog.getDateOfBirth());
        result.setOwnerId(dog.getOwnerId());
        return result;
    }
}
