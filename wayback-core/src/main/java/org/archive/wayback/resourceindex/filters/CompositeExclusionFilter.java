/* CompositeExclusionFilter
 *
 * $Id$
 *
 * Created on 4:57:06 PM Mar 19, 2007.
 *
 * Copyright (C) 2007 Internet Archive.
 *
 * This file is part of wayback.
 *
 * wayback is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * any later version.
 *
 * wayback is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with wayback-svn; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.archive.wayback.resourceindex.filters;

import java.util.ArrayList;
import java.util.Iterator;

import org.archive.wayback.core.SearchResult;
import org.archive.wayback.resourceindex.SearchResultFilter;

/**
 * SearchResultFilter that abstracts multiple SearchResultFilters -- if all
 * filters return INCLUDE, then the result is included, but the first to
 * return ABORT or EXCLUDE short-circuits the rest
 *
 * @author brad
 * @version $Date$, $Revision$
 */
public class CompositeExclusionFilter extends SearchResultFilter {

	private ArrayList<SearchResultFilter> filters = 
		new ArrayList<SearchResultFilter>();
	
	/**
	 * @param filter to be added to the composite.
	 */
	public void addComponent(SearchResultFilter filter) {
		filters.add(filter);
	}
	/* (non-Javadoc)
	 * @see org.archive.wayback.resourceindex.SearchResultFilter#filterSearchResult(org.archive.wayback.core.SearchResult)
	 */
	public int filterSearchResult(SearchResult r) {
		Iterator<SearchResultFilter> itr = filters.iterator();
		while(itr.hasNext()) {
			int result = itr.next().filterSearchResult(r);
			if(result != FILTER_INCLUDE) {
				return result;
			}
		}
		return FILTER_INCLUDE;
	}
}