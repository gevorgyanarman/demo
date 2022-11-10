package com.example.demo.presentation;

import java.util.Optional;

import com.example.demo.CreateObjectiveDto;
import com.example.demo.ObjectiveDto;
import com.example.demo.persistence.Objective;
import com.example.demo.persistence.ObjectiveRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/objectives")
public class ObjectiveController {

    private final ObjectiveRepository objectiveRepository;

    public ObjectiveController(final ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    @PostMapping
    //localhost:8090/objectives
    public ResponseEntity<ObjectiveDto> create(@RequestBody CreateObjectiveDto dto) {

        final Objective objective = new Objective();
        objective.setSubmittedBy(dto.getSubmittedBy());
        objective.setTitle(dto.getTitle());
        objective.setDescription(dto.getDescription());

        final Objective savedObjective = objectiveRepository.save(objective);


        final ObjectiveDto responseDto = new ObjectiveDto();
        responseDto.setId(savedObjective.getId());
        responseDto.setTitle(savedObjective.getTitle());
        responseDto.setDescription(savedObjective.getDescription());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{objectiveId}")
    //127.0.0.1:8080/objectives/1
    public ResponseEntity<ObjectiveDto> getObjectiveById(@PathVariable("objectiveId") Long id) {

        final Optional<Objective> objectiveOptional = objectiveRepository.findById(id);

        if (objectiveOptional.isEmpty()) {
            final ObjectiveDto dto = new ObjectiveDto();
            dto.setError("The objective with id " + id + " is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }

        final Objective objective = objectiveOptional.get();

        final ObjectiveDto dto = new ObjectiveDto();
        dto.setId(objective.getId());
        dto.setTitle(objective.getTitle());
        dto.setDescription(objective.getDescription());
        return ResponseEntity.ok(dto);
    }

}
