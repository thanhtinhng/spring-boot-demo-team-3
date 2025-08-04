package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public String getAdminInfo() {
        return "This is admin info. Only users with ROLE_ADMIN can access this.";
    }
}
