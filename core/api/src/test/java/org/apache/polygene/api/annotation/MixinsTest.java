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
package org.apache.polygene.api.annotation;

import java.lang.annotation.Annotation;
import org.junit.Test;
import org.apache.polygene.api.mixin.Mixins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests public api exposed by Mixins annotation.
 * This will ensure that the public api does not get changed by mistake.
 */
public class MixinsTest
{

    @Test
    public void retention()
    {
        Annotation[] annotations = Annotated.class.getDeclaredAnnotations();
        assertNotNull( "annotations should not be null", annotations );
        assertEquals( "number of annotations", 1, annotations.length );
        assertEquals( "annotation type", Mixins.class, annotations[ 0 ].annotationType() );
    }

    @Mixins( Object.class )
    private static class Annotated
    {

    }
}
