package com.sungwoo.aps.services;

import com.sungwoo.aps.domain.prime.Gate;
import com.sungwoo.aps.repo.prime.GateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    private final GateRepo gateRepo;

    @Autowired
    public GateService(GateRepo gateRepo) {
        this.gateRepo = gateRepo;
    }

    /**
     * Find all gate
     *
     * @return list of gate
     */
    public List<Gate> findAll() {
        return gateRepo.findAll();
    }

    public void saveGate(Gate gate) {
        gateRepo.deleteAll();
        gateRepo.save(gate);
    }
}
