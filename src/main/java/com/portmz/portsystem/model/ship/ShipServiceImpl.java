package com.portmz.portsystem.model.ship;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private UserService userService;

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public List<ShipDto> findAll() {
        List<Ship> list = shipRepository.findAll();
        return list.stream().map(ShipDto::new).collect(Collectors.toList());
    }

    @Override
    public Ship getShipById(Long id) {
        return shipRepository.findById(id).orElseThrow(() -> new BusinesException("Ship with ID " + id + " not found"));
    }

    @Override
    public ShipDto save(ShipDto dto) {

        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }

        Ship ship = new Ship();
        copyDtoToEntityInsert(ship, dto);
        ship = shipRepository.save(ship);
        return new ShipDto(ship);
    }

    @Override
    public void delete(Long id) {
        try {
            shipRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ship update(Long id, ShipDto dto) {
        Optional<Ship> optionalShip = shipRepository.findById(id);
        if (optionalShip.isPresent()) {
            Ship ship = optionalShip.get();
            copyDtoToEntityInsert(ship, dto);
            return shipRepository.save(ship);
        }
        return null;
    }

    private void copyDtoToEntityInsert(Ship ship, ShipDto dto) {
        ship.setName(dto.getName());
        ship.setModel(dto.getModel());
        ship.setQuiet(dto.getQuiet());
        ship.setSize(dto.getSize());
    }
}
