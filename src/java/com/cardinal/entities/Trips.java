/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cardinal.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Trishna
 */
@Entity
@Table(name = "trips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trips.findAll", query = "SELECT t FROM Trips t"),
    @NamedQuery(name = "Trips.findByTripsId", query = "SELECT t FROM Trips t WHERE t.tripsId = :tripsId"),
    @NamedQuery(name = "Trips.findByPassengersName", query = "SELECT t FROM Trips t WHERE t.passengersName = :passengersName"),
    @NamedQuery(name = "Trips.findByNoOfPassengers", query = "SELECT t FROM Trips t WHERE t.noOfPassengers = :noOfPassengers"),
    @NamedQuery(name = "Trips.findByOffice", query = "SELECT t FROM Trips t WHERE t.office = :office"),
    @NamedQuery(name = "Trips.findByFlightCarrier", query = "SELECT t FROM Trips t WHERE t.flightCarrier = :flightCarrier"),
    @NamedQuery(name = "Trips.findByType", query = "SELECT t FROM Trips t WHERE t.type = :type"),
    @NamedQuery(name = "Trips.findByDeparture", query = "SELECT t FROM Trips t WHERE t.departure = :departure"),
    @NamedQuery(name = "Trips.findByDestination", query = "SELECT t FROM Trips t WHERE t.destination = :destination"),
    @NamedQuery(name = "Trips.findByPickupDate", query = "SELECT t FROM Trips t WHERE t.pickupDate = :pickupDate"),
    @NamedQuery(name = "Trips.findByExpectedEndDate", query = "SELECT t FROM Trips t WHERE t.expectedEndDate = :expectedEndDate"),
    @NamedQuery(name = "Trips.findByExpectedDepartureTime", query = "SELECT t FROM Trips t WHERE t.expectedDepartureTime = :expectedDepartureTime"),
    @NamedQuery(name = "Trips.findByVehicleType", query = "SELECT t FROM Trips t WHERE t.vehicleType = :vehicleType"),
    @NamedQuery(name = "Trips.findByVehicle", query = "SELECT t FROM Trips t WHERE t.vehicle = :vehicle")})
public class Trips implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trips_id")
    private Integer tripsId;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "passengers_name")
    private String passengersName;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 45)
    @Column(name = "no_of_passengers")
    private String noOfPassengers;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "office")
    private String office;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "flight_carrier")
    private String flightCarrier;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "type")
    private String type;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "departure")
    private String departure;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "destination")
    private String destination;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "pickup_date")
    @Temporal(TemporalType.DATE)
    private Date pickupDate;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "expected_end_date")
    @Temporal(TemporalType.DATE)
    private Date expectedEndDate;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "expected_departure_time")
    @Temporal(TemporalType.DATE)
    private Date expectedDepartureTime;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "vehicle_type")
    private String vehicleType;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "vehicle")
    private String vehicle;
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Task taskId;

    public Trips() {
    }

    public Trips(Integer tripsId) {
        this.tripsId = tripsId;
    }

    public Trips(Integer tripsId, String passengersName, String noOfPassengers, String office, String flightCarrier, String type, String departure, String destination, Date pickupDate, Date expectedEndDate, Date expectedDepartureTime, String vehicleType, String vehicle) {
        this.tripsId = tripsId;
        this.passengersName = passengersName;
        this.noOfPassengers = noOfPassengers;
        this.office = office;
        this.flightCarrier = flightCarrier;
        this.type = type;
        this.departure = departure;
        this.destination = destination;
        this.pickupDate = pickupDate;
        this.expectedEndDate = expectedEndDate;
        this.expectedDepartureTime = expectedDepartureTime;
        this.vehicleType = vehicleType;
        this.vehicle = vehicle;
    }

    public Integer getTripsId() {
        return tripsId;
    }

    public void setTripsId(Integer tripsId) {
        this.tripsId = tripsId;
    }

    public String getPassengersName() {
        return passengersName;
    }

    public void setPassengersName(String passengersName) {
        this.passengersName = passengersName;
    }

    public String getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(String noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFlightCarrier() {
        return flightCarrier;
    }

    public void setFlightCarrier(String flightCarrier) {
        this.flightCarrier = flightCarrier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        dateFormat.format(pickupDate);
        this.pickupDate = pickupDate;
    }

    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(Date expectedEndDate) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        dateFormat.format(expectedEndDate);
        this.expectedEndDate = expectedEndDate;
    }

    public Date getExpectedDepartureTime() {
        return expectedDepartureTime;
    }

    public void setExpectedDepartureTime(Date expectedDepartureTime) {
        this.expectedDepartureTime = expectedDepartureTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripsId != null ? tripsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trips)) {
            return false;
        }
        Trips other = (Trips) object;
        if ((this.tripsId == null && other.tripsId != null) || (this.tripsId != null && !this.tripsId.equals(other.tripsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cardinal.entities.Trips[ tripsId=" + tripsId + " ]";
    }
    
}
