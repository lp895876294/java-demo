/**
 * Copyright 2016 Netflix, Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.hystrix.strategy.properties;

/**
 * Generic interface to represent a <b>dynamic</b> property value so Hystrix can consume
 * properties without being tied to any particular backing implementation.
 * 
 * @author agentgt
 *
 * @param <T>
 *            Type of property value.
 * @see HystrixProperty
 * @see HystrixDynamicProperties
 */

/**
 * hystrix动态属性配置，在原有属性配置的基础上，新加属性名称的接口。
 * todo 添加了一个回调接口，可利用此机制实现属性值的动态获取。
 * @param <T>
 */
public interface HystrixDynamicProperty<T> extends HystrixProperty<T>{
    
    public String getName();
    
    /**
     * Register a callback to be run if the property is updated.
     * Backing implementations may choose to do nothing.
     * @param callback callback.
     */
    public void addCallback(Runnable callback);
    
}