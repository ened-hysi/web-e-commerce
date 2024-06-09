package com.e.web.commerce.service;


import com.e.web.commerce.Dto.ClientRequestDto;
import com.e.web.commerce.Entity.Client;
import com.e.web.commerce.repository.ClientRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    ModelMapper mapper = new ModelMapper();

    public Client createClient (ClientRequestDto requestDto){
        if(clientRepository.findByEmail(requestDto.getEmail()).isPresent()){
        throw new ResponseStatusException(HttpStatus.CONFLICT , "We have another client with this email address");}
        else {
            Client client = mapper.map(requestDto , Client.class);
            return clientRepository.save(client);
        }
    }
    public Client updateClient(Long id ,ClientRequestDto clientRequestDto){
        Client existing = clientRepository.findById(id).get();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(clientRequestDto , existing);

        return clientRepository.save(existing);
    }
}
