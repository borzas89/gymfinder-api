package hu.zsoltborza.gymfinder.services;

import hu.zsoltborza.gymfinder.commands.AddressCommand;
import hu.zsoltborza.gymfinder.converters.AddressCommandToAddress;
import hu.zsoltborza.gymfinder.converters.AddressToAddressCommand;
import hu.zsoltborza.gymfinder.domain.Address;
import hu.zsoltborza.gymfinder.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressToAddressCommand addressToAddressCommand;
    private final AddressCommandToAddress addressCommandToAddress;


    public AddressServiceImpl(AddressRepository addressRepository, AddressToAddressCommand addressToAddressCommand, AddressCommandToAddress addressCommandToAddress) {
        this.addressRepository = addressRepository;
        this.addressToAddressCommand = addressToAddressCommand;
        this.addressCommandToAddress = addressCommandToAddress;
    }

    @Override
    public Address findById(Long l) {
        Optional<Address> addressOptional = addressRepository.findById(l);

        if (!addressOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return addressOptional.get();
    }

    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public Address saveOrUpdate(Address address) {
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    @Override
    @Transactional
    public void deleteById(Long idToDelete) {
        addressRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public AddressCommand findCommandById(Long id) {
        return addressToAddressCommand.convert(findById(id));
    }

    @Override
    public AddressCommand saveAddressCommand(AddressCommand command) {
        return null;
    }

    @Override
    public List<AddressCommand> getAddressCommands() {
        List<Address> addressList = new ArrayList<>();

        addressRepository.findAll().forEach(addressList::add);

        addressList.get(0).getGym().getId();

        List<AddressCommand> commandList = new ArrayList<>();

        for (Address aGymList : addressList) {
            commandList.add(addressToAddressCommand.convert(aGymList));
        }

        return commandList;
    }

    @Override
    public List<Address> getAddressByCoordinates(double latitude, double longitude) {
        return addressRepository.findAddressesByCoordinates(latitude,longitude);
    }
}
