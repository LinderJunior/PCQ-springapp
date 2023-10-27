package com.portmz.portsystem.model.charge_control;

import java.util.List;

public interface ChargeControlService {
    ChargeControlDto save(ChargeControlDto dto);

    ChargeControl update(Long id, ChargeControlDto dto);

    ChargeControl getChargeControlById(Long id);

    void delete(Long id);

    List<ChargeControlDto> findAll();
}
