package hu.zsoltborza.gymfinder.services;

import hu.zsoltborza.gymfinder.commands.GymCommand;
import hu.zsoltborza.gymfinder.domain.Gym;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface GymService {

    List<Gym> getGyms();

    Gym findById(Long l);

    Gym saveOrUpdate(Gym gym);

    void deleteById(Long idToDelete);

    GymCommand findCommandById(Long l);

    GymCommand saveGymCommand(GymCommand command);

    List<GymCommand> getGymCommands();

    Optional<Gym> findAllFetchAddress(Long id);

    Page<Gym> getGymsByPage();

    public List<Gym> getPage(int pageNumber);

//    List<Gym> findGymByAddressCoordinates(double latitude,double longitude);
}
