package com.portmz.portsystem.model.charge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChargeRepository extends JpaRepository<Charge, Long> {

    List<ChargeDto> findChargeById(Long chargeId);
}
