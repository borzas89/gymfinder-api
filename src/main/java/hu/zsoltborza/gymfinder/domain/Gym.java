package hu.zsoltborza.gymfinder.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Gym implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String information;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public void setAddress(Address address) {
        if (address != null) {
            this.address = address;
            address.setGym(this);
        }
    }


    public Address getAddress(){
        return address;
    }


}
