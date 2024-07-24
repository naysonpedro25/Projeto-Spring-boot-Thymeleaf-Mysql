package com.nayson.minhaLoja.services;

import com.nayson.minhaLoja.model.Client;
import com.nayson.minhaLoja.model.ClientDTO;
import com.nayson.minhaLoja.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServices {
    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());

        client = clientRepository.save(client);
        return new ClientDTO( client.getFirstName(), client.getLastName(), client.getEmail(), client.getPhone(), client.getAddress());
    }
}
