package hu.zsoltborza.gymfinder.bootstrap;

import hu.zsoltborza.gymfinder.domain.Address;
import hu.zsoltborza.gymfinder.domain.Gym;
import hu.zsoltborza.gymfinder.repositories.GymRepository;
import hu.zsoltborza.gymfinder.util.CoordinateUtil;
import hu.zsoltborza.gymfinder.util.filehandling.FileHandlerUtil;
import hu.zsoltborza.gymfinder.util.filehandling.model.GymListItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final GymRepository gymRepository;

    public Bootstrap(GymRepository gymRepository){
        this.gymRepository = gymRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        gymRepository.saveAll(getGyms());
    }

    private List<Gym> getGyms() {

        List<Gym> gymList = new ArrayList<>();

        List<GymListItem> dataList = FileHandlerUtil.gettingData();

        for (GymListItem currentItem: dataList ) {

            Gym currentGym = new Gym();
            currentGym.setInformation(currentItem.getInfo());
            currentGym.setTitle(currentItem.getTitle());

            Address currentAddress = new Address();

            currentAddress.setAddress1(currentItem.getAddress1());
            currentAddress.setAddress2(currentItem.getAddress2());

            double lat = Double.parseDouble(currentItem.getLatitude().replace(",","."));
            double lon = Double.parseDouble(currentItem.getLongitude().replace(",","."));

            currentAddress.setLatitude(lat);
            currentAddress.setLongitude(lon);

            currentAddress.setGym(currentGym);
            currentGym.setAddress(currentAddress);
            gymList.add(currentGym);

        }

        return gymList;
    }
}
