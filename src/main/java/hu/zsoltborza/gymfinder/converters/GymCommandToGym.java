package hu.zsoltborza.gymfinder.converters;

import hu.zsoltborza.gymfinder.commands.GymCommand;
import hu.zsoltborza.gymfinder.domain.Gym;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GymCommandToGym implements Converter<GymCommand, Gym> {


    private final AddressCommandToAddress addressConverter;

    public GymCommandToGym(AddressCommandToAddress addressConverter) {
        this.addressConverter = addressConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public Gym convert(GymCommand source) {

        if (source == null) {
            return null;
        }

        Gym gym = new Gym();

        gym.setId(source.getId());
        gym.setTitle(source.getTitle());
        gym.setInformation(source.getInformation());
        gym.setAddress(addressConverter.convert(source.getAddress()));

        return gym;
}

}
