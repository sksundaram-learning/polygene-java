/*
 * Copyright (c) 2008, Rickard Öberg. All Rights Reserved.
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

package org.qi4j.runtime.service;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.qi4j.api.common.Visibility;
import org.qi4j.api.service.ServiceReference;
import org.qi4j.runtime.service.ImportedServiceModel;
import org.qi4j.runtime.service.ImportedServiceReferenceInstance;
import org.qi4j.runtime.structure.ModuleInstance;
import org.qi4j.runtime.structure.ModelVisitor;

/**
 * JAVADOC
 */
public class ImportedServicesModel
    implements Serializable
{
    private List<ImportedServiceModel> importedServiceModels;
    private List<ImportedServiceReferenceInstance> serviceReferences;

    public ImportedServicesModel( List<ImportedServiceModel> importedServiceModels )
    {
        this.importedServiceModels = importedServiceModels;
    }

    public ImportedServicesInstance newInstance( ModuleInstance module )
    {
        serviceReferences = new ArrayList<ImportedServiceReferenceInstance>();
        for( ImportedServiceModel serviceModel : importedServiceModels )
        {
            ImportedServiceReferenceInstance serviceReferenceInstance = new ImportedServiceReferenceInstance( serviceModel, module );
            serviceReferences.add( serviceReferenceInstance );
        }

        return new ImportedServicesInstance( this, serviceReferences );
    }

    public void visitModel( ModelVisitor modelVisitor )
    {
        for( ImportedServiceModel importedServiceModel : importedServiceModels )
        {
            importedServiceModel.visitModel( modelVisitor );
        }
    }

    public ImportedServiceModel getServiceFor( Type type, Visibility visibility )
    {
        for( ImportedServiceModel serviceModel : importedServiceModels )
        {
            if( serviceModel.isServiceFor( type, visibility ) )
            {
                return serviceModel;
            }
        }

        return null;
    }

    public void getServicesFor( Type type, Visibility visibility, List<ImportedServiceModel> serviceModels )
    {
        for( ImportedServiceModel importedServiceModel : importedServiceModels )
        {
            if (importedServiceModel.isServiceFor( type, visibility ))
                serviceModels.add( importedServiceModel );
        }
    }
}