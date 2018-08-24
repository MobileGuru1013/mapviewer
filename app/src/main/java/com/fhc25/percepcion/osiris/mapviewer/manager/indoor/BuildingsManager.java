/**
Copyright 2015 Osiris Project Team

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/   

package com.fhc25.percepcion.osiris.mapviewer.manager.indoor;

import com.fhc25.percepcion.osiris.mapviewer.common.ICallback;
import com.fhc25.percepcion.osiris.mapviewer.common.ICancellableTask;
import com.fhc25.percepcion.osiris.mapviewer.common.errors.Failure;
import com.fhc25.percepcion.osiris.mapviewer.data.indoor.api.IMapRepository;
import com.fhc25.percepcion.osiris.mapviewer.manager.indoor.builders.IBuildingGroupBuilder;
import com.fhc25.percepcion.osiris.mapviewer.model.indoor.BuildingGroup;
import com.fhc25.percepcion.osiris.mapviewer.model.location.Feature;
import com.fhc25.percepcion.osiris.mapviewer.model.location.Point;
import com.fhc25.percepcion.osiris.mapviewer.model.location.Polygon;
import com.fhc25.percepcion.osiris.mapviewer.model.location.query.MongoGeospatialGEOQuery;
import com.fhc25.percepcion.osiris.mapviewer.model.location.query.MongoGeospatialNEARQuery;
import com.fhc25.percepcion.osiris.mapviewer.model.location.query.MongoGeospatialQuery;
import com.fhc25.percepcion.osiris.mapviewer.model.location.query.MongoGeospatialQueryParser4Building;
import com.fhc25.percepcion.osiris.mapviewer.model.location.query.api.IMongoGeospatialQueryParser;
import com.fhc25.percepcion.osiris.mapviewer.model.states.api.IInternalState;

import java.util.Collection;

public class BuildingsManager {

    private final String mapLabel = "MAP";

    private final IMapRepository repository;
    private final IInternalState internalState;
    private IMongoGeospatialQueryParser mongoGeospatialQueryParser = new MongoGeospatialQueryParser4Building();

    private final IBuildingGroupBuilder buildingGroupBuilder;

    public BuildingsManager(IInternalState internalState, IMapRepository repository, IBuildingGroupBuilder buildingGroupBuilder) {
        this.repository = repository;
        this.internalState = internalState;
        this.buildingGroupBuilder = buildingGroupBuilder;
    }

    public ICancellableTask getBuildings(Point point, Double maxDist, ICallback<BuildingGroup> callback) {

        MongoGeospatialQuery query = new MongoGeospatialNEARQuery(MongoGeospatialNEARQuery.SearchTypeNEAR.NEAR, point, maxDist);
        return getBuildings(query, callback);
    }

    public ICancellableTask getBuildings(Polygon polygon, ICallback<BuildingGroup> callback) {

        MongoGeospatialQuery query = new MongoGeospatialGEOQuery(MongoGeospatialGEOQuery.SearchTypeGEO.GEO_WITHIN, polygon);
        return getBuildings(query, callback);
    }

    public ICancellableTask getBuildings(MongoGeospatialQuery query, final ICallback<BuildingGroup> callback) {

        return repository.getMap(query, mapLabel, mongoGeospatialQueryParser, new ICallback<Collection<Feature>>() {
            @Override
            public void onFinish(Failure error, Collection<Feature> data) {

                BuildingGroup buildingGroup = null;

                if (error == null) {
                    buildingGroup = buildingGroupBuilder.buildBuildingGroup(data);
                    internalState.setBuildingGroup(buildingGroup);
                }

                callback.onFinish(error, buildingGroup);
            }
        });
    }

}
