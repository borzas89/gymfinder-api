package hu.zsoltborza.gymfinder.converters;


import hu.zsoltborza.gymfinder.commands.AddressCommand;
import hu.zsoltborza.gymfinder.commands.GymCommand;
import hu.zsoltborza.gymfinder.domain.Address;
import hu.zsoltborza.gymfinder.domain.Gym;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand, Address> {


    @Synchronized
    @Nullable
    @Override
    public Address convert(AddressCommand source) {


        if (source == null) {
            return null;
        }

        Address address = new Address();

        address.setId(source.getId());
        address.setAddress1(source.getAddress1());
        address.setAddress2(source.getAddress2());
        address.setLatitude(source.getLatitude());
        address.setLongitude(source.getLongitude());


        // kuka...
        Gym gymCommand = new Gym();
        gymCommand.setId(source.getGym().getId());
        address.setGym(gymCommand);

        return address;
    }
}
