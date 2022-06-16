package pl.altkom.scah.dogservice.controller.mapper;

import pl.altkom.scah.dogservice.controller.model.CreateDogRequest;
import pl.altkom.scah.dogservice.controller.model.UpdateDogRequest;
import pl.altkom.scah.dogservice.repository.model.Dog;

public class RequestMapper {

    public static Dog bind(final CreateDogRequest request) {
        final Dog dog = new Dog();
        dog.setName(request.getName());
        dog.setDateOfBirth(request.getDateOfBirth());
        dog.setBreed(request.getBreed());
        dog.setOwnerId(request.getOwnerId());
        return dog;
    }

    public static Dog bind(final UpdateDogRequest request, final Dog destination) {
        destination.setName(request.getName());
        destination.setDateOfBirth(request.getDateOfBirth());
        destination.setBreed(request.getBreed());
        destination.setOwnerId(request.getOwnerId());
        return destination;
    }
}
