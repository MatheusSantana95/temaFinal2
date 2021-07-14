package com.matheussantana.cloudnative.temafinal2.hystrix;

import com.matheussantana.cloudnative.temafinal2.feign.SongFeign;
import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

public class GetSongByIdHystrixCommand extends HystrixObservableCommand<Song> {

    private int idSong;
    private SongFeign songFeign;
    private static Song song = new Song(0, "null");

    public GetSongByIdHystrixCommand(SongFeign songFeign, int idSong) {
        super(HystrixCommandGroupKey.Factory.asKey("Song-Feign"));
        this.idSong = idSong;
        this.songFeign = songFeign;
        HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true);
    }

    @Override
    protected Observable<Song> construct() {
        return Observable.unsafeCreate(observer -> {
            try{
                if(!observer.isUnsubscribed()) {
                    observer.onNext(songFeign.findSongById(idSong));
                    observer.onCompleted();
                }
            } catch (Exception e){
                observer.onError(e);
            }
        });
    }

    @Override
    protected Observable<Song> resumeWithFallback() {
        return Observable.unsafeCreate(observer -> {
            if(!observer.isUnsubscribed()) {
                observer.onNext(song);
            }
        });
    }
}
