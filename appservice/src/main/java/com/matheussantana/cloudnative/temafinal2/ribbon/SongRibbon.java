package com.matheussantana.cloudnative.temafinal2.ribbon;

import com.matheussantana.cloudnative.temafinal2.feign.SongFeign;
import com.matheussantana.cloudnative.temafinal2.hystrix.GetSongByIdHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.model.Song;
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

@Component
public class SongRibbon {

    private BaseLoadBalancer loadBalancer;

    @Autowired
    public SongRibbon(SongEurekaServer eurekaServer) {
        this.loadBalancer = LoadBalancerBuilder.newBuilder().withDynamicServerList(eurekaServer).buildDynamicServerListLoadBalancer();
    }

    public Observable<Song> findSongById(int id) {
        return Observable.just(callSongService(id));
    }

    public Song callSongService(int id) {
        ServerOperation<Song> serverOperation = new ServerOperation<Song>() {
            @Override
            public Observable<Song> call(Server server) {

                return Observable.just(new GetSongByIdHystrixCommand(Feign.builder().decoder(new GsonDecoder())
                        .target(SongFeign.class, "http://" + server.getHost() + ":" + server.getPort()), id)
                        .observe().toBlocking().first());
            }
        };
        return LoadBalancerCommand.<Song>builder().withLoadBalancer(loadBalancer).build().submit(serverOperation).toBlocking().first();
    }
}
