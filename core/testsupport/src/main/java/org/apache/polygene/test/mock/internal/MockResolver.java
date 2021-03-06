/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package org.apache.polygene.test.mock.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Resolves the invocation handler for a registered mock. Actual matching method is specific to each implementation.
 */
public interface MockResolver
{
    /**
     * Matches the method invocation to an invocation handler for a registered mock.
     *
     * @param proxy  object on which the method was invoked
     * @param method invoked method
     * @param args   invocation arguments
     *
     * @return invocation handler if this resolved can handle the call or null otherwise.
     */
    InvocationHandler getInvocationHandler( Object proxy, Method method, Object[] args );
}