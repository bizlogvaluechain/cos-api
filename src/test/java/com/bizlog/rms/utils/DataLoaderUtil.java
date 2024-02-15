package com.bizlog.rms.utils;

import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.OrganizationType;
import com.bizlog.rms.entities.escalationMatrix.BizlogFinanceEscalation;
import com.bizlog.rms.entities.sop.BreachDueTo;
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

    public static List<Organization> getClients() {
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("IDP");
        organization.setDateOfOnboarding(27122023L);
        return List.of(organization);
    }

    public static Organization getClient() {
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("IDP");
        organization.setName("JOHN");
        organization.setDescription("DEVELOPEMENT");
        organization.setEmail("abc@gmail.com");
        organization.setPhoneNumber("7698524598");
        organization.setDomainName("abcdefghi");
        organization.setActive(true);
        organization.setOrganizationType(OrganizationType.ROOT);
        organization.setDateOfOnboarding(23122023L);
        return organization;
    }

    public static List<Location> getLocations(Organization organization) {
        Location location = new Location();
        location.setId(1L);
        location.setOrganization(organization);
        location.setCountries(Arrays.asList("India", "Usa"));
        location.setStates(Arrays.asList("Karnataka ", "up", "bihar"));
        location.setAreas(Arrays.asList("Urban", "costalarea"));
        location.setCities(Arrays.asList("Bangalore", "Delhi", "Goa"));
        location.setVehicle(Arrays.asList("threeVelor", "fourVelor", "bike"));
        location.setPinCodes(Arrays.asList("560001", "560028", "560029"));
        location.setTransportLinehaul("bizlog");
        return List.of(location);
    }

    public static List<Frequency> getFrequency(Organization organization) {
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequency(100l);
        frequency.setFrequencyUnit("abc");
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

    public static List<BizlogFinanceEscalation> getEscalationMatrix(Organization organization) {
        BizlogFinanceEscalation escalationMatrix = new BizlogFinanceEscalation();
        escalationMatrix.setId(1L);
        escalationMatrix.setOrganization(organization);
        escalationMatrix.setDesignation("IDP");
        escalationMatrix.setFirstName("IDP");
        escalationMatrix.setLastName("IDP");
        escalationMatrix.setEmailAddress("IDP");
        escalationMatrix.setMobile("IDP");
        return List.of(escalationMatrix);
    }

    public static List<Notification> getNotification(Organization organization) {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setOrganization(organization);
        notification.setSms(Arrays.asList("client", "customer"));
        notification.setEmail(Arrays.asList("client", "customer"));
        return List.of(notification);
    }

    public static List<ProductInformation> getProductInformation(Organization organization) {
        ProductSize productSize = new ProductSize();
        productSize.setLarge("yes");
        productSize.setMini("no");
        productSize.setMedium("yes");
        productSize.setXLarge("no");
        productSize.setSmall("no");

        ProductInformation productInformation = new ProductInformation();
        productInformation.setId(1L);
        productInformation.setOrganization(organization);
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

    public static List<TATAdherence> getTATActivity(Organization organization) {
        BreachDueTo breachDueTo = new BreachDueTo();
        breachDueTo.setThirdPartyLogistics("yes");
        breachDueTo.setUnavoidableCircumstances("yes");
        breachDueTo.setCustomer("yes");
        breachDueTo.setBizlog("yes");
        TATAdherence tatActivity = new TATAdherence();
        tatActivity.setId(1L);
        tatActivity.setOrganization(organization);
        tatActivity.setTatAdherenceRequired(true);
        tatActivity.setBreachDueTo(breachDueTo);
        return List.of(tatActivity);
    }

    public static List<TicketInflow> getTicketCreationConfig(Organization organization) {
        List<String> creationThroughList = Arrays.asList("Api", "Lr", "excel");

        TicketInflow ticketCreationConfig = new TicketInflow();
        ticketCreationConfig.setId(1L);
        ticketCreationConfig.setOrganization(organization);
        ticketCreationConfig.setTicketCreationThrough(creationThroughList);
        ticketCreationConfig.setTicketType(Arrays.asList("Api", "Lr", "excel"));
        return List.of(ticketCreationConfig);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
