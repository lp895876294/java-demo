/**
 * Copyright 2012 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.hystrix.examples.basic;

import org.junit.Test;
import rx.Observable;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo {

    @Test
    public void test() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1) ;

        Future future = executorService.submit(()->{
            System.out.println( "-----------"+Thread.currentThread().getName() );
            try {
                Thread.currentThread().sleep( 5000 ) ;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok" ;
        }) ;

        Observable.from( future )
//                .subscribeOn(Schedulers.computation())
                .subscribe((result)->{
                    System.out.println("-----------" + Thread.currentThread().getName() + "->" + result);
                }) ;

        System.out.println( "main thread" ) ;

        System.in.read() ;

    }

    @Test
    public void testInterval() throws IOException {

        Observable<TimeTest> observable = Observable.interval( 1 , TimeUnit.SECONDS )
                .map((time)->{
                    TimeTest timeTest = new TimeTest() ;
                    timeTest.index = System.currentTimeMillis()/1000 ;
                    return timeTest ;
                })
                .doOnSubscribe(()->{
                    System.out.println("==============doOnSubscribe");
                }) ;
//                .doOnNext((item)->{
//                    System.out.println("doOnNext=============="+ item.index );
//                })

        observable
                .subscribe((item)->{
                    System.out.println(Thread.currentThread().getName()+" , subscribe1==============" + item.index +" , "+ item );
                }) ;
        observable
                .subscribe((item)->{
                    System.out.println(Thread.currentThread().getName()+" , subscribe2==============" + item.index +" , "+ item  );
                    try {
                        Thread.currentThread().sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }) ;

        System.in.read() ;
    }

    class TimeTest {
        public long index ;
    }

}
