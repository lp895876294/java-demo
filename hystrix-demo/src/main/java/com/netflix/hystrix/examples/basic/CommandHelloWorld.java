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

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        return "Hello " + name + "!";
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() throws Exception {
            CommandHelloWorld commandHelloWorld = new CommandHelloWorld("World") ;

            commandHelloWorld.execute() ;

            Observable<String> observable = commandHelloWorld.toObservable() ;

            System.out.println( observable.toBlocking().toFuture().get() ) ;

//            observable.subscribe(new Subscriber<String>() {
//                @Override
//                public void onCompleted() {
//                    System.out.println( "complete1" );
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    System.out.println( "onError1" );
//                }
//
//                @Override
//                public void onNext(String s) {
//                    System.out.println( "onNext1->" + s);
//                }
//            }) ;
//            System.in.read() ;
//            commandHelloWorld.execute() ;
//            System.out.println( commandHelloWorld.execute() );
        }

        @Test
        public void testAsynchronous1() throws Exception {
            assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
            assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
        }

        @Test
        public void testAsynchronous2() throws Exception {

            Future<String> fWorld = new CommandHelloWorld("World").queue();
            Future<String> fBob = new CommandHelloWorld("Bob").queue();

            assertEquals("Hello World!", fWorld.get());
            assertEquals("Hello Bob!", fBob.get());
        }

        @Test
        public void testObservable() throws Exception {

            Observable<String> fWorld = new CommandHelloWorld("World").observe();
            Observable<String> fBob = new CommandHelloWorld("Bob").observe();

            // blocking
            assertEquals("Hello World!", fWorld.toBlocking().single());
            assertEquals("Hello Bob!", fBob.toBlocking().single());

            // non-blocking 
            // - this is a verbose anonymous inner-class approach and doesn't do assertions
            fWorld.subscribe(new Observer<String>() {

                @Override
                public void onCompleted() {
                    // nothing needed here
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(String v) {
                    System.out.println("onNext: " + v);
                }

            });

            // non-blocking
            // - also verbose anonymous inner-class
            // - ignore errors and onCompleted signal
            fBob.subscribe(new Action1<String>() {

                @Override
                public void call(String v) {
                    System.out.println("onNext: " + v);
                }

            });

            // non-blocking
            // - using closures in Java 8 would look like this:
            
            //            fWorld.subscribe((v) -> {
            //                System.out.println("onNext: " + v);
            //            })
            
            // - or while also including error handling
            
            //            fWorld.subscribe((v) -> {
            //                System.out.println("onNext: " + v);
            //            }, (exception) -> {
            //                exception.printStackTrace();
            //            })
            
            // More information about Observable can be found at https://github.com/Netflix/RxJava/wiki/How-To-Use

        }
    }

}
