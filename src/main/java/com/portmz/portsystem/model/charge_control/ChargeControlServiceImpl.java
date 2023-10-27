package com.portmz.portsystem.model.charge_control;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.charge.Charge;
import com.portmz.portsystem.model.charge.ChargeService;
import com.portmz.portsystem.model.client.Client;
import com.portmz.portsystem.model.container.Container;
import com.portmz.portsystem.model.container.ContainerService;
import com.portmz.portsystem.model.product.Product;
import com.portmz.portsystem.model.product.ProductDto;
import com.portmz.portsystem.model.ship.Ship;
import com.portmz.portsystem.model.ship.ShipService;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChargeControlServiceImpl implements ChargeControlService {

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private ContainerService containerService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChargeControlRepository chargeControlRepository;

    @Override
    public ChargeControlDto save(ChargeControlDto dto) {
        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }

        ChargeControl chargeControl = new ChargeControl();
        copyDtoToEntityInsert(chargeControl, dto);
        chargeControl = chargeControlRepository.save(chargeControl);
        return new ChargeControlDto(chargeControl);
    }

    @Override
    public ChargeControl update(Long id, ChargeControlDto dto) {
        Optional<ChargeControl> optionalChargeControl = chargeControlRepository.findById(id);
        if (optionalChargeControl.isPresent()) {
            ChargeControl chargeControl = optionalChargeControl.get();
            copyDtoToEntityInsert(chargeControl, dto);
            return chargeControlRepository.save(chargeControl);
        }

        return null;
    }

    @Override
    public ChargeControl getChargeControlById(Long id) {
        return chargeControlRepository.findById(id).orElseThrow(() -> new BusinesException("Charge Control with ID " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        try {
            chargeControlRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ChargeControlDto> findAll() {
        List<ChargeControl> list = chargeControlRepository.findAll();
        return list.stream().map(ChargeControlDto::new).collect(Collectors.toList());
    }

    private void copyDtoToEntityInsert(ChargeControl chargeControl, ChargeControlDto dto) {
        chargeControl.setDestiny(dto.getDestiny());
        chargeControl.setOrigin(dto.getOrigin());

        if (dto.getCharge_id() != null) {
            Charge charge = chargeService.getChargeById(dto.getCharge_id());
            chargeControl.setCharge(charge);
        }

        if (dto.getContainer_id() != null) {
            Container container = containerService.getContainerById(dto.getContainer_id());
            chargeControl.setContainer(container);
        }

        if (dto.getShip_id() != null) {
            Ship ship = shipService.getShipById(dto.getShip_id());
            chargeControl.setShip(ship);
        }

    }
}
