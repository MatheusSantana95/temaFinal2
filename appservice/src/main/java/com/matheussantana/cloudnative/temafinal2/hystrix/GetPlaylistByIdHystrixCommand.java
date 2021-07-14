package com.matheussantana.cloudnative.temafinal2.hystrix;

import com.matheussantana.cloudnative.temafinal2.feign.PlaylistFeign;
import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class GetPlaylistByIdHystrixCommand extends HystrixObservableCommand<Playlist> {

    private PlaylistFeign playlistFeign;
    private int id;
    private static List<Song> songs = new ArrayList<>();
    private static Playlist playlist = new Playlist(0, songs);

    public GetPlaylistByIdHystrixCommand(PlaylistFeign playlistFeign, int id) {
        super(HystrixCommandGroupKey.Factory.asKey("Playlist-Feign"));
        this.playlistFeign = playlistFeign;
        this.id = id;
        HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true);
    }

    @Override
    protected Observable<Playlist> construct() {

        return Observable.unsafeCreate(observer -> {
            try{
                if(!observer.isUnsubscribed()) {
                    observer.onNext(playlistFeign.findPlaylistById(id));
                    observer.onCompleted();
                }
            } catch (Exception e){
                observer.onError(e);
            }
        });
    }

    @Override
    protected Observable<Playlist> resumeWithFallback() {
        return Observable.unsafeCreate(observer -> {
            if(!observer.isUnsubscribed()) {
                observer.onNext(playlist);
            }
        });
    }
 }
