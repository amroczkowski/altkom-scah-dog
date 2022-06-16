package pl.altkom.scah.dogservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import pl.altkom.scah.dogservice.controller.mapper.RequestMapper;
import pl.altkom.scah.dogservice.controller.mapper.ResponseMapper;
import pl.altkom.scah.dogservice.controller.model.CreateDogRequest;
import pl.altkom.scah.dogservice.controller.model.Dog;
import pl.altkom.scah.dogservice.controller.model.UpdateDogRequest;
import pl.altkom.scah.dogservice.repository.DogRepository;

@RequiredArgsConstructor
@Service
public class DogService {

    private final DogRepository dogRepository;

    public List<Dog> getDogs() {
        return ResponseMapper.map(dogRepository.findAll());
    }

    public Dog getDog(final Long dogId) {
        final pl.altkom.scah.dogservice.repository.model.Dog dog = dogRepository
                .findById(dogId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseMapper.map(dog);
    }

    public Dog addDog(final CreateDogRequest request) {
        final pl.altkom.scah.dogservice.repository.model.Dog savedDog = dogRepository
                .save(RequestMapper.bind(request));
        return ResponseMapper.map(savedDog);
    }

    public Dog modifyDog(final Long dogId, final UpdateDogRequest request) {

        final pl.altkom.scah.dogservice.repository.model.Dog sourceDog = dogRepository
                .findById(dogId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final pl.altkom.scah.dogservice.repository.model.Dog modifiedDog = dogRepository
                .save(RequestMapper.bind(request, sourceDog));
        return ResponseMapper.map(modifiedDog);
    }

    public List<Dog> getOwnerDogs(final Long ownerId) {
        return ResponseMapper.map(dogRepository.findByOwnerId(ownerId));
    }
}