package hu.zsoltborza.gymfinder.services;

import hu.zsoltborza.gymfinder.commands.AddressCommand;
import hu.zsoltborza.gymfinder.domain.Address;

import java.util.List;

public interface AddressService {

    Address findById(Long l);

    List<Address> findAllAddress();

    Address saveOrUpdate(Address address);

    void deleteById(Long idToDelete);

    AddressCommand findCommandById(Long id);

    AddressCommand saveAddressCommand(AddressCommand command);

    List<AddressCommand> getAddressCommands();

    List<Address> getAddressByCoordinates(double latitude, double longitude);
}
