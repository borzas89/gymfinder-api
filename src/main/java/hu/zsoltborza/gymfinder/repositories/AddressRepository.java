package hu.zsoltborza.gymfinder.repositories;

import hu.zsoltborza.gymfinder.domain.Address;
import hu.zsoltborza.gymfinder.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("Select a from Address a where a.latitude LIKE :latitude AND a.longitude LIKE :longitude")
    List<Address> findAddressesByCoordinates(@Param("latitude") double latitude, @Param("longitude") double longitude);


}
