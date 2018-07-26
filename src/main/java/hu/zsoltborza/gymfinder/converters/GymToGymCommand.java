package hu.zsoltborza.gymfinder.converters;

import hu.zsoltborza.gymfinder.commands.GymCommand;
import hu.zsoltborza.gymfinder.domain.Gym;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GymToGymCommand implements Converter<Gym, GymCommand> {

    private final AddressToAddressCommand addressConverter;

    public GymToGymCommand(AddressToAddressCommand addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public GymCommand convert(Gym source) {

        if (source == null) {
            return null;
        }

        GymCommand gymCommand = new GymCommand();

        gymCommand.setId(source.getId());
        gymCommand.setTitle(source.getTitle());
        gymCommand.setInformation(source.getInformation());
        gymCommand.setAddress(addressConverter.convert(source.getAddress()));


        return gymCommand;
    }
}
