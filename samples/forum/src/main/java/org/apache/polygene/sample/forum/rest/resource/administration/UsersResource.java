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
package org.apache.polygene.sample.forum.rest.resource.administration;

import org.apache.polygene.api.query.Query;
import org.apache.polygene.library.rest.server.api.ContextResource;
import org.apache.polygene.library.rest.server.api.ObjectSelection;
import org.apache.polygene.library.rest.server.api.ResourceIndex;
import org.apache.polygene.sample.forum.data.entity.User;
import org.apache.polygene.sample.forum.data.entity.Users;

/**
 * TODO
 */
public class UsersResource
    extends ContextResource
    implements ResourceIndex<Query<User>>
{
    @Override
    public Query<User> index()
    {
        return ObjectSelection.current().get( Users.class ).users();
    }
}
