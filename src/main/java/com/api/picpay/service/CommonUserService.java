package com.api.picpay.service;

import com.api.picpay.dto.CommonUserDTO;
import com.api.picpay.model.CommonUser;
import com.api.picpay.repository.CommonUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonUserService {

    @Autowired
    private CommonUserRepository repository;

    public CommonUserDTO findCommomUserById(Long id) {
        CommonUser user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new CommonUserDTO(user.getFullName(), user.getCPF(), user.getEmail(), user.getPassword(), user.getBalance());
    }

//    public List<CommonUserDTO> findAllCommomUsers() {
//        List<CommonUser> users = repository.findAll();
//        List<CommonUserDTO> usersDTO = new ArrayList<>();
//
//        for (CommonUser user : users) {
//            CommonUserDTO commonUserDTO = new CommonUserDTO(
//                    user.getFullName(),
//                    user.getCPF(),
//                    user.getEmail(),
//                    user.getPassword(),
//                    user.getBalance()
//            );
//            usersDTO.add(commonUserDTO);
//        }
//        return usersDTO;
//    }

    public void saveCommonUser(CommonUserDTO data) throws Exception {
        CommonUser user = new CommonUser();
        BeanUtils.copyProperties(data, user);

        try {
            repository.save(user);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateCommonUser(CommonUserDTO data, Long id) throws Exception {
        CommonUser user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        BeanUtils.copyProperties(data, user);

        try {
            repository.save(user);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteCommonUser(Long id) {
        repository.deleteById(id);
    }
}
