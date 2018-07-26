package hu.zsoltborza.gymfinder.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GymCommand {

    private Long id;
    private String title;
    private String information;
    private AddressCommand address;
}
