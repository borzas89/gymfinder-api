package hu.zsoltborza.gymfinder.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressCommand {
    private Long id;
    private String address1;
    private String address2;
    private double latitude;
    private double longitude;
    private GymCommand gym;
}
