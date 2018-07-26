package hu.zsoltborza.gymfinder.controllers;

import hu.zsoltborza.gymfinder.commands.AddressCommand;

import hu.zsoltborza.gymfinder.domain.Address;
import hu.zsoltborza.gymfinder.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/")
public class AddressController {

    private final AddressService addressService;


    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("address/{id}/")
    public AddressCommand getAddressById(@PathVariable String id) {
        return addressService.findCommandById(Long.valueOf(id));
    }

    @GetMapping( "address/all/")
    public List<AddressCommand> getAllGyms(){

        return addressService.getAddressCommands();
    }


    // sample request http://localhost:8080/api/address/coordinate/?latitude=47.4979889&longitude=19.072923
    @GetMapping( "address/coordinate")
    public List<Address> getAddressByCooridnates( @RequestParam("latitude") double latitude,
                                                  @RequestParam ("longitude") double longitude){
        // sample data       47.4979889,19.072923
        return addressService.getAddressByCoordinates(latitude,longitude);
    }


}
