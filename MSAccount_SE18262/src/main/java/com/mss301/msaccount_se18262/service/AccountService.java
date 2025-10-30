package com.mss301.msaccount_se18262.service;

import com.mss301.msaccount_se18262.dto.LoginRequest;

public interface AccountService {
    String login(LoginRequest loginRequest);
}
