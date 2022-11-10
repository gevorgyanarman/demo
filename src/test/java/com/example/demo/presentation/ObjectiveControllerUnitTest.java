package com.example.demo.presentation;

import java.util.Optional;

import com.example.demo.ObjectiveDto;
import com.example.demo.persistence.Objective;
import com.example.demo.persistence.ObjectiveRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ObjectiveControllerUnitTest {

    private ObjectiveController objectiveController;

    @Test
    void testGetObjectiveById1WhenObjectiveIsNotFound() {
        objectiveController = new ObjectiveController(new ObjectiveRepository() {
            @Override
            public <S extends Objective> S save(final S entity) {
                return null;
            }

            @Override
            public <S extends Objective> Iterable<S> saveAll(final Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<Objective> findById(final Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(final Long aLong) {
                return false;
            }

            @Override
            public Iterable<Objective> findAll() {
                return null;
            }

            @Override
            public Iterable<Objective> findAllById(final Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(final Long aLong) {

            }

            @Override
            public void delete(final Objective entity) {

            }

            @Override
            public void deleteAllById(final Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(final Iterable<? extends Objective> entities) {

            }

            @Override
            public void deleteAll() {

            }
        });
        final ResponseEntity<ObjectiveDto> result = objectiveController.getObjectiveById(1L);

        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(result.getBody().getId()).isNull();
        Assertions.assertThat(result.getBody().getSubmittedAt()).isNull();
        Assertions.assertThat(result.getBody().getDescription()).isNull();
        Assertions.assertThat(result.getBody().getTitle()).isNull();

        Assertions.assertThat(result.getBody().getError())
            .isEqualTo("The objective with id 1 is not found");
    }

    @Test
    void testGetObjectiveById2WhenObjectiveIsNotFound() {
        objectiveController = new ObjectiveController(new ObjectiveRepository() {
            @Override
            public <S extends Objective> S save(final S entity) {
                return null;
            }

            @Override
            public <S extends Objective> Iterable<S> saveAll(final Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<Objective> findById(final Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(final Long aLong) {
                return false;
            }

            @Override
            public Iterable<Objective> findAll() {
                return null;
            }

            @Override
            public Iterable<Objective> findAllById(final Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(final Long aLong) {

            }

            @Override
            public void delete(final Objective entity) {

            }

            @Override
            public void deleteAllById(final Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(final Iterable<? extends Objective> entities) {

            }

            @Override
            public void deleteAll() {

            }
        });
        final ResponseEntity<ObjectiveDto> result = objectiveController.getObjectiveById(2L);

        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(result.getBody().getId()).isNull();
        Assertions.assertThat(result.getBody().getSubmittedAt()).isNull();
        Assertions.assertThat(result.getBody().getDescription()).isNull();
        Assertions.assertThat(result.getBody().getTitle()).isNull();

        Assertions.assertThat(result.getBody().getError())
            .isEqualTo("The objective with id 2 is not found");
    }

    @Test
    void testGetObjectiveByIdWhenObjectiveIsFound() {
        objectiveController = new ObjectiveController(new ObjectiveRepository() {
            @Override
            public <S extends Objective> S save(final S entity) {
                return null;
            }

            @Override
            public <S extends Objective> Iterable<S> saveAll(final Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<Objective> findById(final Long aLong) {
                final Objective objective = new Objective();
                objective.setId(10L);
                objective.setTitle("Increase code coverage");
                objective.setDescription("Write new unit tests to increase the coverage");
                return Optional.of(objective);
            }

            @Override
            public boolean existsById(final Long aLong) {
                return false;
            }

            @Override
            public Iterable<Objective> findAll() {
                return null;
            }

            @Override
            public Iterable<Objective> findAllById(final Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(final Long aLong) {

            }

            @Override
            public void delete(final Objective entity) {

            }

            @Override
            public void deleteAllById(final Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(final Iterable<? extends Objective> entities) {

            }

            @Override
            public void deleteAll() {

            }
        });
        final ResponseEntity<ObjectiveDto> result = objectiveController.getObjectiveById(10L);

        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getId()).isEqualTo(10L);
        Assertions.assertThat(result.getBody().getTitle())
            .isEqualTo("Increase code coverage");
        Assertions.assertThat(result.getBody().getDescription())
            .isEqualTo("Write new unit tests to increase the coverage");
    }
}