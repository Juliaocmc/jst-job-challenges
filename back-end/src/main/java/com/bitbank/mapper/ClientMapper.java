package com.bitbank.mapper;

import java.util.ArrayList;
import java.util.List;

import com.bitbank.dto.ClientDto;
import com.bitbank.dto.ClientResumeDto;
import com.bitbank.model.Client;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    @Autowired
    public ModelMapper modelMapper;

    public ClientDto toDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    public ClientResumeDto toResumeDto(Client client) {
        return modelMapper.map(client, ClientResumeDto.class);
    }

    public Client toModel(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }

    public List<ClientDto> toDtoList(List<Client> clients){
        List<ClientDto> listDto = new ArrayList<>();
        for (Client client : clients) {
            var clientDto = toDto(client);
            if (listDto != null) {
                listDto.add(clientDto);
                }
                }
                return listDto;
        }

    public List<Client> toModelList(List<ClientDto> clientsDto){
        List<Client> clientList = new ArrayList<>();
        for (ClientDto clientDto : clientsDto) {
            var client = toModel(clientDto);
            if (clientList != null) {
                clientList.add(client);
                }
                }
                return clientList;
        }

    public List<ClientResumeDto> toResumeDtoList(List<Client> clients){
        List<ClientResumeDto> listDto = new ArrayList<>();
        for (Client client : clients) {
            var clientDto = toResumeDto(client);
            if (listDto != null) {
                listDto.add(clientDto);
                }
                }
                return listDto;
        }
    
}
