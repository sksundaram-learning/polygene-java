/*
 * Copyright (c) 2007, Rickard Öberg. All Rights Reserved.
 * Copyright (c) 2012, Paul Merlin.
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

package org.apache.zest.runtime.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.zest.api.activation.Activator;
import org.apache.zest.api.common.MetaInfo;
import org.apache.zest.api.event.ZestApplicationEventHandler;
import org.apache.zest.api.event.ZestEvent;
import org.apache.zest.api.structure.Application;
import org.apache.zest.bootstrap.ApplicationAssembly;
import org.apache.zest.bootstrap.AssemblyVisitor;
import org.apache.zest.bootstrap.LayerAssembly;
import org.apache.zest.bootstrap.ModuleAssembly;
import org.apache.zest.runtime.event.EventBus;

/**
 * The representation of an entire application. From
 * this you can set information about the application
 * and create LayerAssemblies.
 */
public final class ApplicationAssemblyImpl
    implements ApplicationAssembly
{
    private final Map<String, LayerAssemblyImpl> layerAssemblies = new LinkedHashMap<>();
    private final EventBus eventBus;
    private String name = "Application";
    private String version = "1.0"; // Default version
    private Application.Mode mode;
    private final MetaInfo metaInfo = new MetaInfo();
    private final List<Class<? extends Activator<Application>>> activators = new ArrayList<>();

    public ApplicationAssemblyImpl( EventBus eventBus )
    {
        mode = Application.Mode.valueOf( System.getProperty( "mode", "production" ) );
        this.eventBus = eventBus;
    }

    @Override
    public LayerAssembly layer( String name )
    {
        if( name != null )
        {
            LayerAssemblyImpl existing = layerAssemblies.get( name );
            if( existing != null )
            {
                return existing;
            }
        }
        LayerAssemblyImpl layerAssembly = new LayerAssemblyImpl( this, name );
        layerAssemblies.put( name, layerAssembly );
        return layerAssembly;
    }

    @Override
    public ModuleAssembly module( String layerName, String moduleName )
    {
        return layer( layerName ).module( moduleName );
    }

    @Override
    public ApplicationAssembly setName( String name )
    {
        this.name = name;
        return this;
    }

    @Override
    public ApplicationAssembly setVersion( String version )
    {
        this.version = version;
        return this;
    }

    @Override
    public ApplicationAssembly setMode( Application.Mode mode )
    {
        this.mode = mode;
        return this;
    }

    @Override
    public ApplicationAssembly setMetaInfo( Object info )
    {
        metaInfo.set( info );
        return this;
    }

    @Override
    @SafeVarargs
    public final ApplicationAssembly withActivators( Class<? extends Activator<Application>>... activators )
    {
        this.activators.addAll( Arrays.asList( activators ) );
        return this;
    }

    @Override
    public <ThrowableType extends Throwable> void visit( AssemblyVisitor<ThrowableType> visitor )
        throws ThrowableType
    {
        visitor.visitApplication( this );
        for( LayerAssemblyImpl layerAssembly : layerAssemblies.values() )
        {
            layerAssembly.visit( visitor );
        }
    }

    @Override
    public <H extends ZestApplicationEventHandler> void addApplicationEventHandler( ZestEvent.Type<H> type, H handler
    )
    {
        eventBus.addHandler( type, handler );
    }

    public Collection<LayerAssemblyImpl> layerAssemblies()
    {
        return layerAssemblies.values();
    }

    public List<Class<? extends Activator<Application>>> activators()
    {
        return activators;
    }

    public MetaInfo metaInfo()
    {
        return metaInfo;
    }

    @Override
    public String name()
    {
        return name;
    }

    public String version()
    {
        return version;
    }

    @Override
    public Application.Mode mode()
    {
        return mode;
    }
}
