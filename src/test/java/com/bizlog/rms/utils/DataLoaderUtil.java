package com.bizlog.rms.utils;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.escalationMatrix.BizlogFinanceEscalation;
import com.bizlog.rms.entities.sop.TATAdherence;
import com.bizlog.rms.entities.sop.frequency.Frequency;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.entities.sop.notification.Notification;
import com.bizlog.rms.entities.productInformation.ProductInformation;
import com.bizlog.rms.entities.productInformation.ProductSize;
import com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoaderUtil {

    public static List<Client> getClients() {
        Client client = new Client();
        client.setId(1L);
        client.setName("IDP");
        client.setDateOfOnboarding(27122023L);
        return List.of(client);
    }

    public static Client getClient() {
        Client client = new Client();
        client.setId(1L);
        client.setName("IDP");
        client.setName("JOHN");
        client.setDescription("DEVELOPEMENT");
        client.setEmail("abc@gmail.com");
        client.setPhoneNumber("7698524598");
        client.setDomainName("abcdefghi");
        client.setActive(true);
        client.setType("abcde");
        client.setDateOfOnboarding(23122023L);
        return client;
    }

    public static List<Location> getLocations(Client client) {
        Location location = new Location();
        location.setId(1L);
        location.setClient(client);
        location.setCountries(Arrays.asList("India", "Usa"));
        location.setStates(Arrays.asList("Karnataka ", "up", "bihar"));
        location.setAreas(Arrays.asList("Urban", "costalarea"));
        location.setCities(Arrays.asList("Bangalore", "Delhi", "Goa"));
        location.setVehicle(Arrays.asList("threeVelor", "fourVelor", "bike"));
        location.setPinCodes(Arrays.asList("560001", "560028", "560029"));
        location.setTransportLinehaul("bizlog");
        return List.of(location);
    }

    public static List<Frequency> getFrequency(Client client) {
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setFrequency("abc");
        frequency.setFrequencyUnit(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        return List.of(frequency);
    }

    public static List<BizlogFinanceEscalation> getEscalationMatrix(Client client) {
        BizlogFinanceEscalation escalationMatrix = new BizlogFinanceEscalation();
        escalationMatrix.setId(1L);
        escalationMatrix.setClient(client);
        escalationMatrix.setDesignation("IDP");
        escalationMatrix.setFirstName("IDP");
        escalationMatrix.setLastName("IDP");
        escalationMatrix.setEmailAddress("IDP");
        escalationMatrix.setMobile("IDP");
        return List.of(escalationMatrix);
    }

    public static List<Notification> getNotification(Client client) {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setClient(client);
        notification.setSms(Arrays.asList("client", "customer"));
        notification.setEmail(Arrays.asList("client", "customer"));
        return List.of(notification);
    }

    public static List<ProductInformation> getProductInformation(Client client) {
        ProductSize productSize = new ProductSize();
        productSize.setLarge("yes");
        productSize.setMini("no");
        productSize.setMedium("yes");
        productSize.setXLarge("no");
        productSize.setSmall("no");

        ProductInformation productInformation = new ProductInformation();
        productInformation.setId(1L);
        productInformation.setClient(client);
        productInformation.setProductCategory("electronics");

        List<ProductSize> productSizes = new ArrayList<>();
        productSizes.add(productSize);

        productInformation.setProductSize(productSizes);
        productInformation.setIsProductInformationRequiredForTicketCreation(false);
        productInformation.setProductSubCategory("mobiles");
        productInformation.setIsInventoryNeeded(false);
        productInformation.setIsPackingNeeded(true);
        productInformation.setIsVehicleNeeded(true);
        productInformation.setIsWareHousingNeeded(true);
        return List.of(productInformation);
    }

    public static List<TATAdherence> getTATActivity(Client client) {
        TATAdherence tatActivity = new TATAdherence();
        tatActivity.setId(1L);
        tatActivity.setClient(client);
        tatActivity.setTatAdherenceRequired(true);
        tatActivity.setBizlog("yes");
        tatActivity.setCustomer("yes");
        return List.of(tatActivity);
    }

    public static List<TicketInflow> getTicketCreationConfig(Client client) {
        List<String> creationThroughList = Arrays.asList("Api", "Lr", "excel");

        TicketInflow ticketCreationConfig = new TicketInflow();
        ticketCreationConfig.setId(1L);
        ticketCreationConfig.setClient(client);
        ticketCreationConfig.setTicketCreationThrough(creationThroughList);
        ticketCreationConfig.setTicketType(Arrays.asList("Api", "Lr", "excel"));
        return List.of(ticketCreationConfig);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
