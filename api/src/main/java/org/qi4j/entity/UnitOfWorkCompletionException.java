/*
 * Copyright (c) 2007, Rickard Öberg. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.qi4j.entity;

/**
 * When an attempt to {@link UnitOfWork#complete()} an UnitOfWork
 * fails, this exception will be thrown.
 */
public class UnitOfWorkCompletionException extends Exception
{
    private static final long serialVersionUID = 6531642131384516904L;

    public UnitOfWorkCompletionException()
    {
    }

    public UnitOfWorkCompletionException( String string )
    {
        super( string );
    }

    public UnitOfWorkCompletionException( String string, Throwable throwable )
    {
        super( string, throwable );
    }

    public UnitOfWorkCompletionException( Throwable throwable )
    {
        super( throwable );
    }
}
