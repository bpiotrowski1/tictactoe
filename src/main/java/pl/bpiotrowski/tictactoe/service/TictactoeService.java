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
    private Status nextStatus = Status.X;
    private int turn = 1;

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
        if (fieldToChange.getStatus() == Status.N) {
            fieldToChange.setStatus(nextStatus);
        }
        if (nextStatus == Status.X) {
            nextStatus = Status.O;
        } else {
            nextStatus = Status.X;
        }
        fieldRepository.save(fieldToChange);
    }

    public void fillBoard() {
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                Field field = new Field();
                field.setStatus(Status.N);
                field.setRow(i);
                fieldRepository.save(field);
            }
        }
    }

    public boolean checkWin() {
        for (int i = 1; i < 4; i++) {
            List<Field> row = findAllByRow(i);
            if(checkListToWin(row)) {
                return true;
            }
        }
        List<Field> column = fieldRepository.findAllInFirstColumn();
        if(checkListToWin(column)) {
            return true;
        }
        column = fieldRepository.findAllInSecondColumn();
        if(checkListToWin(column)) {
            return true;
        }
        column = fieldRepository.findAllInThirdColumn();
        if(checkListToWin(column)) {
            return true;
        }
        column = fieldRepository.findAllInFirstCross();
        if(checkListToWin(column)) {
            return true;
        }
        column = fieldRepository.findAllInSecondCross();
        if(checkListToWin(column)) {
            return true;
        }

        return false;
    }

    private boolean checkListToWin(List<Field> list) {
        return list.get(0).getStatus() == list.get(1).getStatus() &&
                list.get(0).getStatus() == list.get(2).getStatus() &&
                (list.get(0).getStatus() == Status.X || list.get(0).getStatus() == Status.O);
    }
}
