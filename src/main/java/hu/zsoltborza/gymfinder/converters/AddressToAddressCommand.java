package hu.zsoltborza.gymfinder.converters;

import hu.zsoltborza.gymfinder.commands.AddressCommand;
import hu.zsoltborza.gymfinder.commands.GymCommand;
import hu.zsoltborza.gymfinder.domain.Address;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressCommand implements Converter <Address,AddressCommand> {


    @Synchronized
    @Nullable
    @Override
    public AddressCommand convert(Address source) {

        if (source == null) {
            return null;
        }

        AddressCommand addressCommand = new AddressCommand();

        addressCommand.setId(source.getId());
        addressCommand.setAddress1(source.getAddress1());
        addressCommand.setAddress2(source.getAddress2());
        addressCommand.setLatitude(source.getLatitude());
        addressCommand.setLongitude(source.getLongitude());


        // // kuka...
        GymCommand gymCommand = new GymCommand();
        gymCommand.setId(source.getGym().getId());
        addressCommand.setGym(gymCommand);

        return addressCommand;
    }
}
