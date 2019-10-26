package pl.bpiotrowski.tictactoe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.bpiotrowski.tictactoe.entity.Field;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findAllByRow(int row);

    @Query("select f from Field f where f.id in (1,4,7)")
    List<Field> findAllInFirstColumn();

    @Query("select f from Field f where f.id in (2,5,8)")
    List<Field> findAllInSecondColumn();

    @Query("select f from Field f where f.id in (3,6,9)")
    List<Field> findAllInThirdColumn();

    @Query("select f from Field f where f.id in (1,5,9)")
    List<Field> findAllInFirstCross();

    @Query("select f from Field f where f.id in (3,5,7)")
    List<Field> findAllInSecondCross();
}
