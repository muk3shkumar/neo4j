/*
 * Copyright (c) 2002-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.api;

import org.neo4j.kernel.impl.util.Validator;
import org.neo4j.values.TextValue;
import org.neo4j.values.Value;
import org.neo4j.values.Values;

public class IndexSimpleValueValidator implements Validator<Value>
{
    public static IndexSimpleValueValidator INSTANCE = new IndexSimpleValueValidator();

    private IndexSimpleValueValidator()
    {
    }

    @Override
    public void validate( Value value )
    {
        if ( value == null || value == Values.NO_VALUE )
        {
            throw new IllegalArgumentException( "Null value" );
        }
        if ( Values.isTextValue( value ) )
        {
            IndexValueLengthValidator.INSTANCE.validate( ((TextValue)value).stringValue().getBytes() );
        }
    }
}
