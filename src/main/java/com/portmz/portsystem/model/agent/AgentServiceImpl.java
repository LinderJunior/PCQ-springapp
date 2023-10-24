package com.portmz.portsystem.model.agent;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<AgentDto> findAll() {
        List<Agent> list = agentRepository.findAll();
        return list.stream().map(AgentDto::new).collect(Collectors.toList());
    }

    @Override
    public Agent getAgentById(Long id) {
        return agentRepository.findById(id).orElseThrow(() -> new BusinesException("Agent with ID " + id + " not found"));
    }

    @Override
    public AgentDto save(AgentDto dto) {

        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }


        Agent agent = new Agent();

        if (agentRepository.existsByContact(dto.getContact())) {
            throw new BusinesException("Agent with the same contact already exists");
        }
        copyDtoToEntityInsert(agent, dto);
        agent = agentRepository.save(agent);
        return new AgentDto(agent);
    }

    @Override
    public void delete(Long id) {
        try {
            agentRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Agent update(Long id, AgentDto dto) {
        Optional<Agent> optionalAgent = agentRepository.findById(id);
        if (optionalAgent.isPresent()) {
            Agent agent = optionalAgent.get();
            copyDtoToEntityInsert(agent, dto);
            return agentRepository.save(agent);
        }
        return null;
    }

    private void copyDtoToEntityInsert(Agent agent, AgentDto dto) {
        agent.setName(dto.getName());
        agent.setProvince(dto.getProvince());
        agent.setContact(dto.getContact());
    }
}
