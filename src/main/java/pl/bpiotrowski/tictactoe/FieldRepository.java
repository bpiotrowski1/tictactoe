package pl.bpiotrowski.tictactoe;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bpiotrowski.tictactoe.entity.Field;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findAllByRow(int row);

}
