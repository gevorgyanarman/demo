package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/objectives")
public class ObjectiveController {

    @GetMapping("/{objectiveId}")
    //127.0.0.1:8080/objectives/1
    public ResponseEntity<ObjectiveDto> getObjectiveById(@PathVariable("objectiveId") String id) {
        if (id.equals("1")) {
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
