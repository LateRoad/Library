package com.lateroad.library.database.dao;

import com.lateroad.library.database.CommonDAO;
import com.lateroad.library.entity.Entity;

interface AbstractDAO<T extends Entity> extends CommonDAO<T> {
}