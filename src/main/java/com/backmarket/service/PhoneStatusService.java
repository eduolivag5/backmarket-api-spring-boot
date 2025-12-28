package com.backmarket.service;

import com.backmarket.entity.PhoneStatus;
import com.backmarket.repository.PhoneStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneStatusService {

    @Autowired
    private PhoneStatusRepository phoneStatusRepository;

    public List<PhoneStatus> getAllStatus() {
        return phoneStatusRepository.findAll();
    }

    public Optional<PhoneStatus> getStatusById(int id) {
        return phoneStatusRepository.findById(id);
    }

}
