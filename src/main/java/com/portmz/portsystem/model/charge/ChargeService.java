package com.portmz.portsystem.model.charge;

import java.util.List;

public interface ChargeService {

    List<ChargeDto> findAll();

    Charge getChargeById(Long id);

    ChargeDto save(ChargeDto dto);

    void delete(Long id);

    Charge update(Long id, ChargeDto dto);
}
