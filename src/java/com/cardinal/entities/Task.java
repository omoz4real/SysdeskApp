/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cardinal.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Trishna
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"),
    @NamedQuery(name = "Task.findByClient", query = "SELECT t FROM Task t WHERE t.client = :client"),
    @NamedQuery(name = "Task.findByContactName", query = "SELECT t FROM Task t WHERE t.contactName = :contactName"),
    @NamedQuery(name = "Task.findByEmail", query = "SELECT t FROM Task t WHERE t.email = :email"),
    @NamedQuery(name = "Task.findByPhone", query = "SELECT t FROM Task t WHERE t.phone = :phone"),
    @NamedQuery(name = "Task.findByStatus", query = "SELECT t FROM Task t WHERE t.status = :status"),
    @NamedQuery(name = "Task.findByStartDate", query = "SELECT t FROM Task t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Task.findByEndDate", query = "SELECT t FROM Task t WHERE t.endDate = :endDate")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "client")
    private String client;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "contact_name")
    private String contactName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 245)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 45)
    @Column(name = "phone")
    private String phone;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId", fetch = FetchType.EAGER)
    private List<Trips> tripsCollection;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Task(Integer id, String client, String contactName, String email, String phone, String status, Date startDate, Date endDate) {
        this.id = id;
        this.client = client;
        this.contactName = contactName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        dateFormat.format(startDate);
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        dateFormat.format(endDate);
        this.endDate = endDate;
    }

    @XmlTransient
    public List<Trips> getTripsCollection() {
        return tripsCollection;
    }

    public void setTripsCollection(List<Trips> tripsCollection) {
        this.tripsCollection = tripsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cardinal.entities.Task[ id=" + id + " ]";
    }
    
    public Map getCities() {
        String cityMap = "";
        if (client != null) {
            String countryKey = client;
            if (countryKey.equals("Ackermann Group")) {
                cityMap = "Ackermann";
            } else if (countryKey.equals("Addax Petroleum")) {
                cityMap = "Addax";
            } else if (countryKey.equals("Afren Energy")) {
                cityMap = "Afren";
            }  else if (countryKey.equals("Anadarko")) {
                cityMap = "Anadarko";
            } else if (countryKey.equals("Angelus Energy Solutions Limited")) {
                cityMap = "Angelus";
            }  else if (countryKey.equals("AP Oil Field")) {
                cityMap = "AP";
            } else if (countryKey.equals("Arcfyre")) {
                cityMap = "Arcfyre";
            }  else if (countryKey.equals("Atlantic Energy")) {
                cityMap = "Atlantic";
            } else if (countryKey.equals("Atlas Cement")) {
                cityMap = "Atlas";
            } else if (countryKey.equals("Avante")) {
                cityMap = "Avante";
            } else if (countryKey.equals("Ayi Ayi Ita")) {
                cityMap = "Ayi";
            } else if (countryKey.equals("Broll Properties")) {
                cityMap = "Broll";
            } else if (countryKey.equals("Brook Church")) {
                cityMap = "Brook";
            } else if (countryKey.equals("Brunnel Energy")) {
                cityMap = "Brunnel";
            } else if (countryKey.equals("CAC")) {
                cityMap = "CAC";
            } else if (countryKey.equals("Capital Alliance")) {
                cityMap = "Capital";
            } else if (countryKey.equals("Chevron")) {
                cityMap = "Chevron";
            } else if (countryKey.equals("Contour Global Solution")) {
                cityMap = "Contour";
            } else if (countryKey.equals("Crossbow Oil Field")) {
                cityMap = "Crossbow";
            } else if (countryKey.equals("Dangote Flour Mill")) {
                cityMap = "Dangote";
            } else if (countryKey.equals("Dave")) {
                cityMap = "Dave";
            } else if (countryKey.equals("Diego")) {
                cityMap = "Diego";
            } else if (countryKey.equals("Diligence Management Consultant")) {
                cityMap = "Diligence";
            }  else if (countryKey.equals("Durotoye Residence")) {
                cityMap = "Durotoye";
            } else if (countryKey.equals("Eko Hotel and Suite")) {
                cityMap = "Eko";
            } else if (countryKey.equals("Ericsson")) {
                cityMap = "Ericsson";
            } else if (countryKey.equals("FHN")) {
                cityMap = "FHN";
            } else if (countryKey.equals("First Exploration and Petroleum Dev")) {
                cityMap = "First";
            } else if (countryKey.equals("Folusho Koko")) {
                cityMap = "Folusho";
            } else if (countryKey.equals("Frontier Medex")) {
                cityMap = "Frontier";
            } else if (countryKey.equals("Gardaworld")) {
                cityMap = "Gardaworld";
            } else if (countryKey.equals("GE International Operations Nig")) {
                cityMap = "GE";
            } else if (countryKey.equals("Goshen Parish")) {
                cityMap = "Goshen";
            } else if (countryKey.equals("Guiness")) {
                cityMap = "Guiness";
            } else if (countryKey.equals("Harvesters Int Christian Centre")) {
                cityMap = "Harvesters";
            } else if (countryKey.equals("Hercules Offshore")) {
                cityMap = "Hercules";
            } else if (countryKey.equals("Heritage Bank")) {
                cityMap = "Heritage";
            } else if (countryKey.equals("ICover")) {
                cityMap = "ICover";
            } else if (countryKey.equals("Lafarge")) {
                cityMap = "Lafarge";
            } else if (countryKey.equals("Lekoil")) {
                cityMap = "Lekoil";
            } else if (countryKey.equals("Maerck MSD")) {
                cityMap = "Maerck";
            } else if (countryKey.equals("Mcentrick")) {
                cityMap = "Mcentrick";
            } else if (countryKey.equals("Mckenzie Intelligence")) {
                cityMap = "Mckenzie";
            } else if (countryKey.equals("Mckinsey")) {
                cityMap = "Mckinsey";
            } else if (countryKey.equals("Moonwalker Connect Limited")) {
                cityMap = "Moonwalker";
            } else if (countryKey.equals("Moore Enterprise Ltd")) {
                cityMap = "Moore";
            } else if (countryKey.equals("Mosaic Management Service Ltd")) {
                cityMap = "Mosaic";
            } else if (countryKey.equals("Mr Muhtar Bakare and Ms Yewande")) {
                cityMap = "Muhtar";
            } else if (countryKey.equals("National Oilwell Varco")) {
                cityMap = "National";
            } else if (countryKey.equals("ND Western")) {
                cityMap = "Western";
            } else if (countryKey.equals("Nigerian Breweries")) {
                cityMap = "Breweries";
            } else if (countryKey.equals("Ocean and Oil Holding")) {
                cityMap = "Ocean";
            } else if (countryKey.equals("Ocean View Restaurant")) {
                cityMap = "Oceanview";
            } else if (countryKey.equals("Page Group")) {
                cityMap = "Page";
            } else if (countryKey.equals("Pearson Nigeria")) {
                cityMap = "Pearson";
            }else if (countryKey.equals("Pepsico")) {
                cityMap = "Pepsico";
            } else if (countryKey.equals("Petrofac")) {
                cityMap = "Petrofac";
            } else if (countryKey.equals("Philips")) {
                cityMap = "Phillips";
            } else if (countryKey.equals("Procter and Gamble")) {
                cityMap = "Procter";
            } else if (countryKey.equals("PZ Cussons")) {
                cityMap = "Cussons";
            } else if (countryKey.equals("RCCG Prince of Peace")) {
                cityMap = "RCCG";
            } else if (countryKey.equals("Richard Effah")) {
                cityMap = "Richard";
            } else if (countryKey.equals("Richardson Oil and Gas")) {
                cityMap = "Richardson";
            } else if (countryKey.equals("Rolayo")) {
                cityMap = "Rolayo";
            } else if (countryKey.equals("SACREF")) {
                cityMap = "Sacref";
            } else if (countryKey.equals("Sahara Bulk Storage")) {
                cityMap = "Sahara";
            }else if (countryKey.equals("Sandy Onor")) {
                cityMap = "Sandy";
            } else if (countryKey.equals("Sayo Jimi Oluseye")) {
                cityMap = "Sayo";
            } else if (countryKey.equals("Sea drill Mobile")) {
                cityMap = "Seadrillmobile";
            } else if (countryKey.equals("Seadrill")) {
                cityMap = "Seadrill";
            } else if (countryKey.equals("Searon Educational Nig Ltd")) {
                cityMap = "Searon";
            } else if (countryKey.equals("Seawolf")) {
                cityMap = "Seawolf";
            } else if (countryKey.equals("Sema")) {
                cityMap = "Sema";
            } else if (countryKey.equals("Shelf Drilling")) {
                cityMap = "Shelf";
            } else if (countryKey.equals("Shell Nig Exploration and Production")) {
                cityMap = "Shell";
            } else if (countryKey.equals("Smile Communications Nig Ltd")) {
                cityMap = "Smile";
            } else if (countryKey.equals("Solomon Isokpan")) {
                cityMap = "Solomon";
            } else if (countryKey.equals("Stanbic IBTC")) {
                cityMap = "Stanbic";
            } else if (countryKey.equals("Stanbic IBTC Asset Mgt")) {
                cityMap = "Stanbicibtc";
            } else if (countryKey.equals("Stanbic IBTC Pension")) {
                cityMap = "Stanbicpension";
            } else if (countryKey.equals("Standard Chartered Bank")) {
                cityMap = "Standard";
            } else if (countryKey.equals("Stella hospitality")) {
                cityMap = "Stella";
            } else if (countryKey.equals("Stella Travel")) {
                cityMap = "Stellatravel";
            } else if (countryKey.equals("The Church of Jesus Christ of Latter Rain")) {
                cityMap = "TheChurch";
            } else if (countryKey.equals("Theodore Gentle")) {
                cityMap = "Theodore";
            } else if (countryKey.equals("Tom Salem")) {
                cityMap = "Tom";
            } else if (countryKey.equals("Total")) {
                cityMap = "Total";
            } else if (countryKey.equals("Transocean")) {
                cityMap = "Transocean";
            } else if (countryKey.equals("Trifex Ltd")) {
                cityMap = "Trifex";
            } else if (countryKey.equals("Triton Aviation")) {
                cityMap = "Triton";
            } else if (countryKey.equals("TSU International")) {
                cityMap = "Tsu";
            } else if (countryKey.equals("Tulsi Chanrai Foundation")) {
                cityMap = "Tulsi";
            } else if (countryKey.equals("URSPACE Facility Manager Ltd")) {
                cityMap = "Urspace";
            } else if (countryKey.equals("Vixa Pharmaceutical Ltd")) {
                cityMap = "Vixa";
            } else if (countryKey.equals("Wagpco")) {
                cityMap = "Wagpco";
            } else if (countryKey.equals("Wartsila Marine and Power Services Nig")) {
                cityMap = "Wartsila";
            } else if (countryKey.equals("Wells Property")) {
                cityMap = "Wells";
            }else if (countryKey.equals("Wilfred Usani")) {
                cityMap = "Wilfred";
            } else if (countryKey.equals("Wilmar Biase Plantations")) {
                cityMap = "Wilmar";
            } else if (countryKey.equals("Xodus")) {
                cityMap = "Xodus";
            } else if (countryKey.equals("Yellow Tulips Hotel")) {
                cityMap = "Yellow";
            } else if (countryKey.equals("Yokogawa Nigeria Ltd")) {
                cityMap = "Yokogawa";
            } 
            else {
                cityMap = "EmptyClientMap";
            }
        } else {
            cityMap = "EmptyClientMap";
        }
        cityMap = "#{" + cityMap + "}";
        System.out.println("This is the Cities Map " + cityMap);
//retrieve correct cityMap and return
        FacesContext context = FacesContext.getCurrentInstance();
        ValueBinding binding;
        binding = context.getApplication().createValueBinding(cityMap);
        return (Map) binding.getValue(context);
    }
}
