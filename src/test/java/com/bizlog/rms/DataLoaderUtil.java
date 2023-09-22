package com.bizlog.rms;

import com.bizlog.rms.entities.Client;

import java.util.List;

public class DataLoaderUtil {

    public static List<Client> getClients(){

        Client client = new Client();
        client.setId(1L);
        client.setName("IDP");
        return List.of(client);
    }
}
