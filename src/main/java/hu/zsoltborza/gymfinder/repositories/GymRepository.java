package hu.zsoltborza.gymfinder.repositories;

import hu.zsoltborza.gymfinder.domain.Gym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GymRepository extends PagingAndSortingRepository<Gym, Long> {

    @Query("Select a from Gym a join fetch a.address where a.id = :gymId")
    Optional<Gym> findByIdFetchAddress(@Param("gymId") Long gymId);


    @Query("Select a from Gym a join fetch a.address where a.id = :gymId")
    List<Gym> findGymsById(@Param("gymId") Long gymId);

   // @Query(value = "SELECT a FROM Gym g ORDER BY id")
    //Page<Gym> findAllGymsWithPagination(Pageable pageable);




    /*

    List<TeamMember> findAllTeamMemberByTeamId(@Param("teamId") Long teamId);

    @Modifying
    @Query("UPDATE Team t SET t.questionsAreSended = :status WHERE t.teamRandomId = :teamRandomId")
    void setQuestionsAreSendedStatus(@Param("status") boolean status, @Param("teamRandomId") String teamRandomId);

    @Modifying
    @Query("UPDATE Tour t SET t.active = :setActive WHERE t.id = :id")
    void activateTour(@Param("id") Long id, @Param("setActive") Boolean setActive);

    @Query("SELECT t FROM Tour t WHERE t.active = 1")
    List<Tour> findAllActiveTour();

    */




}
