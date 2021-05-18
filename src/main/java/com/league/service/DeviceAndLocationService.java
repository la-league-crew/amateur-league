package com.league.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface DeviceAndLocationService {
    void verifyDeviceAndLocation(String userName, HttpServletRequest request) throws IOException, GeoIp2Exception;

}
