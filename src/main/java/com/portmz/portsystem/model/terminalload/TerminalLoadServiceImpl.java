package com.portmz.portsystem.model.terminalload;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TerminalLoadServiceImpl implements TerminalLoadService {

    @Autowired
    private TerminalLoadRepository terminalLoadRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<TerminalLoadDto> findAll() {
        List<TerminalLoad> list = terminalLoadRepository.findAll();
        return list.stream().map(TerminalLoadDto::new).collect(Collectors.toList());
    }

    @Override
    public TerminalLoad getTerminalLoadById(Long id) {
        return terminalLoadRepository.findById(id).orElseThrow(() -> new BusinesException("Terminal Load with ID " + id + " not found"));
    }

    @Override
    public TerminalLoadDto save(TerminalLoadDto dto) {

        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }

        TerminalLoad terminalLoad = new TerminalLoad();
        copyDtoToEntityInsert(terminalLoad, dto);
        terminalLoad = terminalLoadRepository.save(terminalLoad);
        return new TerminalLoadDto(terminalLoad);
    }

    @Override
    public void delete(Long id) {
        try {
            terminalLoadRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TerminalLoad update(Long id, TerminalLoadDto dto) {
        Optional<TerminalLoad> optionalTerminalLoad = terminalLoadRepository.findById(id);
        if (optionalTerminalLoad.isPresent()) {
            TerminalLoad terminalLoad = optionalTerminalLoad.get();
            copyDtoToEntityInsert(terminalLoad, dto);
            return terminalLoadRepository.save(terminalLoad);
        }
        return null;
    }

    private void copyDtoToEntityInsert(TerminalLoad terminalLoad, TerminalLoadDto dto) {
        terminalLoad.setSize(dto.getSize());
        terminalLoad.setPriority(dto.getPriority());
        terminalLoad.setState(dto.getState());
    }
}
