package pl.bpiotrowski.tictactoe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiotrowski.tictactoe.FieldRepository;
import pl.bpiotrowski.tictactoe.entity.Field;
import pl.bpiotrowski.tictactoe.entity.Status;
import pl.bpiotrowski.tictactoe.exception.FieldNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TictactoeService {

    private final FieldRepository fieldRepository;

    public List<Field> findAllByRow(int row) {
        return fieldRepository.findAllByRow(row);
    }

    private Field findById(Long id) {
        return fieldRepository.findById(id).orElseThrow(
                () -> new FieldNotFoundException(id)
        );
    }

    public void changeStatus(Long id) {
        Field fieldToChange = findById(id);
        if(fieldToChange.getStatus() == Status.N) {
            fieldToChange.setStatus(Status.X);
        }
        fieldRepository.save(fieldToChange);
    }

    public void fillBoard() {
        for(int i = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                Field field = new Field();
                field.setStatus(Status.N);
                field.setRow(i);
                fieldRepository.save(field);
            }
        }
    }
}
