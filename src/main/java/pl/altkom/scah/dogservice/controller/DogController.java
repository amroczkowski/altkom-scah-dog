package pl.altkom.scah.dogservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.altkom.scah.dogservice.controller.model.CreateDogRequest;
import pl.altkom.scah.dogservice.controller.model.Dog;
import pl.altkom.scah.dogservice.controller.model.UpdateDogRequest;
import pl.altkom.scah.dogservice.service.DogService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dog")
@RestController
public class DogController {

    private final DogService dogService;

    @GetMapping
    public List<Dog> getDogs() {
        return dogService.getDogs();
    }

    @GetMapping("/owner/{ownerId}")
    public List<Dog> getOwnerDogs(@PathVariable("ownerId") final Long ownerId) {
        log.info("Getting owner {} dogs", ownerId);
        return dogService.getOwnerDogs(ownerId);
    }

    @GetMapping("/{id}")
    public Dog getDog(@PathVariable("id") final Long id) {
        log.info("Getting dog {}", id);
        return dogService.getDog(id);
    }

    @PostMapping
    public Dog createDog(@RequestBody final CreateDogRequest request) {
        return dogService.addDog(request);
    }

    @PutMapping("/{id}")
    public Dog updateDog(@PathVariable("id") final Long dogId, @RequestBody final UpdateDogRequest request) {
        return dogService.modifyDog(dogId, request);
    }
}