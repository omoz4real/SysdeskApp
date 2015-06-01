/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardinal.sysdesk;

import com.cardinal.ejb.CustomerSessionBean;
import com.cardinal.entities.Task;
import com.cardinal.entities.Trips;
import com.cardinal.entities.Users;
import com.cardinal.session.TaskFacade;
import com.cardinal.session.TripsFacade;
import com.cardinal.session.UsersFacade;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.DataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trishna
 */
@Named
//@FlowScoped("registration")
@SessionScoped
public class RegistrationBean implements Serializable {

    @Resource(mappedName = "jms/myTopic")
    private Topic myTopic;
    @Resource(mappedName = "jms/myTopicFactory")
    private ConnectionFactory myTopicFactory;

    @EJB
    private CustomerSessionBean customerList;
    @Inject
    Customer customer;

    @EJB
    private TaskFacade taskFacade;
    private Task task;
    @EJB
    private TripsFacade tripsFacade;
    private Trips trips;
    private emailAccount account;

    @Inject
    private RequestProducerBean producer;

//    @Resource(name = "mail/gmailAccount")
//    private javax.mail.Session mailSession;
    private String smtpServe = "smtp.gmail.com";

    private String client;
    private String contactName;
    private String status;
    private Date startDate;
    private Date endDate;
    private String phone;
    private String email;

    private String passengersName;
    private String noOfPassengers;
    private String office;
    private String flightCarrier;
    private String type;
    private String departure;
    private String destination;
    private Date pickupDate;
    private Date expectedEndDate;
    private Date expectedDepartureTime;
    private String vehicleType;
    private String vehicle;

