package com.matheussantana.cloudnative.temafinal2.ribbon;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaylistEurekaServer implements ServerList<Server> {

    private DiscoveryClient client;
    private static final String SERVICE_NAME = "PLAYLIST-SERVICE";

    @Autowired
    public PlaylistEurekaServer(DiscoveryClient client) {
        this.client=client;
    }

    @Override
    public List<Server> getInitialListOfServers() {
        return getUpdatedListOfServers();
    }

    @Override
    public List<Server> getUpdatedListOfServers() {

        List<Server> servers = new ArrayList<>();
        List<InstanceInfo> instanceInfos = client.getApplications().
                getRegisteredApplications(SERVICE_NAME).getInstances();
        try {
            instanceInfos.forEach(server -> servers.add(new Server(server.getHostName(), server.getPort())));
        } catch (NullPointerException e){
            return servers;
        }
        return servers;
    }
}
