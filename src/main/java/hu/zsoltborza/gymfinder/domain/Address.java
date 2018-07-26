package hu.zsoltborza.gymfinder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode(exclude = {"gym"})
@Entity
public class Address implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Lob
    private String address1;

    @Lob
    private String address2;

    private double latitude;

    private double longitude;

    @JsonBackReference
    @OneToOne
    private Gym gym;


    public Address(){

    }

    public Address(Long id, String address1, String address2, double latitude, double longitude, Gym gym){
        this.id = id;
        this.address1 = address1;
        this.address2 = address2;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gym = gym;
    }


//    @OneToOne
    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym){
        this.gym = gym;
    }






}
