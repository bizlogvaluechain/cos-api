package com.bizlog.rms.utils;

import com.bizlog.rms.dto.SOP_TAT.subLists.MajorActivites;
import com.bizlog.rms.dto.SOP_TAT.subLists.MinorActivites;
import com.bizlog.rms.dto.SOP_TAT.subLists.TATBreachDueTo;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.Specifications.SOPActivity;
import com.bizlog.rms.entities.Specifications.TATActivity;
import com.bizlog.rms.entities.escalationMatrix.EscalationMatrix;
import com.bizlog.rms.entities.frequency.Frequency;
import com.bizlog.rms.entities.frequency.HolidayApplicable;
import com.bizlog.rms.entities.location.Charge;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.entities.location.ServiceType;
import com.bizlog.rms.entities.notification.Notification;
import com.bizlog.rms.entities.productInformation.ProductInformation;
import com.bizlog.rms.entities.productInformation.ProductSize;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationBasedOn;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationConfig;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationThrough;

import java.util.ArrayList;
import java.util.Collections;
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
        Charge charge = new Charge();
        charge.setDeliverableArea(10L);
        charge.setNonDeliverableArea(10L);
        charge.setOutDeliverableArea(10L);

        ServiceType serviceType = new ServiceType();
        serviceType.setDeliverableArea("yes");
        serviceType.setOutDeliverableArea("yes");
        serviceType.setNonDeliverableArea("yes");

        Location location = new Location();
        location.setId(1L);
        location.setClient(client);
        List<Charge> charges = new ArrayList<>();
        charges.add(charge);
        location.setCharge(charges);
        location.setBizlogLocationMaster("IDP");
        List<ServiceType> serviceTypes = new ArrayList<>();
        serviceTypes.add(serviceType);
        location.setServiceType(serviceTypes);
        location.setSelectStates("AP");
        location.setSelectCities("viz");
        return List.of(location);
    }

    public static List<Frequency> getFrequency(Client client) {
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
        frequency.setTicketsVolume("100");
        return List.of(frequency);
    }

    public static List<EscalationMatrix> getEscalationMatrix(Client client) {
        EscalationMatrix escalationMatrix = new EscalationMatrix();
        escalationMatrix.setId(1L);
        escalationMatrix.setClient(client);
        escalationMatrix.setAccountContactInfo("IDP");
        escalationMatrix.setItContactInfo("IDP");
        escalationMatrix.setBusinessContactInfo("IDP");
        escalationMatrix.setOpsContactInfo("IDP");
        escalationMatrix.setEmergencyContactInfo("IDP");
        return List.of(escalationMatrix);
    }

    public static List<Notification> getNotification(Client client) {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setClient(client);
        notification.setIsSmsRequired(false);
        notification.setIsReportAlertsRequired(true);
        notification.setIsEmailRequired(true);
        notification.setIsAlertNeededForNegativeCases(false);
        notification.setIsTicketScansRequired(true);
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

    public static List<SOPActivity> getSOPActivity(Client client) {
        MajorActivites majorActivites = new MajorActivites();
        majorActivites.setSPS(Collections.singletonList("IDP"));
        majorActivites.setMultiProductShipment(Collections.singletonList("IDP"));
        majorActivites.setLessThanTruckLoad(Collections.singletonList("IDP"));
        majorActivites.setLinehaul(Collections.singletonList("IDP"));
        majorActivites.setFullTruckLoad(Collections.singletonList("IDP"));

        MinorActivites minorActivites = new MinorActivites();
        minorActivites.setOTPValidation(Collections.singletonList("Yes"));
        minorActivites.setQC(Collections.singletonList("Yes"));
        minorActivites.setGrading(Collections.singletonList("yes"));
        minorActivites.setSegregation(Collections.singletonList("Yes"));
        minorActivites.setEvaluation(Collections.singletonList("yes"));
        minorActivites.setSignatureCapture(Collections.singletonList("yes"));
        minorActivites.setImageCapture(Collections.singletonList("yes"));

        SOPActivity sopActivity = new SOPActivity();
        sopActivity.setId(1L);
        sopActivity.setClient(client);
        List<MajorActivites> majorActivitesList = new ArrayList<>();
        sopActivity.setMajorActivites(majorActivitesList);
        List<MinorActivites> minorActivitesList = new ArrayList<>();
        sopActivity.setMinorActivites(minorActivitesList);
        return List.of(sopActivity);
    }

    public static List<TATActivity> getTATActivity(Client client) {

        TATBreachDueTo tatBreachDueTo = new TATBreachDueTo();
        tatBreachDueTo.setCustomer("yes");
        tatBreachDueTo.setBizlog("no");
        tatBreachDueTo.setThirdPartyLogistics("yes");

        TATActivity tatActivity = new TATActivity();
        tatActivity.setId(1L);
        tatActivity.setClient(client);
        tatActivity.setTatForFirstMile("55");
        tatActivity.setTatForLastMile("165");
        List<TATBreachDueTo> tatBreachDueToList = new ArrayList<>();
        tatActivity.setTatBreachDueTo(tatBreachDueToList);
        tatActivity.setTatForLinehaul("abc");
        tatActivity.setNumberOfReshedules("5");

        return List.of(tatActivity);
    }

    public static List<TicketCreationConfig> getTicketCreationConfig(Client client) {
        TicketCreationThrough ticketCreationThrough = new TicketCreationThrough();
        ticketCreationThrough.setApi("API");
        ticketCreationThrough.setExcel("Excel");
        ticketCreationThrough.setForm("form");

        TicketCreationBasedOn ticketCreationBasedOn = new TicketCreationBasedOn();
        ticketCreationBasedOn.setAwbNumber("AWB");
        ticketCreationBasedOn.setInvoiceNumber("invoice");
        ticketCreationBasedOn.setComplaintNumber("complaint");
        ticketCreationBasedOn.setOrderNumber("order");

        TicketCreationConfig ticketCreationConfig = new TicketCreationConfig();
        ticketCreationConfig.setId(1L);
        ticketCreationConfig.setClient(client);
        List<TicketCreationThrough> ticketCreationThroughs = new ArrayList<>();
        // ticketCreationThroughs.add(ticketCreationThrough);
        ticketCreationConfig.setTicketCreationThrough(ticketCreationThroughs);
        List<TicketCreationBasedOn> ticketCreationBasedOns = new ArrayList<>();
        // ticketCreationBasedOns.add(ticketCreationBasedOn);
        ticketCreationConfig.setTicketCreationBasedOn(ticketCreationBasedOns);

        return List.of(ticketCreationConfig);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