    public RegistrationBean() {
        customer = new Customer();
        task = new Task();
        trips = new Trips();
        producer = new RequestProducerBean();
        account = new emailAccount();
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
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Trips getTrips() {
        return trips;
    }

    public void setTrips(Trips trips) {
        this.trips = trips;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DataModel getCustomers() {
        return this.customerList.getModel();
    }

    public String getSmtpServe() {
        return smtpServe;
    }

    public void setSmtpServe(String smtpServe) {
        this.smtpServe = smtpServe;
    }

    public String addCustomer() {
        Customer newCustomer
                = new Customer(this.customer.getInputCountry(),
                        this.customer.getInputCity(),
                        this.customer.getEmail(),
                        this.customer.getStartDate(),
                        this.customer.getPhone(),
                        this.customer.getStatus(),
                        this.customer.getEndDate()
                );
        this.customerList.addStudent(newCustomer);
        FacesMessage msg = new FacesMessage("New Trip successfully added !");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("", msg);
        return "finish";
    }

    public List<Task> getTasks() {
        return taskFacade.findAll();
    }

    public List<Trips> getTrip() {
        return tripsFacade.findAll();
    }

    public String saveTask() throws MessagingException {
        addTask();
//        addTrips(task);
        sendNotification();
        FacesMessage msg = new FacesMessage("New Trip successfully added !");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("", msg);
        return "finish";
    }

    public emailAccount getAccount() {
        return account;
    }

    public void setAccount(emailAccount account) {
        this.account = account;
    }

    public Task addTask() {
        Task task1 = new Task();
        task1.setClient(client);
        task1.setContactName(contactName);
        task1.setEmail(email);
        task1.setPhone(phone);
        task1.setEndDate(endDate);
        task1.setStatus(status);
        task1.setStartDate(startDate);
        task1.setTripsCollection(new ArrayList<Trips>());
        taskFacade.create(task1);
        try {
            sendJMSMessage(task1);
        } catch (JMSException ex) {
            Logger.getLogger(CustomerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Task updated in RegistrationBean! " + task1.getClient() + " " + task1.getStatus());
        return task1;
    }

    private Message createJMSMessage(Session session, Object messageData) throws JMSException {
        //Modified to use ObjectMessage instead
        ObjectMessage tm = session.createObjectMessage();
        tm.setObject((Serializable) messageData);
        return tm;
    }

    private void sendJMSMessage(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = myTopicFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myTopic);
            messageProducer.send(createJMSMessage(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

//    public Trips addTrips(Task task) {
//        Trips trip1 = new Trips();
//        trip1.setPassengersName(passengersName);
//        trip1.setNoOfPassengers(noOfPassengers);
//        trip1.setFlightCarrier(flightCarrier);
//        trip1.setOffice(office);
//        trip1.setPickupDate(pickupDate);
//        trip1.setExpectedDepartureTime(expectedDepartureTime);
//        trip1.setDeparture(departure);
//        trip1.setVehicle(vehicle);
//        trip1.setExpectedEndDate(expectedEndDate);
//        trip1.setType(type);
//        trip1.setDestination(destination);
//        trip1.setTaskId(task);
//        tripsFacade.create(trip1);
//        return trip1;
//    }
//
//    public void sendRequest() {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Task request = new Task();
//        try {
//            producer.send(request);
//            FacesMessage facesMessage = new FacesMessage("Request sent successfully");
//            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
//            facesContext.addMessage(null, facesMessage);
//        } catch (JMSException je) {
//            FacesMessage facesMessage = new FacesMessage("Request NOT sent. Error: " + je);
//            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
//            facesContext.addMessage(null, facesMessage);
//        }
//    }
    public String validateTask() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (email.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Email is required"));
        }
        if (phone.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Phone number is required"));
        }
        if (status.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Status is required"));
        }
        if (startDate == null) {
            context.addMessage(null,
                    new FacesMessage("Start Date is required"));
        } else if (startDate.getDate() < new Date().getDate()) {
            context.addMessage(null,
                    new FacesMessage("Start date must not be less than current date "));
        }
        if (endDate == null) {
            context.addMessage(null,
                    new FacesMessage("End Date is required"));
        } else if (startDate.getDate() > endDate.getDate()) {
            context.addMessage(null,
                    new FacesMessage("End date must not be less than Start date "));
        }
        if (contactName.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Contact Name is required."));
        }
        if (context.getMessageList().size() > 0) {
            return (null);
        } else {
            System.out.println("This is the Current Date " + endDate.getDate() + "Again" + endDate.getDate());
            return ("trips");

        }
    }

    public String validateTrip() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (pickupDate == null) {
            context.addMessage(null,
                    new FacesMessage("Pick Up Date is required"));
        } else if (pickupDate.getDate() < new Date().getDate()) {
            context.addMessage(null,
                    new FacesMessage("Pick up date must not be less than current date "));
        }
        if (expectedEndDate == null) {
            context.addMessage(null,
                    new FacesMessage("Expected End Date is required"));
        } else if (pickupDate.getDate() > expectedEndDate.getDate()) {
            context.addMessage(null,
                    new FacesMessage("Expected End date must not be less than Pick Up date "));
        }
        if (expectedDepartureTime == null) {
            context.addMessage(null,
                    new FacesMessage("Expected Departure Time is required"));
        }
        if (departure.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Departure field is required"));
        }
        if (destination.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Destination field is required"));
        }
        if (flightCarrier.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Flight Carrier is required"));
        }
        if (office.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Office is required."));
        }
        if (passengersName.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Passengers Name is required"));
        }
        if (noOfPassengers.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Number of passengers is required."));
        }
        if (type.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Type field is required"));
        }
        if (vehicle.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Vehicle field is required."));
        }
        if (vehicleType.equals("")) {
            context.addMessage(null,
                    new FacesMessage("Vehicle type field is required."));
        }
        if (context.getMessageList().size() > 0) {
            return (null);
        } else {
            return ("summary");
        }

    }

    public String showDetails(Task showTask) {
        this.task = showTask;
        return "details";
    }

     public String showTrips(Trips showTrip) {
        this.trips = showTrip;
        return "updatetrip";
    }
     
    public Task getDetails() {
        return task;
    }

    public String list() {
        System.out.println("###LIST###");
        return "finish";
    }

    public String update() {
        FacesMessage msg = new FacesMessage("New Task has been successfully updated !");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("", msg);
        System.out.println("###UPDATE###");
        taskFacade.edit(task);
        return "finish";
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
            } else if (countryKey.equals("Anadarko")) {
                cityMap = "Anadarko";
            } else if (countryKey.equals("Angelus Energy Solutions Limited")) {
                cityMap = "Angelus";
            } else if (countryKey.equals("AP Oil Field")) {
                cityMap = "AP";
            } else if (countryKey.equals("Arcfyre")) {
                cityMap = "Arcfyre";
            } else if (countryKey.equals("Atlantic Energy")) {
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
            } else if (countryKey.equals("Durotoye Residence")) {
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
            } else if (countryKey.equals("Pepsico")) {
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
            } else if (countryKey.equals("Sandy Onor")) {
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
            } else if (countryKey.equals("Wilfred Usani")) {
                cityMap = "Wilfred";
            } else if (countryKey.equals("Wilmar Biase Plantations")) {
                cityMap = "Wilmar";
            } else if (countryKey.equals("Xodus")) {
                cityMap = "Xodus";
            } else if (countryKey.equals("Yellow Tulips Hotel")) {
                cityMap = "Yellow";
            } else if (countryKey.equals("Yokogawa Nigeria Ltd")) {
                cityMap = "Yokogawa";
            } else {
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

    public void sendNotification() throws MessagingException {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages");
        Properties props = System.getProperties();
        props.put("mail.smtp.user", "omoz4real@gmail.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.host", smtpServe);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator auth = new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("omoz4real@gmail.com", "slimmy123");
            }
        };
        javax.mail.Session session = javax.mail.Session.getInstance(props, auth);
        session.setDebug(true);
        javax.mail.Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("Cardinal"));
        message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email, false));
        String subject = bundle.getString("subject");
        String body = bundle.getString("body");
        String messageText = MessageFormat.format(body, contactName, startDate, endDate);
        message.setSubject(subject);
        message.setContent("<html>\n"
                + "<body>\n"
                + messageText
                + "\n" + "<br>"
                + "<h3> Cardinal Security Services Limited </h3>"
                + "<br>"
                + "The Client is : " + client + "<br>"
                + "Contact Person : " + contactName + "<br>"
                + "with Start Date : " + startDate + "<br>"
                + "and End Date : " + endDate + "<br>"
                + "Contact's Person email address : " + email + "<br>"
                + "with Phone number : " + phone + "<br>" + "<br>" + "<br>"
                + "Please Note that this is just a notification and confirmation email."
                + "<br>" + "<br>"
                + "<img src=\"http://cardinal-ng.com/images/logo.jpg\">" + "<br>" + "<br>"
                + "<a href=\"http://www.cardinal-ng.com\">\n"
                + "www.cardinal-ng.com</a>\n"
                + "<br>274 Murtala Muhammed Way"
                + "<br>Yaba Lagos"
                + "<br>Ghana: +233-2771-54730"
                + "<br>Kenya: +254-7206-37717"
                + "<br>Tanzania: +255-78-6118-008"
                + "<br>USA: +1-832-335-3717"
                + "</body>\n"
                + "</html>", "text/html");
//        message.setText(messageText + "<b>" + contactName + "<b>");
        message.setSentDate(new Date());
        message.saveChanges();
        Transport.send(message);
    }
    private Users user;
    @EJB
    private UsersFacade usersFacade;
    public Users getUsers(){
        if (user == null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String username = context.getUserPrincipal().getName();
            user = usersFacade.findByUsername(username);
        }
    return user;
    }
    
    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession httpSession = (HttpSession) ec.getSession(false);
        httpSession.invalidate();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml?faces-redirect=true");
    }

    public void cancel()throws IOException{
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(ec.getRequestContextPath() + "/faces/registration/registration.xhtml");
    }
    
 
//    public String logout() {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
//        httpSession.invalidate();
//        return ".../index.xhtml?faces-redirect=true";
////        return "index";
//    }
}
