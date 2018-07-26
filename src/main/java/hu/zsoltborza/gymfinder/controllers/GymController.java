package hu.zsoltborza.gymfinder.controllers;


import hu.zsoltborza.gymfinder.util.CoordinateUtil;
import hu.zsoltborza.gymfinder.commands.GymCommand;
import hu.zsoltborza.gymfinder.domain.Address;
import hu.zsoltborza.gymfinder.domain.Gym;
import hu.zsoltborza.gymfinder.services.AddressService;
import hu.zsoltborza.gymfinder.services.GymService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/")
public class GymController {

    private GymService gymService;
    private AddressService addressService;

    public GymController(GymService gymService, AddressService addressService) {
        this.gymService = gymService;
        this.addressService = addressService;
    }

    @GetMapping("gym/{id}/")
    public GymCommand getGymById(@PathVariable String id) {
        return gymService.findCommandById(Long.valueOf(id));
    }

    @GetMapping( "gym/all/")
    public List<GymCommand> getAllGyms(){
        return gymService.getGymCommands();
    }


    @GetMapping( "gym/address/{id}/")
    public Optional<Gym> getAllGyms(@PathVariable String id){
        return gymService.findAllFetchAddress(Long.valueOf(id));
    }


    // sample url : http://localhost:8080/api/gym/neargyms?radius=1000&latitude=47.547141&longitude=19.076171
    @GetMapping( "gym/neargyms")
    public List<Gym> getNearGymsByDistanceAndCurrentPosition(@RequestParam ("radius") long radius,
                                                    @RequestParam ("latitude") double latitude,
                                                    @RequestParam ("longitude") double longitude){

        List<Address> addresses = new ArrayList<>();

        addresses = addressService.findAllAddress();

        List<Gym> fetchedGyms = new ArrayList<>();
        List<Address> fetchedAddresses = new ArrayList<>();

        for (Address address : addresses) {
            double finisLat = address.getLatitude();
            double finisLon = address.getLongitude();
            double distances = CoordinateUtil.getDistanceBetween(latitude, longitude, finisLat, finisLon);

            if (distances < radius) {
                fetchedAddresses.add(address);
                Gym currentGym = new Gym();
                currentGym = gymService.findById(address.getGym().getId());
                fetchedGyms.add(currentGym);

            }
        }

        return fetchedGyms;
    }


    @RequestMapping(value = "/pagination", method = RequestMethod.GET)
    public List<Gym> viewPagination(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

        List<Gym> sortedGyms = gymService.getPage(pageNumber);
        List<Gym> returnedGyms = new ArrayList<>();

        for (Gym gym : sortedGyms) {
           // result += customer.toString() + "br/";
            returnedGyms.add(gym);
        }

        return returnedGyms;
    }


}
