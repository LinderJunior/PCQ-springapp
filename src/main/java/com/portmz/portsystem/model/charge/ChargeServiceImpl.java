package com.portmz.portsystem.model.charge;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChargeServiceImpl implements ChargeService {
   @Autowired
    private UserService userService;

    @Autowired
    private ChargeRepository chargeRepository;

    @Override
    public List<ChargeDto> findAll() {
        List<Charge> list = chargeRepository.findAll();
        return list.stream().map(ChargeDto::new).collect(Collectors.toList());
    }

    @Override
    public Charge getChargeById(Long id) {
        return chargeRepository.findById(id).orElseThrow(() -> new BusinesException("Charge with ID " + id + " not found"));
    }

    @Override
    public ChargeDto save(ChargeDto dto) {
        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }

        Charge charge = new Charge();
        copyDtoToEntityInsert(charge, dto);
        charge = chargeRepository.save(charge);
        return new ChargeDto(charge);
    }

    @Override
    public void delete(Long id) {
        try {
            chargeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Charge update(Long id, ChargeDto dto) {
        Optional<Charge> optionalCharge = chargeRepository.findById(id);
        if (optionalCharge.isPresent()) {
            Charge charge = optionalCharge.get();
            copyDtoToEntityInsert(charge, dto);
            return chargeRepository.save(charge);
        }
        return null;
    }

    private void copyDtoToEntityInsert(Charge charge, ChargeDto dto) {
        charge.setType(dto.getType());
        charge.setOrigin(dto.getOrigin());
        charge.setDestiny(dto.getDestiny());
        charge.setDescription(dto.getDescription());
    }
}
