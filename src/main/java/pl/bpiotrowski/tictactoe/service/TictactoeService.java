package pl.bpiotrowski.tictactoe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bpiotrowski.tictactoe.FieldRepository;
import pl.bpiotrowski.tictactoe.entity.Field;
import pl.bpiotrowski.tictactoe.entity.Status;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TictactoeService {

    private final FieldRepository fieldRepository;

    public List<Field> findAllByRow(int row) {
        return fieldRepository.findAllByRow(row);
    }

    public void create(Field field) {
        fieldRepository.save(field);
    }

    public void fillBoard() {
        for(int i = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                Field field = new Field();
                field.setStatus(Status.O);
                field.setRow(i);
                fieldRepository.save(field);
            }
        }
    }
}
