package com.matheussantana.cloudnative.temafinal2.ribbon;

import com.matheussantana.cloudnative.temafinal2.feign.PlaylistFeign;
import com.matheussantana.cloudnative.temafinal2.hystrix.GetPlaylistByIdHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.List;

@Component
public class PlaylistRibbon {

    private BaseLoadBalancer loadBalancer;

    @Autowired
    public PlaylistRibbon(PlaylistEurekaServer playlistEurekaServer) {
        this.loadBalancer = LoadBalancerBuilder.newBuilder().withDynamicServerList(playlistEurekaServer)
                .buildDynamicServerListLoadBalancer();
    }

    public Observable<Playlist> findPlaylistById(int id) {
        return Observable.just(callPlaylistService(id));
    }

    public Playlist callPlaylistService(int id) {
        ServerOperation<Playlist> serverOperation = new ServerOperation<Playlist>() {
            @Override
            public Observable<Playlist> call(Server server) {
                return Observable.just(new GetPlaylistByIdHystrixCommand(Feign.builder().decoder(new GsonDecoder())
                        .target(PlaylistFeign.class, "http://" + server.getHost() + ":" + server.getPort()), id)
                        .observe().toBlocking().first());
            }
        };
        return LoadBalancerCommand.<Playlist>builder().withLoadBalancer(loadBalancer).build().submit(serverOperation).toBlocking().first();
    }
}
