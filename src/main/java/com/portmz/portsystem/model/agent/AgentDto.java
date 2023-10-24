package com.portmz.portsystem.model.agent;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDto {

    private Long id;
    private String name;
    private String province;
    private String contact;

    public AgentDto(Agent entityAgent) {
        id = entityAgent.getId();
        name = entityAgent.getName();
        province = entityAgent.getProvince();
        contact = entityAgent.getContact();
    }
}
