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

package com.fhc25.percepcion.osiris.mapviewer.model.location;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Map feature represented by a Geometry type including a dictionary of
 * properties and a list of relations. Domain object
 */
public class Feature implements Serializable {

    private Map<String, String> properties;
    private List<Map<String, String>> propertiesRelations;
    private String id;

    private Geometry geometry;

    /**
     * Gets the properties related with this Feature
     *
     * @return properties dictionary
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * Sets the properties for this Feature
     *
     * @param properties
     */
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * Gets the properties relations of this Feature
     *
     * @return relations
     */
    public List<Map<String, String>> getPropertiesRelations() {
        return propertiesRelations;
    }

    /**
     * Sets the properties relations of this Feature
     *
     * @param propertiesRelations
     */
    public void setPropertiesRelations(
            List<Map<String, String>> propertiesRelations) {
        this.propertiesRelations = propertiesRelations;
    }

    /**
     * Gets the ID of the Feature
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the Feature
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the geometry of the Feature
     *
     * @return geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Sets the geometry of the Feature
     *
     * @param geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

}
