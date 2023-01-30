package nelson.tacocloud.controller;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import nelson.tacocloud.model.Ingredient;
import nelson.tacocloud.model.Taco;
import nelson.tacocloud.repository.TacoRepository;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
// @CrossOrigin(origins = "http://tacoc")
@Slf4j
public class TacoController {

    private TacoRepository tacoRepository;

    private RestTemplate restTemplate;

    public TacoController(TacoRepository tacoRepository, RestTemplate restTemplate) {
        this.tacoRepository = tacoRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        Pageable page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

        Ingredient ingredient = restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}",
                Ingredient.class, "FLTO");
        log.info(ingredient.getName());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") final Long id) throws Exception {
        Optional<Taco> optTaco = tacoRepository.findById(id);

        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody final Taco taco) {
        return tacoRepository.save(taco);
    }
}
