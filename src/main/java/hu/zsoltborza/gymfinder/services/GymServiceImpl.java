package hu.zsoltborza.gymfinder.services;

import hu.zsoltborza.gymfinder.commands.GymCommand;
import hu.zsoltborza.gymfinder.converters.GymCommandToGym;
import hu.zsoltborza.gymfinder.converters.GymToGymCommand;
import hu.zsoltborza.gymfinder.domain.Gym;
import hu.zsoltborza.gymfinder.repositories.GymRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GymServiceImpl implements GymService {


    private final GymRepository gymRepository;

    private final GymCommandToGym gymCommandToGym;

    private final GymToGymCommand gymToGymCommand;


    private final static int PAGESIZE = 10;

    public GymServiceImpl(GymRepository gymRepository, GymCommandToGym gymCommandToGym, GymToGymCommand gymToGymCommand){
        this.gymRepository = gymRepository;
        this.gymCommandToGym = gymCommandToGym;
        this.gymToGymCommand = gymToGymCommand;
    }

    @Override
    public List<Gym> getGyms() {
        List<Gym> gymList = new ArrayList<>();
        gymRepository.findAll().forEach(gymList::add);
        return gymList;
    }

    @Override
    public Gym findById(Long l) {
        Optional<Gym> gymOptional = gymRepository.findById(l);

        if (!gymOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return gymOptional.get();
    }

    @Override
    public Gym saveOrUpdate(Gym gym) {
        Gym savedGym = gymRepository.save(gym);
        return savedGym;
    }

    @Override
    public void deleteById(Long idToDelete) {
        gymRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public GymCommand findCommandById(Long id) {
        return gymToGymCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public GymCommand saveGymCommand(GymCommand command) {

        Gym detachedGym = gymCommandToGym.convert(command);

        Gym savedGym = gymRepository.save(detachedGym);
        log.debug("Saved GymId:" + savedGym.getId());
        return gymToGymCommand.convert(savedGym);
    }


    // controllerhez
    @Override
    public List<GymCommand> getGymCommands() {

        List<Gym> gymList = new ArrayList<>();
        gymRepository.findAll().forEach(gymList::add);

        List<GymCommand> commandList = new ArrayList<>();

        for (Gym aGymList : gymList) {
            commandList.add(gymToGymCommand.convert(aGymList));
        }

        return commandList;
    }

    @Override
    public Optional<Gym> findAllFetchAddress(Long id) {
        return gymRepository.findByIdFetchAddress(id);
    }

    @Override
    public Page<Gym> getGymsByPage() {
        return null;
    }

    @Override
    public List<Gym> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");

        return gymRepository.findAll(request).getContent();
    }

//    @Override
//    public Page<Gym> getGymsByPage() {
//        return gymRepository.findAllGymsWithPagination(1);
//    }

//    @Override
//    public List<Gym> findGymByAddressCoordinates(double latitude, double longitude) {
//        return gymRepository.findGymByCoordinate(latitude,longitude);
//    }


}
