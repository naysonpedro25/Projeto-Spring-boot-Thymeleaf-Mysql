package com.nayson.minhaLoja.controller;

import com.nayson.minhaLoja.model.Client;
import com.nayson.minhaLoja.model.ClientDTO;
import com.nayson.minhaLoja.repository.ClientRepository;
import com.nayson.minhaLoja.services.ClientServices;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.Cleaner;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientServices clientServices;

    @GetMapping // é chamado quando o /clients for
    public String findAllClients(Model model) {
        try {
            List<Client> list = clientRepository.findAll();
            model.addAttribute("clients", list); // é usado pra passar dados paras as view ( html )
        } catch (Exception e) {
            System.err.println("Erro ao pegar lista" + e.getMessage());
            model.addAttribute("erroText", "Não foi possível carregar os clients");
        }
        return "/clients/index";
    }

    @GetMapping("/create")
    public String showCreateClientForm(Model model) {
        try {
            ClientDTO clientDTO = new ClientDTO();
            model.addAttribute("clientDTO", clientDTO);
        } catch (Exception e) {
            System.err.println("Erro ao tentar ir para pagina create");
            model.addAttribute("erroText", "Não foi possível carregar os clients");
        }
        return "/clients/create";
    }

    @PostMapping("/create")
    public String createClient(@Valid @ModelAttribute ClientDTO clientDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/clients/create";
        }

        Client client = clientRepository.getClientByEmail(clientDTO.getEmail());
        if (client != null) {
            result.addError(new FieldError("clientDTO", "email", clientDTO.getEmail(), false, null, null, "Email ja está em uso"));
            return "/clients/create";
        }
        model.addAttribute("clientDTO", clientDTO);
        try {
            clientServices.saveClient(clientDTO);
        } catch (Exception e) {
            model.addAttribute("erroMessage", "Erro ao criar client: " + e.getMessage());
            // Adiciona o objeto clientDTO ao modelo para ser reutilizado no formulário
            model.addAttribute("clientDTO", clientDTO);

        }
        return "redirect:/clients";
    }

    @GetMapping("/edit{id}")
    public String showEditForm(Model model, @RequestParam long id){
        Client client = clientRepository.getClientById(id);
        if(client == null){
            return "redirect:/clients";
        }
        model.addAttribute("client", client);
        ClientDTO clientDTO = new ClientDTO(client.getFirstName(),
                client.getLastName(), client.getEmail(), client.getPhone(), client.getAddress());
        model.addAttribute("clientDTO", clientDTO);
        return "clients/edit";
    }

    @PostMapping("/edit")
    public String updateClient(@RequestParam long id , @Valid @ModelAttribute ClientDTO clientDTO, BindingResult result, Model model) {
        Client client = clientRepository.getClientById(id);
        if(client == null){
            return "redirect:/clients";
        }
        model.addAttribute("client", client);
        if(result.hasErrors()){
            return "clients/edit";
        }

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());

        try {
            clientRepository.save(client);
        }catch (Exception e) {
            model.addAttribute("erroMessage", "Erro ao atualizar cliente: " + e.getMessage());
            return "clients/edit";
        }
        return "redirect:/clients";
    }

    @GetMapping("/delete")
    public String deleteClient(@RequestParam long id){
        try {
        clientRepository.deleteById(id);

        }catch (Exception e){
            System.err.println("Erro ao deleta: " + e.getMessage());
        }
        return "redirect:/clients";
    }
}
