/*
 * Piwigo for Android
 * Copyright (C) 2016-2019 Piwigo Team http://piwigo.org
 *
 * This program is free software: you can redistribute it and/or modify
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.piwigo.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import java.util.List;

@Dao
public abstract class ImageCategoryMapDao {
    @Insert
    abstract public void insert(List<CacheDBInternals.ImageCategoryMap> joins);

    @Delete
    abstract public void delete(List<CacheDBInternals.ImageCategoryMap> join);
}