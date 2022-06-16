package pl.altkom.scah.dogservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.altkom.scah.dogservice.controller.mapper.ResponseMapper;
import pl.altkom.scah.dogservice.controller.model.CreateDogRequest;
import pl.altkom.scah.dogservice.controller.model.Dog;
import pl.altkom.scah.dogservice.controller.model.UpdateDogRequest;

@Service
public class DogService {

    public List<Dog> getDogs() {
        final List<pl.altkom.scah.dogservice.repository.model.Dog> dogs = null; // Get list of dogs from db
        return ResponseMapper.map(dogs);
    }

    public Dog getDog(final Long dogId) {
        final pl.altkom.scah.dogservice.repository.model.Dog dog = null; // Get dog from db
        return ResponseMapper.map(dog);
    }

    public Dog addDog(final CreateDogRequest request) {
        final pl.altkom.scah.dogservice.repository.model.Dog savedDog = null; // Save dog data from request to db
        return ResponseMapper.map(savedDog);
    }

    public Dog modifyDog(final Long dogId, final UpdateDogRequest request) {

        final pl.altkom.scah.dogservice.repository.model.Dog sourceDog = null; // Get dog from db

        final pl.altkom.scah.dogservice.repository.model.Dog modifiedDog = null; // Update dog
        return ResponseMapper.map(modifiedDog);
    }

    public List<Dog> getOwnerDogs(final Long ownerId) {
        final List<pl.altkom.scah.dogservice.repository.model.Dog> dogs = null; // Find owner dogs
        return ResponseMapper.map(dogs);
    }
}
