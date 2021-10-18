package com.ayla.emqxruleenginedemo.controller;

import com.aylaasia.corecloud.common.pojo.RestResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Gary.Jin
 * @create: 2021-10-16 11:56
 */
@Slf4j
@RestController
public class TestController {
    public static final String url = "https://ads-alifield.ayla.com.cn/apiv1/devices.json?env=docker&[filter][direction]=desc&[filter][order]=desc&[filter][order_by]=connected_at&[filter][sort_by]=connected_at&direction=desc&ignore_limit=true&is_forward_page=false&match=true&order=desc&order_by=connected_at&page=1&paginated=true&per_page=25&sort_by=connected_at&[filter][dsn]=";
    public static final String token = "auth_token 073cfeac86334c729700575ae351742e";
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/devices")
    public RestResponseBody<List<Device>> getDevicesByDsns(@RequestBody List<String> dsns) {
        List<Device> result = new ArrayList<>(dsns.size());
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization", token);
        HttpEntity<?> objectHttpEntity = new HttpEntity<>(null, requestHeaders);
        for (String dsn : dsns) {
            ResponseEntity<DeviceInfo> responseEntity = restTemplate.exchange(url + dsn, HttpMethod.GET, objectHttpEntity, DeviceInfo.class);
            result.add(new Device(null, dsn, responseEntity.getBody().getDevices().get(0).getDevice().getConnection_status()));
        }
        return RestResponseBody.success(result);
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Device {
        private Integer key;
        private String dsn;
        private String connection_status;
    }

    @Data
    public static class DeviceInfo {
        private List<Dev> devices;
    }

    @Data
    public static class Dev {
        private Device device;
    }
    public static final String dpList = "https://ads-alifield.ayla.com.cn/apiv1/properties/propertyId/datapoints.json?env=docker&[filter][direction]=asc&[filter][order]=asc&[filter][order_by]=datapoint.created_at&[filter][sort_by]=datapoint.created_at&direction=desc&is_forward_page=true&limit=100&order=asc&order_by=datapoint.created_at&page=1&paginated=true&per_page=10&sort_by=datapoint.created_at";
    public static final String properyList = "https://ads-alifield.ayla.com.cn/apiv1/devices/deviceId/properties.json?env=docker";
    public static final String connHis = "https://ads-alifield.ayla.com.cn/apiv1/devices/deviceId/connection_history/page/1/limit/100/field/event_time/order/desc.json?env=docker";
    public static final String deviceInfo = "https://ads-alifield.ayla.com.cn/apiv1/dsns/DSN.json?env=docker";
    @PostMapping("/deviceVersion")
    public RestResponseBody<List<DeviceDetail>> getDeviceVersion(@RequestBody List<String> dsns) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization", token);
        HttpEntity<?> objectHttpEntity = new HttpEntity<>(null, requestHeaders);
        List<DeviceDetail> result = new ArrayList<>(dsns.size());
        for (String dsn : dsns) {
            ResponseEntity<Dev> deviceRes = restTemplate.exchange(deviceInfo.replaceFirst("DSN", dsn), HttpMethod.GET, objectHttpEntity, Dev.class);
            ResponseEntity<List<ConnHis>> connHistory = restTemplate.exchange(
                connHis.replaceFirst("deviceId", deviceRes.getBody().getDevice().getKey().toString()), HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<List<ConnHis>>(){});
            List<ConnHis> connHistoryBody = connHistory.getBody();
            if (ObjectUtils.isEmpty(connHistoryBody)) {
                continue;
            }
            List<Date> offlineTimeList = connHistoryBody.stream()
                .filter(x -> "offline".equalsIgnoreCase(x.getConnection_history().getStatus()))
                .map(x -> x.getConnection_history().getEvent_time())
                .collect(Collectors.toList());
            if (ObjectUtils.isEmpty(offlineTimeList)) {
                log.info("{} 没有离线状态", dsn);
                continue;
            }
            ResponseEntity<List<Property>> propertyListRes = restTemplate.exchange(properyList.replaceFirst("deviceId", deviceRes.getBody().getDevice().getKey().toString()),
                HttpMethod.GET, objectHttpEntity, new ParameterizedTypeReference<List<Property>>(){});
            List<Property> propertyListResBody = propertyListRes.getBody();
            if (ObjectUtils.isEmpty(propertyListResBody)) {
                log.info("{} 没有property", dsn);
                continue;
            }
            Integer versionPropertyId = propertyListResBody.stream().filter(x -> "version".equalsIgnoreCase(x.getProperty().getName())).findFirst().get().getProperty().getKey();
            ResponseEntity<Datapoint> dpListRes = restTemplate.exchange(dpList.replaceFirst("propertyId", versionPropertyId.toString()), HttpMethod.GET, objectHttpEntity, Datapoint.class);
            Datapoint dpListResBody = dpListRes.getBody();
            if (ObjectUtils.isEmpty(dpListResBody) || ObjectUtils.isEmpty(dpListResBody.getDatapoints())) {
                log.info("{} 没有 DP，但是有离线状态：{}", dsn, connHistoryBody);
                continue;
            }
            //ZBGW0-A000002 AGW-1.8 MTK7688 2.0.9 2021-08-28-18:30:08 ncpFwVer-257
            List<Dp> dpList = dpListResBody.getDatapoints().stream().filter(x -> {
                String version = x.getDatapoint().getValue();
                String[] split = version.split(" ");
                return "2.0.7".equals(split[3]) || "2.0.8".equals(split[3]) || "2.0.9".equals(split[3]);
            })
                .sorted(Comparator.comparing((Dp x) ->x.getDatapoint().getCreated_at()).reversed())
                .collect(Collectors.toList());
            if (ObjectUtils.isEmpty(dpList)) {
                log.info("{}有离线状态，但是没有对应的 DP，conn_his:{}, DP: {}",
                    dsn, connHistoryBody, dpListResBody);
                continue;
            }
            List<DeviceDetail.OfflineHisVersion> offlineHisVersions = offlineTimeList
                .stream()
                .map(x -> {
                    Optional<Dp> first = dpList.stream()
                        .filter(y -> x.after(y.getDatapoint().getCreated_at()))
                        .findFirst();
                    if (first.isEmpty()) {
                        return new DeviceDetail.OfflineHisVersion(x, null, null);
                    }
                    return new DeviceDetail.OfflineHisVersion(x, first.get().getDatapoint().getValue(), first.get().getDatapoint().getCreated_at());
            }).collect(Collectors.toList());
            result.add(new DeviceDetail(dsn, offlineHisVersions.stream().filter(x -> !ObjectUtils.isEmpty(x.getVersion())).collect(Collectors.toList())));

        }
        return RestResponseBody.success(result);
    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Property {
        private PropertyVO property;

        @Data
        public static class PropertyVO {
            private String name;
            private Integer key;
        }
    }

    @Data
    public static class Dp {
        public DpVO datapoint;
        @Data
        public static class DpVO {
            private String value;
            @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ssZ")
            private Date created_at;
        }
    }

    @Data
    public static class Datapoint {
        private List<Dp> datapoints;
    }

    @Data
    public static class ConnHis {
        private Conn connection_history;
        @Data
        public static class Conn {
            @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ssZ")
            private Date event_time;
            private String status;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceDetail {
        private String dsn;
        private List<OfflineHisVersion> offlineHisVersionList;
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class OfflineHisVersion {
            private Date statusTime;
            private String version;
            private Date dpCreateTime;
        }
    }

}
