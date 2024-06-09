package com.e.web.commerce.Controller;

import com.e.web.commerce.Dto.ClientRequestDto;
import com.e.web.commerce.Dto.ProductRequestDto;
import com.e.web.commerce.Entity.Client;
import com.e.web.commerce.Entity.Product;
import com.e.web.commerce.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping
    public ResponseEntity<Client> register (@RequestBody ClientRequestDto requestDto){
        Client response = clientService.createClient(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{email}")
    public ResponseEntity<Client> updateClient (@PathVariable Long id , @RequestBody ClientRequestDto requestDto ){
        Client response = clientService.updateClient(id , requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
