package com.bizlog.rms.mapper;

import com.bizlog.rms.dto.NotifyApiDTO;
import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.dto.SOP_TAT.*;
import com.bizlog.rms.dto.TAT.TATDTO;

import com.bizlog.rms.dto.activity.FirstMileDTO;
import com.bizlog.rms.dto.activity.LastMileDTO;
import com.bizlog.rms.dto.activity.MiddleMileDTO;

import com.bizlog.rms.dto.clientengagement.ClientEngagementDTO;
import com.bizlog.rms.dto.clientinfo.*;
import com.bizlog.rms.dto.escalationMatrix.*;
import com.bizlog.rms.dto.frequency.FrequencyDTO;
import com.bizlog.rms.dto.locationService.LocationDTO;
import com.bizlog.rms.dto.locationService.RegionSpecificLocationDTO;
import com.bizlog.rms.dto.notification.NotificationDTO;
import com.bizlog.rms.dto.product.*;
import com.bizlog.rms.dto.productInformation.ProductInformationDTO;
import com.bizlog.rms.dto.users.UserDTO;
import com.bizlog.rms.entities.ClientSetting;
import com.bizlog.rms.entities.NotifyApi;
import com.bizlog.rms.entities.Organization;

import com.bizlog.rms.entities.TAT.TAT;

import com.bizlog.rms.entities.activity.FirstMile;
import com.bizlog.rms.entities.activity.LastMile;
import com.bizlog.rms.entities.activity.MiddleMile;
import com.bizlog.rms.entities.clientengagement.ClientEngagement;
import com.bizlog.rms.entities.clientinfo.*;
import com.bizlog.rms.entities.clientinfo.contactinformation.FinanceContactInformation;
import com.bizlog.rms.entities.clientinfo.contactinformation.OperationContactInformation;

import com.bizlog.rms.entities.product.*;
import com.bizlog.rms.entities.location.RegionSpecificLocation;

