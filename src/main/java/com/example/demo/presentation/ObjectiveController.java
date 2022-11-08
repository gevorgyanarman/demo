package com.example.demo.presentation;

import java.time.LocalDateTime;

import com.example.demo.CreateObjectiveDto;
import com.example.demo.ObjectiveDto;
import com.example.demo.persistence.Objective;
import com.example.demo.persistence.ObjectiveRepository;
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
        if (id == 1) {
            ObjectiveDto dto = new ObjectiveDto();
            dto.setError("The objective with id 1 is not found");
            return ResponseEntity.status(404).body(dto);
        }

        ObjectiveDto dto = new ObjectiveDto();
        dto.setId(id);
        dto.setTitle("Increase code coverage");
        dto.setDescription("Write new unit tests to incerase the code coverage");
        dto.setSubmittedBy("aua student");
        dto.setSubmittedAt(LocalDateTime.now());
        dto.setError(null);
        return ResponseEntity.ok(dto);
    }

}
