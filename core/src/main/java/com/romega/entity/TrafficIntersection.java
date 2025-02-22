package com.romega.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "traffic_light_data")
public class TrafficIntersection implements Serializable {


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrafficLightStatus getStatus() {
        return status;
    }

    public void setStatus(TrafficLightStatus status) {
        this.status = status;
    }

    public Location getCoordinates() {
        return location;
    }

    public void setCoordinates(Location location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TrafficLightStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "longitude"))
    })
    private Location location;

    @Column(name = "time")
    private String time;

}