import com.bizlog.rms.entities.sop.*;
import com.bizlog.rms.entities.escalationMatrix.*;
import com.bizlog.rms.entities.sop.frequency.Frequency;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.entities.sop.labourtoolvechile.Labour;
import com.bizlog.rms.entities.sop.labourtoolvechile.Tools;
import com.bizlog.rms.entities.sop.labourtoolvechile.Vehicle;
import com.bizlog.rms.entities.sop.linehaul.Linehaul;
import com.bizlog.rms.entities.sop.notification.Notification;
import com.bizlog.rms.entities.productInformation.ProductInformation;
import com.bizlog.rms.entities.sop.packing.Packing;
import com.bizlog.rms.entities.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface GenericMapper {

    OrganizationDTO toDTO(Organization entity);

    Organization toEntity(OrganizationDTO dto);

    ClientSettingDTO toDTO(ClientSetting entity);

    ClientSetting toEntity(ClientSettingDTO dto);

    CustomerInfoDTO toDTO(CustomerInfo entity);

    CustomerInfo toEntity(CustomerInfoDTO dto);

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    NotifyApiDTO toDTO(NotifyApi entity);

    NotifyApi toEntity(NotifyApiDTO dto);

    TATAdherenceDTO toDTO(TATAdherence entity);

    TATAdherence toEntity(TATAdherenceDTO dto);

    TicketInflowDTO toDTO(com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow ticketCreationConfig);

    com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow toEntity(TicketInflowDTO ticketCreationConfigDTO);

    Frequency toEntity(FrequencyDTO frequencyDTO);

    FrequencyDTO toDTO(Frequency frequency);

    ProductInformation toEntity(ProductInformationDTO productInformationDTO);

    ProductInformationDTO toDTO(ProductInformation productInformation);

    Notification toEntity(NotificationDTO notificationDTO);

    NotificationDTO toDTO(Notification notification);

    EscalationMatrix toEntity(EscalationMatrixDTO escalationMatrixDTO);

    EscalationMatrixDTO toDTO(EscalationMatrix escalationMatrix);

    Location toEntity(LocationDTO locationDTO);

    LocationDTO toDTO(Location location);

    // @Mapping(target = "ticketsVolume", source = "ticketsVolume")
    @Mapping(target = "id", expression = "java(toLong(row.get(\"id\")))")
    @Mapping(target = "activityStartdate", expression = "java(toLong(row.get(\"activityStartdate\")))")
    @Mapping(target = "frequency", expression = "java(toLong(row.get(\"frequency\")))")
    @Mapping(target = "frequencyUnit", expression = "java(row.get(\"frequencyUnit\"))")
    @Mapping(target = "activityEndDate", expression = "java(toLong(row.get(\"activityEndDate\")))")
    @Mapping(target = "operationStartTime", expression = "java(toLong(row.get(\"operationStartTime\")))")
    @Mapping(target = "operationEndTime", expression = "java(toLong(row.get(\"operationEndTime\")))")
    FrequencyDTO toDTO(Map<String, String> row);

    default Long toLong(String value) {
        return value != null ? Long.parseLong(value) : null;
    }

    List<BillingInfo> toEntity(List<BillingInfoDTO> dto);

    BillingInfo toEntity(BillingInfoDTO dto);

    List<BillingInfoDTO> toDTO(List<BillingInfo> entity);

    BillingInfoDTO toDTO(BillingInfo entity);

    CompanyContactDetails toEntity(CompanyContactDetailsDTO dto);

    CompanyContactDetailsDTO toDTO(CompanyContactDetails entity);

    ClientStaging toEntity(ClientStagingDTO dto);

    ClientStagingDTO toDTO(ClientStaging entity);

    ClientCode toEntity(ClientCodeDTO dto);

    ClientCodeDTO toDTO(ClientCode entity);

    ShipmentInfo toEntity(ShipmentInfoDTO dto);

    ShipmentInfoDTO toDTO(ShipmentInfo entity);

    ClientEngagement toEntity(ClientEngagementDTO dto);

    ClientEngagementDTO toDTO(ClientEngagement entity);

    ClientHierarchy toEntity(ClientHierarchyDTO dto);

    ClientHierarchyDTO toDTO(ClientHierarchy entity);

    OperationContactInformation toEntity(OperationContactInformationDTO dto);

    OperationContactInformationDTO toDTO(OperationContactInformation entity);

    FinanceContactInformation toEntity(FinanceContactInformationDTO dto);

    FinanceContactInformationDTO toDTO(FinanceContactInformation entity);

    BizlogFinanceEscalation toEntity(BizlogFinanceEscalationDTO dto);

    BizlogFinanceEscalationDTO toDTO(BizlogFinanceEscalation entity);

    BizlogOpsEscalation toEntity(BizlogOpsEscalationDTO dto);

    BizlogOpsEscalationDTO toDTO(BizlogOpsEscalation entity);

    BizlogTechEscalation toEntity(BizlogTechEscalationDTO dto);

    BizlogTechEscalationDTO toDTO(BizlogTechEscalation entity);

    ClientFinanceEscalation toEntity(ClientFinanceEscalationDTO dto);

    ClientFinanceEscalationDTO toDTO(ClientFinanceEscalation entity);

    ClientOpsEscalation toEntity(ClientOpsEscalationDTO dto);

    ClientOpsEscalationDTO toDTO(ClientOpsEscalation entity);

    ClientTechEscalation toEntity(ClientTechEscalationDTO dto);

    ClientTechEscalationDTO toDTO(ClientTechEscalation entity);

    Packing toEntity(PackingDTO dto);

    PackingDTO toDTO(Packing entity);

    Linehaul toEntity(LinehaulDTO dto);

    LinehaulDTO toDTO(Linehaul entity);

    Vehicle toEntity(VehicleDTO dto);

    VehicleDTO toDTO(Vehicle entity);

    Labour toEntity(LabourDTO dto);

    LabourDTO toDTO(Labour entity);

    Tools toEntity(ToolsDTO dto);

    ToolsDTO toDTO(Tools entity);

    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product entity);

    TicketsFollow toEntity(TicketFollowDTO dto);

    TicketFollowDTO toDTO(TicketsFollow entity);

    Payment toEntity(PaymentDTO dto);

    PaymentDTO toDTO(Payment entity);

    ActivitySOP toEntity(ActivitySOPDTO dto);

    ActivitySOPDTO toDTO(ActivitySOP entity);

    LocationSop toEntity(LocationSopDTO dto);

    LocationSopDTO toDTO(LocationSop entity);

    ProductVehicle toEntity(ProductVehicleDTO dto);

    ProductVehicleDTO toDTO(ProductVehicle entity);

    ProductInfo toEntity(ProductInfoDTO dto);

    ProductInfoDTO toDTO(ProductInfo entity);

    ProductEvalutions toEntity(ProductEvalutionsDTO dto);

    ProductEvalutionsDTO toDTO(ProductEvalutions entity);

    ProductDetails toEntity(ProductDetailsDTO dto);

    ProductDetailsDTO toDTO(ProductDetails entity);

    PackingMaterial toEntity(PackingMaterialDTO dto);

    PackingMaterialDTO toDTO(PackingMaterial entity);

    ProductLocationCenter toEntity(ProductLocationCenterDTO dto);

    ProductLocationCenterDTO toDTO(ProductLocationCenter entity);

    RegionSpecificLocation toEntity(RegionSpecificLocationDTO dto);

    RegionSpecificLocationDTO toDTO(RegionSpecificLocation entity);

    TAT toEntity(TATDTO dto);

    TATDTO toDTO(TAT entity);

    FirstMile toEntity(FirstMileDTO dto);

    FirstMileDTO toDTO(FirstMile entity);

    LastMile toEntity(LastMileDTO dto);

    LastMileDTO toDTO(LastMile entity);

    MiddleMile toEntity(MiddleMileDTO dto);

    MiddleMileDTO toDTO(MiddleMile entity);

}
